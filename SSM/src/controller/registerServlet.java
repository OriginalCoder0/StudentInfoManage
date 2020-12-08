package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "处理管理员的注册请求", 
            urlPatterns = { "/register"})
public class registerServlet extends HttpServlet {
	
	/**
	 * 序列号版本标识值
	 */
    private static final long serialVersionUID = 1343605010593326069L;

	public registerServlet() {
        
    }

	public void init(ServletConfig config) throws ServletException {
		
	}

	public void destroy() {
		// 销毁的时候调用
	}

	protected void doGet(HttpServletRequest request, 
			             HttpServletResponse response) 
			             throws ServletException, IOException {
		
		// 设置服务器的字符编码：
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Content-type","text/html;Charaset=UTF-8");
		
		// 获取向浏览器写入数据的对象：
		PrintWriter pw = response.getWriter();
			    		
		// 提取浏览器提交的账号等参数值：
		String strUid = request.getParameter("uid");
		String strName = request.getParameter("name"); 
		String strPwd = request.getParameter("pwd"); 
		String strRepwd = request.getParameter("repwd"); 
		
		// 实现注册验证和数据插入：
		// 1. 验证重复密码：
		if(strPwd.equalsIgnoreCase(strRepwd)==false) {
			pw.append("重复密码错误！");
			return;
		}
		
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/tbstudent";//数据库名
			url = url + "?serverTimezone=GMT%2B8&useUnicode=true";
			url = url + "&characterEncoding=UTF-8";
			cn = DriverManager.getConnection(url, "root", "root");
			
			// 2. 验证账号的合法性：
			String sql = "Select * from tbuser Where uid='"+ strUid + "'";
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs!=null) {
				if(rs.next()==true) {
					pw.append("注册账号：" + strUid + "已经被抢注！");
					return;
				}
			}
			
			// 3. 写入新账号：
			sql = "INSERT INTO tbuser(uid,name,pwd) ";
			sql = sql + "Values('" + strUid + "','" + strPwd;
			sql = sql + "','"+strName + "')";
			pst = cn.prepareStatement(sql);
			pst.execute();
			if(pst.getUpdateCount()>0) {
				pw.append("注册账号成功！");				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭并销毁对象：
			try {
				if(rs!=null && rs.isClosed()==false) {
					rs.close();
				}
				if(pst!=null && !pst.isClosed()) {
					pst.close();
				}
				if(cn!=null && !cn.isClosed()) {
					cn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
