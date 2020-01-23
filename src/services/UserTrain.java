package services;

import java.util.List;

import dto.UserTrainDTO;
import persistences.UserTrainDAO;

public class UserTrain {

	public UserTrain() {

	}

	// 查询一个培训计划下的所有学员
	public List<UserTrainDTO> queryByTrain_id(String train_id) {
		return new UserTrainDAO().queryByTrain_id(train_id);
	}

	// 判断员工是否已经加入指定培训
	public boolean employeeExisted(UserTrainDTO userTrainDTO) {
		return new UserTrainDAO().employeeExisted(userTrainDTO);
	}

	// 将员工添加至培训计划
	public boolean saveEmployeeToTrainPlan(UserTrainDTO userTrainDTO) {
		return new UserTrainDAO().saveEmployeeToTrainPlan(userTrainDTO);
	}

	// 将员工添加至培训计划
	public boolean modifyEmployeeToTrainPlan(UserTrainDTO userTrainDTO) {
		return new UserTrainDAO().saveEmployeeToTrainPlan(userTrainDTO);
	}

	// 删除学员通过员工id和基本培训计划id
	public boolean deleteByUser_idAndTrain_id(int user_id, int train_id) {
		return new UserTrainDAO().deleteByUser_idAndTrain_id(user_id, train_id);
	}

	// 修改学员通过员工id和基本培训计划id submit
	public boolean modifyByUser_idAndTrain_id(int user_id, int train_id) {
		return new UserTrainDAO().modifyByUser_idAndTrain_id(user_id, train_id);
	}

	// 保存学员的成绩
	public boolean saveGrade(UserTrainDTO userTrainDTO, int train_id, int user_id, int unit_id, String zy_name) {
		return new UserTrainDAO().saveGrade(userTrainDTO, train_id, user_id, unit_id, zy_name);
	}

	// 删除培训计划的员工通过培训计划id
	public boolean deleteByTrain_id(int train_id) {
		return new UserTrainDAO().deleteByTrain_id(train_id);
	}

	// 删除培训计划的员工通过员工id
	public boolean deleteByUser_id(int user_id) {

		return new UserTrainDAO().deleteByUser_id(user_id);
	}

	// 修改部门id通过员工id
	public boolean updateUnit_idByUser_id(int unit_id, int user_id) {
		return new UserTrainDAO().updateUnit_idByUser_id(unit_id, user_id);
	}

	// 删除培训计划的员工通过部门id
	public boolean deleteByUnit_id(int unit_id) {
		return new UserTrainDAO().deleteByUnit_id(unit_id);
	}

	// 查询详细计划下的员工
	public List<UserTrainDTO> queryTrainPlanEmp(String Item_id, String plan_id) {
		return new UserTrainDAO().queryTrainPlanEmp(Item_id, plan_id);
	}

}