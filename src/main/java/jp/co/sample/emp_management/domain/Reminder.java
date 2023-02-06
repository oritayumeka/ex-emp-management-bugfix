package jp.co.sample.emp_management.domain;

import java.util.Date;

public class Reminder {
	
	private Integer id;
	
	private Integer administratorId;
	
	private String randomStr;
	
	private Date registDate;
	
	private Integer delFlg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}

	public String getRandomStr() {
		return randomStr;
	}

	public void setRandomStr(String randomStr) {
		this.randomStr = randomStr;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

	@Override
	public String toString() {
		return "Reminder [id=" + id + ", administratorId=" + administratorId + ", randomStr=" + randomStr
				+ ", registDate=" + registDate + ", delFlg=" + delFlg + "]";
	}

}
