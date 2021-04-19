package LMS.controller;

import LMS.db.BookItemRepository;
import LMS.db.BookRepository;
import LMS.db.RecordRepository;
import LMS.db.UserRepository;
import LMS.domain.BookEntity;
import LMS.domain.BookItem;
import LMS.domain.Record;
import LMS.service.BookItemService;
import LMS.service.BookService;
import LMS.service.RecordService;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/book")
public class adminBookController {

    @Resource
    private BookService bookService;

    @Resource
    private BookItemService bookItemService;

    @Resource
    private RecordService recordService;

    /**
     *
     *  查询图书信息,及其副本信息
     *
     * @param bookId
     *      图书id
     * @param model
     *      控制器model
     */
    @RequestMapping(value = "/bookInfo", method = GET)
    public void bookInfo(@RequestParam(value = "bookId") long bookId, Model model) {
        BookEntity bookEntity = bookService.findOne(bookId);
        model.addAttribute(bookEntity);
        List<BookItem> bookItem = bookItemService.findBookItems(bookId);

        model.addAttribute("bookItem", bookItem);
    }

    /**
     * 查询图书信息
     *
     * @param id
     *      图书id
     * @return
     *      对应的BookEntity
     */
    @RequestMapping(value = "/modifyBookInfo", method = GET)
    public BookEntity modifyBookInfo(@RequestParam(value = "bookId") long id) {

        return bookService.findOne(id);
    }

    /**
     * 上传更新图书信息
     *
     * @param bookName
     *      书名
     * @param author
     *      作者
     * @param publisher
     *      出版社
     * @param publishTime
     *      出版时间
     * @param description
     *      描述
     * @param bookId
     *      图书id
     * @return
     *      redirect : url
     */
    @RequestMapping(value = "/modifyBookInfo", method = POST)
    public String modifyBookInfo(@RequestParam(value = "bookName") String bookName,
                                 @RequestParam(value = "author") String author,
                                 @RequestParam(value = "publisher") String publisher,
                                 @RequestParam(value = "publishTime") String publishTime,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value = "bookId") long bookId) {

        bookService.modifyInfo(bookName, author, publisher, publishTime, description, bookId);
        return "redirect: ../../book/bookList?bookId=" + bookId;
    }

    /**
     * 获取图书实体
     *
     * @param bookItemId
     *      图书实体id
     * @return
     *      图书实体
     */
    @RequestMapping(value = "/modifyBookItemInfo", method = GET)
    public BookItem modifyBookItemInfo(@RequestParam(value = "bookItemId") long bookItemId) {

        return bookItemService.findBookItem(bookItemId);
    }

    /**
     * 更新图书信息
     *
     * @param location
     *      图书在馆中的位置
     * @param bookItemId
     *      图书实体id
     * @return
     */
    @RequestMapping(value = "/modifyBookItemInfo", method = POST)
    public String modifyBookItemInfo(@RequestParam(value = "location") String location,
                                     @RequestParam(value = "bookItemId") long bookItemId) {


        bookItemService.modifyInfo( location, bookItemId);
        return "redirect: bookInfo?bookId=" + bookItemId;
    }

    /**
     * 正在借出的列表
     *
     * @param bookItemId
     *      图书实体id
     * @param pageNo
     *      页码
     * @param pageSize
     *      页面容量
     * @param model
     *      控制器model
     */
    @RequestMapping(value = "/borrowingList", method = GET)
    public void borrowingList(@RequestParam(value = "bookItemId") long bookItemId,
                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                              Model model) {

        PaginationSupport<Record> records = recordService.findRecordPage(bookItemId, pageNo, pageSize);
        Record record = recordService.getBorrowingRecord(bookItemId);
        BookItem bookItem = bookItemService.findBookItem(bookItemId);
        BookEntity bookEntity = bookService.findOne(bookItem.getBookId());
        model.addAttribute("bookEntity", bookEntity);
        model.addAttribute("bookItem", bookItem);
        model.addAttribute("current", record);
        model.addAttribute("records", records);
    }

    /**
     * 还书界面
     *
     * @return
     *      还书界面url
     */
    @RequestMapping(value = "/returnBook", method = GET)
    public String returnBook() {
        return "admin/book/returnBook";
    }

    /**
     * 提交还书命令
     *
     * @param bookCode
     *      图书编号
     * @return
     *      重定向url
     */
    @RequestMapping(value = "/return", method = POST)
    public String returnBook(@RequestParam(value = "bookCode") String bookCode) {
        if (bookItemService.returnBook(bookCode))
            return "redirect: returnBook";
        return "admin/book/returnBookError";
    }

    /**
     * 添加副本
     *
     * @param bookId
     *      图书id
     * @return
     *      重定向url
     */
    @RequestMapping(value = "/addBookItem", method = GET)
    public String addBookItem(@RequestParam(value = "bookId") long bookId) {

        bookItemService.addBookItem(bookId);
        return "redirect: bookInfo?bookId=" + bookId;
    }

    /**
     * 删除图书实体
     *
     * @param bookItemId
     *      实体id
     * @return
     *      重定向url
     */
    @RequestMapping(value = "/deleteBookItem", method = GET)
    public String deleteBookItem(@RequestParam(value = "bookItemId") long bookItemId) {

        bookItemService.deleteBookItem(bookItemId);
        long bookId = bookItemService.findBookItem(bookItemId).getBookId();
        bookService.deleteBookItem(bookId);
        return "redirect: bookInfo?bookId=" + bookId;
    }

    /**
     * 添加图书页面
     *
     * @return
     */
    @RequestMapping(value = "/addBook", method = GET)
    public String addBook() {

        return "admin/book/addBookForm";
    }

    /**
     * 提交添加图书表单
     *
     * @param isbn
     *      国际标准书号
     * @param bookName
     *      书名
     * @param author
     *      作者
     * @param publisher
     *      出版社
     * @param publishTime
     *      出版时间
     * @param description
     *      描述
     * @return
     */
    @RequestMapping(value = "/addBook", method = POST)
    public String addBook(@RequestParam(value = "isbn") String isbn,
                          @RequestParam(value = "bookName") String bookName,
                          @RequestParam(value = "author") String author,
                          @RequestParam(value = "publisher") String publisher,
                          @RequestParam(value = "publishTime") String publishTime,
                          @RequestParam(value = "description") String description) {

        BookEntity bookEntity = new BookEntity(isbn, bookName, author, publisher, publishTime, description);
        if (bookService.save(bookEntity) == null)//isbn重复时sava返回null
            return "error";
        return "redirect: ../../book/bookList";
    }

}
