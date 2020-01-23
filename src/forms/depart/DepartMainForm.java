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

		// ���Ų�ѯ
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
//��ʼ����
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

				// �������ѡ�еĽ��

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeDep.getLastSelectedPathComponent();
				name = node.toString();// ��������������
				// seleDep = selectDepForm.getSelectedDep();
				// seleDep = (KeyValue<String, DepartDTO>) node.getUserObject();
				System.out.println(name);
			}
		});

		tblDepart = new JTable();
		tblDepart.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "���", "��������", "���ű��", "���ż��", "��ϵ��ʽ", "��ע" }) {
					public boolean isCellEditblDepart(int row, int column) {
						if (column == 0)
							return false;
						return true;
					}
				});
//���ñ���		
//		tblDepart.getColumnModel().getColumn(0).setPreferredWidth(50);

		tblDepart.setRowHeight(30);
		scrollPane.setViewportView(tblDepart);

		searchAll();// ˢ�±������

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBounds(0, 0, 966, 27);
		panel.add(toolBar_1);
//��Ӳ�����Ϣ

		JButton btnNew = new JButton("\u6DFB\u52A0");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (name == null)
					JOptionPane.showMessageDialog(null, "��ѡ���ϼ����ţ�");
				else {
					AddDepart addEmp = new AddDepart(name);
					addEmp.setVisible(true);
					System.out.println("��ת�������ҳ��.....");
					searchAll();
				}

			}
		});

		toolBar_1.add(btnNew);
//ɾ������
		JButton btnDel = new JButton("\u5220\u9664");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblDepart.getSelectedRowCount() == 1) {
					int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��?", "ȷ��", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					// �û�ȷ�Ϻ�ִ��ɾ��������ɾ�����ݿ��ж�Ӧ��¼
					if (result == JOptionPane.OK_OPTION) {
						Boolean successful = false;
						String delEmployId = getSelectedUnitId();
						System.out.println(delEmployId);
						Depart depart = new Depart();
						depart.delUnit(delEmployId);
						if (successful) {
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
						}

						// ɾ���ɹ���ˢ�� Table ����Ϣ��
						// ((DefaultTableModel)
						// tblDepart.getModel()).removeRow(tblDepart.getSelectedRow());
						searchAll();

					}

				} else
					JOptionPane.showMessageDialog(null, "δѡ�У�");
			}

		});
		toolBar_1.add(btnDel);

// �޸Ĳ�����Ϣ
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblDepart.getSelectedRowCount() == 1) {
					int result = JOptionPane.showConfirmDialog(null, "ȷ���޸�?", "ȷ��", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					// �û�ȷ�Ϻ�ִ���޸Ĳ������޸����ݿ��ж�Ӧ��¼
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
					JOptionPane.showMessageDialog(null, "δѡ�У�");

			}
		});
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		toolBar_1.add(btnUpdate);
//ˢ��
		JButton button = new JButton("\u5237\u65B0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAll();

				Depart departService = new Depart();
				DepartDTO depart = departService.getDepartById("001");
				System.out.println("*****" + depart.getUnit_name());
				DefaultMutableTreeNode root = new DefaultMutableTreeNode(
						new KeyValue<String, DepartDTO>(depart.getUnit_name(), depart));
				// ��ʼ����
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

		String format = "���Ʋ��ţ�%d��";
		this.lblDepartCount.setText(String.format(format, new Depart().getDepartcount()));

	}

	private void hideTableColumn(JTable table, int column) {
		TableColumnModel tcm = table.getColumnModel();
		// ��ʵû���Ƴ����������ض���
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
		// �����������
		Depart unit = new Depart();
		// �������ݴ������
		List<DepartDTO> allUnits = new ArrayList<DepartDTO>();
		// �������ݴ������
		DepartDTO unitDTO = new DepartDTO();

		// ִ�в�ѯ
		allUnits = unit.getAllDeparts();// �������в���

		// �����ǰ�������
		int j = tblDepart.getRowCount();
		for (int i = 0; i < j; i++) {
			((DefaultTableModel) tblDepart.getModel()).removeRow(0);
		}
		// ��ʾ��ѯ���
		for (int i = 0; i < allUnits.size(); i++) {
			Vector vector = new Vector();
			vector.addElement(i + 1);// ���
			vector.addElement(allUnits.get(i).getUnit_name());// ��������
			vector.addElement(allUnits.get(i).getUnit_id());// ���ű��
			vector.addElement(allUnits.get(i).getHeader());// ���ż��
			vector.addElement(allUnits.get(i).getTelephone());// ��ϵ��ʽ
			vector.addElement(allUnits.get(i).getContact_person());// ��ע
			((DefaultTableModel) tblDepart.getModel()).addRow(vector);
		}
	}

	private void searchSome(List<DepartDTO> allUnits) {

		// �����ǰ�������
		int j = tblDepart.getRowCount();
		for (int i = 0; i < j; i++) {
			((DefaultTableModel) tblDepart.getModel()).removeRow(0);
		}
		// ��ʾ��ѯ���
		for (int i = 0; i < allUnits.size(); i++) {
			Vector vector = new Vector();
			vector.addElement(i + 1);// ���
			vector.addElement(allUnits.get(i).getUnit_name());// ��������
			vector.addElement(allUnits.get(i).getUnit_id());// ���ű��
			vector.addElement(allUnits.get(i).getHeader());// ���ż��
			vector.addElement(allUnits.get(i).getTelephone());// ��ϵ��ʽ
			vector.addElement(allUnits.get(i).getContact_person());// ��ע
			((DefaultTableModel) tblDepart.getModel()).addRow(vector);
		}
	}

}
