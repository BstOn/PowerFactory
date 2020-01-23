package forms.trainingplan;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dto.TrainPlanDTO;
import services.TrainPlan;
import util.CommUtil;
import util.DateChooser;

public class AddTrainingPlanForm extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JTextField StartTime;
	private JTextField EndTime;
	private JTextField Year;
	private JTextField Name;
	private String train_plan_type;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTrainingPlanForm frame = new AddTrainingPlanForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddTrainingPlanForm() {

		setTitle("\u521B\u5EFA\u57F9\u8BAD\u8BA1\u5212");
		setBounds(100, 100, 700, 450);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(22, 29, 642, 384);
		contentPane.add(panel);
		panel.setLayout(null);

		// ����
		JButton button = new JButton("\u4FDD\u5B58");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
				// ��ȡ����ƻ�����
				// stringתdate
				DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				TrainPlanDTO trainingPlanDTO = new TrainPlanDTO();
				trainingPlanDTO.setTrain_plan_id(CommUtil.getId());
				trainingPlanDTO.setTrain_plan_name(Name.getText());
				trainingPlanDTO.setTrain_plan_year(Year.getText());
				trainingPlanDTO.setTrain_plan_type(train_plan_type);
				try {
					trainingPlanDTO.setStart_time(format1.parse(StartTime.getText()));
					trainingPlanDTO.setEnd_time(format1.parse(EndTime.getText()));
				} catch (ParseException a) {
					a.printStackTrace();
				}
				trainingPlanDTO.setCreate_time(currentDate);
				trainingPlanDTO.setIs_finish("δ��ѵ");
				trainingPlanDTO.setRemark1("δ�ύ");

				TrainPlan trainingPlan = new TrainPlan();
				boolean saveSuccessful = trainingPlan.trainPlanSave(trainingPlanDTO);
				// ��ȡ��������
				int rowCount = table.getRowCount();
				for (int i = 0; i < rowCount; i++) {
					TrainPlanDTO trainingItemDTO = new TrainPlanDTO();
					trainingItemDTO.setZy_name((String) table.getValueAt(i, 0));
					trainingItemDTO.setTrain_purpose((String) table.getValueAt(i, 1));
					trainingItemDTO.setTrain_content((String) table.getValueAt(i, 2));
					trainingItemDTO.setClass_count((String) table.getValueAt(i, 3));
					trainingItemDTO.setTeacher((String) table.getValueAt(i, 4));
					trainingItemDTO.setTrain_item_id(CommUtil.getId());
					trainingItemDTO.setCreate_time(currentDate);
					trainingItemDTO.setTrain_plan_id(trainingPlanDTO.getTrain_plan_id());

					trainingPlan = new TrainPlan();
					saveSuccessful = trainingPlan.trainItemSave(trainingItemDTO) && saveSuccessful;
					if (!saveSuccessful) {
						System.out.println("save failed");
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
						break;
					}

				}
				if (saveSuccessful) {
					System.out.println("save successful");
					JOptionPane.showMessageDialog(null, "����ɹ���");
				}
			}
		});
		button.setFont(new Font("SimSun", Font.PLAIN, 12));
		button.setBounds(516, 61, 97, 23);
		panel.add(button);

		// ���
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel) table.getModel()).addRow(new Vector());
			}
		});
		btnNewButton.setBounds(411, 105, 97, 23);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("����", Font.PLAIN, 14));

		// ɾ��item
		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.addActionListener(new ActionListener() {// ����¼�
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// ���ѡ���е�����
				if (selectedRow != -1) // ����ѡ����
				{
					((DefaultTableModel) table.getModel()).removeRow(selectedRow); // ɾ����
				}
			}
		});
		btnNewButton_1.setBounds(516, 105, 97, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 14));

		Label label = new Label("\u5E74\u5EA6");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(32, 10, 64, 22);
		panel.add(label);

		Label label_1 = new Label("\u5F00\u59CB\u65F6\u95F4");
		label_1.setBounds(32, 61, 64, 22);
		panel.add(label_1);

		// ���
		Year = new JTextField();
		Year.setColumns(10);
		Year.setBounds(100, 11, 101, 21);
		panel.add(Year);

		// ����
		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(305, 10, 101, 21);
		panel.add(Name);

		// ��ʼʱ��
		StartTime = new JTextField();
		StartTime.setBounds(102, 61, 96, 22);
		panel.add(StartTime);

		DateChooser dateChooserStartTime = DateChooser.getInstance("yyyy-MM-dd");
		dateChooserStartTime.register(StartTime);
		// txtStartTime.setColumns(10);
		panel.add(StartTime);

		// ����ʱ��
		EndTime = new JTextField();
		EndTime.setBounds(305, 61, 101, 22);
		panel.add(EndTime);

		DateChooser dateChooserEndTime = DateChooser.getInstance("yyyy-MM-dd");
		dateChooserEndTime.register(EndTime);
		// txtEndTime.setColumns(10);
		panel.add(EndTime);

		Label label_8 = new Label("\u540D\u79F0");
		label_8.setBounds(235, 10, 64, 22);
		panel.add(label_8);

		Label label_9 = new Label("\u7ED3\u675F\u65F6\u95F4");
		label_9.setBounds(235, 61, 64, 22);
		panel.add(label_9);

		// ���
		table = new JTable();
		table.setBounds(0, 138, 642, 246);
		panel.add(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null } },
				new String[] { "רҵ", "��ѵĿ��", "��ѵ����", "��ʱ", "�ڿν�ʦ" }));

		table.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 138, 642, 217);
		panel.add(scrollPane);

		JLabel label_2 = new JLabel("\u7C7B\u578B");
		label_2.setBounds(425, 10, 47, 18);
		panel.add(label_2);

		// ��ѵ����������
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\u65B0\u5458\u5DE5\u5165\u5382\u57F9\u8BAD",
				"\u4E2D\u5C42\u7BA1\u7406\u4EBA\u5458\u57F9\u8BAD", "\u73ED\u7EC4\u957F\u57F9\u8BAD" }));
		comboBox.setBounds(483, 10, 112, 24);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					train_plan_type = (String) e.getItem();
					System.out.println("Selected item is " + train_plan_type);
				}
			}
		});
		panel.add(comboBox);

		// רҵ�����ؼ�
		String[] values = { "����", "����", "��¯", "��ˮ", "ȼ��" };
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JComboBox(values)));

	}
}
