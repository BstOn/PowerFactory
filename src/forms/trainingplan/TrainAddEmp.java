package forms.trainingplan;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import dto.DepartDTO;
import dto.KeyValue;
import dto.TrainResultDTO;
import dto.UserDTO;
import dto.UserTrainDTO;
import forms.employee.SelectDepForm;
import services.Depart;
import services.Duty;
import services.Education;
import services.TrainPlan;
import services.TrainResult;
import services.User;
import services.UserTrain;

public class TrainAddEmp extends JDialog {

	private KeyValue<String, DepartDTO> seleDep = null;
	SelectDepForm selectDepForm = null;
	private JTable table;
	private JTable table_1;
	String name = null;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddEmployee dialog = new AddEmployee();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public TrainAddEmp(final String itemid, final String trainid) {

		setTitle("\u5B66\u5458\u5B89\u6392");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 629, 406);
		// getContentPane().setLayout(new BorderLayout());
		// contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		// 初始化树
		Depart departService = new Depart();
		DepartDTO depart = departService.getDepartById("001");
		System.out.println("*****" + depart.getUnit_name());
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				new KeyValue<String, DepartDTO>(depart.getUnit_name(), depart));

		List<DepartDTO> list = departService.getAllDeparts();
		SelectDepForm.initDepartTree(list, "001", root);
		DefaultTreeModel model = new DefaultTreeModel(root);

		// 显示部门员工
		final JTree treeDep = new JTree();
		treeDep.setBounds(10, 36, 119, 257);
		panel.add(treeDep);
		treeDep.setVisibleRowCount(40);
		treeDep.setModel(model);
		treeDep.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent evt) {

				// 返回最后选中的结点

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeDep.getLastSelectedPathComponent();
				name = node.toString();// 获得这个结点的名称

				((DefaultTableModel) table.getModel()).setRowCount(0);
				User user = new User();
				List<UserDTO> data = user.getOneUsersByUnitName(name);
				for (int i = 0; i < data.size(); i++) {

					Vector vector = new Vector();
					vector.addElement(data.get(i).getUser_id());// 员工id
					vector.addElement(data.get(i).getName());// 姓名
					String selectedDuty = Duty.getName(data.get(i).getDuty());
					vector.addElement(selectedDuty);// 职位
					vector.addElement(data.get(i).getBirthday());// 出生年月
					// vector.addElement(data.get(i).getUnit_name());// 所属部门名称
					String selectedEdu = Education.getName(data.get(i).getTecduty());
					vector.addElement(selectedEdu);// 学位

					((DefaultTableModel) table.getModel()).addRow(vector);
				}

				System.out.println(name);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 36, 311, 257);
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "员工号", "\u59D3\u540D", "岗位", "\u51FA\u751F\u5E74\u6708", "学位" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		// table.getColumnModel().getColumn(0).setPreferredWidth(1);
		this.hideTableColumn(table, 0);
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(460, 36, 146, 257);
		panel.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "员工号", "姓名", "部门" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		// table_1.getColumnModel().getColumn(0).setPreferredWidth(1);
		this.hideTableColumn(table_1, 0);
		scrollPane_1.setViewportView(table_1);
		// 添加
		Button button = new Button("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 1) {
					boolean employeeExisted;
					// 定义服务层对象
					UserTrain userTrain = new UserTrain();
					Depart unit = new Depart();
					// 定义数据传输对象

					UserTrainDTO userTrainDTO = new UserTrainDTO();
					userTrainDTO.setTrain_id(trainid);
					userTrainDTO.setUser_id((String) table.getValueAt(table.getSelectedRow(), 0));
					// 该员工是否已经加入了该培训计划
					employeeExisted = userTrain.employeeExisted(userTrainDTO);
					// 如果没有加入
					if (!employeeExisted) {
						Vector vector = new Vector();
						vector.addElement(table.getValueAt(table.getSelectedRow(), 0));// userid
						vector.addElement(table.getValueAt(table.getSelectedRow(), 1));// 姓名
						vector.addElement(name);
						((DefaultTableModel) table_1.getModel()).addRow(vector);
					} else
						JOptionPane.showMessageDialog(null, "员工已加入该培训！");
				} else
					JOptionPane.showMessageDialog(null, "未选中一行！");

			}
		});
		button.setBounds(390, 299, 72, 22);
		panel.add(button);

		// 删除
		Button button_1 = new Button("\u5220\u9664");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getSelectedRowCount() == 1) {
					((DefaultTableModel) table_1.getModel()).removeRow(table_1.getSelectedRow());
				} else
					JOptionPane.showMessageDialog(null, "未选中一行！");
			}
		});
		button_1.setBounds(534, 299, 72, 22);
		panel.add(button_1);

		JLabel lblNewLabel = new JLabel("\u90E8\u95E8\u9009\u62E9");
		lblNewLabel.setBounds(10, 10, 58, 15);
		panel.add(lblNewLabel);

		JLabel label = new JLabel("\u5DF2\u6DFB\u52A0\u5B66\u5458");
		label.setBounds(487, 10, 82, 15);
		panel.add(label);

		// 保存
		Button button_3 = new Button("\u4FDD\u5B58");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTrain addEmp = new UserTrain();
				UserTrainDTO addEmpDTO = new UserTrainDTO();
				TrainResult addresult = new TrainResult();
				TrainResultDTO addresultDTO = new TrainResultDTO();
				for (int i = 0; i < table_1.getRowCount(); i++) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
					System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
					Date date = new Date();
					addEmpDTO.setTrain_plan_id(trainid);
					addEmpDTO.setTrain_item_id(itemid);
					addEmpDTO.setUser_id((String) table_1.getValueAt(i, 0));
					addEmpDTO.setCreate_time(date);
					addEmp.saveEmployeeToTrainPlan(addEmpDTO);

					addresultDTO = addresult.queryTrainEmpIdByEmpId((String) table_1.getValueAt(i, 0), itemid);
					addresultDTO.setAttendance_result("0");
					addresultDTO.setExam_result("0");
					addresultDTO.setTotal_result("0");
					boolean success = false;
					success = addresult.saveResultToTrainPlan(addresultDTO);
					System.out.println("成绩初始化" + success);
				}
				TrainPlan train = new TrainPlan();
				String a = "未培训";
				train.IsFinish(a, trainid);
			}
		});
		button_3.setBounds(403, 346, 72, 22);
		panel.add(button_3);

		// 退出
		Button button_4 = new Button("\u9000\u51FA");

		button_4.setBounds(497, 346, 72, 22);
		panel.add(button_4);

		JLabel label_1 = new JLabel("\u90E8\u95E8\u5458\u5DE5");
		label_1.setBounds(139, 10, 72, 18);
		panel.add(label_1);

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

}
