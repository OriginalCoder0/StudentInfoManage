package controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.MySqlDataSource;

/**
 * Application Lifecycle Listener implementation class WebAppListerner
 *
 */
/**
 * Web应用程序监听器
 * @author OriginalCoder
 */
@WebListener
public class WebAppListerner implements ServletContextListener {

    /**
     * Default constructor. 
     * 默认的构造方法
     */
    public WebAppListerner() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // 关闭应用程序时执行本方法
    	Object oV = sce.getServletContext().getAttribute("cnMySQL");
    	if (oV!=null) {
    		Connection cn = (Connection)oV;
    		if(cn!=null) {
    			try {
					cn.close();		//关闭连接对象
					cn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    	}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // 程序启动时执行本方法
    	ServletContext sc = sce.getServletContext();//获取ServletContext对象
    	//读取web.xml配置文件中的变量
    	String ip = sc.getInitParameter("dbsIP");
    	String port = sc.getInitParameter("dbsPort");
    	String name = sc.getInitParameter("dbName");
    	String user = sc.getInitParameter("dbUser");
    	String pwd = sc.getInitParameter("dbPwd");
    	
    	
    	try {
    		MySqlDataSource dsMySql = new MySqlDataSource();
    		dsMySql.setDbsIP(ip);
    		dsMySql.setDbsPort(port);
    		dsMySql.setDbName(name);
    		dsMySql.setDbUser(user);
    		dsMySql.setDbPwd(pwd);
    		
    		Connection cn = dsMySql.getConnection();  // 获取连接对象
			sc.setAttribute("cnMySQL", cn);  // 将连接对象保存在 ServletContext 中
    		
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
