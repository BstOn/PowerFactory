package persistences;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dto.UserTrainDTO;
import util.CommUtil;
import util.DBUtil;

public class UserTrainDAO {
	private Connection conn;
	private DBUtil dbUtil;

	public UserTrainDAO() {
		dbUtil = new DBUtil();
		conn = dbUtil.getConnection();
	}

	// 查询一个培训计划下的所有学员
	public List<UserTrainDTO> queryByTrain_id(String train_id) {
		List<UserTrainDTO> allUserTrains = new ArrayList<UserTrainDTO>();
		QueryRunner qr = new QueryRunner();

		String sql = "select * from train_emp where train_plan_id=? ";// order by zy_name ASC

		try {
			allUserTrains = qr.query(conn, sql, new BeanListHandler<UserTrainDTO>(UserTrainDTO.class), train_id);
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

	// 判断员工是否已经加入指定培训
	public boolean employeeExisted(UserTrainDTO userTrainDTO) {
		boolean result;
		UserTrainDTO userTrainDTO_1 = null;
		QueryRunner qr = new QueryRunner();

		String sql = "select * from train_emp where emp_id=?  and train_plan_id=? ";

		try {
			userTrainDTO_1 = qr.query(conn, sql, new BeanHandler<UserTrainDTO>(UserTrainDTO.class),
					userTrainDTO.getUser_id(), userTrainDTO.getTrain_id());
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
		result = (userTrainDTO_1 == null) ? false : true;
		return result;
	}

	// 将员工添加至培训计划
	public boolean saveEmployeeToTrainPlan(UserTrainDTO userTrainDTO) {
		boolean result;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String train_emp_id = CommUtil.getId();

		String sql = "insert into train_emp(train_emp_id,train_plan_id,train_item_id,emp_id,create_time) values(?,?,?,?,?)";

		try {
			insertRows = qr.update(conn, sql, train_emp_id, userTrainDTO.getTrain_plan_id(),
					userTrainDTO.getTrain_item_id(), userTrainDTO.getUser_id(), userTrainDTO.getCreate_time());
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

	// 将员工添加至培训计划 modify
	public boolean modifyEmployeeToTrainPlan(UserTrainDTO userTrainDTO) {
		boolean result;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "update user_train set values(?,?,?,?)";

		try {
			insertRows = qr.update(conn, sql, userTrainDTO.getUser_id(), userTrainDTO.getUnit_id(),
					userTrainDTO.getTrain_id(), userTrainDTO.getZy_name());
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

	// 删除学员通过员工id和基本培训计划id
	public boolean deleteByUser_idAndTrain_id(int user_id, int train_id) {
		boolean result;
		int deleteRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "delete from user_train where user_id=? and train_id=?";

		try {
			deleteRows = qr.update(conn, sql, user_id, train_id);
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
		result = (deleteRows == 0) ? false : true;
		return result;
	}

	// 修改学员通过员工id和基本培训计划id
	public boolean modifyByUser_idAndTrain_id(int user_id, int train_id) {
		boolean result;
		int modifyRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "update user_train set is_submit=1 where user_id=? and train_id=?";

		try {
			modifyRows = qr.update(conn, sql, user_id, train_id);
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
		result = (modifyRows == 0) ? false : true;
		return result;
	}

	// 保存学员的成绩
	public boolean saveGrade(UserTrainDTO userTrainDTO, int train_id, int user_id, int unit_id, String zy_name) {
		boolean result;
		int updateRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "update  user_train set exam_grade=?,attendance_grade=?,is_submit=? where  train_id=? and user_id=? and unit_id=? and zy_name=?";

		try {
			updateRows = qr.update(conn, sql, userTrainDTO.getExam_grade(), userTrainDTO.getAttendance_grade(),
					userTrainDTO.getIs_submit(), train_id, user_id, unit_id, zy_name);
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
		result = (updateRows == 0) ? false : true;
		return result;
	}

	// 删除培训计划的员工通过培训计划id
	public boolean deleteByTrain_id(int train_id) {
		boolean result;
		int deleteRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "delete from user_train where train_id=?";

		try {
			deleteRows = qr.update(conn, sql, train_id);
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
		result = (deleteRows == 0) ? false : true;
		return result;
	}

	// 删除培训计划的员工通过员工id
	public boolean deleteByUser_id(int user_id) {
		boolean result;
		int deleteRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "delete from user_train where user_id=?";

		try {
			deleteRows = qr.update(conn, sql, user_id);
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
		result = (deleteRows == 0) ? false : true;
		return result;
	}

	// 修改部门id通过员工id
	public boolean updateUnit_idByUser_id(int unit_id, int user_id) {
		boolean result;
		int updateRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "update  user_train set unit_id=? where user_id=?";

		try {
			updateRows = qr.update(conn, sql, unit_id, user_id);
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
		result = (updateRows == 0) ? false : true;
		return result;
	}

	// 删除培训计划的员工通过部门id
	public boolean deleteByUnit_id(int unit_id) {
		boolean result;
		int deleteRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "delete from user_train where unit_id=?";

		try {
			deleteRows = qr.update(conn, sql, unit_id);
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
		result = (deleteRows == 0) ? false : true;
		return result;
	}

	// 根据planid itemid 查询学员
	public List<UserTrainDTO> queryTrainPlanEmp(String Item_id, String plan_id) {
		List<UserTrainDTO> allTrainPlanemps = new ArrayList<UserTrainDTO>();
		QueryRunner qr = new QueryRunner();

		String sql = "select * from train_emp where train_plan_id=? and train_item_id=? ";
		try {
			allTrainPlanemps = qr.query(conn, sql, new BeanListHandler<UserTrainDTO>(UserTrainDTO.class), plan_id,
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
}
