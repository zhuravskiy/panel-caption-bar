Vaadin addon adds ability to put some buttons on right side of caption`s panel or window header (near close button).

Currently supported reindeer theme.

##Usage

```
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
```

##Build and Development

```./gradlew idea``` - Intelij IDEA project files generation

```./gradlew :demo:vaadinRun``` - run embedded jetty server

```./gradlew :addon:publish``` - build and publish to maven repository, see more into build.gradle publishing.repositories.maven

If you need debug client side, you can use GWT SuperDev mode, run
code server ```./gradlew :demo:vaadinSuperDevMode``` and server ```./gradlew :demo:vaadinRun```  