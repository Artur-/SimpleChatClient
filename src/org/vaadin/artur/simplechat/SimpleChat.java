package org.vaadin.artur.simplechat;

import java.text.DateFormat;
import java.util.Date;

import org.vaadin.artur.simplechat.Broadcaster.MessageListener;
import org.vaadin.artur.simplechat.client.SimpleChatClientRpc;
import org.vaadin.artur.simplechat.client.SimpleChatServerRpc;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.UIDetachedException;

public class SimpleChat extends AbstractComponent {

	public SimpleChat() {
		setSizeFull();

		registerRpc(new SimpleChatServerRpc() {
			@Override
			public void sendMessage(String message) {
				Broadcaster.sendMessage(message);
			}
		});

		Broadcaster.addMessageListener(new MessageListener() {
			@Override
			public void messageReceived(final MessageEvent event) {
				try {
					getUI().access(new Runnable() {
						@Override
						public void run() {
							DateFormat df = DateFormat
									.getTimeInstance(DateFormat.MEDIUM);
							String message = df.format(new Date()) + ": "
									+ event.getMessage();
							getRpcProxy(SimpleChatClientRpc.class).onMessage(
									message);
						}
					});
				} catch (UIDetachedException e) {
					Broadcaster.removeMessageListener(this);
				}
			}
		});

	}
}
