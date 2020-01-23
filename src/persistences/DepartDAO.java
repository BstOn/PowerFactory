package persistences;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dto.DepartDTO;
import util.DBUtil;

public class DepartDAO {
	private DBUtil dbUtil;
	private Connection conn;

	public DepartDAO() {
		dbUtil = new DBUtil();
		conn = dbUtil.getConnection();
	}

	public int getDepartCount() {
		int departCount = 0;
		String sql = "select count(*) from t_base_unit_info";
		QueryRunner qr = new QueryRunner();

		try {
			departCount = (qr.query(conn, sql, new ScalarHandler<Long>())).intValue();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departCount;
	}

	public List<DepartDTO> getAllDeparts() {
		List<DepartDTO> allDeparts = new ArrayList<DepartDTO>();
		String sql = "select * from t_base_unit_info order by unit_id";
		QueryRunner qr = new QueryRunner();

		try {
			allDeparts = qr.query(conn, sql, new BeanListHandler<DepartDTO>(DepartDTO.class));

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

		return allDeparts;
	}

	public List<DepartDTO> getSomeDeparts(String unitid, String unitname) {
		List<DepartDTO> allDeparts = new ArrayList<DepartDTO>();
		String sql = "select * from t_base_unit_info where unit_id=? and unit_name=?";
		QueryRunner qr = new QueryRunner();

		try {
			allDeparts = qr.query(conn, sql, new BeanListHandler<DepartDTO>(DepartDTO.class), unitid, unitname);

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

		return allDeparts;
	}

	public DepartDTO getDepartByName(String name) {
		DepartDTO depart = new DepartDTO();
		QueryRunner qr = new QueryRunner();
		String sql = "select * from t_base_unit_info where unit_name = ?";

		try {
			depart = qr.query(conn, sql, new BeanHandler<DepartDTO>(DepartDTO.class), name);
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

		return depart;
	}

	public DepartDTO getDepartById(String id) {
		DepartDTO depart = new DepartDTO();
		QueryRunner qr = new QueryRunner();
		String sql = "select * from t_base_unit_info where unit_id = ?";

		try {
			depart = qr.query(conn, sql, new BeanHandler<DepartDTO>(DepartDTO.class), id);
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

		return depart;
	}

	// 删除部门
	public boolean delUnit(String unit_id) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String sql = "delete from t_base_unit_info where unit_id = ? ;";

		try {
			insertRows = qr.update(conn, sql, unit_id);

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

	// 添加部门
	public boolean addUnit(DepartDTO departDTO) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String sql = "insert into t_base_unit_info (UNIT_ID,UP_UNIT_ID,UNIT_NAME,CONTACT_PERSON,HEADER) values(?,?,?,?,?)";

		try {
			insertRows = qr.update(conn, sql, departDTO.getUnit_id(), departDTO.getUp_unit_id(),
					departDTO.getUnit_name(), departDTO.getContact_person(), departDTO.getHeader());

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

	// 修改部门
	public boolean updateDepart(DepartDTO departDTO) {
		boolean result = false;
		int insertRows = 0;
		QueryRunner qr = new QueryRunner();
		String sql = "update t_base_unit_info set UNIT_NAME=?,CONTACT_PERSON=?,HEADER=?,TELEPHONE=?  where unit_id=?";
		try {
			insertRows = qr.update(conn, sql, departDTO.getUnit_name(), departDTO.getContact_person(),
					departDTO.getHeader(), departDTO.getTelephone(), departDTO.getUnit_id());

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

}
