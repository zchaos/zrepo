package com.zchaos.ztrain;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.zchaos.ztrain.events.LoginEvent;

public class ZTrain {
	public static void main(String[] args) {
		ZTrain ztrain = new ZTrain();
		ztrain.start();
	}

	private JTextField username;//用户名

	private JPasswordField password;//密码

	private JLabel code;//图片验证码

	private JTextField authcode;//手工输入验证码

	private JButton loginBtn;//登录按钮

	private JTextField session1;//

	/**
	 * 内部控件的位置
	 */
	private static final int RECT_LEFT = 10;

	private static final int RECT_TOP = 10;

	private static final int RECT_WIDTH = 800;

	/**
	 * 整个frame的宽高
	 */
	private static final int ALL_LEFT = 100;

	private static final int ALL_TOP = 100;

	private static final int ALL_WIDTH = 850;

	private static final int ALL_HEIGHT = 600;

	public void start() {
		JFrame frame = genFrame();

		addComponent(frame, genUserInfo());
		addComponent(frame, genSession());
		addComponent(frame, genContacts());
		addComponent(frame, genConfigs());
		addComponent(frame, genTrains());
		addComponent(frame, genStates());
		frame.setVisible(true);
	}

	private void addComponent(JFrame frame, JComponent component) {
		Container container = frame.getContentPane();
		int count = container.getComponentCount();
		int left = RECT_LEFT;
		int top = RECT_TOP;
		if (count > 0) {
			Component preComp = container.getComponent(count - 1);
			Rectangle bounds = preComp.getBounds();
			top += bounds.y + bounds.height;
		}
		component.setLocation(left, top);
		container.add(component);
	}

	public JFrame genFrame() {
		JFrame frame = new JFrame("ztrain");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(ALL_LEFT, ALL_TOP, ALL_WIDTH, ALL_HEIGHT);
		frame.setLayout(null);
		return frame;
	}

	/**
	 * 生成用户信息的界面
	 */
	public JComponent genUserInfo() {
		JPanel panel = new JPanel();
		panel.setSize(RECT_WIDTH, 60);
		panel.setBorder(new TitledBorder("用户信息"));

		//用户名
		JLabel label_o = new JLabel("用户名");
		label_o.setHorizontalAlignment(4);
		panel.add(label_o);

		this.username = new JTextField();
		this.username.setName("username");
		this.username.setToolTipText("用户名");
		this.username.setColumns(10);
		panel.add(this.username);
		//密码
		JLabel label_o1 = new JLabel("密码");
		label_o1.setHorizontalAlignment(4);
		panel.add(label_o1);

		this.password = new JPasswordField();
		this.password.setName("password");
		this.password.setToolTipText("密码");
		this.password.setColumns(10);
		panel.add(this.password);

		this.code = new JLabel("图片");
		this.code.setToolTipText("点我刷新验证码！");
		this.code.setHorizontalAlignment(4);
		//		this.code.addMouseListener(new codeClick());
		panel.add(this.code);

		this.authcode = new JTextField();
		this.authcode.setToolTipText("输入验证码");
		this.authcode.setColumns(10);
		panel.add(this.authcode);

		this.loginBtn = new JButton();
		this.loginBtn.setText("登录");
		this.loginBtn.addActionListener(new LoginEvent(username, password));
		panel.add(this.loginBtn);

		return panel;
	}

	/**
	 * 保存session可以让用户始终处于活动状态
	 * @return
	 */
	public JComponent genSession() {
		JPanel panel = new JPanel();
		panel.setSize(RECT_WIDTH, 60);
		panel.setBorder(new TitledBorder("session"));

		this.session1 = new JTextField();
		this.session1.setToolTipText("输入session");
		this.session1.setColumns(10);
		panel.add(this.session1);

		return panel;
	}

	public JComponent genContacts() {
		JPanel panel = new JPanel();
		panel.setSize(RECT_WIDTH, 50);
		panel.setBorder(new TitledBorder("联系人"));

		return panel;
	}

	public JComponent genConfigs() {
		JPanel panel = new JPanel();
		panel.setSize(RECT_WIDTH, 50);
		panel.setBorder(new TitledBorder("相关配置"));

		return panel;
	}

	public JComponent genTrains() {
		JPanel panel = new JPanel();
		panel.setSize(RECT_WIDTH, 50);
		panel.setBorder(new TitledBorder("列车列表"));

		return panel;
	}

	public JComponent genStates() {
		JPanel panel = new JPanel();
		panel.setSize(RECT_WIDTH, 50);
		panel.setBorder(new TitledBorder("当前情况"));

		return panel;
	}

}
