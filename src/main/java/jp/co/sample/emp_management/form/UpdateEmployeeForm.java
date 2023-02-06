package jp.co.sample.emp_management.form;




import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * 従業員情報更新時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
public class UpdateEmployeeForm {
	/** id */
	private String id;
	
	/** ↓自分で追加 */
	/** 従業員名 */
	@NotBlank(message="名前の形式が不正です")
	@Size(max = 20)
	private String name;
	
	/** 性別 */
	@NotBlank
	private String gender;
	
	/** 入社日 */
	@NotNull(message="入社日の形式が不正です")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	
	
	/** メールアドレス */
	@NotBlank
	@Size(max = 50)
	@Email
	private String mailAddress;
	
	/** 郵便番号 */
	@NotBlank
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "{error.zipCode}")
	private String zipCode;
	
	/** 住所 */
	@NotBlank
	@Size(max = 100)
	private String address;
	
	/** 電話番号 */
	@NotBlank
	@Pattern(regexp = "^[0-9]{1}[0-9¥-]{1,13}[0-9]{1}$", message = "{error.telephone}")
	private String telephone;
	
	/** 給料 */
	@NotNull
	@Range(min = 0, max = 1000000)
	private Integer salary;
	
	/** 特性 */
	@NotBlank
	private String characteristics;
	
	/** 扶養人数 */
	@Pattern(regexp = "^[0-9]+$", message = "扶養人数は数値で入力してください")
	private String dependentsCount;

	/**
	 * IDを数値として返します.
	 * 
	 * @return 数値のID
	 */
	public Integer getIntId() {
		return Integer.parseInt(id);
	}

	/**
	 * 扶養人数を数値として返します.
	 * 
	 * @return 数値の扶養人数
	 */
	public Integer getIntDependentsCount() {
		return Integer.parseInt(dependentsCount);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	


}
