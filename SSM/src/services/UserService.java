/**
 * 
 */
package services;

import java.sql.SQLException;

import dao.UserDAO;
import model.DataViewModel;

/**
 * ����Ա������
 */
public class UserService {

	/**
	 * ���е�¼��֤
	 * 
	 * @param uid ��¼�˺�
	 * @param pwd ��¼����
	 * @throws SQLException 
	 * */
	public DataViewModel login(String uid, String pwd) throws SQLException {
		DataViewModel dvmR = new DataViewModel();
		// 1. ��֤�˺��Ƿ�Ϸ����������û������ݿ���ʷ�����
		boolean b = UserDAO.isUidExists(uid);
		if(b==false) {
			dvmR.setSuccess(false);
			dvmR.setUidError(true);
			dvmR.setMessage("��¼�˺Ų����ڣ�");
			return dvmR;
		}
		
		// 2. ��֤�����Ƿ���ȷ��
		b = UserDAO.isUidPwdExists(uid, pwd);
		if(b==false) {
			dvmR.setSuccess(false);
			dvmR.setPwdError(true);
			dvmR.setMessage("��¼�������");
			return dvmR;
		}
		dvmR.setSuccess(true);
		dvmR.setMessage("��¼�ɹ���");
		return dvmR;
	}
	
	
}
