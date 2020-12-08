package model;
/**
 * ʵ���ࣺ��װѧ����Ϣ
 */
import java.util.Date;


public class Student {
	private String stuId = null;
	private String name = null;
	private String sex = "Ů";
	private String pwd = null;
	private String classId = null;
	private Date birthday = null;
	private String mobile = null;
	
	
	/**
	 * �޲ι��캯
	 * @Title: Student
	 * @Description: Student���캯��
	 * @author OriginalCoder
	 * @date 2020-12-04 09:18:04
	 */
	public Student() {
	}
	
	/**
	 * �������Ĺ��췽��
	 * @param stuId ѧ��
	 * @param name ����
	 * @param sex �Ա�
	 */
	public Student(String stuId, String name, String sex) {
		this.stuId = stuId;
		this.name = name;
		this.sex = sex;
	}
	/**
	 * ����ѧ��
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
	 * �����Ա�ֻ����������/Ů
	 */
	public void setSex(String sex) {
		if("��".equals(sex)||"Ů".equals(sex)) {
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
