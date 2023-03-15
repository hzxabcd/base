package com.xuan.base.bean;
/**
 * 错误码统一处理类，所有的错误码统一定义在这里
 * @author Administrator
 *
 */
public class CodeMsg {

	private int code;//错误码
	
	private String msg;//错误信息
	
	/**
	 * 构造函数私有化即单例模式
	 * @param code
	 * @param msg
	 */
	private CodeMsg(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	//通用错误码定义
	//处理成功消息码
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	//非法数据错误码
	public static CodeMsg DATA_ERROR = new CodeMsg(-1, "非法数据！");
	public static CodeMsg CPACHA_EMPTY = new CodeMsg(-2, "验证码不能为空！");
	public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-3, "");
	public static CodeMsg SESSION_EXPIRED = new CodeMsg(-4, "会话已失效，请刷新页面重试！");
	public static CodeMsg CPACHA_ERROR = new CodeMsg(-5, "验证码错误！");
	public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6, "还未登录或会话失效，请重新登录！");
	public static CodeMsg UPLOAD_PHOTO_SUFFIX_ERROR = new CodeMsg(-7, "图片格式不正确！");
	public static CodeMsg UPLOAD_PHOTO_ERROR = new CodeMsg(-8, "图片上传错误！");
	
	
	//后台管理类错误码
	//用户管理类错误
	public static CodeMsg ADMIN_USERNAME_EMPTY = new CodeMsg(-2000, "用户名不能为空！");
	public static CodeMsg ADMIN_PASSWORD_EMPTY = new CodeMsg(-2001, "密码不能为空！");
	public static CodeMsg ADMIN_NO_RIGHT = new CodeMsg(-2002, "您所属的角色没有该权限！");
	
	//登录类错误码
	public static CodeMsg ADMIN_USERNAME_NO_EXIST = new CodeMsg(-3000, "该用户名不存在！");
	public static CodeMsg ADMIN_PASSWORD_ERROR = new CodeMsg(-3001, "密码错误！");
	public static CodeMsg ADMIN_USER_UNABLE = new CodeMsg(-3002, "该用户已被冻结，请联系管理员！");
	public static CodeMsg ADMIN_USER_ROLE_UNABLE = new CodeMsg(-3003, "该用户所属角色状态不可用，请联系管理员！");
	public static CodeMsg ADMIN_USER_ROLE_AUTHORITES_EMPTY = new CodeMsg(-3004, "该用户所属角色无可用权限，请联系管理员！");
	
	//后台菜单管理类错误码
	public static CodeMsg ADMIN_MENU_ADD_ERROR = new CodeMsg(-4000, "菜单添加失败，请联系管理员！");
	public static CodeMsg ADMIN_MENU_EDIT_ERROR = new CodeMsg(-4001, "菜单编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_MENU_ID_EMPTY = new CodeMsg(-4002, "菜单ID不能为空！");
	public static CodeMsg ADMIN_MENU_ID_ERROR = new CodeMsg(-4003, "菜单ID错误！");
	public static CodeMsg ADMIN_MENU_DELETE_ERROR = new CodeMsg(-4004, "改菜单下有子菜单，不允许删除！");
	//后台角色管理类错误码
	public static CodeMsg ADMIN_ROLE_ADD_ERROR = new CodeMsg(-5000, "角色添加失败，请联系管理员！");
	public static CodeMsg ADMIN_ROLE_NO_EXIST = new CodeMsg(-5001, "该角色不存在！");
	public static CodeMsg ADMIN_ROLE_EDIT_ERROR = new CodeMsg(-5002, "角色编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_ROLE_DELETE_ERROR = new CodeMsg(-5003, "该角色下存在用户信息，不可删除！");
	//后台用户管理类错误码
	public static CodeMsg ADMIN_USER_ROLE_EMPTY = new CodeMsg(-6000, "请选择用户所属角色！");
	public static CodeMsg ADMIN_USERNAME_EXIST = new CodeMsg(-6001, "该用户名已存在，请换一个试试！");
	public static CodeMsg ADMIN_USE_ADD_ERROR = new CodeMsg(-6002, "用户添加失败，请联系管理员！");
	public static CodeMsg ADMIN_USE_NO_EXIST = new CodeMsg(-6003, "用户不存在！");
	public static CodeMsg ADMIN_USE_EDIT_ERROR = new CodeMsg(-6004, "用户编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_USE_DELETE_ERROR = new CodeMsg(-6005, "该用户存在关联数据，不允许删除！");
	
	//后台用户修改密码类错误码
	public static CodeMsg ADMIN_USER_UPDATE_PWD_ERROR = new CodeMsg(-7000, "旧密码错误！");
	public static CodeMsg ADMIN_USER_UPDATE_PWD_EMPTY = new CodeMsg(-7001, "新密码不能为空！");
	
	//后台用户修改密码类错误码
	public static CodeMsg ADMIN_DATABASE_BACKUP_NO_EXIST = new CodeMsg(-8000, "备份记录不存在！");
	
	//后台服装管理错误码
	public static CodeMsg ADMIN_CLOTHES_ADD_ERROR = new CodeMsg(-9001, "服装添加失败，请联系管理员！");
	public static CodeMsg ADMIN_CLOTHES_EDIT_ERROR = new CodeMsg(-9002, "服装编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_CLOTHES_DELETE_ERROR = new CodeMsg(-9003, "该服装存在关联数据，不允许删除！");

	//前台订单添加错误类别码
	public static CodeMsg UNIQLO_ADD_ORDER_ERROR= new CodeMsg(-1000, "前台订单添加失败！");
	public static CodeMsg UNIQLO_CLOTHES_STATUS_UNALLOW= new CodeMsg(-10001, "该衣服状态不允许，请换一件租赁！");
	public static CodeMsg UNIQLO_CLOTHES_NO_EXIST= new CodeMsg(-10002, "该衣服不存在，请换一件租赁！");
	public static CodeMsg UNIQLO_USER_INSUFFICIENT= new CodeMsg(-10003, "您是普通用户，请充值会员！");
	public static CodeMsg UNIQLO_USER_UNTHORITY= new CodeMsg(-10004, "您的权限不足，请提高会员等级！");
	public static CodeMsg UNIQLO_CLOTHES_SETSTATUS_ERROR =new CodeMsg(-10005, "订单生成成功，服装状态更改失败！");
	public static CodeMsg UNIQLO_ORDER_NO_EXIST =new CodeMsg(-10006, "删除失败，该订单不存在！");
	public static CodeMsg UNIQLO_ORDER_CLOTHES_UNBACK =new CodeMsg(-10007, "删除失败，服装未送回！");
	public static CodeMsg UNIQLO_ORDER_CLOTHES_ABNORMAL =new CodeMsg(-10008, "删除失败，订单异常！");
	public static CodeMsg UNIQLO_ORDER_DAMAGE_MONEY_ERROR =new CodeMsg(-10009, "赔偿金额错误，请重新选择！");
	public static CodeMsg UNIQLO_ORDER_STATUS_ERROR = new CodeMsg(-10010, "取消订单失败！请联系管理员。");
	public static CodeMsg UNIQLO_ORDER_CANNOT_CANCEL = new CodeMsg(-10011, "非待配送订单无法取消！");

	//后台品牌错误类别码
	public static CodeMsg ADMIN_BRAND_ADD_ERROR = new CodeMsg(-11001, "品牌添加失败，请联系管理员！");
	public static CodeMsg ADMIN_BRAND_EDIT_ERROR = new CodeMsg(-11002, "品牌编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_BRAND_DELETE_ERROR = new CodeMsg(-11003, "该品牌存在关联数据，不允许删除！");

	//后台分类错误类别码
	public static CodeMsg ADMIN_CLASS_ADD_ERROR = new CodeMsg(-12001, "分类添加失败，请联系管理员！");
	public static CodeMsg ADMIN_CLASS_EDIT_ERROR = new CodeMsg(-12002, "分类编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_CLASS_DELETE_ERROR = new CodeMsg(-12003, "该分类存在关联数据，不允许删除！");

	//前台个人部分处理错误类别码
	public static CodeMsg MEMBER_STATUS_NO_EXIST = new CodeMsg(-13000, "会员类型不存在，请重新选择！");
	public static CodeMsg MEMBER_MONEY_ERROR = new CodeMsg(-13001, "金额错误 请重新充值！");

	// 后台审核类错误类别码
	public static CodeMsg ADMIN_CHECK_SUBMIT_ERROR = new CodeMsg(-14001, "审核提交失败，请联系管理员！");
}
