package forms.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import forms.MainForm;
import services.User;

public class LoginForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField passwordField;
	private JLabel lblUserNameTip;
	private JLabel lblPSWTip;
	private JLabel label;

	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					setFrameLoaction(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void setFrameLoaction(JFrame frame) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = screen.width;
		int height = screen.height;

		int left = (width - frame.getSize().width) / 2;
		int top = (height - frame.getSize().height) / 2;

		frame.setLocation(left, top);

	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setTitle("\u7528\u6237\u767B\u5F55");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 10));

		JPanel bar = new JPanel();
		FlowLayout fl_bar = (FlowLayout) bar.getLayout();
		fl_bar.setVgap(20);
		fl_bar.setAlignment(FlowLayout.CENTER);
		bar.setBackground(new Color(135, 206, 235));
		panel.add(bar, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("\u7535\u5382\u804C\u5DE5\u57F9\u8BAD\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("华文琥珀", Font.PLAIN, 25));
		bar.add(lblNewLabel);

		JPanel body = new JPanel();
		panel.add(body, BorderLayout.CENTER);
		body.setLayout(null);

		// 点击登录按钮事件的处理逻辑
		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateUser()) {
					// 如果通过语法验证，则进行判断用户是否为合法用户
					user = new User(txtUserName.getText(), new String(passwordField.getPassword()));
					if (user.findUser()) { // TODO 添加事件，显示主页面
						showMainPage();
						System.out.println("logon successful");
					} else {// TODO 错误提示
						JOptionPane.showMessageDialog(null, "密码错误", "提示", JOptionPane.WARNING_MESSAGE);
						System.out.println("logon failed");
					}

				} else {// TODO 用户名或密码空
					JOptionPane.showMessageDialog(null, "请输入用户名或密码", "提示", JOptionPane.QUESTION_MESSAGE);
					System.out.println("The User is not passed!");
				}

			}
		});

		btnLogin.setBounds(101, 172, 93, 32);
		body.add(btnLogin);

		JButton btnCancle = new JButton("\u53D6\u6D88");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancle.setBounds(251, 172, 93, 32);
		body.add(btnCancle);

		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setBounds(101, 48, 54, 15);
		body.add(lblNewLabel_1);

		txtUserName = new JTextField();
		txtUserName.setBounds(165, 40, 176, 32);
		body.add(txtUserName);
		txtUserName.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("\u5BC6   \u7801\uFF1A");
		lblNewLabel_2.setBounds(101, 90, 54, 15);
		body.add(lblNewLabel_2);

		lblUserNameTip = new JLabel("");
		lblUserNameTip.setForeground(new Color(220, 20, 60));
		lblUserNameTip.setBounds(351, 48, 140, 15);
		body.add(lblUserNameTip);

		lblPSWTip = new JLabel("");
		lblPSWTip.setForeground(new Color(220, 20, 60));
		lblPSWTip.setBounds(351, 90, 140, 15);
		body.add(lblPSWTip);

		passwordField = new JPasswordField();
		passwordField.setBounds(165, 87, 176, 32);
		body.add(passwordField);

		label = new JLabel("\u6CE8\u518C");
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AddUserForm addUser = new AddUserForm();
				addUser.setVisible(true);
			}
		});
		label.setBounds(133, 141, 47, 18);
		body.add(label);
	}

	/*
	 * 对用户登录信息从语法方面进行验证
	 */
	private boolean validateUser() {
		boolean result = true;

		if (txtUserName.getText().isEmpty()) {
			this.lblUserNameTip.setText("请输入用户名！");
			result = result && false;
		}
		if (new String(passwordField.getPassword()).isEmpty()) {
			this.lblPSWTip.setText("请输入密码!");
			result = result && false;
		}
		return result;
	}

// TODO 转主界面

	private void showMainPage() {
		this.dispose();
		MainForm emf = new MainForm();
		emf.setVisible(true);
	}
}
