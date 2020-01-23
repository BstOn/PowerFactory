package forms.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.UserDTO;
import services.User;

public class AddUserForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JLabel lblUserNameTip;
	private JLabel lblPSWTip;

	private User user;
	private JTextField txtPassWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUserForm frame = new AddUserForm();
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
	public AddUserForm() {
		setTitle("\u7528\u6237\u6CE8\u518C");
		setResizable(false);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 10));

		JPanel body = new JPanel();
		panel.add(body, BorderLayout.CENTER);
		body.setLayout(null);

		// �����¼��ť�¼��Ĵ����߼�
		JButton btnRegister = new JButton("\u6CE8\u518C");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateUser()) {
					// ���ͨ���﷨��֤�����û���Ϣ���浽���ݿ���
					UserDTO userDTO = new UserDTO(txtUserName.getText(), txtPassWord.getText());
					User user = new User();

					// ������֤��������û��Ƿ��Ѿ�����
					boolean nameExisted = user.nameExisted(txtUserName.getText());
					if (!nameExisted) {
						boolean saveSuccessful = user.saveUser(userDTO);

						if (saveSuccessful) {
							System.out.println("save successful");
							JOptionPane.showMessageDialog(null, "����ɹ���");
						} else {
							System.out.println("save failed");
							JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
						}
					} else {
						JOptionPane.showMessageDialog(null, "���û��Ѿ����ڣ�");

					}

				} else
					System.out.println("The User is not passed!");
			}
		});

		btnRegister.setBounds(152, 236, 93, 32);
		body.add(btnRegister);

		JButton btnCancle = new JButton("\u53D6\u6D88");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		btnCancle.setBounds(271, 236, 93, 32);
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

		txtPassWord = new JTextField();
		txtPassWord.setBounds(165, 87, 176, 32);
		body.add(txtPassWord);
		txtPassWord.setColumns(10);
	}

	/*
	 * ���û���¼��Ϣ���﷨���������֤
	 */
	private boolean validateUser() {
		boolean result = true;

		if (txtUserName.getText().isEmpty()) {
			this.lblUserNameTip.setText("�������û�����");
			result = result && false;
		}
		if (new String(txtPassWord.getText()).isEmpty()) {
			this.lblPSWTip.setText("����������!");
			result = result && false;
		}
		return result;
	}
}
