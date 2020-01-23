package persistences;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dto.TrainPlanDTO;
import util.DBUtil;

public class TrainPlanDAO {
	private DBUtil dbUtil;
	private Connection conn;

	public TrainPlanDAO() {
		dbUtil = new DBUtil();
		conn = dbUtil.getConnection();
	}

//保存培训计划内容
	public boolean trainItemSave(TrainPlanDTO data) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "insert into train_plan_item(TRAIN_PLAN_ID,TRAIN_ITEM_ID,ZY_NAME,TRAIN_PURPOSE,TRAIN_CONTENT,CLASS_COUNT,TEACHER,CREATE_TIME) VALUES(?,?,?,?,?,?,?,?);";

		try {

			insertRows = qr.update(conn, sql, data.getTrain_plan_id(), data.getTrain_item_id(), data.getZy_name(),
					data.getTrain_purpose(), data.getTrain_content(), data.getClass_count(), data.getTeacher(),
					data.getCreate_time());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		result = (insertRows == 1) ? true : false;
		System.out.println("*******" + result);
		return result;

	}

//保存培训计划实体
	public boolean trainPlanSave(TrainPlanDTO data) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "insert into train_plan_info(TRAIN_PLAN_ID,TRAIN_PLAN_NAME,TRAIN_PLAN_YEAR,TRAIN_PLAN_TYPE,START_TIME,END_TIME,IS_FINISH,CREATE_TIME,remark1) VALUES(?,?,?,?,?,?,?,?,?);";

		try {

			insertRows = qr.update(conn, sql, data.getTrain_plan_id(), data.getTrain_plan_name(),
					data.getTrain_plan_year(), data.getTrain_plan_type(), data.getStart_time(), data.getEnd_time(),
					data.getIs_finish(), data.getCreate_time(), data.getRemark1());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		result = (insertRows == 1) ? true : false;
		System.out.println("*******" + result);
		return result;

	}

	// 查询所有培训计划
	public List<TrainPlanDTO> queryAllTrainPlan() {
		List<TrainPlanDTO> allTrainPlans = new ArrayList<TrainPlanDTO>();
		QueryRunner qr = new QueryRunner();

		String sql = "select distinct train_plan_info.train_plan_id,train_plan_name,train_plan_type,train_plan_year,start_time,end_time,train_plan_info.create_time,is_finish"
				+ " from train_plan_info,train_plan_item where train_plan_info.train_plan_id=train_plan_item.train_plan_id";

		try {
			allTrainPlans = qr.query(conn, sql, new BeanListHandler<TrainPlanDTO>(TrainPlanDTO.class));

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
		return allTrainPlans;
	}

	// 查询所有满足条件的培训计划
	public List<TrainPlanDTO> querySome(TrainPlanDTO trainPlanInfoDTO, String zy_name) {
		List<TrainPlanDTO> allTrainPlans = new ArrayList<TrainPlanDTO>();
		QueryRunner qr = new QueryRunner();

//		String sql = "select  train_plan_info.train_plan_id,train_plan_name,train_plan_type,train_plan_year,start_time,end_time,train_plan_info.create_time,is_finish"
//				+ " from train_plan_info,train_plan_item where  train_plan_info.train_plan_id=train_plan_item.train_plan_id"
//				+ " and train_plan_info.train_plan_name=? and train_plan_info.train_plan_year=? and train_plan_item.zy_name=? and train_plan_info.is_finish=?";

		String sql = "select  train_plan_info.*,train_plan_item.*"
				+ " from train_plan_info,train_plan_item where  train_plan_info.train_plan_id=train_plan_item.train_plan_id"
				+ " and train_plan_info.train_plan_type=? and train_plan_info.train_plan_year=? and train_plan_item.zy_name=? and train_plan_info.is_finish=?";

		try {
			allTrainPlans = qr.query(conn, sql, new BeanListHandler<TrainPlanDTO>(TrainPlanDTO.class),
					trainPlanInfoDTO.getTrain_plan_type(), trainPlanInfoDTO.getTrain_plan_year(), zy_name,
					trainPlanInfoDTO.getIs_finish());

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

		return allTrainPlans;
	}

//查询
	public List<TrainPlanDTO> queryAll() {
		List<TrainPlanDTO> allTrainPlans = new ArrayList<TrainPlanDTO>();
		QueryRunner qr = new QueryRunner();

		// String sql = "select
		// train_plan_info.train_plan_id,train_plan_name,train_plan_type,train_plan_year,start_time,end_time,train_plan_info.create_time,is_finish"
		// + " from train_plan_info,train_plan_item where
		// train_plan_info.train_plan_id=train_plan_item.train_plan_id ";

		String sql = "select  train_plan_info.train_plan_id,train_plan_name,train_plan_type,train_plan_year,start_time,end_time,train_plan_info.create_time,is_finish,remark1"
				+ " from train_plan_info ";

		try {
			allTrainPlans = qr.query(conn, sql, new BeanListHandler<TrainPlanDTO>(TrainPlanDTO.class));

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

		return allTrainPlans;
	}

	// 删除培训计划以及item
	public boolean trainPlanDel(String trainPlanId) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "DELETE FROM train_plan_item, train_plan_info USING train_plan_item INNER JOIN train_plan_info"
				+ "  WHERE train_plan_item.train_plan_id=train_plan_info.train_plan_id and train_plan_info.train_plan_id=? ; ";

		try {

			insertRows = qr.update(conn, sql, trainPlanId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		result = (insertRows == 1) ? true : false;
		System.out.println("*******" + result);
		return result;

	}

	// 删除培训计划以及item
	public boolean trainPlanDel1(String trainPlanId) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "DELETE FROM  train_plan_info " + "  WHERE train_plan_info.train_plan_id=? ; ";

		try {

			insertRows = qr.update(conn, sql, trainPlanId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		result = (insertRows == 1) ? true : false;
		System.out.println("*******" + result);
		return result;

	}

	// 查询培训计划基本信息通过id
	public TrainPlanDTO queryTrainPlanInfoById(String id) {
		TrainPlanDTO trainPlanInfoDTO = null;
		QueryRunner qr = new QueryRunner();

		String sql = "select train_plan_name,train_plan_year,train_plan_type,start_time,end_time,remark1 from train_plan_info where train_plan_id=?";

		try {
			trainPlanInfoDTO = qr.query(conn, sql, new BeanHandler<TrainPlanDTO>(TrainPlanDTO.class), id);

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

		return trainPlanInfoDTO;

	}

	// 查询培训计划详细信息集通过基本培训id
	public List<TrainPlanDTO> queryTrainPlanItemById(String id) {
		List<TrainPlanDTO> allTrainPlanItems = new ArrayList<TrainPlanDTO>();
		QueryRunner qr = new QueryRunner();

		String sql = "select * from train_plan_item where train_plan_id=? order by zy_name";
		try {
			allTrainPlanItems = qr.query(conn, sql, new BeanListHandler<TrainPlanDTO>(TrainPlanDTO.class), id);
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

	// 根据planid itemid 查询学员
	public List<TrainPlanDTO> queryTrainPlanEmp(String Item_id, String plan_id) {
		List<TrainPlanDTO> allTrainPlanItems = new ArrayList<TrainPlanDTO>();
		QueryRunner qr = new QueryRunner();

		String sql = "select * from train_emp where train_plan_id=? and train_item_id=? ";
		try {
			allTrainPlanItems = qr.query(conn, sql, new BeanListHandler<TrainPlanDTO>(TrainPlanDTO.class), plan_id,
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

		return allTrainPlanItems;

	}

	// 修改培训计划基本信息通过id
	public boolean updateTrainPlanInfoById(TrainPlanDTO trainPlanInfoDTO, String id) {
		boolean result;
		int updateRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "update train_plan_info set train_plan_name=?,train_plan_year=?,start_time=?,end_time=?,"
				+ "create_time=? where train_plan_id=?";

		try {
			updateRows = qr.update(conn, sql, trainPlanInfoDTO.getTrain_plan_name(),
					trainPlanInfoDTO.getTrain_plan_year(), trainPlanInfoDTO.getStart_time(),
					trainPlanInfoDTO.getEnd_time(), trainPlanInfoDTO.getCreate_time(), id);
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

	// 修改培训计划详细信息集通过id和子id
	public boolean updateTrainPlanItemById(TrainPlanDTO trainPlanItemDTO, String infoId, String itemId) {
		boolean result;
		int updateRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "update train_plan_item set zy_name=?,train_purpose=?,train_content=?,class_count=?,teacher=? where "
				+ "train_item_id=? and train_plan_id=?";
		try {
			updateRows = qr.update(conn, sql, trainPlanItemDTO.getZy_name(), trainPlanItemDTO.getTrain_purpose(),
					trainPlanItemDTO.getTrain_content(), trainPlanItemDTO.getClass_count(),
					trainPlanItemDTO.getTeacher(), itemId, infoId);
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

	public boolean IsFinish(String isfinish, String id) {
		boolean result;
		int updateRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "update train_plan_info set is_finish=? where train_plan_id=?";

		try {
			updateRows = qr.update(conn, sql, isfinish, id);
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

	public boolean Is_submit(String remark1, String id) {
		boolean result;
		int updateRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "update train_plan_info set remark1=? where train_plan_id=?";

		try {
			updateRows = qr.update(conn, sql, remark1, id);
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
}
