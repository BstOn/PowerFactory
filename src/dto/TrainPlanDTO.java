package dto;

import java.util.Date;

public class TrainPlanDTO {
	private String train_plan_id;
	private String train_plan_name;
	private String train_plan_type;
	private String train_plan_year;
	private Date start_time;
	private Date end_time;
	private Date create_time;
	private String is_finish;
	private String train_item_id;
	private String zy_name;
	private String train_purpose;
	private String train_content;
	private String class_count;
	private String teacher;
	private String remark1;

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public TrainPlanDTO() {

	}

	@Override
	public String toString() {
		return "TrainingPlanDTO [train_plan_id=" + train_plan_id + ", train_plan_name=" + train_plan_name
				+ ", train_plan_type=" + train_plan_type + ", train_plan_year=" + train_plan_year + ", start_time="
				+ start_time + ", end_time=" + end_time + ", create_time=" + create_time + ", is_finish=" + is_finish
				+ ", train_item_id=" + train_item_id + ", zy_name=" + zy_name + ", train_purpose=" + train_purpose
				+ ", train_content=" + train_content + ", class_count=" + class_count + ", teacher=" + teacher + "]";
	}

	public String getTrain_plan_id() {
		return train_plan_id;
	}

	public String getTrain_plan_name() {
		return train_plan_name;
	}

	public String getTrain_plan_type() {
		return train_plan_type;
	}

	public String getTrain_plan_year() {
		return train_plan_year;
	}

	public Date getStart_time() {
		return start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public String getIs_finish() {
		return is_finish;
	}

	public String getTrain_item_id() {
		return train_item_id;
	}

	public String getZy_name() {
		return zy_name;
	}

	public String getTrain_purpose() {
		return train_purpose;
	}

	public String getTrain_content() {
		return train_content;
	}

	public String getClass_count() {
		return class_count;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTrain_plan_id(String train_plan_id) {
		this.train_plan_id = train_plan_id;
	}

	public void setTrain_plan_name(String train_plan_name) {
		this.train_plan_name = train_plan_name;
	}

	public void setTrain_plan_type(String train_plan_type) {
		this.train_plan_type = train_plan_type;
	}

	public void setTrain_plan_year(String train_plan_year) {
		this.train_plan_year = train_plan_year;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public void setIs_finish(String is_finish) {
		this.is_finish = is_finish;
	}

	public void setTrain_item_id(String train_item_id) {
		this.train_item_id = train_item_id;
	}

	public void setZy_name(String zy_name) {
		this.zy_name = zy_name;
	}

	public void setTrain_purpose(String train_purpose) {
		this.train_purpose = train_purpose;
	}

	public void setTrain_content(String train_content) {
		this.train_content = train_content;
	}

	public void setClass_count(String class_count) {
		this.class_count = class_count;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

}