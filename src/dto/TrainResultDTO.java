package dto;

public class TrainResultDTO {
	private String train_emp_id;
	private String train_result_id;
	private String exam_result;
	private String attendance_result;
	private String total_result;

	public TrainResultDTO() {

	}

	public String getExam_result() {
		return exam_result;
	}

	public String getAttendance_result() {
		return attendance_result;
	}

	public String getTotal_result() {
		return total_result;
	}

	public void setTrain_emp_id(String train_emp_id) {
		this.train_emp_id = train_emp_id;
	}

	public void setTrain_result_id(String train_result_id) {
		this.train_result_id = train_result_id;
	}

	public void setExam_result(String exam_result) {
		this.exam_result = exam_result;
	}

	public void setAttendance_result(String attendance_result) {
		this.attendance_result = attendance_result;
	}

	public void setTotal_result(String total_result) {
		this.total_result = total_result;
	}

	public String getTrain_emp_id() {
		return train_emp_id;
	}

	public String getTrain_result_id() {
		return train_result_id;
	}

}
