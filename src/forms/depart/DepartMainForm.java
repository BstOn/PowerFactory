package forms.depart;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;

import dto.DepartDTO;
import dto.KeyValue;
import forms.employee.SelectDepForm;
import services.Depart;

public class DepartMainForm extends JInternalFrame {
	private JTextField txtUnitId;
	private JPanel panel;
	private JTable tblDepart;
	private JLabel lblDepartCount;
	private JTable table_1;
	private JTree myTreeDep;
	private JTable table_2;
	private JComboBox sexComboBox;
	private JTextField textField_1;
	private KeyValue<String, DepartDTO> seleDep = null;
	SelectDepForm selectDepForm = null;
	String name = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartMainForm frame = new DepartMainForm();
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
	public DepartMainForm() {
		setTitle("\u90E8\u95E8\u7BA1\u7406");
		setClosable(true);
		setBounds(100, 100, 978, 679);

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		toolBar.add(panel_1);

		JLabel lblNewLabel = new JLabel("\u90E8\u95E8\u7F16\u53F7");
		panel_1.add(lblNewLabel);

		txtUnitId = new JTextField();
		panel_1.add(txtUnitId);
		txtUnitId.setHorizontalAlignment(SwingConstants.LEFT);
		txtUnitId.setColumns(20);

		JLabel lblNewLabel_1 = new JLabel("\u90E8\u95E8");
		panel_1.add(lblNewLabel_1);

		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		// 部门查询
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<DepartDTO> tableDatas = new Depart().getSomeDeparts(txtUnitId.getText(), textField_1.getText());
				searchSome(tableDatas);

			}

		});
		panel_1.add(btnNewButton);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 27, 551, 511);
		panel.add(scrollPane);

		Depart departService = new Depart();
		DepartDTO depart = departService.getDepartById("001");
		System.out.println("*****" + depart.getUnit_name());
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				new KeyValue<String, DepartDTO>(depart.getUnit_name(), depart));
//初始化树
		List<DepartDTO> list = departService.getAllDeparts();
		SelectDepForm.initDepartTree(list, "001", root);
		DefaultTreeModel model = new DefaultTreeModel(root);
		final JTree treeDep = new JTree();
		treeDep.setBounds(10, 27, 177, 511);
		panel.add(treeDep);
		treeDep.setVisibleRowCount(40);
		treeDep.setModel(model);
		treeDep.getSelectionModel().setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);

		treeDep.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent evt) {

				// 返回最后选中的结点

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeDep.getLastSelectedPathComponent();
				name = node.toString();// 获得这个结点的名称
				// seleDep = selectDepForm.getSelectedDep();
				// seleDep = (KeyValue<String, DepartDTO>) node.getUserObject();
				System.out.println(name);
			}
		});

		tblDepart = new JTable();
		tblDepart.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "序号", "部门名称", "部门编号", "部门简称", "联系方式", "备注" }) {
					public boolean isCellEditblDepart(int row, int column) {
						if (column == 0)
							return false;
						return true;
					}
				});
//设置表格宽		
//		tblDepart.getColumnModel().getColumn(0).setPreferredWidth(50);

		tblDepart.setRowHeight(30);
		scrollPane.setViewportView(tblDepart);

		searchAll();// 刷新表格内容

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBounds(0, 0, 966, 27);
		panel.add(toolBar_1);
//添加部门信息

		JButton btnNew = new JButton("\u6DFB\u52A0");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (name == null)
					JOptionPane.showMessageDialog(null, "请选择上级部门！");
				else {
					AddDepart addEmp = new AddDepart(name);
					addEmp.setVisible(true);
					System.out.println("跳转部门添加页面.....");
					searchAll();
				}

			}
		});

		toolBar_1.add(btnNew);
//删除部门
		JButton btnDel = new JButton("\u5220\u9664");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblDepart.getSelectedRowCount() == 1) {
					int result = JOptionPane.showConfirmDialog(null, "确认删除?", "确认", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					// 用户确认后，执行删除操作，删除数据库中对应记录
					if (result == JOptionPane.OK_OPTION) {
						Boolean successful = false;
						String delEmployId = getSelectedUnitId();
						System.out.println(delEmployId);
						Depart depart = new Depart();
						depart.delUnit(delEmployId);
						if (successful) {
							JOptionPane.showMessageDialog(null, "删除成功！");
						}

						// 删除成功后，刷新 Table 中信息。
						// ((DefaultTableModel)
						// tblDepart.getModel()).removeRow(tblDepart.getSelectedRow());
						searchAll();

					}

				} else
					JOptionPane.showMessageDialog(null, "未选中！");
			}

		});
		toolBar_1.add(btnDel);

// 修改部门信息
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblDepart.getSelectedRowCount() == 1) {
					int result = JOptionPane.showConfirmDialog(null, "确认修改?", "确认", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					// 用户确认后，执行修改操作，修改数据库中对应记录
					if (result == JOptionPane.OK_OPTION) {
						Depart user = new Depart();
						String a = tblDepart.getValueAt(tblDepart.getSelectedRow(), 2).toString();
						DepartDTO tableData = user.getDepartById(a);
						System.out.println(tableData.getUnit_id());
						UpdateDepart updateEmp = new UpdateDepart(tableData);
						updateEmp.setVisible(true);
						searchAll();
					}

				} else
					JOptionPane.showMessageDialog(null, "未选中！");

			}
		});
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		toolBar_1.add(btnUpdate);
//刷新
		JButton button = new JButton("\u5237\u65B0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAll();

				Depart departService = new Depart();
				DepartDTO depart = departService.getDepartById("001");
				System.out.println("*****" + depart.getUnit_name());
				DefaultMutableTreeNode root = new DefaultMutableTreeNode(
						new KeyValue<String, DepartDTO>(depart.getUnit_name(), depart));
				// 初始化树
				List<DepartDTO> list = departService.getAllDeparts();
				SelectDepForm.initDepartTree(list, "001", root);
				DefaultTreeModel model = new DefaultTreeModel(root);
				final JTree treeDep = new JTree();
				treeDep.setBounds(10, 27, 177, 511);
				panel.add(treeDep);
				treeDep.setVisibleRowCount(40);
				treeDep.setModel(model);
				treeDep.getSelectionModel().setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);

			}
		});
		toolBar_1.add(button);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		toolBar_1.add(panel_2);

		lblDepartCount = new JLabel("");
		panel_2.add(lblDepartCount);

		String format = "共计部门：%d个";
		this.lblDepartCount.setText(String.format(format, new Depart().getDepartcount()));

	}

	private void hideTableColumn(JTable table, int column) {
		TableColumnModel tcm = table.getColumnModel();
		// 其实没有移除，仅仅隐藏而已
		TableColumn tc = tcm.getColumn(column);
		tcm.removeColumn(tc);
	}

	private String getSelectedUnitId() {
		int row = tblDepart.getSelectedRow();

		if (row == -1)
			return "";
		String k = tblDepart.getValueAt(row, 2).toString();
		System.out.println(k);

		return k;
	}

	private void searchAll() {
		// 定义服务层对象
		Depart unit = new Depart();
		// 定义数据传输对象
		List<DepartDTO> allUnits = new ArrayList<DepartDTO>();
		// 定义数据传输对象
		DepartDTO unitDTO = new DepartDTO();

		// 执行查询
		allUnits = unit.getAllDeparts();// 查找所有部门

		// 清除当前表格数据
		int j = tblDepart.getRowCount();
		for (int i = 0; i < j; i++) {
			((DefaultTableModel) tblDepart.getModel()).removeRow(0);
		}
		// 显示查询结果
		for (int i = 0; i < allUnits.size(); i++) {
			Vector vector = new Vector();
			vector.addElement(i + 1);// 序号
			vector.addElement(allUnits.get(i).getUnit_name());// 部门名称
			vector.addElement(allUnits.get(i).getUnit_id());// 部门编号
			vector.addElement(allUnits.get(i).getHeader());// 部门简称
			vector.addElement(allUnits.get(i).getTelephone());// 联系方式
			vector.addElement(allUnits.get(i).getContact_person());// 备注
			((DefaultTableModel) tblDepart.getModel()).addRow(vector);
		}
	}

	private void searchSome(List<DepartDTO> allUnits) {

		// 清除当前表格数据
		int j = tblDepart.getRowCount();
		for (int i = 0; i < j; i++) {
			((DefaultTableModel) tblDepart.getModel()).removeRow(0);
		}
		// 显示查询结果
		for (int i = 0; i < allUnits.size(); i++) {
			Vector vector = new Vector();
			vector.addElement(i + 1);// 序号
			vector.addElement(allUnits.get(i).getUnit_name());// 部门名称
			vector.addElement(allUnits.get(i).getUnit_id());// 部门编号
			vector.addElement(allUnits.get(i).getHeader());// 部门简称
			vector.addElement(allUnits.get(i).getTelephone());// 联系方式
			vector.addElement(allUnits.get(i).getContact_person());// 备注
			((DefaultTableModel) tblDepart.getModel()).addRow(vector);
		}
	}

}
