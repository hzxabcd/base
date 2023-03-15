package com.xuan.base.controller.front;

import com.google.code.kaptcha.Producer;
import com.xuan.base.constant.SessionConstant;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.service.front.UserService;
import com.xuan.base.util.SessionUtil;
import com.xuan.base.util.front.CommunityUtil;
import com.xuan.base.util.front.RedisKeyUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 梦致A远
 * @Date: 2021/8/24 13:50
 * @Description:
 */

@Api(description = "登录注册管理")
@Controller
@Slf4j
public class LoginController {


    /**
     * 默认状态的登录凭证的超时时间
     */
    private final int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住状态的登录凭证超时时间
     */
    private final int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;

    @Autowired
    private UserService userService;

    @Autowired
    private Producer kaptchaProducer;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("")
    private String contextPath;


//    @ApiOperation("注册")
    @GetMapping("/register")
    public String getRegisterPage(){
        return "/front/register";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "/front/login";
    }

    @PostMapping("/register")
    public String register(Model model, FrontUser user){
        System.out.println(user);
        Map<String, Object> map = userService.register(user);
        if(map==null||map.isEmpty()){
            return "/front/login";
        }else{
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            model.addAttribute("mobileMsg",map.get("mobileMsg"));
            return "/front/register";
        }
    }



private Logger log= LoggerFactory.getLogger(LoginController.class);
    //将验证码存入redis里头
    @GetMapping("/kaptcha")
    public void getKaptcha(HttpServletResponse response){
        //生成验证码
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);

        //验证码的归属
        String kaptchaOwner = CommunityUtil.generateUUID();
        Cookie cookie = new Cookie("kaptchaOwner", kaptchaOwner);
        cookie.setMaxAge(60);
        cookie.setPath(contextPath);
        response.addCookie(cookie);
        //将验证码存入redis
        String redisKey = RedisKeyUtil.getKaptchaKey(kaptchaOwner);
        redisTemplate.opsForValue().set(redisKey,text,60, TimeUnit.SECONDS);


        //将图片输出给浏览器
        response.setContentType("image/png");



        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image,"png",os);
        } catch (IOException e) {
            log.error("响应验证码失败："+e.getMessage());
            //System.out.println("响应失败");
        }
    }



    @PostMapping("/login")
    public String login(String username, String password, String code, boolean rememberme,
                        Model model,/*HttpSession session,*/HttpServletResponse response,
                        @CookieValue(value = "kaptchaOwner",required = false) String kaptchaOwner){

//        //检查验证码
//        String kaptcha = (String)session.getAttribute("kaptcha");

        String kaptcha = null;
        if(StringUtils.isNotBlank(kaptchaOwner)){
            String redisKey = RedisKeyUtil.getKaptchaKey(kaptchaOwner);
            kaptcha = (String)redisTemplate.opsForValue().get(redisKey);
        }

        if(StringUtils.isBlank(kaptcha)||StringUtils.isBlank(code)||!kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码不正确");
            return "/front/login";
        }

        //检查账号，密码
        int expiredSeconds = rememberme ? REMEMBER_EXPIRED_SECONDS : DEFAULT_EXPIRED_SECONDS;
        Map<String, Object> map = userService.login(username, password, expiredSeconds);
        //有登陆凭证，说明登录成功
        if(map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge(expiredSeconds);
            response.addCookie(cookie); //响应中添加Cookie

            SessionUtil.set(SessionConstant.SESSION_FRONTUSER_LOGIN_KEY,userService.findUserByName(username));

            return "redirect:/user/usercenter";
        }else{
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "/front/login";
        }
    }

    @GetMapping("/logout")
    public String logout(@CookieValue("ticket") String ticket){ //@CookieValue的作用 :用来获取Cookie中的值
        userService.logout(ticket);
        return "redirect:/login";
    }


}
