/**
 *
**/	
package model;

/**  
 * @author OriginalCoder
 * ����ʵ���ࣺ��װ���������ݣ������ڷ�����������֮�䴫������
 */

public class DataViewModel {
	private boolean success = false;	//�����Ƿ�ɹ���
	private boolean uidError = false;	//�Ƿ��˺Ŵ���
	private boolean pwdError = false;	//�Ƿ��������
	private String message = null;		//������Ϣ
	
	/**��ȡ�Ƿ�ɹ��Ĳ���ֵ**/
	public boolean isSuccess() {
		return success;
	}
	/**
	 * �����Ƿ�ɹ��Ĳ���ֵ
	 * @param success 
	 * **/
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public boolean isUidError() {
		return uidError;
	}
	public void setUidError(boolean uidError) {
		this.uidError = uidError;
	}
	public boolean isPwdError() {
		return pwdError;
	}
	public void setPwdError(boolean pwdError) {
		this.pwdError = pwdError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
