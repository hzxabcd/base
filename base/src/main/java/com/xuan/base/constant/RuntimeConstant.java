package com.xuan.base.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 系统运行时的常量
 * @author Administrator
 *
 */
public class RuntimeConstant {

	/**
	 * 系统统一运行错误页面视图
	 */
	public static final String RUNTIME_ERROR_VIEW = "error/runtime_error";
	//登录拦截器无需拦截的url
	public static List<String> loginExcludePathPatterns = Arrays.asList(
			"/system/login",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/js/**",
			"/admin/images/**",
			"/error",
			"/pay/alipay_notify",
			"/cpacha/generate_cpacha",

			/*hzx*/
			"/site/login",
			"/home/css/**",
			"/home/**",
			"/home/imgs/**",
			"/admin/paisong/add",
			"/upaisong/**",
			"/photo/**",
			"/uniqlo/clothes/**",
			"/uniqlo/order/**",
			"/uniqlo/**",
			"/user/**",
			"/login",
			"/register",
			"/kaptcha",
			"/logout"

		);
	//权限拦截器无需拦截的url
	public static List<String> authorityExcludePathPatterns = Arrays.asList(
			"/system/login",
			"/system/index",
			"/system/no_right",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/js/**",
			"/admin/images/**",
			"/error",
			"/cpacha/generate_cpacha",
			"/system/logout",
			"/system/update_userinfo",
			"/system/update_pwd",
			"/pay/alipay_notify",
			"/photo/view",

			/*hzx*/
			"/site/login",
			"/home/css/**",
			"/home/js/**",
			"/home/imgs/**",
			"/admin/paisong/add",
			"/upaisong/**",
			"/photo/**",
			"/uniqlo/**",
			"/home/**",
			"/home",
			"/user/**",
			"/login",
			"/register",
			"/kaptcha",
			"/logout"
		);
}
