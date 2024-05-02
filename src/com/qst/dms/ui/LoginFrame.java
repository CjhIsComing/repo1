package com.qst.dms.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.qst.dms.entity.User;
import com.qst.dms.service.UserService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	//private JTextField textField;
	//private JTextField textField_1;
	// 用户名，文本框
	private JTextField txtName;
	// 密码，密码框
	private JPasswordField txtPwd;
	// 登录的用户
	private static User user;

	// 用户业务类
	private UserService userService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		super("登录");

		// 实例化用户业务类对象
		userService = new UserService();
		
		txtName = new JTextField(16);
		txtPwd = new JPasswordField(16);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(75, 59, 72, 18);
		panel.add(lblNewLabel);
		
		/*textField = new JTextField();
		textField.setBounds(179, 56, 168, 24);
		panel.add(textField);
		textField.setColumns(10);*/
		txtName = new JTextField();
		txtName.setBounds(179, 56, 168, 24);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 根据用户名查询用户
				user = userService.findUserByName(txtName.getText().trim());
				// 判断用户是否存在
				if (user != null) {
					// 判断输入的密码是否正确
					if (user.getPassword().equals(new String(txtPwd.getPassword()))) {
						// 登录成功，隐藏登录窗口
						LoginFrame.this.setVisible(false);
						// 显示主窗口
						new MainFrame_temp();
						//MainFrame mainFrame = new MainFrame();
						//mainFrame.setVisible(true);
					} else {
						// 输出提示信息
						//System.out.println("密码错误！请重新输入！");
						JOptionPane.showMessageDialog(null, "密码错误！请重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
						// 清空密码框
						txtPwd.setText("");
					}
				} else {
					// 输出提示信息
					//System.out.println("该用户不存在，请先注册！");
					JOptionPane.showMessageDialog(null, "该用户不存在，请先注册！","错误提示",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(20, 203, 113, 27);
		panel.add(btnNewButton);
		
		/*textField_1 = new JTextField();
		textField_1.setBounds(179, 121, 168, 24);
		panel.add(textField_1);
		textField_1.setColumns(10);*/
		txtPwd = new JPasswordField();
		txtPwd.setBounds(179, 121, 168, 24);
		panel.add(txtPwd);
		txtPwd.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密  码：");
		lblNewLabel_1.setBounds(75, 124, 72, 18);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 清空文本框
				txtName.setText("");
				txtPwd.setText("");
			}
		});
		btnNewButton_1.setBounds(158, 203, 113, 27);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("注册");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistFrame registFrame = new RegistFrame();
				registFrame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(295, 203, 113, 27);
		panel.add(btnNewButton_2);
	}
}
