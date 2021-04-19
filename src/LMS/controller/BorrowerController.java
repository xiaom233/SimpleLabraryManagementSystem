package LMS.controller;

import LMS.db.BookItemRepository;
import LMS.db.BookRepository;
import LMS.db.RecordRepository;
import LMS.db.RequestRepository;
import LMS.domain.*;
import LMS.domain.body.ShowRecordBody;
import LMS.domain.body.ShowRequestBody;
import LMS.service.BookItemService;
import LMS.service.BookService;
import LMS.service.RecordService;
import LMS.service.RequestService;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/borrower")
public class BorrowerController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private BookItemRepository bookItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Resource
    private RequestService requestService;

    @Resource
    private RecordService recordService;

    @Resource
    private BookItemService bookItemService;

    @Resource
    private BookService bookService;

    /**
     * 加入借书车
     *
     * @param bookItemId
     *      图书实体id
     * @param session
     *      HtpSession
     * @return
     */
    @RequestMapping(value = "/addIntoCart", method = GET)
    public String requestBook(@RequestParam(value = "bookItemId") long bookItemId, HttpSession session) {
        BookItem bookItem = bookItemService.findBookItem(bookItemId);
        BookEntity book = bookService.findOne(bookItem.getBookId());

        Integer itemCount = (Integer) session.getAttribute("itemCount");
        if (itemCount == null)
            itemCount = new Integer(0);
        session.setAttribute("item" + itemCount, bookItem);
        session.setAttribute("itemCount", itemCount + 1);
        session.setAttribute("name" + itemCount, book.getBookName());

        return "redirect: ../book/bookInfo?bookId=" + bookItem.getBookId();
    }

    /**
     * 查看借书车
     *
     * @return
     */
    @RequestMapping(value = "/borrowingCart", method = GET)
    public String showCart() {

        return "/borrowingCart";

    }

    /**
     * 提交借书请求
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/commitRequest", method = GET)
    public String commit(HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("user");

        Integer itemCount = (Integer) session.getAttribute("itemCount");
        if (itemCount == null)
            itemCount = new Integer(0);

        for (int i = 0; i < itemCount; i++) {
            BookItem bookItem = (BookItem) session.getAttribute("item" + i);
            requestService.createRequest(user.getAccount(), bookItem.getId());
            session.removeAttribute("item" + i);
            session.removeAttribute("name" + i);
        }
        session.removeAttribute("itemCount");

        return "/book/commitSuccess";
    }

    /**
     * 获取当前borrower 的借书列表
     *
     * @param session
     * @param model
     */
    @RequestMapping(value = "currentLendingList", method = GET)
    public void currentLending(HttpSession session, Model model) {


        UserEntity user = (UserEntity) session.getAttribute("user");
        List<ShowRecordBody> records = recordService.getBorrowerLendingRecords(user.getAccount());

        model.addAttribute("records", records);

    }

    /**
     * 历史借书记录
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      容量
     * @param session
     *      HttpSession
     * @param model
     *      控制器model
     * @return
     */
    @RequestMapping(value = "historyLendingList", method = GET)
    public PaginationSupport<ShowRecordBody> historyList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                         @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                                         HttpSession session, Model model) {

        UserEntity user = (UserEntity) session.getAttribute("user");
       return recordService.getBorrowerHistoryRecords(user.getAccount(),pageNo,pageSize);
    }

    /**
     * 当前用户的借书请求列表
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      页码容量
     * @param session
     *      HttpSession
     * @return
     */
    @RequestMapping(value = "requestList", method = GET)
    public PaginationSupport<ShowRequestBody> showBorrowerRequestList(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("user");
        return requestService.getBorrowerRequests(user.getAccount(), pageNo, pageSize);
    }
}
