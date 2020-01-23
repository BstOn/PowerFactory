package services;

import java.util.List;

import dto.TrainResultDTO;
import persistences.TrainResultDAO;

public class TrainResult {
	public TrainResult() {

	}

	// 查询详细计划下的员工
	public List<TrainResultDTO> queryTrainPlanEmp(String Item_id, String plan_id) {
		return new TrainResultDAO().queryTrainPlanEmp(Item_id, plan_id);
	}

	public TrainResultDTO queryTrainEmpIdByEmpId(String user_id, String item) {
		return new TrainResultDAO().queryTrainEmpIdByEmpId(user_id, item);
	}

	public boolean saveResultToTrainPlan(TrainResultDTO addresultDTO) {

		return new TrainResultDAO().saveResultToTrainPlan(addresultDTO);

	}

	public TrainResultDTO queryTrainEmpResultByEmpId(String train_emp_id) {
		return new TrainResultDAO().queryTrainEmpResultByEmpId(train_emp_id);
	}

	public boolean updateResultToTrainPlan(TrainResultDTO addresultDTO) {

		return new TrainResultDAO().updateResultToTrainPlan(addresultDTO);

	}

	public boolean deleteResultToTrainPlan(TrainResultDTO addresultDTO) {

		return new TrainResultDAO().deleteResultToTrainPlan(addresultDTO);

	}

	// 查询基本计划下的员工
	public List<TrainResultDTO> queryTrainEmpByid(String plan_id) {
		return new TrainResultDAO().queryTrainEmpByid(plan_id);
	}
}
