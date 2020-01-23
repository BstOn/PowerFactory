package forms.employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import dto.KeyValue;
import dto.UserDTO;
import services.User;

public class EmployeeMainForm extends JInternalFrame {
	private JTextField txtEmployeeName;
	private JTable tblEmployee;
	private JLabel lblDepartCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMainForm frame = new EmployeeMainForm();
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
	public EmployeeMainForm() {
		setTitle("\u5458\u5DE5\u7BA1\u7406");
		setClosable(true);
		setBounds(100, 100, 978, 679);

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		toolBar.add(panel_1);

		JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
		panel_1.add(lblNewLabel);

		txtEmployeeName = new JTextField();
		panel_1.add(txtEmployeeName);
		txtEmployeeName.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmployeeName.setColumns(20);

		JLabel lblNewLabel_1 = new JLabel("\u6027\u522B\uFF1A");
		panel_1.add(lblNewLabel_1);

		final JComboBox sexComboBox = new JComboBox();
		panel_1.add(sexComboBox);
		sexComboBox.setModel(new DefaultComboBoxModel(new String[] { "\u7537", "\u5973" }));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		tblEmployee = new JTable();

		Vector<Vector<Object>> tableDatas = new User().getAllUsers();

		Vector<String> colums = new Vector<String>();
		colums.add("部门");
		colums.add("姓名");
		colums.add("出生日期");
		colums.add("身份证");
		colums.add("职务");
		colums.add("Email");

		DefaultTableModel tableModel = new DefaultTableModel(tableDatas, colums);
		RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);

		tblEmployee.setRowHeight(30);
		tblEmployee.setRowSorter(sorter);
		tblEmployee.setModel(tableModel);
		// this.hideTableColumn(tblEmployee, 0);
		scrollPane.setViewportView(tblEmployee);
//		scrollPane.setColumnHeaderView(tblEmployee);

		JToolBar toolBar_1 = new JToolBar();
		panel.add(toolBar_1, BorderLayout.NORTH);

		// 员工查询,姓名+性别
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sex;
				if (sexComboBox.getSelectedItem() == "男") {
					sex = "0";
				} else {
					sex = "1";
				}

				Vector<Vector<Object>> tableDatas = new User().getSomeUsers(txtEmployeeName.getText(), sex);

				Vector<String> colums = new Vector<String>();
				colums.add("部门");
				colums.add("姓名");
				colums.add("出生日期");
				colums.add("身份证");
				colums.add("职务");
				colums.add("Email");

				DefaultTableModel tableModel = new DefaultTableModel(tableDatas, colums);
				RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);

				tblEmployee.setRowHeight(30);
				tblEmployee.setRowSorter(sorter);
				tblEmployee.setModel(tableModel);

			}

		});
		panel_1.add(btnNewButton);
		// 性别查询
		JButton button_1 = new JButton("\u6027\u522B\u67E5\u8BE2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sex;
				if (sexComboBox.getSelectedItem() == "男") {
					sex = "0";
				} else {
					sex = "1";
				}

				Vector<Vector<Object>> tableDatas = new User().getSomeUsersBysex(sex);

				Vector<String> colums = new Vector<String>();
				colums.add("部门");
				colums.add("姓名");
				colums.add("出生日期");
				colums.add("身份证");
				colums.add("职务");
				colums.add("Email");

				DefaultTableModel tableModel = new DefaultTableModel(tableDatas, colums);
				RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);

				tblEmployee.setRowHeight(30);
				tblEmployee.setRowSorter(sorter);
				tblEmployee.setModel(tableModel);
			}
		});
		panel_1.add(button_1);

		JButton button_2 = new JButton("\u59D3\u540D\u67E5\u8BE2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Vector<Vector<Object>> tableDatas = new User().getSomeUsersByname(txtEmployeeName.getText());

				Vector<String> colums = new Vector<String>();
				colums.add("部门");
				colums.add("姓名");
				colums.add("出生日期");
				colums.add("身份证");
				colums.add("职务");
				colums.add("Email");

				DefaultTableModel tableModel = new DefaultTableModel(tableDatas, colums);
				RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);

				tblEmployee.setRowHeight(30);
				tblEmployee.setRowSorter(sorter);
				tblEmployee.setModel(tableModel);

			}
		});
		panel_1.add(button_2);

		// 添加员工信息
		JButton btnNewButton_1 = new JButton("\u6DFB\u52A0");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployee addEmp = new AddEmployee();
				addEmp.setVisible(true);
			}
		});
		toolBar_1.add(btnNewButton_1);

		// 删除员工信息
		JButton btnDel = new JButton("\u5220\u9664");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblEmployee.getSelectedRowCount() == 1) {
					int result = JOptionPane.showConfirmDialog(null, "确认删除?", "确认", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					// 用户确认后，执行删除操作，删除数据库中对应记录
					if (result == JOptionPane.OK_OPTION) {
						String delEmployId = getSelectedEmployeeId();
						System.out.println(delEmployId);
						User user = new User();
						user.delEmployee(delEmployId);

						JOptionPane.showMessageDialog(null, "删除成功！");
						// 删除成功后，刷新 Table 中信息。
						((DefaultTableModel) tblEmployee.getModel()).removeRow(tblEmployee.getSelectedRow());
					}

				} else
					JOptionPane.showMessageDialog(null, "未选中！");
			}

		});
		toolBar_1.add(btnDel);

		// 修改员工信息
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] member = new String[6];
				if (tblEmployee.getSelectedRowCount() == 1) {
					int result = JOptionPane.showConfirmDialog(null, "确认修改?", "确认", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					// 用户确认后，执行修改操作，修改数据库中对应记录
					if (result == JOptionPane.OK_OPTION) {

						User user = new User();
						List<UserDTO> tableData = user.getOneUsersByUserId(getSelectedEmployeeId());
						UpdateEmployee updateEmp = new UpdateEmployee(tableData);
						updateEmp.setVisible(true);

						// 修改成功后，刷新 Table 中信息。
						Vector<Vector<Object>> tableDatas = new User().getAllUsers();

						Vector<String> colums = new Vector<String>();
						colums.add("部门");
						colums.add("姓名");
						colums.add("出生日期");
						colums.add("身份证");
						colums.add("职务");
						colums.add("Email");

						DefaultTableModel tableModel = new DefaultTableModel(tableDatas, colums);
						RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);

						tblEmployee.setRowHeight(30);
						tblEmployee.setRowSorter(sorter);
						tblEmployee.setModel(tableModel);
					}

				} else
					JOptionPane.showMessageDialog(null, "未选中！");

			}
		});
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		toolBar_1.add(btnUpdate);
		// 刷新
		JButton button = new JButton("\u5237\u65B0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 刷新 Table 中信息。
				Vector<Vector<Object>> tableDatas = new User().getAllUsers();

				Vector<String> colums = new Vector<String>();
				colums.add("部门");
				colums.add("姓名");
				colums.add("出生日期");
				colums.add("身份证");
				colums.add("职务");
				colums.add("Email");

				DefaultTableModel tableModel = new DefaultTableModel(tableDatas, colums);
				RowSorter sorter = new TableRowSorter<DefaultTableModel>(tableModel);

				tblEmployee.setRowHeight(30);
				tblEmployee.setRowSorter(sorter);
				tblEmployee.setModel(tableModel);
			}
		});
		toolBar_1.add(button);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		toolBar_1.add(panel_2);

	}

	private void hideTableColumn(JTable table, int index) {

		TableColumn tc = table.getColumnModel().getColumn(index);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setMinWidth(0);
		tc.setWidth(0);

		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(0);

	}

	private String getSelectedEmployeeId() {
		int row = tblEmployee.getSelectedRow();

		if (row == -1)
			return "";

		KeyValue<String, String> k = (KeyValue<String, String>) tblEmployee.getValueAt(row, 1);
		System.out.println(k.getValue() + k.getKey());

		return k.getValue();
	}
}
