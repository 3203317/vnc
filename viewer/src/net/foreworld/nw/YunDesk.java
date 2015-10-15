package net.foreworld.nw;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public interface YunDesk {

	public void start() throws Exception;

	public void stop() throws Exception;

	public void restart() throws Exception;

	public void lock() throws Exception;

	/**
	 * 注销
	 */
	public void logout() throws Exception;

	/**
	 * 切换用户
	 */
	public void switchUser() throws Exception;

	public void onShowListener(OnShowListener listener);
}
