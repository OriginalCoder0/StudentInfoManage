/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据源管理类
 *
 */
public class MySqlDataSource {
	
    // 定义成员变量，保存数据库服务器的相关信息：
	private String dbsIP = null;   //保存数据库服务器的IP
	private String dbsPort = null; // 数据库服务器的端口号
	private String dbName = null;  // 要连接的数据库名称
	private String dbUser = null;  // 数据库的合法用户
	private String dbPwd = null;   // 登录数据库的密码
	
	private static Connection cnA = null; // 静态变量引用数据库连接对象
	
	public MySqlDataSource() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // 添加 MySQL 驱动类
	}
	
	/**
	 * 带参数的构造方法。
	 * @param dbsIP 数据库服务器的IP
	 * @param dbsPort 数据库服务器的端口号
	 * @param dbName 数据库名称
	 * @throws ClassNotFoundException 
	 * */
	public MySqlDataSource(String dbsIP, String dbsPort, String dbName) 
			                throws NullPointerException, 
			                       ClassNotFoundException {
		this.setDbsIP(dbsIP);
		this.setDbsPort(dbsPort);
		this.setDbName(dbName);
		Class.forName("com.mysql.cj.jdbc.Driver"); // 添加 MySQL 驱动类
	}
	
	public String getDbsIP() {
		return dbsIP;
	}
	
	public void setDbsIP(String dbsIP) throws NullPointerException {
		if(dbsIP==null || dbsIP.trim().isEmpty()==true) {
			throw new NullPointerException("数据库服务器的IP地址不能为空！");
		}
		this.dbsIP = dbsIP;
	}
	
	public String getDbsPort() {
		return dbsPort;
	}	
	
	public void setDbsPort(String dbsPort) throws NullPointerException {
		if(dbsPort==null || dbsPort.trim().isEmpty()==true) {
			throw new NullPointerException("数据库服务器的端口号不能为空！");
		}
		this.dbsPort = dbsPort;
	}
	
	public String getDbName() {
		return dbName;
	}
	
	
	public void setDbName(String dbName) throws NullPointerException {
		if(dbName==null || dbName.trim().isEmpty()==true) {
			throw new NullPointerException("数据库名称不能为空！");
			//trim()方法是去除字符两端多余的空格
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
	
	/**获取本数据源的连接对象。
	 * @throws SQLException */
	public Connection getConnection() throws SQLException {
		if(cnA==null) {  
			StringBuffer sbUrl = new StringBuffer();	//字符串构造器
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
	 * 获取当前活动的活动连接对象
	 * @return
	 */
	public static Connection getActiveConnection() {
		return cnA;
	}
}
