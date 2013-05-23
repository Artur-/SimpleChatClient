package org.vaadin.artur.simplechat.client;

import org.vaadin.artur.simplechat.SimpleChat;

import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(SimpleChat.class)
public class SimpleChatConnector extends AbstractComponentConnector {

	@Override
	protected void init() {
		super.init();
		getWidget().setServerRpc(getRpcProxy(SimpleChatServerRpc.class));
		registerRpc(SimpleChatClientRpc.class, new SimpleChatClientRpc() {
			
			@Override
			public void onMessage(String message) {
				getWidget().onMessage(message);
				
			}
		});
	}

	@Override
	public SimpleChatWidget getWidget() {
		return (SimpleChatWidget) super.getWidget();
	};
}
