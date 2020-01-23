package forms.depart;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.DepartDTO;
import dto.KeyValue;
import forms.employee.SelectDepForm;
import services.Depart;

public class UpdateDepart extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUnitId;
	private JTextField txtTele;
	private JTextField txtName;

	private KeyValue<String, DepartDTO> seleDep = null;
	SelectDepForm selectDepForm = null;
	private String selectedDuty = null;
	private String selectedEdu = null;
	private JTextField txtAddress;
	private String userId, unitId;
	private JTextField textHeader;

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
	public UpdateDepart(DepartDTO tableData) {
		setTitle("\u4FEE\u6539\u5458\u5DE5\u4FE1\u606F");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 629, 406);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u90E8\u95E8\u7F16\u53F7");
		lblNewLabel.setBounds(138, 126, 54, 15);
		contentPanel.add(lblNewLabel);

		txtUnitId = new JTextField();
		txtUnitId.setBounds(215, 117, 173, 33);
		txtUnitId.setText(tableData.getUnit_id());
		txtUnitId.setEditable(false);
		contentPanel.add(txtUnitId);
		txtUnitId.setColumns(10);

		JLabel label = new JLabel("\u90E8\u95E8\u7B80\u79F0");
		label.setBounds(138, 189, 79, 15);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
		label_1.setBounds(138, 240, 54, 15);
		contentPanel.add(label_1);

		txtTele = new JTextField();
		txtTele.setColumns(10);
		txtTele.setBounds(215, 231, 173, 33);
		txtTele.setText(tableData.getTelephone());
		contentPanel.add(txtTele);

		JLabel label_2 = new JLabel("\u90E8\u95E8\u540D\u79F0");
		label_2.setBounds(138, 63, 54, 15);
		contentPanel.add(label_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(215, 54, 173, 33);
		txtName.setText(tableData.getUnit_name());
		contentPanel.add(txtName);
//		ButtonGroup sexRdbtn = new ButtonGroup();
//		selectedDuty = temp.getDuty();
//		int index = 0;
//		try {
//			index = Integer.parseInt(temp.getDuty()) - 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (index < 0 || index > 3)
//			index = 0;

		JLabel lblEmile = new JLabel("\u5907\u6CE8");
		lblEmile.setFont(new Font("宋体", Font.PLAIN, 13));
		lblEmile.setBounds(138, 286, 54, 15);
		contentPanel.add(lblEmile);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(215, 277, 173, 34);
		txtAddress.setText(tableData.getAddress());
		contentPanel.add(txtAddress);

		textHeader = new JTextField();
		textHeader.setText(tableData.getHeader());
		textHeader.setColumns(10);
		textHeader.setBounds(215, 180, 173, 34);
		contentPanel.add(textHeader);
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
			if (validateDepart()) {
				DepartDTO userDTO = this.generateUserDTO();
				System.out.println(userDTO);
				Depart addEmployee = new Depart();
				boolean saveSuccessful = false;
				saveSuccessful = addEmployee.updateDepart(userDTO);
				if (saveSuccessful) {
					System.out.println("save successful");
					JOptionPane.showMessageDialog(null, "修改成功！");
				}
			}
		} else if (cmd.equals("Cancel")) {
			this.dispose();
			DepartMainForm frame = new DepartMainForm();
			frame.setVisible(true);
			System.out.println("exit");
		}
	}

	/*
	 * 对部门信息从语法方面进行验证
	 */
	private boolean validateDepart() {
		boolean result = true;

		if (txtName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "部门名称为空！");
			result = result && false;
		}

		return result;
	}

	private DepartDTO generateUserDTO() {
		DepartDTO userDTO = new DepartDTO();
		userDTO.setUnit_name(txtName.getText());// 名称

		userDTO.setUnit_id(txtUnitId.getText());// 编号

		userDTO.setTelephone(txtTele.getText());// 联系

		userDTO.setAddress(txtAddress.getText());// 备注

		userDTO.setHeader(textHeader.getText());// 简称
		return userDTO;

	}
}
