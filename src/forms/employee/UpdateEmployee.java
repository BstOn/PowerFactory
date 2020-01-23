package forms.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.DepartDTO;
import dto.KeyValue;
import dto.UserDTO;
import services.Duty;
import services.User;
import util.AgeUtil;
import util.DateChooser;

public class UpdateEmployee extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtBirthday;
	private JTextField txtCardID;
	private JTextField txtDep;
	private JComboBox<String> cmbDuty;

	private KeyValue<String, DepartDTO> seleDep = null;
	SelectDepForm selectDepForm = null;
	private String selectedDuty = null;
	private String selectedEdu = null;
	private JTextField txtEmail;
	private String userId, unitId;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			UpdateEmployee dialog = new UpdateEmployee();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public UpdateEmployee(List<UserDTO> tableData) {
		setTitle("\u4FEE\u6539\u5458\u5DE5\u4FE1\u606F");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 629, 406);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		userId = tableData.get(0).getUser_id();
		unitId = tableData.get(0).getUnit_id();
		UserDTO temp = tableData.get(0);

		JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel.setBounds(298, 72, 54, 15);
		contentPanel.add(lblNewLabel);

		txtName = new JTextField();
		txtName.setBounds(365, 63, 155, 33);
		txtName.setText(temp.getName());
		contentPanel.add(txtName);
		txtName.setColumns(10);

		JLabel label = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
		label.setBounds(45, 138, 79, 15);
		contentPanel.add(label);

		txtBirthday = new JTextField();
		txtBirthday.setColumns(10);
		txtBirthday.setBounds(107, 129, 173, 33);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(txtBirthday);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(temp.getBirthday());
		txtBirthday.setText(dateString);
		contentPanel.add(txtBirthday);

		JLabel label_1 = new JLabel("\u8EAB\u4EFD\u8BC1\uFF1A");
		label_1.setBounds(298, 138, 54, 15);
		contentPanel.add(label_1);

		txtCardID = new JTextField();
		txtCardID.setColumns(10);
		txtCardID.setBounds(365, 128, 155, 33);
		txtCardID.setText(temp.getCard_id());
		contentPanel.add(txtCardID);

		JLabel label_2 = new JLabel("\u90E8\u95E8\uFF1A");
		label_2.setBounds(45, 72, 54, 15);
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
		txtDep.setBounds(107, 63, 173, 33);
		txtDep.setText(temp.getUnit_name());
		contentPanel.add(txtDep);
		ButtonGroup sexRdbtn = new ButtonGroup();

		JLabel label_4 = new JLabel("\u804C\u52A1\uFF1A");
		label_4.setBounds(45, 213, 54, 15);
		contentPanel.add(label_4);

		cmbDuty = new JComboBox();
		selectedDuty = temp.getDuty();
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
		int index = 0;
		try {
			index = Integer.parseInt(temp.getDuty()) - 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (index < 0 || index > 3)
			index = 0;
		cmbDuty.setSelectedIndex(index);
		contentPanel.add(cmbDuty);

		JLabel lblEmile = new JLabel("Email\uFF1A");
		lblEmile.setFont(new Font("宋体", Font.PLAIN, 13));
		lblEmile.setBounds(301, 213, 54, 15);
		contentPanel.add(lblEmile);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(365, 204, 155, 34);
		txtEmail.setText(temp.getEmail());
		contentPanel.add(txtEmail);
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
				saveSuccessful = addEmployee.updateEmployees(userDTO);
				if (saveSuccessful) {
					System.out.println("save successful");
					JOptionPane.showMessageDialog(null, "修改成功！");
				}
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
		userDTO.setUser_id(userId); // 员工号
		userDTO.setName(txtName.getText().trim());// 姓名

		// userDTO.setSex(rdbtnSexMale.isSelected() ? "0" : "1");// 性别
		userDTO.setCard_id(txtCardID.getText());// 身份证
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			userDTO.setBirthday(format.parse(txtBirthday.getText()));

		} catch (ParseException a) {
			a.printStackTrace();
		}

		try {
			DepartDTO selDepart = seleDep.getValue();
			userDTO.setUnit_id(selDepart.getUnit_id());// 部门编号
		} catch (Exception e) {
			userDTO.setUnit_id(unitId);
			e.printStackTrace();
		}

		userDTO.setDuty(selectedDuty);// 职务
		userDTO.setEmail(txtEmail.getText());
		return userDTO;

	}

	/*
	 * 对员工信息从语法方面进行验证
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
			JOptionPane.showMessageDialog(null, "未满18岁，不予录用！");
			result = result && false;
		}

		if (txtCardID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "身份证为空！");
			result = result && false;
		}

		return result;
	}
}
