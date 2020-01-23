package forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import forms.depart.DepartMainForm;
import forms.employee.EmployeeMainForm;
import forms.trainingplan.TrainPlanMainForm;
import forms.trainresult.TrainResultMainForm;
import forms.user.LoginForm;

public class MainForm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
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
	public MainForm() {
		setTitle("\u7535\u5382\u804C\u5DE5\u57F9\u8BAD\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF PowerStationMIS V1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 635, 611);
		setBounds(100, 100, 800, 611);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// 培训计划管理
		JButton btnNewButton_1 = new JButton("\u57F9\u8BAD\u8BA1\u5212\u7BA1\u7406");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTrainPlanMainForm();
			}
		});
		menuBar.add(btnNewButton_1);
		// 培训成绩管理
		JButton btnNewButton_2 = new JButton("\u57F9\u8BAD\u6210\u7EE9\u7BA1\u7406");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTrainResultMainForm();
			}
		});
		menuBar.add(btnNewButton_2);
		// 员工管理
		JButton button = new JButton("\u5458\u5DE5\u7BA1\u7406");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEmployeeMainForm();
			}
		});
		menuBar.add(button);
		// 部门管理
		JButton button_1 = new JButton("\u90E8\u95E8\u7BA1\u7406");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDepartMainForm();
			}
		});
		menuBar.add(button_1);

		// 退出登录
		JButton button_2 = new JButton("\u9000\u51FA\u767B\u5F55");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showFirstPage();
			}
		});
		menuBar.add(button_2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(135, 206, 235));
		contentPane.add(desktopPane, BorderLayout.CENTER);

		JLabel lblWelcomToPowerstation = new JLabel("Welcom to PowerStation!");
		lblWelcomToPowerstation.setFont(new Font("Ravie", Font.PLAIN, 40));
		lblWelcomToPowerstation.setBounds(51, 155, 707, 135);
		desktopPane.add(lblWelcomToPowerstation);

		JPanel panel = new JPanel();
		panel.setBounds(0, 513, 782, 25);
		desktopPane.add(panel);

		JLabel lblNewLabel = new JLabel(
				"\u2605\u592A\u539F\u79D1\u6280\u5927\u5B66  \u8BA1\u7B97\u673A\u5B66\u9662\u2605");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("华文隶书", Font.PLAIN, 13));
		panel.add(lblNewLabel);

		JLabel lblByCheer = new JLabel("-- By cheer!");
		lblByCheer.setFont(new Font("楷体", Font.PLAIN, 15));
		lblByCheer.setBounds(564, 341, 112, 18);
		desktopPane.add(lblByCheer);
	}

	// 显示员工管理主界面函数
	private void showEmployeeMainForm() {
		EmployeeMainForm emf = new EmployeeMainForm();

		desktopPane.add(emf);
		emf.setVisible(true);
		try {
			emf.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// 显示部门管理主界面函数
	private void showDepartMainForm() {
		DepartMainForm emf = new DepartMainForm();

		desktopPane.add(emf);
		emf.setVisible(true);
		try {
			emf.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// 显示培训计划管理主界面函数
	private void showTrainPlanMainForm() {
		TrainPlanMainForm emf = new TrainPlanMainForm();

		desktopPane.add(emf);
		emf.setVisible(true);
		try {
			emf.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// 显示培训成绩管理主界面函数
	private void showTrainResultMainForm() {
		TrainResultMainForm emf = new TrainResultMainForm();

		desktopPane.add(emf);
		emf.setVisible(true);
		try {
			emf.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void showFirstPage() {
		this.dispose();
		LoginForm emf = new LoginForm();
		emf.setVisible(true);
	}
}
