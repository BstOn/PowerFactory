package services;

import java.util.List;

import dto.TrainPlanDTO;
import persistences.TrainPlanDAO;

public class TrainPlan {

	public TrainPlan() {

	}

	// 创建计划
	public boolean trainPlanSave(TrainPlanDTO data) {
		boolean result = false;
		result = new TrainPlanDAO().trainPlanSave(data);
		return result;
	}

	// 添加课时教师
	public boolean trainItemSave(TrainPlanDTO data) {
		boolean result = false;
		result = new TrainPlanDAO().trainItemSave(data);
		return result;
	}

	// 查询条件培训计划
	public List<TrainPlanDTO> querySome(TrainPlanDTO trainPlanInfoDTO, String zy_name) {

		return new TrainPlanDAO().querySome(trainPlanInfoDTO, zy_name);
	}

	// 查询所有
	public List<TrainPlanDTO> queryAll() {
		return new TrainPlanDAO().queryAll();
	}

	// 查询培训计划详细信息通过id
	public List<TrainPlanDTO> queryTrainPlanItemById(String id) {
		return new TrainPlanDAO().queryTrainPlanItemById(id);
	}

	// 查询详细计划下的员工
	public List<TrainPlanDTO> queryTrainPlanEmp(String Item_id, String plan_id) {
		return new TrainPlanDAO().queryTrainPlanEmp(Item_id, plan_id);
	}

	public boolean trainPlanDel(String data) {
		boolean result = false;
		result = new TrainPlanDAO().trainPlanDel(data);
		return result;
	}

	// 删除培训计划 、培训详细信息、培训学员、培训成绩 级联操作
	public boolean trainPlanDel1(String data) {
		boolean result = false;
		result = new TrainPlanDAO().trainPlanDel1(data);
		return result;
	}

	// 查询培训计划基本信息通过id
	public TrainPlanDTO queryTrainPlanInfoById(String id) {
		return new TrainPlanDAO().queryTrainPlanInfoById(id);

	}

	// 修改培训计划基本信息
	public boolean updateTrainPlanInfoById(TrainPlanDTO trainPlanInfoDTO, String id) {
		return new TrainPlanDAO().updateTrainPlanInfoById(trainPlanInfoDTO, id);
	}

	// 修改培训计划详细信息
	public boolean updateTrainPlanItemById(TrainPlanDTO trainPlanItemDTO, String infoId, String itemId) {
		return new TrainPlanDAO().updateTrainPlanItemById(trainPlanItemDTO, infoId, itemId);
	}

	// 设置培训计划完成，安排学院后即完成
	public boolean IsFinish(String isfinish, String trainid) {
		return new TrainPlanDAO().IsFinish(isfinish, trainid);
	}

	// 设置培训计划成绩是否提交，点击提交后即可
	public boolean Is_submit(String remark1, String trainid) {
		return new TrainPlanDAO().Is_submit(remark1, trainid);
	}
}
