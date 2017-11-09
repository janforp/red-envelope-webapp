package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.param.LoginParamVo;
import com.iask.red_envelope.service.LoginService;
import com.iask.red_envelope.service.user.UserService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.captcha.CaptchaUtil;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jan on 16/8/22.
 *
 * 登录页面
 */
@RequestMapping(value = "/p/login", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    /**
     * 弹出 登录 页面
     *
     * 只有在微信浏览器打开的才能跳网页的登录
     * 若是在app上点击则用app登录
     * 其他浏览器无法登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/alertLogin")
    @ResponseBody
    public String loginAlert(HttpServletRequest request) {

        Boolean isWeiXinBrowser = CommonMethod.isWeixinBrower(request) ;

        Map<String,Object> map = new HashMap<>(1) ;

        map.put("isWeiXin",isWeiXinBrowser);

        return JsonUtil.buildSuccessDataJson(map) ;

    }

    /**
     * 获取验证码 图片
     * @param request
     * @param response
     */
    @RequestMapping(value = "/captcha.png", produces = "image/png", method = RequestMethod.GET)
    public void loginCaptcha (HttpServletRequest request, HttpServletResponse response){

        ServletOutputStream respOs = null ;
        ByteArrayOutputStream imageOutputStream = null ;

        try{
            imageOutputStream = new ByteArrayOutputStream();
            String textCaptcha = EncoderHelper.getChallangeAndWriteImage(CaptchaUtil.getRandomCaptchaService(),"png",imageOutputStream);
            request.getSession().setAttribute("captcha",textCaptcha);

            response.setHeader("Cache-Control" , "no-store");
            response.setHeader("Pragma" , "no-cache");
            response.setDateHeader("Expires" , 0L);
            response.setContentType("image/png");

            byte[] captchaChallengeAsJpeg = imageOutputStream.toByteArray();
            respOs = response.getOutputStream();
            respOs.write(captchaChallengeAsJpeg);
            respOs.flush();

        }catch (IOException e){

            e.printStackTrace();
        }finally {
            if (imageOutputStream != null) {
                try {
                    imageOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (respOs != null) {
                try {
                    respOs.close();
                }catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取 短信 验证码
     * 没有注册的手机无法获取,直接让他跳转到下载app
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSmsCode")
    @ResponseBody
    public String getSmsCode(HttpServletRequest request, String phone) {

        phone = CommonMethod.validateAndFixedTelephone(phone);

        if (phone == null) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"手机号码不合法");
        }

        //手机号码合法,则判断此手机是否注册
        Boolean isRegister = loginService.isRegister(request, phone);

        if (!isRegister){ //没有注册
            return JsonUtil.buildError(JsonCodeConsts.not_register, "该手机号未注册,立即前往下载APP注册") ;
        }

        //验证通过,发送验证码到手机
        return loginService.sendLoginCode(phone);

    }

    /**
     * 注册 或 登录
     * @param request
     * @param vo
     * @return
     */
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public String login(HttpServletRequest request,
                        HttpServletResponse response ,
                        LoginParamVo vo) {

        String phone = vo.getPhone() ;

        vo.setPhone(phone);

        phone = CommonMethod.validateAndFixedTelephone(phone) ;

        if (phone == null) {

            return JsonUtil.buildError(JsonCodeConsts.error_normal,"手机号码不合法");
        }

        return loginService.doLogin(request,response ,vo) ;
    }
}
