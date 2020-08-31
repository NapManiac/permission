package com.fly.controller;

import com.fly.codeutil.IVerifyCodeGen;
import com.fly.codeutil.SimpleCharVerifyCodeGenImpl;
import com.fly.codeutil.VerifyCode;
import com.fly.po.User;
import com.fly.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录 退出相关
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 获取验证码方法
     * @param request
     * @param response
     */
    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
//            LOGGER.info(code);
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
//            LOGGER.info("", e);
            System.out.println("异常处理");
        }
    }


    /**
     * 登录验证
     */
    @RequestMapping("/loginIn")
    public String loginIndex(HttpServletRequest request, Model model){
        //获取用户
        String username=request.getParameter("username");
        //获取密码
        String password=request.getParameter("password");
        //验证码
        String code=request.getParameter("code");
        System.out.println(code);
        //验证登录是否超时
        HttpSession session=request.getSession();
        if(session==null){
            model.addAttribute("msg","session 超时了");
            return "login";
        }
        //获取真正的验证码
        String trueCode= (String) session.getAttribute("VerifyCode");
        System.out.println(trueCode);
        if(!trueCode.toLowerCase().equals(code.toLowerCase())){//不区分大小写判断输入的验证码和真实的验证码是否一致
            model.addAttribute("msg","验证码不正确，请重新输入...");
            return "login";
        }

        //判断用户名和密码是否正确
        //1 获取subject
        Subject subject= SecurityUtils.getSubject();
        //2、封装用户数据
        UsernamePasswordToken token= new UsernamePasswordToken(username,password);
        //3 执行登录方法
        try {
            subject.login(token);
            User user= (User) subject.getPrincipal();
            session.setAttribute("user",user);
            //跳转到成功页面
            return "redirect:/index";
        }catch (UnknownAccountException ex){
            model.addAttribute("msg","用户名不存在");
            return "pages/login";
        }catch (IncorrectCredentialsException exs){
            model.addAttribute("msg","密码不正确");
            return "pages/login";
        }

//        //判断用户名和密码是否正确
//        User user = userService.queryUserInfoByNameAndPwd(username,password);
//        if(user == null){//如果该用户存在 登录到主页
//            model.addAttribute("msg", "用户名密码不正确");
//            return "login";
//        }
//        System.out.println("**********************************");
//        session.setAttribute("user", user);
//        return "redirect:/index";
    }


    /**
     * 退出功能
     */
    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * 登录页面的转发
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     *主页
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
