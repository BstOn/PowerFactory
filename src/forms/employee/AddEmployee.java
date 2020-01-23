package forms.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.DepartDTO;
import dto.KeyValue;
import dto.UserDTO;
import services.Duty;
import services.Education;
import services.User;
import util.AgeUtil;
import util.DateChooser;

public class AddEmployee extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtBirthday;
	private JTextField txtCardID;
	private JTextField txtDep;
	private JTextField txtTel;
	private JComboBox<String> cmbDuty;
	private JComboBox<String> cmbEdu;
	private JRadioButton rdbtnSexMale;
	private JRadioButton rdbtnSexFemale;

	private KeyValue<String, DepartDTO> seleDep = null;
	SelectDepForm selectDepForm = null;
	private String selectedDuty = null;
	private String selectedEdu = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEmployee dialog = new AddEmployee();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEmployee() {
		setTitle("\u6DFB\u52A0\u5458\u5DE5\u4FE1\u606F");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 629, 406);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel.setBounds(45, 72, 54, 15);
		contentPanel.add(lblNewLabel);

		txtName = new JTextField();
		txtName.setBounds(107, 63, 173, 33);
		contentPanel.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_1.setBounds(301, 72, 54, 15);
		contentPanel.add(lblNewLabel_1);

		JLabel label = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
		label.setBounds(45, 115, 79, 15);
		contentPanel.add(label);

		txtBirthday = new JTextField();
		txtBirthday.setColumns(10);
		txtBirthday.setBounds(107, 106, 173, 33);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(txtBirthday);
		contentPanel.add(txtBirthday);

		JLabel label_1 = new JLabel("\u8EAB\u4EFD\u8BC1\uFF1A");
		label_1.setBounds(301, 115, 54, 15);
		contentPanel.add(label_1);

		txtCardID = new JTextField();
		txtCardID.setColumns(10);
		txtCardID.setBounds(365, 103, 155, 36);
		contentPanel.add(txtCardID);

		JLabel label_2 = new JLabel("\u90E8\u95E8\uFF1A");
		label_2.setBounds(45, 162, 54, 15);
		contentPanel.add(label_2);

		txtDep = new JTextField();
		txtDep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				selectDepForm = new SelectDepForm();
				selectDepForm.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (selectDepForm == null)
					return;

				seleDep = selectDepForm.getSelectedDep();
				if (seleDep == null)
					return;
				System.out.println("mouse exited.....");
				System.out.println(seleDep.getKey());
				txtDep.setText(seleDep.getKey());
				txtDep.setVisible(true);
			}

		});
		txtDep.setColumns(10);
		txtDep.setBounds(107, 153, 173, 33);
		contentPanel.add(txtDep);

		JLabel label_3 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_3.setBounds(301, 162, 72, 15);
		contentPanel.add(label_3);

		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(365, 149, 155, 34);
		contentPanel.add(txtTel);

		rdbtnSexMale = new JRadioButton("\u7537");
		rdbtnSexMale.setBounds(364, 68, 59, 23);
		contentPanel.add(rdbtnSexMale);

		rdbtnSexFemale = new JRadioButton("\u5973");
		rdbtnSexFemale.setBounds(425, 68, 59, 23);
		contentPanel.add(rdbtnSexFemale);
		ButtonGroup sexRdbtn = new ButtonGroup();
		sexRdbtn.add(rdbtnSexMale);
		sexRdbtn.add(rdbtnSexFemale);

		JLabel label_4 = new JLabel("\u804C\u52A1\uFF1A");
		label_4.setBounds(45, 213, 54, 15);
		contentPanel.add(label_4);

		cmbDuty = new JComboBox();

		cmbDuty.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selDuty = (String) e.getItem();
					selectedDuty = Duty.getIndex(selDuty);
					System.out.println("Selected item is " + Duty.getIndex(selDuty));
				}
			}
		});
		cmbDuty.setBackground(Color.WHITE);
		cmbDuty.setForeground(Color.BLACK);
		cmbDuty.setModel(new DefaultComboBoxModel<String>(Duty.getNames()));
		cmbDuty.setBounds(105, 202, 175, 36);
		contentPanel.add(cmbDuty);

		cmbEdu = new JComboBox();
		cmbEdu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selEdu = (String) e.getItem();
				selectedEdu = Education.getIndex(selEdu);
			}
		});
		cmbEdu.setModel(new DefaultComboBoxModel<String>(Education.getNames()));
		cmbEdu.setForeground(Color.BLACK);
		cmbEdu.setBackground(Color.WHITE);
		cmbEdu.setBounds(365, 202, 155, 36);
		contentPanel.add(cmbEdu);

		JLabel label_5 = new JLabel("\u5B66\u5386\uFF1A");
		label_5.setBounds(301, 213, 54, 15);
		contentPanel.add(label_5);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u4FDD\u5B58");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u9000\u51FA");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				getRootPane().setDefaultButton(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String cmd = e.getActionCommand();
		if (cmd.equals("OK")) {
			if (validateEmployee()) {
				UserDTO userDTO = this.generateUserDTO();
				System.out.println(userDTO);
				User addEmployee = new User();
				boolean saveSuccessful = false;
				saveSuccessful = addEmployee.saveAddEmployees(userDTO);
				if (saveSuccessful) {
					System.out.println("save successful");
					JOptionPane.showMessageDialog(null, "����ɹ���");
				} else
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
			}

		} else if (cmd.equals("Cancel")) {
			this.dispose();
			EmployeeMainForm frame = new EmployeeMainForm();
			frame.setVisible(true);
			System.out.println("exit");
		}
	}

	private UserDTO generateUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(txtName.getText().trim());// ����

		userDTO.setSex(rdbtnSexMale.isSelected() ? "0" : "1");// �Ա�
		userDTO.setCard_id(txtCardID.getText());// ���֤
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			userDTO.setBirthday(format.parse(txtBirthday.getText()));

		} catch (ParseException a) {
			a.printStackTrace();
		}

		DepartDTO selDepart = seleDep.getValue();
		userDTO.setUnit_id(selDepart.getUnit_id());// ���ű��

		userDTO.setTelphone(txtTel.getText().trim());// ��ϵ�绰

		userDTO.setDuty(selectedDuty);// ְ��

		userDTO.setTecduty(selectedEdu);// ѧ��

		return userDTO;

	}

	/*
	 * ��Ա����Ϣ���﷨���������֤
	 */
	private boolean validateEmployee() {
		boolean result = true;
		java.util.Date birthday = null;
		int age = 0;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			birthday = format.parse(txtBirthday.getText());

		} catch (ParseException a) {
			a.printStackTrace();
		}
		try {
			age = new AgeUtil().getAge(birthday);
			System.out.println("age==" + age);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		if (age < 18)

		{
			JOptionPane.showMessageDialog(null, "����Ӧ����18�꣡");
			result = result && false;
		}

		if (txtCardID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "���֤��ϢΪ�գ�");
			result = result && false;
		}

		return result;
	}
}
