package persistences;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dto.TrainResultDTO;
import util.CommUtil;
import util.DBUtil;

public class TrainResultDAO {
	private DBUtil dbUtil;
	private Connection conn;

	public TrainResultDAO() {
		dbUtil = new DBUtil();
		conn = dbUtil.getConnection();
	}

	// 根据planid itemid 查询学员
	public List<TrainResultDTO> queryTrainPlanEmp(String Item_id, String plan_id) {
		List<TrainResultDTO> allTrainPlanemps = new ArrayList<TrainResultDTO>();
		QueryRunner qr = new QueryRunner();

		String sql = "select * from train_result where train_plan_id=? and train_item_id=? ";
		try {
			allTrainPlanemps = qr.query(conn, sql, new BeanListHandler<TrainResultDTO>(TrainResultDTO.class), plan_id,
					Item_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return allTrainPlanemps;

	}

	public TrainResultDTO queryTrainEmpIdByEmpId(String user_id, String item_id) {
		TrainResultDTO allUserTrains = new TrainResultDTO();
		QueryRunner qr = new QueryRunner();

		String sql = "select * from train_emp where emp_id=? and train_item_id=?";// order by zy_name ASC

		try {
			allUserTrains = qr.query(conn, sql, new BeanHandler<TrainResultDTO>(TrainResultDTO.class), user_id,
					item_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return allUserTrains;
	}

	public TrainResultDTO queryTrainEmpResultByEmpId(String train_emp_id) {
		TrainResultDTO allUserTrains = new TrainResultDTO();
		QueryRunner qr = new QueryRunner();

		String sql = "select * from train_result where train_emp_id=? ";// order by zy_name ASC

		try {
			allUserTrains = qr.query(conn, sql, new BeanHandler<TrainResultDTO>(TrainResultDTO.class), train_emp_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return allUserTrains;
	}

//初始化成绩
	public boolean saveResultToTrainPlan(TrainResultDTO addresultDTO) {
		boolean result;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String train_result_id = CommUtil.getId();

		String sql = "insert into train_result(train_result_id,train_emp_id,exam_result,attendance_result,total_result) values(?,?,?,?,?)";

		try {
			insertRows = qr.update(conn, sql, train_result_id, addresultDTO.getTrain_emp_id(),
					addresultDTO.getExam_result(), addresultDTO.getAttendance_result(), addresultDTO.getTotal_result());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		result = (insertRows == 0) ? false : true;
		return result;
	}

	// 更新成绩
	public boolean updateResultToTrainPlan(TrainResultDTO addresultDTO) {

		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String sql = "update train_result "
				+ "set exam_result=?,attendance_result=?,total_result=?  where train_emp_id=? ;";

		try {
			insertRows = qr.update(conn, sql, addresultDTO.getExam_result(), addresultDTO.getAttendance_result(),
					addresultDTO.getTotal_result(), addresultDTO.getTrain_emp_id());

			System.out.println("*******" + insertRows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		result = (insertRows == 1) ? true : false;
		System.out.println("*******" + result);
		return result;

	}

	// 更新成绩为0
	public boolean deleteResultToTrainPlan(TrainResultDTO addresultDTO) {

		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String sql = "update train_result "
				+ "set exam_result=\"0\",attendance_result=\"0\",total_result=\"0\"  where train_emp_id=? ;";

		try {
			insertRows = qr.update(conn, sql, addresultDTO.getTrain_emp_id());

			System.out.println("*******" + insertRows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		result = (insertRows == 1) ? true : false;
		System.out.println("*******" + result);
		return result;

	}

	// 根据planid 查询学员
	public List<TrainResultDTO> queryTrainEmpByid(String plan_id) {
		List<TrainResultDTO> allTrainPlanItems = new ArrayList<TrainResultDTO>();
		QueryRunner qr = new QueryRunner();

		String sql = "select * from train_emp where train_plan_id=?  ";
		try {
			allTrainPlanItems = qr.query(conn, sql, new BeanListHandler<TrainResultDTO>(TrainResultDTO.class), plan_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return allTrainPlanItems;

	}
}
