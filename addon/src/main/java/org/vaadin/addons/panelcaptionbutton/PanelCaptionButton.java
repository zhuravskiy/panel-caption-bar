package org.vaadin.addons.panelcaptionbutton;

import com.vaadin.server.Resource;
import com.vaadin.shared.ComponentConstants;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.ui.*;
import org.vaadin.addons.panelcaptionbutton.client.PanelCaptionButtonRpc;
import org.vaadin.addons.panelcaptionbutton.shared.PanelCaptionButtonState;

import java.util.*;

/**
 * @author zhuravskiy.vs@gmail.com
 */
public class PanelCaptionButton extends com.vaadin.server.AbstractExtension {
    protected Panel extendable;
    public PanelCaptionButton(Panel target) {
        super(target);
        extendable = target;
        registerRpc(new PanelCaptionButtonRpc() {
            @Override
            public void click(MouseEventDetails eventDetails) {
                fireClick(eventDetails);
            }
        });
    }

    @Override
    protected PanelCaptionButtonState getState() {
        return (PanelCaptionButtonState)super.getState();
    }

    public void setIcon(Resource icon) {
        setResource(ComponentConstants.ICON_RESOURCE, icon);
    }

    public Resource getIcon() {
        return getResource(ComponentConstants.ICON_RESOURCE);
    }

    public boolean isVisible() {
        return getState().visible;
    }

    public void setVisible(boolean visible) {
        getState().visible = visible;
    }

    public boolean isEnabled() {
        return getState().enabled;
    }

    public void setEnabled(boolean enabled) {
        getState().enabled = enabled;
    }

    public String getDescription() {
        return getState().description;
    }

    public void setDescription(String description) {
        getState().description = description;
    }

    public String getCaption() {
        return getState().caption;
    }

    public void setCaption(String caption) {
        getState().caption = caption;
    }

    protected void fireClick(MouseEventDetails details) {
        fireEvent(new Button.ClickEvent(extendable, details));
    }

    public void addClickListener(Button.ClickListener clickListener) {
        addListener(Button.ClickEvent.class, clickListener, Button.ClickListener.BUTTON_CLICK_METHOD);
    }

    public void removeClickListener(Button.ClickListener clickListener) {
        removeListener(Button.ClickEvent.class, clickListener, Button.ClickListener.BUTTON_CLICK_METHOD);
    }
}
