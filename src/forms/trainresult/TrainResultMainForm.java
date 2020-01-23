package forms.trainresult;

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
import dto.TrainResultDTO;
import services.TrainPlan;
import services.TrainResult;
import services.UserTrain;

public class TrainResultMainForm extends JInternalFrame {
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
					TrainResultMainForm frame = new TrainResultMainForm();
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
	public TrainResultMainForm() {
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

		// ��ѯ��ѵ�ɼ�
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

		// ɾ���ɼ�
		JButton btnDel = new JButton("ɾ��");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRowCount() == 1) {
					int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��?", "ȷ��", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					// �û�ȷ�Ϻ�ִ��ɾ��������ɾ�����ݿ��ж�Ӧ��¼
					if (result == JOptionPane.OK_OPTION) {
						int row = table.getSelectedRow();
						String planid = (String) table.getValueAt(row, 8);
						System.out.println(planid);
						TrainResult delTrain = new TrainResult();
						List<TrainResultDTO> trainresultDTO = delTrain.queryTrainEmpByid(planid);
						for (int i = 0; i < trainresultDTO.size(); i++) {
							delTrain.deleteResultToTrainPlan(trainresultDTO.get(i));
						}
						TrainPlan trainp = new TrainPlan();
						String remark1 = "δ�ύ";
						trainp.Is_submit(remark1, planid);
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
						searchAll();
					}

				} else
					JOptionPane.showMessageDialog(null, "δѡ�У�");
			}
		});

		// �޸�
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ѡ����һ��
				if (table.getSelectedRowCount() == 1) {
					// ��õ�ǰѡ���е�id
					String planid = (String) table.getValueAt(table.getSelectedRow(), 8);

					// �����������
					TrainPlan trainPlan = new TrainPlan();
					// �������ݴ������
					TrainPlanDTO trainPlanInfoDTO = new TrainPlanDTO();
					List<TrainPlanDTO> allTrainPlanItems = new ArrayList<TrainPlanDTO>();

					trainPlanInfoDTO = trainPlan.queryTrainPlanInfoById(planid);
					allTrainPlanItems = trainPlan.queryTrainPlanItemById(planid);
					// ��Ϊ������ʼ���޸Ľ���
					ShowTrainResult updateTrainResultForm = new ShowTrainResult(trainPlanInfoDTO, allTrainPlanItems,
							planid);
					updateTrainResultForm.main(null);

				} else
					JOptionPane.showMessageDialog(null, "δѡ��һ��");
			}
		});
		toolBar_2.add(button_1);
		toolBar_2.add(btnDel);

		// ˢ��
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
				new String[] { "\u5E8F\u53F7", "\u5E74\u5EA6", "��ѵ�ƻ�����", "\u57F9\u8BAD\u7C7B\u578B",
						"\u57F9\u8BAD\u4E13\u4E1A", "\u57F9\u8BAD\u65E5\u671F", "����", "\u5B8C\u6210\u60C5\u51B5",
						"id" }) {
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
		hideTableColumn(table, 8);
		scrollPane.setViewportView(table);
		searchAll();

		// ѧԱ�ɼ�������¼�
		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// ����������������
				if (SwingUtilities.isLeftMouseButton(arg0) && arg0.getClickCount() == 2) {
					// ���ѡ����һ��
					if (table.getSelectedRowCount() == 1) {
						// ��õ�ǰѡ���е�id
						String planid = (String) table.getValueAt(table.getSelectedRow(), 8);

						// �����������
						TrainPlan trainPlan = new TrainPlan();
						// �������ݴ������
						TrainPlanDTO trainPlanInfoDTO = new TrainPlanDTO();
						List<TrainPlanDTO> allTrainPlanItems = new ArrayList<TrainPlanDTO>();

						trainPlanInfoDTO = trainPlan.queryTrainPlanInfoById(planid);
						allTrainPlanItems = trainPlan.queryTrainPlanItemById(planid);
						// ��Ϊ������ʼ���޸Ľ���
						ShowTrainResult updateTrainPlanForm = new ShowTrainResult(trainPlanInfoDTO, allTrainPlanItems,
								planid);
						updateTrainPlanForm.main(null);

					} else
						JOptionPane.showMessageDialog(null, "δѡ��һ��");
				}
			}
		});
	}

	private void searchAll() {
		// �����������
		TrainPlan trainPlan = new TrainPlan();
		UserTrain userTrain = new UserTrain();
		// ���ղ�ѯ���
		List<TrainPlanDTO> allTrainPlans = new ArrayList<TrainPlanDTO>();
		// ִ�в�ѯ
		allTrainPlans = trainPlan.queryAll();

		// �����ǰ�������
		int j = table.getRowCount();
		for (int i = 0; i < j; i++) {
			((DefaultTableModel) table.getModel()).removeRow(0);
		}

		// ��ʾ��ѯ���
		for (int i = 0; i < allTrainPlans.size(); i++) {
			Vector vector = new Vector();
			vector.addElement(i + 1);

			vector.addElement(allTrainPlans.get(i).getTrain_plan_year());// �ƻ����
			vector.addElement(allTrainPlans.get(i).getTrain_plan_name());// �ƻ�����
			vector.addElement(allTrainPlans.get(i).getTrain_plan_type());// �ƻ�����
			// רҵ
			List<TrainPlanDTO> allTrainPlanItems = trainPlan
					.queryTrainPlanItemById(allTrainPlans.get(i).getTrain_plan_id());
			String zy = "";
			for (int k = 0; k < allTrainPlanItems.size(); k++) {
				zy = zy + allTrainPlanItems.get(k).getZy_name() + " ";
			}
			vector.addElement(zy);

			vector.addElement(allTrainPlans.get(i).getStart_time() + "��" + allTrainPlans.get(i).getEnd_time());// ��ʼʱ��

			// �ɼ��Ƿ��ύ
			String remark1 = allTrainPlans.get(i).getRemark1();
			if (remark1.equals("���ύ")) {
				remark1 = "�鿴�ɼ�";
				vector.addElement(remark1);
			} else {
				remark1 = "¼��ɼ�";
				vector.addElement(remark1);
			}

			// �ƻ�״̬

			vector.addElement(allTrainPlans.get(i).getIs_finish());
			vector.addElement(allTrainPlans.get(i).getTrain_plan_id());

			((DefaultTableModel) table.getModel()).addRow(vector);
		}
	}

	public void searchSome() {
		// �����������
		TrainPlan trainPlan = new TrainPlan();
		UserTrain userTrain = new UserTrain();
		// ���ղ�ѯ���
		List<TrainPlanDTO> allTrainPlans = new ArrayList<TrainPlanDTO>();

		// �������ݴ������
		TrainPlanDTO trainPlanInfoDTO = new TrainPlanDTO();
		trainPlanInfoDTO.setTrain_plan_year((String) comboBox.getSelectedItem());
		trainPlanInfoDTO.setTrain_plan_type((String) comboBox_2.getSelectedItem());
		trainPlanInfoDTO.setIs_finish((String) comboBox_3.getSelectedItem());
		String zy_name = (String) comboBox_1.getSelectedItem();
		// ִ�в�ѯ
		allTrainPlans = trainPlan.querySome(trainPlanInfoDTO, zy_name);

		// �����ǰ�������
		int j = table.getRowCount();
		for (int i = 0; i < j; i++) {
			((DefaultTableModel) table.getModel()).removeRow(0);
		}

		// ��ʾ��ѯ���
		for (int i = 0; i < allTrainPlans.size(); i++) {
			Vector vector = new Vector();
			vector.addElement(i + 1);

			vector.addElement(allTrainPlans.get(i).getTrain_plan_year());// �ƻ����
			vector.addElement(allTrainPlans.get(i).getTrain_plan_name());// �ƻ�����
			vector.addElement(allTrainPlans.get(i).getTrain_plan_type());// �ƻ�����
			// רҵ
			List<TrainPlanDTO> allTrainPlanItems = trainPlan
					.queryTrainPlanItemById(allTrainPlans.get(i).getTrain_plan_id());
			String zy = "";
			for (int k = 0; k < allTrainPlanItems.size(); k++) {
				zy = zy + allTrainPlanItems.get(k).getZy_name() + " ";
			}
			vector.addElement(zy);

			vector.addElement(allTrainPlans.get(i).getStart_time() + "��" + allTrainPlans.get(i).getEnd_time());// ��ʼʱ��

			// �ɼ��Ƿ��ύ
			String remark1 = allTrainPlans.get(i).getRemark1();
			if (remark1.equals("���ύ")) {
				remark1 = "�鿴�ɼ�";
				vector.addElement(remark1);
			} else {
				remark1 = "¼��ɼ�";
				vector.addElement(remark1);
			}

			// �ƻ�״̬

			vector.addElement(allTrainPlans.get(i).getIs_finish());
			vector.addElement(allTrainPlans.get(i).getTrain_plan_id());

			((DefaultTableModel) table.getModel()).addRow(vector);
		}
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
