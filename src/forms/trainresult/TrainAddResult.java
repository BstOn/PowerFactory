package forms.trainresult;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dto.TrainResultDTO;
import dto.UserDTO;
import dto.UserTrainDTO;
import services.TrainResult;
import services.User;
import services.UserTrain;
import util.WindowsUtil;

public class TrainAddResult {
	private static JFrame frame;
	private JTable table_1;
	String name = null;

	@SuppressWarnings("rawtypes")
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new WindowsUtil(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TrainAddResult(String itemid, String trainid) {
		initialize(itemid, trainid);
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(String itemid, String trainid) {
		frame = new JFrame();
		frame.setTitle("\u57F9\u8BAD\u8BA1\u5212\u8BE6\u7EC6\u4FE1\u606F");
		frame.setBounds(100, 100, 650, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(36, 36, 570, 257);
		panel.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "id", "序号", "姓名", "部门", "出勤成绩", "考试成绩", "总成绩" }) {
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3)
					return false;
				return true;
			}
		});
		// table_1.getColumnModel().getColumn(0).setPreferredWidth(1);
		this.hideTableColumn(table_1, 0);
		scrollPane_1.setViewportView(table_1);
		List<UserTrainDTO> trainplanemp = new UserTrain().queryTrainPlanEmp(itemid, trainid);
		for (int i = 0; i < trainplanemp.size(); i++) {
			List<UserDTO> user = new User().getOneUsersByUserId(trainplanemp.get(i).getEmp_id());
			Vector vector = new Vector();

			TrainResultDTO trainempdto = new TrainResult().queryTrainEmpIdByEmpId(trainplanemp.get(i).getEmp_id(),
					itemid);
			TrainResultDTO trainemp = new TrainResult().queryTrainEmpResultByEmpId(trainempdto.getTrain_emp_id());
			vector.addElement(trainempdto.getTrain_emp_id());
			vector.addElement(i + 1);
			vector.addElement(user.get(0).getName());
			vector.addElement(user.get(0).getUnit_name());
			vector.addElement(trainemp.getAttendance_result());
			vector.addElement(trainemp.getExam_result());
			vector.addElement(trainemp.getTotal_result());
			((DefaultTableModel) table_1.getModel()).addRow(vector);
		}

		// 保存
		Button button_3 = new Button("\u4FDD\u5B58");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainResult addEmp = new TrainResult();
				TrainResultDTO addEmpDTO = new TrainResultDTO();
				for (int i = 0; i < table_1.getRowCount(); i++) {
					addEmpDTO.setTrain_emp_id((String) table_1.getValueAt(i, 0));
					addEmpDTO.setAttendance_result((String) table_1.getValueAt(i, 4));
					addEmpDTO.setExam_result((String) table_1.getValueAt(i, 5));
					addEmpDTO.setTotal_result((String) table_1.getValueAt(i, 6));

					addEmp.updateResultToTrainPlan(addEmpDTO);
				}

			}
		});
		button_3.setBounds(395, 321, 72, 22);
		panel.add(button_3);

		// 退出
		Button button_4 = new Button("\u9000\u51FA");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_4.setBounds(497, 321, 72, 22);
		panel.add(button_4);

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
