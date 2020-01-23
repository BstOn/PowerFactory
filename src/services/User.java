package services;

import java.util.List;
import java.util.Vector;

import dto.KeyValue;
import dto.UserDTO;
import persistences.UserDAO;

public class User {
	private String user_name;
	private String password;

	public User(String name, String psw) {
		this.user_name = name;
		this.password = psw;
	}

	public User() {

	}

	public boolean findUser() {
		boolean result = false;

		result = new UserDAO().findUser(user_name, password);

		return result;
	}

	public Vector<Vector<Object>> getAllUsers() {
		List<UserDTO> allUsers = null;

		allUsers = new UserDAO().getAllUser();

		return list2vector(allUsers);
	}

	public Vector<Vector<Object>> getSomeUsers(String name, String sex) {
		List<UserDTO> allUsers = null;

		allUsers = new UserDAO().getSomeUser(name, sex);

		return list2vector(allUsers);
	}

	public Vector<Vector<Object>> getSomeUsersBysex(String sex) {
		List<UserDTO> allUsers = null;

		allUsers = new UserDAO().getSomeUsersBysex(sex);

		return list2vector(allUsers);
	}

	public Vector<Vector<Object>> getSomeUsersByname(String name) {
		List<UserDTO> allUsers = null;

		allUsers = new UserDAO().getSomeUsersByname(name);

		return list2vector(allUsers);
	}

	// 以userid获取修改人信息
	public List<UserDTO> getOneUsersByUserId(String delEmployID) {
		List<UserDTO> allUsers = null;

		allUsers = new UserDAO().getOneUsersByUserId(delEmployID);

		return allUsers;
	}

	// 以unitName获取userid 为查找该部门下的员工
	public List<UserDTO> getOneUsersByUnitName(String UnitName) {
		List<UserDTO> allUsers = null;

		allUsers = new UserDAO().getOneUsersByUnitName(UnitName);

		return allUsers;
	}

	private Vector<Vector<Object>> list2vector(List<UserDTO> allUser) {

		Vector<Vector<Object>> result = new Vector<Vector<Object>>();

		for (UserDTO u : allUser) {
			Vector<Object> v = new Vector<Object>();
			v.add(u.getUnit_name());
			v.add(new KeyValue<String, String>(u.getName(), u.getUser_id()));
			v.add(u.getBirthday());
			v.add(u.getCard_id());

			v.add(Duty.getName(u.getDuty()));
			v.add(u.getEmail());
			result.add(v);
		}

		return result;
	}

	public boolean saveUser(UserDTO user) {
		boolean saveSuccessful = false;
		saveSuccessful = new UserDAO().saveUser(user);
		return saveSuccessful;

	}

	// 保存员工
	public boolean saveAddEmployees(UserDTO user) {
		boolean saveSuccessful = false;
		saveSuccessful = new UserDAO().saveAddemployees(user);
		return saveSuccessful;

	}

	// 修改员工
	public boolean updateEmployees(UserDTO user) {
		boolean saveSuccessful = false;
		saveSuccessful = new UserDAO().updateEmployees(user);
		return saveSuccessful;

	}

	public boolean nameExisted(String userName) {

		return (new UserDAO().nameExisted(userName));

	}

	// 删除员工
	public boolean delEmployee(String delEmployID) {

		boolean saveSuccessful = false;
		saveSuccessful = new UserDAO().delEmployee(delEmployID);
		return saveSuccessful;
	}
}
