package dto;

import java.util.Calendar;
import java.util.Date;

public class UserDTO {
	private String user_id;
	private String user_name;
	private String sex;
	private String name;
	private String card_id;
	private Date birthday;
	private String unit_id;
	private String unit_name;
	private String telphone;
	private String duty;
	private String tecduty;
	private String email;
	private String mobile;
	private String password;

	public UserDTO() {

	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		String format = "%tF";
		System.out.println(d);
		System.out.println(String.format(format, d));

	}

	public String toString() {
		String format = "name:%s,sex:%s,cardId:%s,birthday:%tF,unitId:%s,tel:%s,duty:%s,telduty:%s";
		String result = String.format(format, this.name, this.sex, this.card_id, this.birthday, this.unit_id,
				this.telphone, this.duty, this.tecduty);
		return result;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getTecduty() {
		return tecduty;
	}

	public void setTecduty(String tecduty) {
		this.tecduty = tecduty;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserDTO(String user_name, String password) {
		this.user_name = user_name;
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
