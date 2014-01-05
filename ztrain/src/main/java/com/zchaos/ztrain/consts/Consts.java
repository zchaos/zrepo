package com.zchaos.ztrain.consts;

public class Consts {
	public static final String URL_BASE = "https://kyfw.12306.cn";//基本的URL

	/**
	 * 输入正确用户名和密码访问的是otn/login/userLogin，没有传用户名和密码上去，不知道怎么登录成功的
	 * 输入错误的用户名和密码访问的是otn/login/loginAysnSuggest，可以看到用户名和密码上传上去了
	 */
	public static final String URL_LOGIN = URL_BASE + "/otn/login/loginAysnSuggest";

	public static final String PARAM_USERNAME = "loginUserDTO.user_name";//用户名

	public static final String PARAM_PASSWORD = "userDTO.password";//密码

	public static final String PARAM_CAPTCHA = "randCode";//验证码

	public static final String COOKIE_JSESSIONID = "JSESSIONID";//sessionid

	public static final String COOKIE_BIGIPSERVER = "BIGipServerotn";//BIGipServerotn
}
