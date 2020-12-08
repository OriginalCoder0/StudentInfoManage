package model;
/**
 * 实体类：封装学生信息
 */
import java.util.Date;


public class Student {
	private String stuId = null;
	private String name = null;
	private String sex = "女";
	private String pwd = null;
	private String classId = null;
	private Date birthday = null;
	private String mobile = null;
	
	
	/**
	 * 无参构造函
	 * @Title: Student
	 * @Description: Student构造函数
	 * @author OriginalCoder
	 * @date 2020-12-04 09:18:04
	 */
	public Student() {
	}
	
	/**
	 * 带参数的构造方法
	 * @param stuId 学号
	 * @param name 姓名
	 * @param sex 性别
	 */
	public Student(String stuId, String name, String sex) {
		this.stuId = stuId;
		this.name = name;
		this.sex = sex;
	}
	/**
	 * 设置学号
	 */
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	/**
	 * 设置性别：只允许输入男/女
	 */
	public void setSex(String sex) {
		if("男".equals(sex)||"女".equals(sex)) {
			this.sex = sex;
		}
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
