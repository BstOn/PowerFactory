package forms.depart;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

public class AddDepart extends JDialog implements ActionListener {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUpName;
	private JTextField txtUnitId;
	private JTextField txtPerson;
	private JTextField textHeader;
	private JTextField txtUpUnitId;
	private JTextField txtUnitName;

	private KeyValue<String, DepartDTO> seleDep = null;
	SelectDepForm selectDepForm = null;
	private String selectedDuty = null;
	private String selectedEdu = null;
	private static String name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddDepart dialog = new AddDepart(name);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddDepart(String name) {
		setTitle("\u6DFB\u52A0\u90E8\u95E8\u4FE1\u606F");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 629, 440);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u4E0A\u7EA7\u90E8\u95E8");
		lblNewLabel.setBounds(98, 43, 54, 15);
		contentPanel.add(lblNewLabel);

		// 获取上级部门名称
		txtUpName = new JTextField();
		txtUpName.setBounds(240, 34, 173, 34);
		txtUpName.setText(name);
		txtUpName.setEditable(false);
		contentPanel.add(txtUpName);
		txtUpName.setColumns(10);

		JLabel label = new JLabel("\u4E0A\u7EA7\u90E8\u95E8\u7F16\u53F7");
		label.setBounds(87, 97, 79, 15);
		contentPanel.add(label);
		// DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");

		txtUnitId = new JTextField();
		txtUnitId.setColumns(10);
		txtUnitId.setBounds(240, 203, 173, 26);
		contentPanel.add(txtUnitId);

		JLabel unitName = new JLabel("\u90E8\u95E8\u540D\u79F0");
		unitName.setBounds(98, 154, 54, 15);
		contentPanel.add(unitName);

		txtPerson = new JTextField();
		txtPerson.setColumns(10);
		txtPerson.setBounds(240, 301, 173, 34);
		contentPanel.add(txtPerson);

		JLabel label_4 = new JLabel("\u90E8\u95E8\u7F16\u53F7");
		label_4.setBounds(98, 208, 54, 15);
		contentPanel.add(label_4);

		textHeader = new JTextField();
		textHeader.setColumns(10);
		textHeader.setBounds(240, 249, 173, 33);
		contentPanel.add(textHeader);

		JLabel lblNewLabel_1 = new JLabel("\u90E8\u95E8\u7B80\u79F0");
		lblNewLabel_1.setBounds(98, 258, 58, 15);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u5907\u6CE8");
		lblNewLabel_2.setBounds(108, 310, 58, 15);
		contentPanel.add(lblNewLabel_2);

		// 获取上级部门编号
		txtUpUnitId = new JTextField();
		txtUpUnitId.setBounds(240, 94, 173, 26);
		Depart depart = new Depart();
		DepartDTO departdto = new DepartDTO();
		departdto = depart.getDepartByName(name);
		txtUpUnitId.setText(departdto.getUnit_id());
		txtUpUnitId.setEditable(false);
		contentPanel.add(txtUpUnitId);
		txtUpUnitId.setColumns(10);

		txtUnitName = new JTextField();
		txtUnitName.setBounds(240, 143, 173, 34);
		contentPanel.add(txtUnitName);
		txtUnitName.setColumns(10);
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
				DepartDTO departDTO = generateUnitDTO();
				System.out.println(departDTO);
				Depart depart = new Depart();
				boolean saveSuccessful = false;
				saveSuccessful = depart.addUnit(departDTO);
				if (saveSuccessful) {
					System.out.println("save successful");
					JOptionPane.showMessageDialog(null, "保存成功！");
				} else
					JOptionPane.showMessageDialog(null, "保存失败！");
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

		if (txtUnitId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "部门编号为空！");
			result = result && false;
		}
		if (txtUnitName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "部门名称为空！");
			result = result && false;
		}

		return result;
	}

	// 获取界面部门信息
	private DepartDTO generateUnitDTO() {
		DepartDTO departDTO = new DepartDTO();

		departDTO.setUp_unit_id(txtUpUnitId.getText()); // 上级部门编号
		departDTO.setUnit_id(txtUnitId.getText());// 部门编号

		departDTO.setUnit_name(txtUnitName.getText());// 部门名称

		departDTO.setHeader(textHeader.getText());// 部门简称

		departDTO.setContact_person(txtPerson.getText());// 备注

		return departDTO;

	}
}
