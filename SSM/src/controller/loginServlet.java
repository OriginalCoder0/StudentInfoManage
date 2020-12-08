package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataViewModel;
import services.UserService;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet(description = "�����¼����", urlPatterns = { "/login" })
public class loginServlet extends HttpServlet {
	
	/**
	 * ���кŰ汾��ʶֵ
	 */
    private static final long serialVersionUID = 6723654986471531731L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//�����¼����
		//1.��ȡ������ύ��ע��������˺ţ����룬�ظ����룬��ʵ����
		String strUid = request.getParameter("uid");
		String strName = request.getParameter("name");
		String strPwd = request.getParameter("pwd");
		String strRepwd = request.getParameter("repwd");
		
//		request.getServletContext();
		
		UserService usA = new UserService();
		try {
			DataViewModel dvmA = usA.login(strUid, strPwd);
			RequestDispatcher rdA = null;
			if(dvmA.isSuccess()==true) {
				rdA = request.getRequestDispatcher("manager.jsp");
				rdA.forward(request, response);		//ת��������Ա����̨��
			} else {
				request.setAttribute("uid", strUid);	//ת����ҳ��������
				if(dvmA.isUidError()) {
					request.setAttribute("uidError", dvmA.getMessage());
				} else if (dvmA.isPwdError()) {
					request.setAttribute("pwdError", dvmA.getMessage());
				}
				rdA = request.getRequestDispatcher("login.jsp");
				rdA.forward(request, response);		//ת������¼ҳ�棬���µ�¼
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		PrintWriter pw = response.getWriter();
		
		//2.�ж�ע������ĺϷ���
		if(strPwd != null) {
			if(strPwd.equals(strRepwd)==false) {
				//�ظ��������
				pw.append("�ظ��������!");
				return; 	//�˳�����
			}
		}
		//2.2�ж����ݿ�����Ƿ��Ѿ��е�ע���¼
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("Select * from tbUser");
		sbSql.append("'Where uid='");
		sbSql.append(strUid);
		sbSql.append("'");
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String url = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/dbTest";
			url = url + "serverTimezone=GMT%288%useUnicode=true";
			url = url + "characterEncoding=UTF-8";
			conn= DriverManager.getConnection(url,"root","root");

			
			//3.���ע���¼
			sbSql.delete(0, sbSql.length());
			sbSql.append("Insert into tbUser(uid,pwd,name)");
			sbSql.append("Values('");
			sbSql.append(strUid);
			sbSql.append("','");
			
			sbSql.append(strPwd);
			sbSql.append("','");
			
			sbSql.append(strName);
			sbSql.append("')'");
			
			psmt = conn.prepareStatement(sbSql.toString());
			rs = psmt.executeQuery();
			//�ж��Ƿ�Ϊ��
			if(rs!=null) {
				if(rs.next()==true) {
					//�����Ѿ���һ��ͬ�����˺�
					pw.append("ע���˺��ظ�!");
					return;
				}
			}
			
			
			
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(psmt != null) {
					psmt.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
