package forms.trainingplan;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dto.TrainPlanDTO;
import services.TrainPlan;
import util.DateChooser;
import util.WindowsUtil;

public class UpdateTrainPlanForm {

	private static JFrame frame;
	private JPanel panel;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox, comboBox_1;
	private JTextField textField, textField_1, txtyear, txtname;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// UpdateTrainPlanForm window = new UpdateTrainPlanForm();
					new WindowsUtil(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateTrainPlanForm(TrainPlanDTO trainPlanInfoDTO, List<TrainPlanDTO> allTrainPlanItems, String id) {
		initialize(trainPlanInfoDTO, allTrainPlanItems, id);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 * 
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(TrainPlanDTO trainPlanInfoDTO, List<TrainPlanDTO> allTrainPlanItems, final String id) {
		frame = new JFrame();
		frame.setTitle("\u57F9\u8BAD\u8BA1\u5212\u4FEE\u6539");
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new JPanel();
		frame.setContentPane(panel);
		panel.setLayout(null);

		// ���水ť�¼�
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �������ڱ༭״̬
				if (!table.isEditing()) {
					boolean updateSuccessful = false;// ��־�޸��Ƿ�ɹ�
					// �����������
					TrainPlan trainPlan = new TrainPlan();
					// �������ݴ����߶���
					TrainPlanDTO trainPlanInfoDTO_1 = new TrainPlanDTO();
					TrainPlanDTO trainPlanItemDTO_1 = new TrainPlanDTO();

					trainPlanInfoDTO_1.setTrain_plan_name((String) txtname.getText());
					// trainPlanInfoDTO_1.setTrain_plan_type((String) comboBox_1.getSelectedItem());
					trainPlanInfoDTO_1.setTrain_plan_year((String) txtyear.getText());
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						trainPlanInfoDTO_1.setStart_time(dateformat.parse(textField.getText()));
						trainPlanInfoDTO_1.setEnd_time(dateformat.parse(textField_1.getText()));
						trainPlanInfoDTO_1.setCreate_time(dateformat.parse(dateformat.format(new Date())));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					updateSuccessful = trainPlan.updateTrainPlanInfoById(trainPlanInfoDTO_1, id);// �޸Ļ����ƻ���Ϣ�����޸����ݣ������ƻ�id��
					if (updateSuccessful) {

						for (int i = 0; i < table.getRowCount(); i++) {
							trainPlanItemDTO_1.setZy_name((String) table.getValueAt(i, 0));
							trainPlanItemDTO_1.setTrain_purpose((String) table.getValueAt(i, 1));
							trainPlanItemDTO_1.setTrain_content((String) table.getValueAt(i, 2));
							trainPlanItemDTO_1.setClass_count((String) table.getValueAt(i, 3));
							trainPlanItemDTO_1.setTeacher((String) table.getValueAt(i, 4));
							trainPlanItemDTO_1.setTrain_item_id((String) table.getValueAt(i, 5));
							// �޸���ϸ��Ϣ�����޸����ݣ������ƻ�id����ϸ�ƻ�id��
							updateSuccessful = trainPlan.updateTrainPlanItemById(trainPlanItemDTO_1, id,
									(String) table.getValueAt(i, 5));// allItemId.get(i).getTrain_item_id()
							if (!updateSuccessful) {
								JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
								break;
							}
							if (updateSuccessful && i == table.getRowCount() - 1) {
								JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
							}
						}
					} else
						JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
				} else
					JOptionPane.showMessageDialog(null, "���˳��༭״̬��");
			}
		});
		btnNewButton.setBounds(282, 380, 78, 23);
		panel.add(btnNewButton);

		// ���ذ�ťʱ��
		JButton button = new JButton("����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button.setBounds(451, 380, 78, 23);
		panel.add(button);

		JLabel label = new JLabel("\u5E74\u5EA6");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label.setForeground(Color.BLACK);
		label.setBounds(70, 49, 54, 15);
		panel.add(label);

		// ���ѡ���

		txtyear = new JTextField();
		txtyear.setText(trainPlanInfoDTO.getTrain_plan_year());
		txtyear.setBounds(145, 47, 122, 21);
		panel.add(txtyear);

		JLabel label_1 = new JLabel("\u57F9\u8BAD\u7C7B\u578B");
		label_1.setBounds(321, 50, 54, 15);
		panel.add(label_1);

		// ��ѵ����ѡ���
		txtname = new JTextField();
		txtname.setText(trainPlanInfoDTO.getTrain_plan_name());
		txtname.setBounds(407, 48, 122, 21);
		panel.add(txtname);

		JLabel label_2 = new JLabel("\u5F00\u59CB\u65F6\u95F4");
		label_2.setBounds(70, 89, 54, 15);
		panel.add(label_2);

		JLabel label_3 = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		label_3.setBounds(321, 89, 54, 15);
		panel.add(label_3);

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		// ��ʼʱ��
		textField = new JTextField();
		textField.setBounds(145, 86, 122, 21);
		textField.setText(dateformat.format(trainPlanInfoDTO.getStart_time()));
		panel.add(textField);
		textField.setColumns(10);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(textField);
		panel.add(textField);

		// ����ʱ��
		textField_1 = new JTextField();
		textField_1.setBounds(407, 86, 122, 21);
		textField_1.setText(dateformat.format(trainPlanInfoDTO.getEnd_time()));
		panel.add(textField_1);
		textField_1.setColumns(10);
		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser2.register(textField_1);
		panel.add(textField_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(70, 159, 459, 210);
		panel.add(scrollPane_1);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u4E13\u4E1A", "\u5185\u5BB9",
				"\u5907\u6CE8", "\u603B\u8BFE\u65F6", "\u6388\u8BFE\u4EBA", "id" }));
		scrollPane_1.setViewportView(table);
		hideTableColumn(table, 5);
		String[] values = { "����", "����", "��¯" };

		table.setRowHeight(20);

		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JComboBox(values)));
		// table.getColumnModel().getColumn(0).setCellRenderer(new
		// MyComboBoxRenderer(values));

		// ��ʼ�����
		for (int i = 0; i < allTrainPlanItems.size(); i++) {
			Vector vector = new Vector();
			vector.addElement(allTrainPlanItems.get(i).getZy_name()); // רҵ
			vector.addElement(allTrainPlanItems.get(i).getTrain_purpose());// Ŀ��
			vector.addElement(allTrainPlanItems.get(i).getTrain_content());// ����
			vector.addElement(allTrainPlanItems.get(i).getClass_count());// �ܿ�ʱ
			vector.addElement(allTrainPlanItems.get(i).getTeacher());// ��ʦ
			vector.addElement(allTrainPlanItems.get(i).getTrain_item_id());
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
