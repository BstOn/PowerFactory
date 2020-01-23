package forms.trainingplan;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dto.TrainPlanDTO;
import util.WindowsUtil;

public class ShowTrainPlanInfo {

	private static JFrame frame;
	private JPanel panel;
	@SuppressWarnings("rawtypes")
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
	public ShowTrainPlanInfo(TrainPlanDTO trainPlanInfoDTO, List<TrainPlanDTO> allTrainPlanItems, String id) {
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
	private void initialize(final TrainPlanDTO trainPlanInfoDTO, List<TrainPlanDTO> allTrainPlanItems,
			final String id) {
		frame = new JFrame();
		frame.setTitle("\u57F9\u8BAD\u8BA1\u5212\u8BE6\u7EC6\u4FE1\u606F");
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new JPanel();
		frame.setContentPane(panel);
		panel.setLayout(null);

		// 返回按钮时间
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button.setBounds(451, 380, 78, 23);
		panel.add(button);

		JLabel label = new JLabel("\u5E74\u5EA6");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setForeground(Color.BLACK);
		label.setBounds(70, 49, 54, 15);
		panel.add(label);

		// 年度选择框

		txtyear = new JTextField();
		txtyear.setText(trainPlanInfoDTO.getTrain_plan_year());
		txtyear.setBounds(145, 47, 122, 21);
		txtyear.setEditable(false);
		panel.add(txtyear);

		JLabel label_1 = new JLabel("\u57F9\u8BAD\u540D\u79F0");
		label_1.setBounds(321, 50, 54, 15);
		panel.add(label_1);

		// 培训类型选择框
		txtname = new JTextField();
		txtname.setText(trainPlanInfoDTO.getTrain_plan_name());
		txtname.setBounds(407, 48, 122, 21);
		txtname.setEditable(false);
		panel.add(txtname);

		JLabel label_2 = new JLabel("\u5F00\u59CB\u65F6\u95F4");
		label_2.setBounds(70, 89, 54, 15);
		panel.add(label_2);

		JLabel label_3 = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		label_3.setBounds(321, 89, 54, 15);
		panel.add(label_3);

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		// 开始时间
		textField = new JTextField();
		textField.setBounds(145, 86, 122, 21);
		textField.setText(dateformat.format(trainPlanInfoDTO.getStart_time()));
		panel.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		panel.add(textField);

		// 结束时间
		textField_1 = new JTextField();
		textField_1.setBounds(407, 86, 122, 21);
		textField_1.setText(dateformat.format(trainPlanInfoDTO.getEnd_time()));
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		panel.add(textField_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(70, 159, 459, 210);
		panel.add(scrollPane_1);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u4E13\u4E1A", "\u5185\u5BB9",
				"\u5907\u6CE8", "\u603B\u8BFE\u65F6", "\u6388\u8BFE\u4EBA", "操作", "id" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane_1.setViewportView(table);

		// 初始化表格
		for (int i = 0; i < allTrainPlanItems.size(); i++) {
			String todo = new String();
			todo = "安排学员";
			Vector vector = new Vector();
			vector.addElement(allTrainPlanItems.get(i).getZy_name()); // 专业
			vector.addElement(allTrainPlanItems.get(i).getTrain_purpose());// 目的
			vector.addElement(allTrainPlanItems.get(i).getTrain_content());// 内容
			vector.addElement(allTrainPlanItems.get(i).getClass_count());// 总课时
			vector.addElement(allTrainPlanItems.get(i).getTeacher());// 讲师
			vector.addElement(todo);
			vector.addElement(allTrainPlanItems.get(i).getTrain_item_id());
			((DefaultTableModel) table.getModel()).addRow(vector);
		}
		hideTableColumn(table, 6);
		// 安排学员表格点击事件
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				// 如果鼠标左键点击两下
				if (SwingUtilities.isLeftMouseButton(arg0) && arg0.getClickCount() == 2) {
					// 如果选中了一行
					if (table.getSelectedRowCount() == 1) {

						// 获得当前选中行的Item_id

						String Item_id = (String) table.getValueAt(table.getSelectedRow(), 6);

						// 作为参数初始化修改界面
						TrainAddEmp trainAndEmp = new TrainAddEmp(Item_id, id);
						trainAndEmp.setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "未选中一行");
				}
			}
		});
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
