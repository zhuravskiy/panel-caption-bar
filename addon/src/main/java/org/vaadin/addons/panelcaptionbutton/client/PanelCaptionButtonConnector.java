package org.vaadin.addons.panelcaptionbutton.client;

import com.google.gwt.aria.client.Roles;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.*;
import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.Icon;
import com.vaadin.client.ui.VButton;
import com.vaadin.client.ui.VPanel;
import com.vaadin.client.ui.VWindow;
import com.vaadin.shared.ComponentConstants;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.button.ButtonServerRpc;
import org.vaadin.addons.panelcaptionbutton.PanelCaptionButton;
import org.vaadin.addons.panelcaptionbutton.shared.PanelCaptionButtonState;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zhuravskiy.vs@gmail.com
 */
@SuppressWarnings("GwtInconsistentSerializableClass")
@Connect(PanelCaptionButton.class)
public class PanelCaptionButtonConnector extends AbstractExtensionConnector implements ClickHandler {
    public VButton button;

    @Override
    protected void extend(ServerConnector target) {
        if(target instanceof ComponentConnector)
            extend(((ComponentConnector) target).getWidget());
        else
            Logger.getLogger(getClass().getSimpleName())
                    .log(Level.WARNING,
                            "PanelCaptionButtonConnector can work only with classes implemented ComponentConnector");
    }

    protected void extend(Widget widget) {
        if(widget instanceof VWindow)
            extend((VWindow) widget);
        else if(widget instanceof VPanel)
            extend((VPanel) widget);
        else
            Logger.getLogger(getClass().getSimpleName())
                    .log(Level.WARNING,
                            "PanelCaptionButtonConnector could not extend " + widget.getClass().getName() + ", VWindow and VPanel supports only");
    }

    protected void createButton() {
        if(button != null)
            return;
        button = new VButton();
        button.addClickHandler(this);
    }

    protected String getIconUri() {
        return getResourceUrl(ComponentConstants.ICON_RESOURCE);
    }

    protected Icon getIcon() {
        return getConnection().getIcon(getIconUri());
    }

    @OnStateChange("resources")
    void onResourceChange() {
        if (button.icon != null) {
            button.wrapper.removeChild(button.icon.getElement());
            button.icon = null;
        }
        Icon icon = getIcon();
        if (icon != null) {
            button.icon = icon;
            button.wrapper.insertBefore(icon.getElement(), button.captionElement);
        }
    }

/*
    @OnStateChange("caption")
    void setCaption() {
        button.captionElement.setInnerText(getState().caption);
    }
*/

    @OnStateChange("visible")
    void setVisible() {
        button.setVisible(getState().visible);
    }

    @OnStateChange("enabled")
    void setEnabled() {
        button.setEnabled(getState().enabled);
    }

    @OnStateChange("description")
    void setDescription() {
        button.setTitle(getState().description);
    }

    /**
     * Find absolute right position for new element, needed for window header.
     * @return position in px
     */
    protected int getAbsoluteRight(Element newButton, Element container) {
        NodeList<Element> divs = container.getElementsByTagName(DivElement.TAG);
        int max = 0;
        for (int i = 0; i < divs.getLength(); i++) {
            Element element = divs.getItem(i);
            if(!element.getAttribute("role").equals(Roles.getButtonRole().getName()))
            continue;
            if(element == newButton) {
                break;
            }
            int right = getRight(element);
            max = Math.max(right, max);
        }
        return max == 0 ? 3 : max;
    }

    protected native int getRight(Element element) /*-{
        if(element.parentElement) {
            return element.parentElement.getBoundingClientRect().right - element.getBoundingClientRect().right + element.getBoundingClientRect().width + 3
        }
        return 0;
    }-*/;

    protected native int getWidth(Element element) /*-{
        return element.getBoundingClientRect().width + 3;
    }-*/;

    protected void extend(final VWindow window) {
        createButton();
        button.setStyleName("v-window-header-box");
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            public void execute() {
                int right = getAbsoluteRight(button.getElement(), window.header);
                button.getElement().getStyle().setRight(right, Style.Unit.PX);

                window.header.getStyle().setPaddingRight(right + 15 + 3, Style.Unit.PX); //change minimum window width
                LayoutManager lm = LayoutManager.get(getConnection());
                int minWidth = lm.getOuterWidth(window.header) - lm.getInnerWidth(window.header);
                window.getWidget().getElement().getStyle().setPropertyPx("minWidth",  minWidth);
            }
        });
        DOM.appendChild(window.header, button.getElement());
        DOM.sinkEvents(button.getElement(), Event.ONCLICK);
        DOM.setEventListener(button.getElement(), button);
    }

    protected void extend(final VPanel panel) {
        createButton();
        button.setStyleName("v-panel-caption-box");
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            public void execute() {
                button.getElement().getStyle().setRight(getAbsoluteRight(button.getElement(), panel.captionNode), Style.Unit.PX);
            }
        });
        DOM.appendChild(panel.captionNode, button.getElement());
        DOM.sinkEvents(button.getElement(), Event.ONCLICK);
        DOM.setEventListener(button.getElement(), button);
    }

    @Override
    public void onClick(ClickEvent event) {
        MouseEventDetails details = MouseEventDetailsBuilder
                .buildMouseEventDetails(event.getNativeEvent(), button
                        .getElement());
        getRpcProxy(PanelCaptionButtonRpc.class).click(details);
    }

    @Override
    public PanelCaptionButtonState getState() {
        return (PanelCaptionButtonState)super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        getState();
    }

    @Override
    public void onUnregister() {
        super.onUnregister();
    }
}
