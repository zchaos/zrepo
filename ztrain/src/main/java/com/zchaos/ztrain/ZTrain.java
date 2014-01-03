package com.zchaos.ztrain;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.zchaos.ztrain.consts.Consts;

public class ZTrain {
	public static void main(String[] args) {
		ZTrain ztrain = new ZTrain();
		ztrain.start();
	}

	private JTextField username;//用户名

	private JPasswordField password;//密码

	private JLabel code;//图片验证码

	private JTextField authcode;//手工输入验证码

	private JCheckBox loginAuto;//自动登录

	private JComboBox<String> browse;//浏览器

	private JButton loginBtn;//登录按钮

	public void start() {
		JFrame frame = genFrame();
		frame.add(genUserInfo());
		frame.setVisible(true);
	}

	public JFrame genFrame() {
		JFrame frame = new JFrame("ztrain");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 600);
		return frame;
	}

	/**
	 * 生成用户信息的界面
	 */
	public JComponent genUserInfo() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 12, 650, 54);
		panel.setBorder(new TitledBorder("用户信息"));

		//用户名
		JLabel label_o = new JLabel("用户名");
		label_o.setBounds(10, 26, 40, 15);
		label_o.setHorizontalAlignment(4);
		panel.add(label_o);

		this.username = new JTextField();
		this.username.setName("username");
		this.username.setToolTipText("用户名");
		this.username.setBounds(60, 23, 100, 21);
		this.username.setColumns(10);
		panel.add(this.username);
		//密码
		JLabel label_o1 = new JLabel("密码");
		label_o1.setBounds(170, 26, 40, 15);
		label_o1.setHorizontalAlignment(4);
		panel.add(label_o1);

		this.password = new JPasswordField();
		this.password.setName("password");
		this.password.setToolTipText("密码");
		this.password.setBounds(220, 23, 100, 21);
		this.password.setColumns(10);
		panel.add(this.password);

		this.code = new JLabel("图片");
		this.code.setBounds(340, 20, 60, 20);
		this.code.setToolTipText("点我刷新验证码！");
		this.code.setHorizontalAlignment(4);
		//		this.code.addMouseListener(new codeClick());
		panel.add(this.code);

		this.authcode = new JTextField();
		this.authcode.setToolTipText("输入验证码");
		this.authcode.setBounds(410, 23, 40, 21);
		this.authcode.setColumns(10);
		panel.add(this.authcode);

		this.loginAuto = new JCheckBox("自动登录");
		this.loginAuto.setBounds(430, 26, 110, 15);
		this.loginAuto.setHorizontalAlignment(4);
		panel.add(this.loginAuto);

		this.browse = new JComboBox<>(Consts.BROWSES);
		this.browse.setToolTipText("自动从登录的浏览器加载登录信息");
		this.browse.setBounds(560, 18, 65, 28);
		panel.add(this.browse);

		this.loginBtn = new JButton();
		this.loginBtn.setText("登录");
		this.loginBtn.setBounds(560, 18, 65, 28);
		//		this.loginBtn.addActionListener(new LoginBtn());
		panel.add(this.loginBtn);

		return panel;
	}

	public JComponent genContacts() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 12, 650, 54);
		panel.setBorder(new TitledBorder("联系人"));

		return panel;
	}

	public JComponent genSession() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 12, 650, 54);
		panel.setBorder(new TitledBorder("session"));

		return panel;
	}
}
