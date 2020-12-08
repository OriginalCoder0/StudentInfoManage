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

@WebServlet(description = "�������Ա��ע������", 
            urlPatterns = { "/register"})
public class registerServlet extends HttpServlet {
	
	/**
	 * ���кŰ汾��ʶֵ
	 */
    private static final long serialVersionUID = 1343605010593326069L;

	public registerServlet() {
        
    }

	public void init(ServletConfig config) throws ServletException {
		
	}

	public void destroy() {
		// ���ٵ�ʱ�����
	}

	protected void doGet(HttpServletRequest request, 
			             HttpServletResponse response) 
			             throws ServletException, IOException {
		
		// ���÷��������ַ����룺
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Content-type","text/html;Charaset=UTF-8");
		
		// ��ȡ�������д�����ݵĶ���
		PrintWriter pw = response.getWriter();
			    		
		// ��ȡ������ύ���˺ŵȲ���ֵ��
		String strUid = request.getParameter("uid");
		String strName = request.getParameter("name"); 
		String strPwd = request.getParameter("pwd"); 
		String strRepwd = request.getParameter("repwd"); 
		
		// ʵ��ע����֤�����ݲ��룺
		// 1. ��֤�ظ����룺
		if(strPwd.equalsIgnoreCase(strRepwd)==false) {
			pw.append("�ظ��������");
			return;
		}
		
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/tbstudent";//���ݿ���
			url = url + "?serverTimezone=GMT%2B8&useUnicode=true";
			url = url + "&characterEncoding=UTF-8";
			cn = DriverManager.getConnection(url, "root", "root");
			
			// 2. ��֤�˺ŵĺϷ��ԣ�
			String sql = "Select * from tbuser Where uid='"+ strUid + "'";
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs!=null) {
				if(rs.next()==true) {
					pw.append("ע���˺ţ�" + strUid + "�Ѿ�����ע��");
					return;
				}
			}
			
			// 3. д�����˺ţ�
			sql = "INSERT INTO tbuser(uid,name,pwd) ";
			sql = sql + "Values('" + strUid + "','" + strPwd;
			sql = sql + "','"+strName + "')";
			pst = cn.prepareStatement(sql);
			pst.execute();
			if(pst.getUpdateCount()>0) {
				pw.append("ע���˺ųɹ���");				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �رղ����ٶ���
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
