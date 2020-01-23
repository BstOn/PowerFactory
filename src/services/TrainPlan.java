package services;

import java.util.List;

import dto.TrainPlanDTO;
import persistences.TrainPlanDAO;

public class TrainPlan {

	public TrainPlan() {

	}

	// �����ƻ�
	public boolean trainPlanSave(TrainPlanDTO data) {
		boolean result = false;
		result = new TrainPlanDAO().trainPlanSave(data);
		return result;
	}

	// ��ӿ�ʱ��ʦ
	public boolean trainItemSave(TrainPlanDTO data) {
		boolean result = false;
		result = new TrainPlanDAO().trainItemSave(data);
		return result;
	}

	// ��ѯ������ѵ�ƻ�
	public List<TrainPlanDTO> querySome(TrainPlanDTO trainPlanInfoDTO, String zy_name) {

		return new TrainPlanDAO().querySome(trainPlanInfoDTO, zy_name);
	}

	// ��ѯ����
	public List<TrainPlanDTO> queryAll() {
		return new TrainPlanDAO().queryAll();
	}

	// ��ѯ��ѵ�ƻ���ϸ��Ϣͨ��id
	public List<TrainPlanDTO> queryTrainPlanItemById(String id) {
		return new TrainPlanDAO().queryTrainPlanItemById(id);
	}

	// ��ѯ��ϸ�ƻ��µ�Ա��
	public List<TrainPlanDTO> queryTrainPlanEmp(String Item_id, String plan_id) {
		return new TrainPlanDAO().queryTrainPlanEmp(Item_id, plan_id);
	}

	public boolean trainPlanDel(String data) {
		boolean result = false;
		result = new TrainPlanDAO().trainPlanDel(data);
		return result;
	}

	// ɾ����ѵ�ƻ� ����ѵ��ϸ��Ϣ����ѵѧԱ����ѵ�ɼ� ��������
	public boolean trainPlanDel1(String data) {
		boolean result = false;
		result = new TrainPlanDAO().trainPlanDel1(data);
		return result;
	}

	// ��ѯ��ѵ�ƻ�������Ϣͨ��id
	public TrainPlanDTO queryTrainPlanInfoById(String id) {
		return new TrainPlanDAO().queryTrainPlanInfoById(id);

	}

	// �޸���ѵ�ƻ�������Ϣ
	public boolean updateTrainPlanInfoById(TrainPlanDTO trainPlanInfoDTO, String id) {
		return new TrainPlanDAO().updateTrainPlanInfoById(trainPlanInfoDTO, id);
	}

	// �޸���ѵ�ƻ���ϸ��Ϣ
	public boolean updateTrainPlanItemById(TrainPlanDTO trainPlanItemDTO, String infoId, String itemId) {
		return new TrainPlanDAO().updateTrainPlanItemById(trainPlanItemDTO, infoId, itemId);
	}

	// ������ѵ�ƻ���ɣ�����ѧԺ�����
	public boolean IsFinish(String isfinish, String trainid) {
		return new TrainPlanDAO().IsFinish(isfinish, trainid);
	}

	// ������ѵ�ƻ��ɼ��Ƿ��ύ������ύ�󼴿�
	public boolean Is_submit(String remark1, String trainid) {
		return new TrainPlanDAO().Is_submit(remark1, trainid);
	}
}
