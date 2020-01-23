package dto;

import java.util.Date;

public class UserTrainDTO {
	private String unit_id;
	private String user_id;
	private String train_id;
	private String train_plan_id;
	private String train_item_id;
	private String emp_id;

	private Date create_time;
	private String zy_name;
	private String exam_grade;
	private String attendance_grade;
	private int is_submit;

	public UserTrainDTO() {

	}

	public String getUnit_id() {
		return unit_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getTrain_id() {
		return train_id;
	}

	public String getTrain_plan_id() {
		return train_plan_id;
	}

	public String getTrain_item_id() {
		return train_item_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public String getZy_name() {
		return zy_name;
	}

	public String getExam_grade() {
		return exam_grade;
	}

	public String getAttendance_grade() {
		return attendance_grade;
	}

	public int getIs_submit() {
		return is_submit;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setTrain_id(String train_id) {
		this.train_id = train_id;
	}

	public void setTrain_plan_id(String train_plan_id) {
		this.train_plan_id = train_plan_id;
	}

	public void setTrain_item_id(String train_item_id) {
		this.train_item_id = train_item_id;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public void setZy_name(String zy_name) {
		this.zy_name = zy_name;
	}

	public void setExam_grade(String exam_grade) {
		this.exam_grade = exam_grade;
	}

	public void setAttendance_grade(String attendance_grade) {
		this.attendance_grade = attendance_grade;
	}

	public void setIs_submit(int is_submit) {
		this.is_submit = is_submit;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

}
