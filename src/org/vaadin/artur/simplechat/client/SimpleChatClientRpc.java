package org.vaadin.artur.simplechat.client;

import com.vaadin.shared.communication.ClientRpc;

public interface SimpleChatClientRpc extends ClientRpc {

	public void onMessage(String message);
}
