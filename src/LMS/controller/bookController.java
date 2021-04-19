package LMS.controller;

import LMS.db.BookItemRepository;
import LMS.db.BookRepository;
import LMS.domain.BookEntity;
import LMS.domain.BookItem;
import LMS.domain.UserEntity;
import LMS.service.BookItemService;
import LMS.service.BookService;
import LMS.service.RequestService;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/book")
public class bookController {

    @Resource
    private BookService bookService;

    @Resource
    private BookItemService bookItemService;

    /**
     * 查看图书列表
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      页码容量
     * @return
     */
    @RequestMapping(value = "/bookList", method = GET)
    public PaginationSupport<BookEntity> bookList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                  @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        return bookService.findPage(pageNo, pageSize);
    }

    /**
     * 查看图书信息
     *
     * @param bookId
     *      图书id
     * @param model
     *      控制器model
     * @param session
     *      HttpSession
     * @return
     */
    @RequestMapping(value = "/bookInfo", method = GET)
    public String bookInfo(@RequestParam(value = "bookId") long bookId, Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");

        BookEntity bookEntity = bookService.findOne(bookId);
        model.addAttribute(bookEntity);

        List<BookItem> bookItem = bookItemService.findBookItems(bookId);
        model.addAttribute("bookItem", bookItem);
        List<Boolean> status = new ArrayList<>(bookItem.size());
        for(int i=0;i<bookItem.size();i++){
            status.add(bookItemService.getBookItemLendingStatus(user.getAccount(),bookId));
        }
        model.addAttribute("status", status);
        if (!user.getRole().equals("borrower"))
            return "redirect: ../admin/book/bookInfo?bookId=" + bookId;
        return null;
    }


}
