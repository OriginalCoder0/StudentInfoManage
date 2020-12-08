/**
 *
**/	
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**  
 * @author OriginalCoder
 * ���ݿ���ʷ����ࣺ��װ���û���ص����ݷ��ʷ���
 */

public class UserDAO {
	/**
	 * ָ�����û��˺��Ƿ����
	 * @param uid �˺�
	 * @return
	 * @throws SQLException 
	 */
	public static boolean isUidExists(String uid) throws SQLException {
		boolean bR = false;
		
		Connection cn = MySqlDataSource.getActiveConnection();
		if(cn==null) {
			throw new SQLException("���ݿ����ӵĶ��󲻴���");
		}
		
		StringBuffer sbSql = new StringBuffer();
		PreparedStatement pstA = null;
		ResultSet rsA = null;
		
		try {
			sbSql.append("Select * from tbUser where uid=?");
			pstA = cn.prepareStatement(sbSql.toString());
			pstA.setString(1, uid);
			rsA = pstA.executeQuery();	//ִ�в�ѯ����ȡResultSet�����
			if(rsA!=null) {
				if(rsA.next()==true) {
					bR = true;	//��ʾָ�����˺Ŵ���
				}
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			if(rsA!=null) {
				rsA.close();
			}
			if(pstA!=null) {
				pstA.close();
			}
			
		}
		
		return bR;
	}
	
	/**
	 * ָ���û�������
	 * @param uid �˺�
	 * @param pwd ����
	 * @return bR
	 * @throws SQLException
	 */
	public static boolean isUidPwdExists(String uid,String pwd) throws SQLException {
		boolean bR = false;		
		Connection cn = MySqlDataSource.getActiveConnection();
		
		if(cn==null) {
			throw new SQLException("���ݿ����ӵĶ��󲻴���");
		}
		
		StringBuffer sbSql = new StringBuffer();
		PreparedStatement pstA = null;
		ResultSet rsA = null;
		
		try {
			sbSql.append("Select * from tbUser where uid=? and pwd=?");
			pstA = cn.prepareStatement(sbSql.toString());
			pstA.setString(1, uid);
			pstA.setString(2, pwd);
			rsA = pstA.executeQuery();	//ִ�в�ѯ����ȡResultSet�����
			if(rsA!=null) {
				if(rsA.next()==true) {
					bR = true;	//��ʾָ�����˺Ŵ���
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rsA!=null) {
				rsA.close();
			}
			if(pstA!=null) {
				pstA.close();
			}
			
		}
		
		return bR;
	}

}
