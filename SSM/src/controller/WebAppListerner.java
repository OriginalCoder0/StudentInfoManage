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
 * WebӦ�ó��������
 * @author OriginalCoder
 */
@WebListener
public class WebAppListerner implements ServletContextListener {

    /**
     * Default constructor. 
     * Ĭ�ϵĹ��췽��
     */
    public WebAppListerner() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // �ر�Ӧ�ó���ʱִ�б�����
    	Object oV = sce.getServletContext().getAttribute("cnMySQL");
    	if (oV!=null) {
    		Connection cn = (Connection)oV;
    		if(cn!=null) {
    			try {
					cn.close();		//�ر����Ӷ���
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
         // ��������ʱִ�б�����
    	ServletContext sc = sce.getServletContext();//��ȡServletContext����
    	//��ȡweb.xml�����ļ��еı���
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
    		
    		Connection cn = dsMySql.getConnection();  // ��ȡ���Ӷ���
			sc.setAttribute("cnMySQL", cn);  // �����Ӷ��󱣴��� ServletContext ��
    		
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
