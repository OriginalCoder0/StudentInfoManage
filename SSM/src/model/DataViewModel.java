/**
 *
**/	
package model;

/**  
 * @author OriginalCoder
 * 数据实体类：封装处理结果数据，用于在服务层与控制器之间传递数据
 */

public class DataViewModel {
	private boolean success = false;	//处理是否成功？
	private boolean uidError = false;	//是否账号错误
	private boolean pwdError = false;	//是否密码错误
	private String message = null;		//错误消息
	
	/**获取是否成功的布尔值**/
	public boolean isSuccess() {
		return success;
	}
	/**
	 * 设置是否成功的布尔值
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
