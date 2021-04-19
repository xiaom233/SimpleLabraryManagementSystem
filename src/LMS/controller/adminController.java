package LMS.controller;

import LMS.db.*;
import LMS.domain.UserEntity;
import LMS.domain.body.ShowRequestBody;
import LMS.service.RecordService;
import LMS.service.RequestService;
import LMS.service.UserService;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private RequestService requestService;

    @Resource
    private UserService userService;

    @Resource
    private RecordService recordService;

    /**
     * 管理员主页
     *
     * @return
     */
    @RequestMapping(value = "/", method = GET)
    public String adminHome() {
        return "/admin/adminHome";
    }

    /**
     * 管理员列表
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      页面容量
     * @return
     *      重定向url
     */
    @RequestMapping(value = "/adminList", method = GET)
    public PaginationSupport<UserEntity> showAdminList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                       @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        return userService.findAdminPage(pageNo, pageSize);
    }

    /**
     * 添加管理员页面
     *
     * @return
     */
    @RequestMapping(value = "/addAdmin", method = GET)
    public String addAdminForm() {
        return "/admin/addAdminForm";
    }

    /**
     * 提交添加管理员表单
     *
     * @param fullName
     *      全名
     * @param email
     *      邮箱
     * @param phoneNo
     *      电话
     * @return
     */
    @RequestMapping(value = "/addAdmin", method = POST)
    public String addAdmin(@RequestParam(value = "fullName") String fullName,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "phoneNo") String phoneNo) {

        userService.addAdmin(fullName,email,phoneNo);
        return "redirect:adminList";
    }

    /**
     * 展示管理员信息
     *
     * @param account
     *      管理员账号
     * @return
     */
    @RequestMapping(value = "/adminProfile", method = GET)
    public UserEntity showAdminProfile(@RequestParam(value = "account") String account) {
        UserEntity userEntity = userService.findOneByAccount(account);
        return userEntity;
    }

    /**
     * 锁定用户
     *
     * @param account
     *      账户名
     * @return
     *      管理员列表 或者 用户信息主页
     */
    @RequestMapping(value = "/lock", method = GET)
    public String deleteAdmin(@RequestParam(value = "account") String account) {
        UserEntity userEntity = userService.findOneByAccount(account);

        userService.lock(account);
        System.out.println(userEntity.getRole());
        if (userEntity.getRole().equals("admin"))
            return "redirect: adminList";
        else
            return "redirect: borrowerProfile?account=" + account;
    }

    /**
     * 查看借书请求页面
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      页码容量
     * @param model
     *      控制器model
     * @return
     */
    @RequestMapping(value = "/checkRequests", method = GET)
    public PaginationSupport<ShowRequestBody> requestList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                          @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                                          Model model) {
        return requestService.getCurrentRequests(pageNo, pageSize);
    }

    /**
     * 通过借书请求
     *
     * @param requestId
     *      借书请求id
     * @param session
     *       HttpSession
     * @return
     */
    @RequestMapping(value = "/check", method = GET)
    public String passRequest(@RequestParam(value = "requestId") long requestId, HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("user");
        requestService.passRequest(requestId, user.getId());
        return "redirect: checkRequests";
    }

    /**
     * 拒绝借书请求
     *
     * @param requestId
     *      请求id
     * @param session
     *      HttpSession
     * @return
     */
    @RequestMapping(value = "/deny", method = GET)
    public String denyRequest(@RequestParam(value = "requestId") long requestId, HttpSession session){
        UserEntity user = (UserEntity) session.getAttribute("user");
        requestService.denyRequest(requestId, user.getId());
        return  "redirect: checkRequests";
    }

    /**
     * 借阅者列表
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      页面容量
     * @return
     */
    @RequestMapping(value = "/borrowerList", method = GET)
    public PaginationSupport<UserEntity> borrowerList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        return userService.getBorrowerList(pageNo, pageSize);
    }

    /**
     * 借阅者个人信息
     *
     * @param account
     *      用户名
     * @param model
     *      控制器model
     * @return
     */
    @RequestMapping(value = "/borrowerProfile", method = GET)
    public UserEntity showBorrowerProfile(@RequestParam(value = "account") String account,Model model) {
        UserEntity userEntity = userService.findOneByAccount(account);
        model.addAttribute("records",recordService.getBorrowerLendingRecords(account));
        return userEntity;
    }

    /**
     * 修改用户信息界面
     *
     * @param account
     *      用户账号
     * @return
     */
    @RequestMapping(value = "/modifyUserInfo", method = GET)
    public UserEntity modifyUserInfo(@RequestParam(value = "account") String account) {
        return userService.findOneByAccount(account);
    }

    /**
     * 修改用户信息
     *
     * @param fullName
     *      全名
     * @param email
     *      邮箱
     * @param phoneNo
     *      电话
     * @param userId
     *      用户id
     * @return
     */
    @RequestMapping(value = "/modifyUserInfo", method = POST)
    public String modifyUserInfo(@RequestParam(value = "fullName") String fullName,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "phoneNo") String phoneNo,
                                 @RequestParam(value = "userId") long userId) {


        userService.modify(fullName, email, phoneNo, userId);
        UserEntity user = userService.findOneById(userId);
        if (user.getRole().equals("borrower"))
            return "redirect:  borrowerProfile?account=" + user.getAccount();
        else
            return "redirect: adminProfile?account=" + user.getAccount();
    }
}
