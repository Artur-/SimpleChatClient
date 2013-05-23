package org.vaadin.artur.simplechat.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public class SimpleChatWidget extends FlowPanel {

	private Panel chatPanel;
	private FlowPanel chatLog;
	private TextBox chatMessage;
	SimpleChatServerRpc serverRpc;

	public SimpleChatWidget() {
		createUI();
		setupLogic();
	}

	public void setServerRpc(SimpleChatServerRpc serverRpc) {
		this.serverRpc = serverRpc;
	}

	private void createUI() {
		setWidth("100%");
		setHeight("100%");

		chatPanel = new SimplePanel();
		chatPanel.setWidth("100%");
		chatPanel.setHeight("90%");

		chatLog = new FlowPanel();
		chatLog.setWidth("100%");
		chatPanel.add(chatLog);

		chatMessage = new TextBox();
		chatMessage.setWidth("100%");

		add(new Label("Chat"));
		add(chatPanel);
		add(new Label("Write your message and press enter"));
		add(chatMessage);
	}

	public void onMessage(String message) {
		chatLog.add(new Label(message));
	}

	private void setupLogic() {
		chatMessage.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String message = chatMessage.getText();
				if (!"".equals(message)) {
					serverRpc.sendMessage(message);
					chatMessage.setText("");
				}
			}
		});

	}

}
