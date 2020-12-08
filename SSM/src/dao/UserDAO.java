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
 * 数据库访问服务类：封装与用户相关的数据访问方法
 */

public class UserDAO {
	/**
	 * 指定的用户账号是否存在
	 * @param uid 账号
	 * @return
	 * @throws SQLException 
	 */
	public static boolean isUidExists(String uid) throws SQLException {
		boolean bR = false;
		
		Connection cn = MySqlDataSource.getActiveConnection();
		if(cn==null) {
			throw new SQLException("数据库连接的对象不存在");
		}
		
		StringBuffer sbSql = new StringBuffer();
		PreparedStatement pstA = null;
		ResultSet rsA = null;
		
		try {
			sbSql.append("Select * from tbUser where uid=?");
			pstA = cn.prepareStatement(sbSql.toString());
			pstA.setString(1, uid);
			rsA = pstA.executeQuery();	//执行查询，获取ResultSet结果集
			if(rsA!=null) {
				if(rsA.next()==true) {
					bR = true;	//表示指定的账号存在
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
	 * 指定用户的密码
	 * @param uid 账号
	 * @param pwd 密码
	 * @return bR
	 * @throws SQLException
	 */
	public static boolean isUidPwdExists(String uid,String pwd) throws SQLException {
		boolean bR = false;		
		Connection cn = MySqlDataSource.getActiveConnection();
		
		if(cn==null) {
			throw new SQLException("数据库连接的对象不存在");
		}
		
		StringBuffer sbSql = new StringBuffer();
		PreparedStatement pstA = null;
		ResultSet rsA = null;
		
		try {
			sbSql.append("Select * from tbUser where uid=? and pwd=?");
			pstA = cn.prepareStatement(sbSql.toString());
			pstA.setString(1, uid);
			pstA.setString(2, pwd);
			rsA = pstA.executeQuery();	//执行查询，获取ResultSet结果集
			if(rsA!=null) {
				if(rsA.next()==true) {
					bR = true;	//表示指定的账号存在
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
