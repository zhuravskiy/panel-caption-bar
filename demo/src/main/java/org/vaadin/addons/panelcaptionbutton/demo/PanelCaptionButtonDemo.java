package org.vaadin.addons.panelcaptionbutton.demo;


import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.vaadin.addons.panelcaptionbutton.PanelCaptionButton;

import java.util.Arrays;

@Theme("reindeer-test")
public class PanelCaptionButtonDemo extends com.vaadin.ui.UI {

   private String[] themes = { "reindeer-test", "valo-test", "runo-test", "chameleon-test", "liferay-test" };

   protected ComboBox createThemePicker() {
      ComboBox themePicker = new ComboBox("Theme", Arrays.asList(themes));
      themePicker.setValue(getTheme());

      themePicker.addValueChangeListener(new Property.ValueChangeListener() {
         @Override
         public void valueChange(Property.ValueChangeEvent event) {
            String theme = (String) event.getProperty().getValue();
            UI.getCurrent().setTheme(theme);
         }
      });
      return themePicker;
   }

   @Override
   protected void init(com.vaadin.server.VaadinRequest request) {
      setContent(new HorizontalLayout(
              createThemePicker(),
              new Button("Show test window dark", new Button.ClickListener() {
         @Override
         public void buttonClick(Button.ClickEvent event) {
            Window w = new Window("Test window");
            w.setModal(true);
            w.setHeight("100px");
            w.setWidth("100px");

            PanelCaptionButton settingsButton = new PanelCaptionButton(w);
            settingsButton.setIcon(FontAwesome.COG);
            settingsButton.setDescription("Settings");
            settingsButton.addClickListener(new Button.ClickListener() {
               @Override
               public void buttonClick(Button.ClickEvent event) {
                  com.vaadin.ui.Notification.show("Button settings clicked");
               }
            });

            PanelCaptionButton newBrowserTabButton = new PanelCaptionButton(w);
            newBrowserTabButton.setIcon(FontAwesome.SHARE);
            newBrowserTabButton.setCaption("To tab");
            newBrowserTabButton.addClickListener(new Button.ClickListener() {
               @Override
               public void buttonClick(Button.ClickEvent event) {
                  com.vaadin.ui.Notification.show("To browser tab clicked");
               }
            });

            final Panel windowContent = new Panel();
            windowContent.setIcon(FontAwesome.BAR_CHART_O);
            windowContent.setCaption("Statistic panel");
            windowContent.setSizeFull();

            PanelCaptionButton panelSettingsButton = new PanelCaptionButton(windowContent);
            panelSettingsButton.setIcon(FontAwesome.COGS);
            panelSettingsButton.setDescription("Settings");
            panelSettingsButton.addClickListener(new Button.ClickListener() {
               @Override
               public void buttonClick(Button.ClickEvent event) {
                  com.vaadin.ui.Notification.show("Button settings clicked");
               }
            });

            PanelCaptionButton panelCloseButton = new PanelCaptionButton(windowContent);
            panelCloseButton.setIcon(FontAwesome.TIMES);
            panelCloseButton.setDescription("Close panel");
            panelCloseButton.addClickListener(new Button.ClickListener() {
               @Override
               public void buttonClick(Button.ClickEvent event) {
                  com.vaadin.ui.Notification.show("Panel close clicked");
                  windowContent.setVisible(false);
               }
            });

            w.setContent(windowContent);

            getUI().addWindow(w);
         }
      }),
              new Button("Show test window reindeer light", new Button.ClickListener() {
                 @Override
                 public void buttonClick(Button.ClickEvent event) {
                    Window w = new Window("Test window");
                    //w.setModal(true);
                    w.center();
                    w.setHeight("100px");
                    w.setWidth("100px");
                    w.addStyleName(Reindeer.WINDOW_LIGHT);
                    PanelCaptionButton settingsButton = new PanelCaptionButton(w);
                    settingsButton.setIcon(FontAwesome.COG);
                    settingsButton.setDescription("Settings");
                    settingsButton.addClickListener(new Button.ClickListener() {
                       @Override
                       public void buttonClick(Button.ClickEvent event) {
                          com.vaadin.ui.Notification.show("Button settings clicked");
                       }
                    });

                    PanelCaptionButton newBrowserTabButton = new PanelCaptionButton(w);
                    newBrowserTabButton.setIcon(FontAwesome.SHARE);
                    newBrowserTabButton.setCaption("To tab");
                    newBrowserTabButton.addClickListener(new Button.ClickListener() {
                       @Override
                       public void buttonClick(Button.ClickEvent event) {
                          com.vaadin.ui.Notification.show("To browser tab clicked");
                       }
                    });

                    final Panel windowContent = new Panel();
                    windowContent.setIcon(FontAwesome.BAR_CHART_O);
                    windowContent.setCaption("Statistic panel");
                    windowContent.setSizeFull();
                    windowContent.addStyleName(Reindeer.PANEL_LIGHT);

                    PanelCaptionButton panelSettingsButton = new PanelCaptionButton(windowContent);
                    panelSettingsButton.setIcon(FontAwesome.COGS);
                    panelSettingsButton.setDescription("Settings");
                    panelSettingsButton.addClickListener(new Button.ClickListener() {
                       @Override
                       public void buttonClick(Button.ClickEvent event) {
                          com.vaadin.ui.Notification.show("Button settings clicked");
                       }
                    });

                    PanelCaptionButton panelCloseButton = new PanelCaptionButton(windowContent);
                    panelCloseButton.setIcon(FontAwesome.TIMES);
                    panelCloseButton.setDescription("Close panel");
                    panelSettingsButton.addClickListener(new Button.ClickListener() {
                       @Override
                       public void buttonClick(Button.ClickEvent event) {
                          com.vaadin.ui.Notification.show("Panel close clicked");
                          windowContent.setVisible(false);
                       }
                    });

                    w.setContent(windowContent);

                    getUI().addWindow(w);
                 }
              })
      ));
   }

}