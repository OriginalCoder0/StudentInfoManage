/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ����Դ������
 *
 */
public class MySqlDataSource {
	
    // �����Ա�������������ݿ�������������Ϣ��
	private String dbsIP = null;   //�������ݿ��������IP
	private String dbsPort = null; // ���ݿ�������Ķ˿ں�
	private String dbName = null;  // Ҫ���ӵ����ݿ�����
	private String dbUser = null;  // ���ݿ�ĺϷ��û�
	private String dbPwd = null;   // ��¼���ݿ������
	
	private static Connection cnA = null; // ��̬�����������ݿ����Ӷ���
	
	public MySqlDataSource() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // ��� MySQL ������
	}
	
	/**
	 * �������Ĺ��췽����
	 * @param dbsIP ���ݿ��������IP
	 * @param dbsPort ���ݿ�������Ķ˿ں�
	 * @param dbName ���ݿ�����
	 * @throws ClassNotFoundException 
	 * */
	public MySqlDataSource(String dbsIP, String dbsPort, String dbName) 
			                throws NullPointerException, 
			                       ClassNotFoundException {
		this.setDbsIP(dbsIP);
		this.setDbsPort(dbsPort);
		this.setDbName(dbName);
		Class.forName("com.mysql.cj.jdbc.Driver"); // ��� MySQL ������
	}
	
	public String getDbsIP() {
		return dbsIP;
	}
	
	public void setDbsIP(String dbsIP) throws NullPointerException {
		if(dbsIP==null || dbsIP.trim().isEmpty()==true) {
			throw new NullPointerException("���ݿ��������IP��ַ����Ϊ�գ�");
		}
		this.dbsIP = dbsIP;
	}
	
	public String getDbsPort() {
		return dbsPort;
	}	
	
	public void setDbsPort(String dbsPort) throws NullPointerException {
		if(dbsPort==null || dbsPort.trim().isEmpty()==true) {
			throw new NullPointerException("���ݿ�������Ķ˿ںŲ���Ϊ�գ�");
		}
		this.dbsPort = dbsPort;
	}
	
	public String getDbName() {
		return dbName;
	}
	
	
	public void setDbName(String dbName) throws NullPointerException {
		if(dbName==null || dbName.trim().isEmpty()==true) {
			throw new NullPointerException("���ݿ����Ʋ���Ϊ�գ�");
			//trim()������ȥ���ַ����˶���Ŀո�
		}
		this.dbName = dbName;
	}
	
	public String getDbUser() {
		return dbUser;
	}
	
	public void setDbUser(String dbUser) {
		if(dbUser==null || dbUser.trim().isEmpty()==true) {
			dbUser = "root";
		}
		this.dbUser = dbUser;
	}
	
	public String getDbPwd() {
		return dbPwd;
	}
	
	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
	}		
	
	/**��ȡ������Դ�����Ӷ���
	 * @throws SQLException */
	public Connection getConnection() throws SQLException {
		if(cnA==null) {  
			StringBuffer sbUrl = new StringBuffer();	//�ַ���������
			sbUrl.append("jdbc:mysql://");
			sbUrl.append(this.dbsIP);
			sbUrl.append(":");
			sbUrl.append(this.dbsPort);
			sbUrl.append("/");
			sbUrl.append(this.dbName);
			sbUrl.append("?serverTimezone=GMT%2B8");
			sbUrl.append("&useUnicode=true&characterEncoding=utf8");
			cnA = DriverManager.getConnection(sbUrl.toString(), this.dbUser, this.dbPwd);
		}
		return cnA;
	}
	
	/**
	 * ��ȡ��ǰ��Ļ���Ӷ���
	 * @return
	 */
	public static Connection getActiveConnection() {
		return cnA;
	}
}
