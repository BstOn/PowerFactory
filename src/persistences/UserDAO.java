package persistences;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dto.UserDTO;
import util.CommUtil;
import util.DBUtil;

public class UserDAO {
	private DBUtil dbUtil;
	private Connection conn;

	public UserDAO() {
		dbUtil = new DBUtil();
		conn = dbUtil.getConnection();
	}

	public boolean findUser(String name, String psw) {
		boolean result = false;
		QueryRunner qr = new QueryRunner();
		UserDTO userDTO = null;

		String sql = "select * from t_base_manager where user_name=? and password=?";

		try {
			userDTO = qr.query(conn, sql, new BeanHandler<UserDTO>(UserDTO.class), name, psw);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (userDTO != null) {
			result = true;
			System.out.println(userDTO.getUser_name() + "  " + userDTO.getPassword());
		}

		return result;
	}

	public List<UserDTO> getAllUser() {
		QueryRunner qr = new QueryRunner();
		List<UserDTO> allUsers = null;

		String sql = "select user_id,NAME,sex,card_id,birthday,userInfo.unit_id,depart.unit_name,userInfo.telephone,duty,TECDUTY,userInfo.email from t_base_user_info userInfo,t_base_unit_info depart where userInfo.unit_id = depart.unit_id;";
		try {
			allUsers = qr.query(conn, sql, new BeanListHandler<UserDTO>(UserDTO.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allUsers;

	}

	// 获取员工姓名、性别查询结果
	public List<UserDTO> getSomeUser(String name, String sex) {
		QueryRunner qr = new QueryRunner();
		List<UserDTO> someUsers = null;
		Object[] params = new Object[] { name, sex };
		String sql = "select user_id,NAME,sex,card_id,birthday,userInfo.unit_id,depart.unit_name,userInfo.telephone,duty,TECDUTY,userInfo.email from t_base_user_info userInfo,t_base_unit_info depart where userInfo.unit_id = depart.unit_id and userInfo.name = ? and userInfo.sex = ?";
		try {
			someUsers = qr.query(conn, sql, new BeanListHandler<UserDTO>(UserDTO.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return someUsers;

	}

//性别查询
	public List<UserDTO> getSomeUsersBysex(String sex) {
		QueryRunner qr = new QueryRunner();
		List<UserDTO> someUsers = null;
		Object[] params = new Object[] { sex };
		String sql = "select user_id,NAME,sex,card_id,birthday,userInfo.unit_id,depart.unit_name,userInfo.telephone,duty,TECDUTY,userInfo.email from t_base_user_info userInfo,t_base_unit_info depart where userInfo.unit_id = depart.unit_id  and userInfo.sex = ?";
		try {
			someUsers = qr.query(conn, sql, new BeanListHandler<UserDTO>(UserDTO.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return someUsers;

	}

	// 姓名查询

	public List<UserDTO> getSomeUsersByname(String name) {
		QueryRunner qr = new QueryRunner();
		List<UserDTO> someUsers = null;
		Object[] params = new Object[] { name };
		String sql = "select user_id,NAME,sex,card_id,birthday,userInfo.unit_id,depart.unit_name,userInfo.telephone,duty,TECDUTY,userInfo.email from t_base_user_info userInfo,t_base_unit_info depart where userInfo.unit_id = depart.unit_id  and userInfo.name = ?";
		try {
			someUsers = qr.query(conn, sql, new BeanListHandler<UserDTO>(UserDTO.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return someUsers;

	}

	public boolean saveUser(UserDTO user) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();

		String sql = "insert into t_base_manager (user_id,user_name,password) values(?,?,?)";
		String userId = CommUtil.getId();

		try {
			insertRows = qr.update(conn, sql, userId, user.getUser_name(), user.getPassword());

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

	public Boolean nameExisted(String userName) {
		boolean existed = false;
		UserDTO userDTO = null;

		QueryRunner qr = new QueryRunner();
		String sql = "select user_id,user_name,password from t_base_manager where user_name = ?";
		try {
			userDTO = qr.query(conn, sql, new BeanHandler<UserDTO>(UserDTO.class), userName);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (userDTO != null) {
			System.out.println("inputed name:" + userName + "****");
			System.out.println("query name:" + userDTO.getUser_name() + "****");
		}
		existed = userDTO == null ? false : true;
		return existed;
	}

	// 保存添加员工
	public boolean saveAddemployees(UserDTO user) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String sql = "insert into t_base_user_info (user_id,user_name,sex,name,card_id,birthday,unit_id,password,telephone,duty,tecduty) values(?,?,?,?,?,?,?,?,?,?,?)";
		String userId = CommUtil.getId();

		try {
			insertRows = qr.update(conn, sql, userId, user.getUser_name(), user.getSex(), user.getName(),
					user.getCard_id(), user.getBirthday(), user.getUnit_id(), user.getPassword(), user.getTelphone(),
					user.getDuty(), user.getTecduty());

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

	// 修改员工信息
	public boolean updateEmployees(UserDTO user) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String sql = "update t_base_user_info " + "set unit_id=?,name=?,birthday=?,card_id=?,duty=?,email=? "
				+ "where user_id=?; ";

		try {
			insertRows = qr.update(conn, sql, user.getUnit_id(), user.getName(), user.getBirthday(), user.getCard_id(),
					user.getDuty(), user.getEmail(), user.getUser_id());

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

	// 删除员工
	public boolean delEmployee(String delEmployID) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String sql = "delete from t_base_user_info where user_id = ? ;";

		try {
			insertRows = qr.update(conn, sql, delEmployID);

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

	// 以uerid找出该人所有信息

	public List<UserDTO> getOneUsersByUserId(String delEmployID) {

		QueryRunner qr = new QueryRunner();
		List<UserDTO> someUsers = null;

		String sql = "select t_base_unit_info.UNIT_NAME,t_base_user_info.NAME,t_base_user_info.birthday,"
				+ "t_base_user_info.card_id,t_base_user_info.duty,t_base_user_info.email,t_base_user_info.user_id,t_base_user_info.unit_id "
				+ "from t_base_user_info ,t_base_unit_info  "
				+ "where t_base_unit_info.unit_id =t_base_user_info.unit_id " + "and t_base_user_info.user_id= ? ;";
		try {
			someUsers = qr.query(conn, sql, new BeanListHandler<UserDTO>(UserDTO.class), delEmployID);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return someUsers;
	}

	public List<UserDTO> getOneUsersByUnitName(String UnitName) {
		QueryRunner qr = new QueryRunner();
		List<UserDTO> someUsers = null;

		String sql = "select t_base_unit_info.UNIT_ID,t_base_user_info.NAME,t_base_user_info.birthday,"
				+ "t_base_user_info.card_id,t_base_user_info.duty,t_base_user_info.TECDUTY,t_base_user_info.email,t_base_user_info.user_id,t_base_user_info.unit_id "
				+ "from t_base_user_info ,t_base_unit_info  "
				+ "where t_base_unit_info.unit_id =t_base_user_info.unit_id and t_base_unit_info.unit_name= ? ;";
		try {
			someUsers = qr.query(conn, sql, new BeanListHandler<UserDTO>(UserDTO.class), UnitName);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return someUsers;
	}

}
