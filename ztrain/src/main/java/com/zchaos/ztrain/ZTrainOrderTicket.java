package com.zchaos.ztrain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zchaos.ztrain.consts.Consts;
import com.zchaos.ztrain.consts.TrainType;
import com.zchaos.ztrain.content.Address;
import com.zchaos.ztrain.content.Contact;
import com.zchaos.ztrain.content.Ticket;
import com.zchaos.ztrain.content.Train;

/**
 * 向服务器发送请求
 */
public class ZTrainOrderTicket {
	private static Logger log = LoggerFactory.getLogger(ZTrainOrderTicket.class);

	private String sessionid;

	private String BIGipServerotn;

	public static void main(String[] args) {
		ZTrainOrderTicket test = new ZTrainOrderTicket();
		//		test.login("zz", "123456", "");
		test.getSessionId();
	}

	public ZTrainOrderTicket() {
	}

	private HttpClient getClient() {
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		return clientBuilder.build();
	}

	private String getSessionId() {
		if (this.sessionid == null) {
			this.init();
		}
		return this.sessionid;
	}

	private String getBIGipServerotn() {
		if (BIGipServerotn == null) {
			this.init();
		}
		return BIGipServerotn;
	}

	private void init() {
		try {
			HttpPost httppost = new HttpPost(Consts.URL_LOGIN);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = getClient().execute(httppost, responseHandler);

			System.out.println(responseBody);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 登录
	 * @param username
	 * @param password
	 */
	public boolean login(String username, String password, String captchar) {
		log.debug("-----------------login start-----------------------");
		HttpPost httppost = new HttpPost(Consts.URL_LOGIN);
		httppost.addHeader("Cookie", "JSESSION");

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair(Consts.PARAM_USERNAME, username));
		parameters.add(new BasicNameValuePair(Consts.PARAM_PASSWORD, ""));
		parameters.add(new BasicNameValuePair(Consts.PARAM_CAPTCHA, password));
		String responseBody = null;
		try {
			UrlEncodedFormEntity uef = new UrlEncodedFormEntity(parameters, HTTP.ASCII);
			httppost.setEntity(uef);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = getClient().execute(httppost, responseHandler);
			//			String info = Util.removeTagFromHtml(responseBody);
			log.debug("-----------------------------------------------------");
			log.debug(responseBody);
			log.debug("-----------------------------------------------------\n\n\n\n\n");
			//			if (responseBody.contains(Constants.USER_NOT_EXIST)) {
			//				log.error("用户:" + username + Constants.USER_NOT_EXIST);
			//				rs.setState(Result.ACC_ERROR);
			//				rs.setMsg(Constants.USER_NOT_EXIST);
			//			}
			//			else if (info.contains(Constants.USER_PWD_ERR)) {
			//				log.error("用户:" + username + Constants.USER_PWD_ERR);
			//				rs.setState(Result.PWD_ERROR);
			//				rs.setMsg(Constants.USER_PWD_ERR);
			//			}
			//			else if (info.contains(Constants.USER_SUCC_INFO)) {
			//				int index = responseBody.indexOf("-->");
			//				log.debug(responseBody.substring(index + 4));
			//				rs.setState(Result.SUCC);
			//				rs.setMsg(Constants.LOGIN_SUC);
			//
			//				// 将Session信息到静态变量中，方便代理服务器获取
			//				List<Cookie> cookies = ((DefaultHttpClient) httpclient).getCookieStore().getCookies();
			//				for (Cookie cookie : cookies) {
			//					String name = cookie.getName();
			//					if ("JSESSIONID".equals(name)) {
			//						JSESSIONID = cookie.getValue();
			//					}
			//					else if ("BIGipServerotsweb".equals(name)) {
			//						BIGipServerotsweb = cookie.getValue();
			//					}
			//				}
			//				System.out.println("JSESSIONID=" + TrainClient.JSESSIONID + ",BIGipServerotsweb="
			//						+ TrainClient.BIGipServerotsweb);
			//			}
			//			else if (info.contains(Constants.CODE_ERROR)) {
			//				log.warn("用户:" + username + Constants.CODE_ERROR);
			//				rs.setState(Result.RAND_CODE_ERROR);
			//				rs.setMsg(Constants.CODE_ERROR);
			//			}
			//			else if (responseBody.contains(Constants.LOGIN_ERR_INFO)) {
			//				log.info("用户:" + username + Constants.USER_RELOGIN);
			//				rs.setState(Result.LOGIN_ERROR);
			//				rs.setMsg(Constants.USER_RELOGIN);
			//			}
			//			else if (responseBody.contains(Constants.LOGIN_LOSTS_POEPLE)) {
			//				log.info("用户:" + username + Constants.LOGIN_LOSTS_POEPLE);
			//				rs.setState(Result.LOST_OF_PEOPLE);
			//				rs.setMsg(Constants.LOGIN_LOSTS_POEPLE);
			//			}
			//			else if (responseBody.contains(Constants.LOGIN_ERR_LOCKED)) {
			//				log.info("用户:" + username + Constants.LOGIN_ERR_LOCKED);
			//				rs.setState(Result.LOGIN_ERR_LOCKED);
			//				rs.setMsg(Constants.LOGIN_ERR_LOCKED);
			//			}
			//			else if (responseBody.contains(Constants.LOGIN_SYS_MAINTAIN)) {
			//				log.info("用户:" + username + Constants.LOGIN_SYS_MAINTAIN);
			//				rs.setState(Result.LOGIN_SYS_MAINTAIN);
			//				rs.setMsg(Constants.LOGIN_SYS_MAINTAIN);
			//			}
			//			else {
			//				log.info("用户:" + username + Constants.UNKNOW_ERROR);
			//				rs.setState(Result.OTHER);
			//				rs.setMsg(Constants.UNKNOW_ERROR);
			//				System.out.println(responseBody);
			//			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		log.debug("-------------------login end---------------------");
		return true;
		//		return rs;
	}

	/**
	 * 验证码是否正确
	 * @param captcha
	 * @return
	 */
	public boolean checkCaptcha(String captcha) {
		return false;
	}

	/**
	 * 刷新验证码
	 */
	public void refreshCaptcha() {
	}

	/**
	 * 获得联系人
	 * @param username
	 * @param password
	 */
	public List<Contact> getContacts() {
		return null;
	}

	/**
	 * 获得所有的车站信息
	 * @return
	 */
	public List<Address> getAddresses() {
		return null;
	}

	/**
	 * 获得列车信息
	 * @param startTime 列车在出发地的时间，在此时间之后。用于限定发车时间
	 * @param endTime 列车在出发地的时间，在此时间之前。用于限定发车时间
	 * @param type 列车类型
	 * @param startAddress 出发地
	 * @param endAddress 目的地
	 * @return
	 */
	public List<Train> getTrains(Date startTime, Date endTime, TrainType type, Address startAddress, Address endAddress) {
		return null;
	}

	/**
	 * 订票
	 * @return 如果订票成功，返回true，否则返回false
	 */
	public boolean orderTickets(List<Ticket> tickets) {
		return false;
	}
}
