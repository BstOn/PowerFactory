package forms.trainingplan;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dto.TrainPlanDTO;
import dto.UserTrainDTO;
import services.TrainPlan;
import services.UserTrain;

public class TrainPlanMainForm extends JInternalFrame {
	private JTextField txtEmployeeName;
	private JLabel lblDepartCount;
	private JTable table;
	private JComboBox comboBox, comboBox_1, comboBox_2, comboBox_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainPlanMainForm frame = new TrainPlanMainForm();
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
	public TrainPlanMainForm() {
		setClosable(true);
		setBounds(100, 100, 700, 679);

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setToolTipText("wwfd");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u67E5\u8BE2\u6761\u4EF6",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		toolBar.add(panel);

		JLabel lblNewLabel = new JLabel("\u57F9\u8BAD\u8BA1\u5212\u5E74\u5EA6\uFF1A");
		panel.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "2018", "2019", "2020" }));
		panel.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("\u57F9\u8BAD\u4E13\u4E1A\uFF1A");
		panel.add(lblNewLabel_1);

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "\u7535\u6C14", "\u6C7D\u673A", "\u9505\u7089" }));
		panel.add(comboBox_1);

		JLabel label = new JLabel("\u57F9\u8BAD\u8BA1\u5212\u7C7B\u578B\uFF1A");
		panel.add(label);

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "\u65B0\u5458\u5DE5\u5165\u5382\u57F9\u8BAD",
				"\u4E2D\u5C42\u7BA1\u7406\u4EBA\u5458\u57F9\u8BAD", "\u73ED\u7EC4\u957F\u57F9\u8BAD" }));
		panel.add(comboBox_2);

		JLabel label_1 = new JLabel("\u5B8C\u6210\u60C5\u51B5\u57F9\u8BAD\uFF1A");
		panel.add(label_1);

		comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(
				new String[] { "\u672A\u57F9\u8BAD", "\u57F9\u8BAD\u4E2D", "\u5DF2\u57F9\u8BAD" }));
		panel.add(comboBox_3);

		// 查询培训计划
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchSome();
			}
		});
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar_2 = new JToolBar();
		panel_1.add(toolBar_2, BorderLayout.NORTH);

		// 创建培训计划
		JButton btnAdd = new JButton("创建");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddTrainPlanForm();
			}
		});

		toolBar_2.add(btnAdd);

		// 删除培训计划
		JButton btnDel = new JButton("删除");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRowCount() == 1) {
					int result = JOptionPane.showConfirmDialog(null, "确认删除?", "确认", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					// 用户确认后，执行删除操作，删除数据库中对应记录
					if (result == JOptionPane.OK_OPTION) {
						int row = table.getSelectedRow();
						String trainplanid = (String) table.getValueAt(row, 8);
						TrainPlan delTrain = new TrainPlan();
						// 包括item
						delTrain.trainPlanDel1(trainplanid);

						JOptionPane.showMessageDialog(null, "删除成功！");
						// 删除成功后，刷新 Table 中信息。
						((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
						searchAll();
					}

				} else
					JOptionPane.showMessageDialog(null, "未选中！");
			}
		});
		toolBar_2.add(btnDel);

		// 修改培训计划
		JButton btnMod = new JButton("修改");
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 如果选中了一行
				if (table.getSelectedRowCount() == 1) {
					// 如果培训未开始
					if (table.getValueAt(table.getSelectedRow(), 7).equals("未培训")) {
						// 如果还未安排学员
						if (table.getValueAt(table.getSelectedRow(), 6).equals("未安排")) {

							TrainPlan trainPlan = new TrainPlan();
							TrainPlanDTO trainPlanInfoDTO = new TrainPlanDTO();
							List<TrainPlanDTO> allTrainPlanItems = new ArrayList<TrainPlanDTO>();
							// 获得当前选中行的id

							String id = (String) table.getValueAt(table.getSelectedRow(), 8);

							trainPlanInfoDTO = trainPlan.queryTrainPlanInfoById(id);
							allTrainPlanItems = trainPlan.queryTrainPlanItemById(id);
							// 作为参数初始化修改界面
							UpdateTrainPlanForm updateTrainPlanForm = new UpdateTrainPlanForm(trainPlanInfoDTO,
									allTrainPlanItems, id);
							updateTrainPlanForm.main(null);
						} else
							JOptionPane.showMessageDialog(null, "已安排学员，无法进行修改");
					} else
						JOptionPane.showMessageDialog(null, "培训已开始或完成，无法进行修改");
				} else
					JOptionPane.showMessageDialog(null, "未选中一行");
			}
		});
		toolBar_2.add(btnMod);

		JButton button = new JButton("\u5237\u65B0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAll();
			}
		});
		toolBar_2.add(button);

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u5E8F\u53F7", "\u5E74\u5EA6", "培训计划名称", "\u57F9\u8BAD\u7C7B\u578B",
						"\u57F9\u8BAD\u4E13\u4E1A", "\u57F9\u8BAD\u65E5\u671F", "\u57F9\u8BAD\u4EBA\u6570",
						"\u5B8C\u6210\u60C5\u51B5", "id" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(25);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(75);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(125);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		// table.setEnabled(false);
		scrollPane.setViewportView(table);
		hideTableColumn(table, 8);
		searchAll();

		// 安排学员表格点击事件
		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 如果鼠标左键点击两下
				if (SwingUtilities.isLeftMouseButton(arg0) && arg0.getClickCount() == 2) {
					// 如果选中了一行
					if (table.getSelectedRowCount() == 1) {
						// 如果培训未开始
						if (table.getValueAt(table.getSelectedRow(), 7).equals("未培训")) {
							// 如果还未安排学员
							if (table.getValueAt(table.getSelectedRow(), 6).equals("未安排")) {
								// 获得当前选中行的id

								String trainplanid = (String) table.getValueAt(table.getSelectedRow(), 8);

								// 定义服务层对象
								TrainPlan trainPlan = new TrainPlan();
								// 定义数据传输对象
								TrainPlanDTO trainPlanInfoDTO = new TrainPlanDTO();
								List<TrainPlanDTO> allTrainPlanItems = new ArrayList<TrainPlanDTO>();

								trainPlanInfoDTO = trainPlan.queryTrainPlanInfoById(trainplanid);
								allTrainPlanItems = trainPlan.queryTrainPlanItemById(trainplanid);
								// 作为参数初始化修改界面
								ShowTrainPlanInfo updateTrainPlanForm = new ShowTrainPlanInfo(trainPlanInfoDTO,
										allTrainPlanItems, trainplanid);
								updateTrainPlanForm.main(null);
							} else
								JOptionPane.showMessageDialog(null, "已安排学员，无法进行修改");
						} else
							JOptionPane.showMessageDialog(null, "培训已开始或完成，无法进行修改");
					} else
						JOptionPane.showMessageDialog(null, "未选中一行");
				}
			}
		});
	}

	private void searchAll() {
		// 定义服务层对象
		TrainPlan trainPlan = new TrainPlan();
		UserTrain userTrain = new UserTrain();
		// 接收查询结果
		List<TrainPlanDTO> allTrainPlans = new ArrayList<TrainPlanDTO>();
		// 执行查询
		allTrainPlans = trainPlan.queryAll();

		// 清除当前表格数据
		int j = table.getRowCount();
		for (int i = 0; i < j; i++) {
			((DefaultTableModel) table.getModel()).removeRow(0);
		}

		// 显示查询结果
		for (int i = 0; i < allTrainPlans.size(); i++) {
			Vector vector = new Vector();
			vector.addElement(i + 1);

			vector.addElement(allTrainPlans.get(i).getTrain_plan_year());// 计划年度
			vector.addElement(allTrainPlans.get(i).getTrain_plan_name());// 计划名称
			vector.addElement(allTrainPlans.get(i).getTrain_plan_type());// 计划类型
			// 专业
			List<TrainPlanDTO> allTrainPlanItems = trainPlan
					.queryTrainPlanItemById(allTrainPlans.get(i).getTrain_plan_id());
			String zy = "";
			for (int k = 0; k < allTrainPlanItems.size(); k++) {
				zy = zy + allTrainPlanItems.get(k).getZy_name() + " ";
			}
			vector.addElement(zy);

			vector.addElement(allTrainPlans.get(i).getStart_time() + "—" + allTrainPlans.get(i).getEnd_time());// 起始时间
			// 人数
			List<UserTrainDTO> allUserTrains = userTrain.queryByTrain_id(allTrainPlans.get(i).getTrain_plan_id());
			if (allUserTrains.size() == 0)
				vector.addElement("未安排");
			else
				vector.addElement(allUserTrains.size());

			vector.addElement(allTrainPlans.get(i).getIs_finish());
			vector.addElement(allTrainPlans.get(i).getTrain_plan_id());

			// 计划状态
			((DefaultTableModel) table.getModel()).addRow(vector);
		}
	}

	public void searchSome() {
		// 定义服务层对象
		TrainPlan trainPlan = new TrainPlan();
		UserTrain userTrain = new UserTrain();
		// 接收查询结果
		List<TrainPlanDTO> allTrainPlans = new ArrayList<TrainPlanDTO>();

		// 定义数据传输对象
		TrainPlanDTO trainPlanInfoDTO = new TrainPlanDTO();
		trainPlanInfoDTO.setTrain_plan_year((String) comboBox.getSelectedItem());
		trainPlanInfoDTO.setTrain_plan_type((String) comboBox_2.getSelectedItem());
		trainPlanInfoDTO.setIs_finish((String) comboBox_3.getSelectedItem());
		String zy_name = (String) comboBox_1.getSelectedItem();
		// 执行查询
		allTrainPlans = trainPlan.querySome(trainPlanInfoDTO, zy_name);

		// 清除当前表格数据
		int j = table.getRowCount();
		for (int i = 0; i < j; i++) {
			((DefaultTableModel) table.getModel()).removeRow(0);
		}

		// 显示查询结果
		for (int i = 0; i < allTrainPlans.size(); i++) {
			Vector vector = new Vector();
			vector.addElement(i + 1);

			vector.addElement(allTrainPlans.get(i).getTrain_plan_year());// 计划年度
			vector.addElement(allTrainPlans.get(i).getTrain_plan_name());// 计划名称
			vector.addElement(allTrainPlans.get(i).getTrain_plan_type());// 计划类型
			// 专业
			List<TrainPlanDTO> allTrainPlanItems = trainPlan
					.queryTrainPlanItemById(allTrainPlans.get(i).getTrain_plan_id());
			String zy = "";
			for (int k = 0; k < allTrainPlanItems.size(); k++) {
				zy = zy + allTrainPlanItems.get(k).getZy_name() + " ";
			}
			vector.addElement(zy);

			vector.addElement(allTrainPlans.get(i).getStart_time() + "—" + allTrainPlans.get(i).getEnd_time());// 起始时间
			// 人数
			List<UserTrainDTO> allUserTrains = userTrain.queryByTrain_id(allTrainPlans.get(i).getTrain_plan_id());
			if (allUserTrains.size() == 0)
				vector.addElement("未安排");
			else
				vector.addElement(allUserTrains.size());
			// 计划状态
			vector.addElement(allTrainPlans.get(i).getIs_finish());
			vector.addElement(allTrainPlans.get(i).getTrain_plan_id());

			((DefaultTableModel) table.getModel()).addRow(vector);
		}
	}

	private void showAddTrainPlanForm() {
		AddTrainingPlanForm emf = new AddTrainingPlanForm();
		emf.setVisible(true);
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
