package org.vaadin.artur.simplechat.client;

import com.vaadin.shared.communication.ServerRpc;

public interface SimpleChatServerRpc extends ServerRpc {

	public void sendMessage(String message);
}
