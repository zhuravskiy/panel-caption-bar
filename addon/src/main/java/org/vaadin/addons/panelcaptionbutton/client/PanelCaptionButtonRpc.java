package org.vaadin.addons.panelcaptionbutton.client;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

/**
 * @author zhuravskiy.vs@gmail.com
 */
public interface PanelCaptionButtonRpc extends ServerRpc {
    void click(MouseEventDetails eventDetails);
}
