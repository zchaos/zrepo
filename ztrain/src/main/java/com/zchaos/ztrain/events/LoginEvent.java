package com.zchaos.ztrain.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginEvent implements ActionListener {
	private static Logger log = LoggerFactory.getLogger(LoginEvent.class);

	private JTextField username;

	private JPasswordField password;

	public LoginEvent(JTextField username, JPasswordField password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("username:" + username.getText());
		log.debug("password:" + new String(password.getPassword()));
		System.out.println("LoginEvent");
	}
}
