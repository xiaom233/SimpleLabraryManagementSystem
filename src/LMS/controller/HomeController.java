package LMS.controller;

import LMS.domain.UserEntity;
import LMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import LMS.db.UserRepository;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 系统主页控制类
 *
 * @author wben
 * @version v1.0
 */
@Controller // 控制定义
@RequestMapping("/") // 相应web路径
public class HomeController {


    @Resource
    private UserService userService;

    /**
     * 首页
     * 包括不同身份进入不同首页
     *
     * @return
     */
    @RequestMapping(method = GET) // 相应的请求方法
    public String home(HttpSession session, HttpServletRequest req) {


        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user == null) {
            Cookie[] cookies = req.getCookies();
            String account;
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("account")) {
                        account = cookies[i].getValue();
                        session.setAttribute("account", account);
                    }
                }
            }
            return "loginForm";
        }

        if (user.getRole().equals("borrower"))
            return "home";
        else
            return "redirect: admin/";
    }


    /**
     * 提交表单进行登陆
     *
     * @param account
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(method = POST)
    public String processLogin(@RequestParam(value = "account", defaultValue = "") String account,
                               @RequestParam(value = "password", defaultValue = "") String password,
                               HttpSession session, HttpServletResponse res) {

        UserEntity userEntity = userService.findOneByAccount(account, password);
        if (userEntity != null && userEntity.isLock() == false) {
            session.setAttribute("user", userEntity);
            Cookie cookie = new Cookie("account", userEntity.getAccount());
            cookie.setMaxAge(24 * 60 * 60);
            res.addCookie(cookie);
            if (userEntity.getRole().equals("borrower"))
                return "redirect:/";
            else
                return "redirect:/admin/";
        } else {
            return "loginError";
        }

    }

    /**
     * 注销
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }
}
