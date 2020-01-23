package services;

import java.util.List;

import dto.UserTrainDTO;
import persistences.UserTrainDAO;

public class UserTrain {

	public UserTrain() {

	}

	// ��ѯһ����ѵ�ƻ��µ�����ѧԱ
	public List<UserTrainDTO> queryByTrain_id(String train_id) {
		return new UserTrainDAO().queryByTrain_id(train_id);
	}

	// �ж�Ա���Ƿ��Ѿ�����ָ����ѵ
	public boolean employeeExisted(UserTrainDTO userTrainDTO) {
		return new UserTrainDAO().employeeExisted(userTrainDTO);
	}

	// ��Ա���������ѵ�ƻ�
	public boolean saveEmployeeToTrainPlan(UserTrainDTO userTrainDTO) {
		return new UserTrainDAO().saveEmployeeToTrainPlan(userTrainDTO);
	}

	// ��Ա���������ѵ�ƻ�
	public boolean modifyEmployeeToTrainPlan(UserTrainDTO userTrainDTO) {
		return new UserTrainDAO().saveEmployeeToTrainPlan(userTrainDTO);
	}

	// ɾ��ѧԱͨ��Ա��id�ͻ�����ѵ�ƻ�id
	public boolean deleteByUser_idAndTrain_id(int user_id, int train_id) {
		return new UserTrainDAO().deleteByUser_idAndTrain_id(user_id, train_id);
	}

	// �޸�ѧԱͨ��Ա��id�ͻ�����ѵ�ƻ�id submit
	public boolean modifyByUser_idAndTrain_id(int user_id, int train_id) {
		return new UserTrainDAO().modifyByUser_idAndTrain_id(user_id, train_id);
	}

	// ����ѧԱ�ĳɼ�
	public boolean saveGrade(UserTrainDTO userTrainDTO, int train_id, int user_id, int unit_id, String zy_name) {
		return new UserTrainDAO().saveGrade(userTrainDTO, train_id, user_id, unit_id, zy_name);
	}

	// ɾ����ѵ�ƻ���Ա��ͨ����ѵ�ƻ�id
	public boolean deleteByTrain_id(int train_id) {
		return new UserTrainDAO().deleteByTrain_id(train_id);
	}

	// ɾ����ѵ�ƻ���Ա��ͨ��Ա��id
	public boolean deleteByUser_id(int user_id) {

		return new UserTrainDAO().deleteByUser_id(user_id);
	}

	// �޸Ĳ���idͨ��Ա��id
	public boolean updateUnit_idByUser_id(int unit_id, int user_id) {
		return new UserTrainDAO().updateUnit_idByUser_id(unit_id, user_id);
	}

	// ɾ����ѵ�ƻ���Ա��ͨ������id
	public boolean deleteByUnit_id(int unit_id) {
		return new UserTrainDAO().deleteByUnit_id(unit_id);
	}

	// ��ѯ��ϸ�ƻ��µ�Ա��
	public List<UserTrainDTO> queryTrainPlanEmp(String Item_id, String plan_id) {
		return new UserTrainDAO().queryTrainPlanEmp(Item_id, plan_id);
	}

}