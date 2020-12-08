/**
 * 
 */
package services;

import java.sql.SQLException;

import dao.UserDAO;
import model.DataViewModel;

/**
 * 管理员服务类
 */
public class UserService {

	/**
	 * 进行登录验证
	 * 
	 * @param uid 登录账号
	 * @param pwd 登录密码
	 * @throws SQLException 
	 * */
	public DataViewModel login(String uid, String pwd) throws SQLException {
		DataViewModel dvmR = new DataViewModel();
		// 1. 验证账号是否合法：（调用用户的数据库访问方法）
		boolean b = UserDAO.isUidExists(uid);
		if(b==false) {
			dvmR.setSuccess(false);
			dvmR.setUidError(true);
			dvmR.setMessage("登录账号不存在！");
			return dvmR;
		}
		
		// 2. 验证密码是否正确：
		b = UserDAO.isUidPwdExists(uid, pwd);
		if(b==false) {
			dvmR.setSuccess(false);
			dvmR.setPwdError(true);
			dvmR.setMessage("登录密码错误！");
			return dvmR;
		}
		dvmR.setSuccess(true);
		dvmR.setMessage("登录成功！");
		return dvmR;
	}
	
	
}
