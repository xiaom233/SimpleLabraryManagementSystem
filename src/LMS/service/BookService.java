package LMS.service;

import LMS.domain.BookEntity;
import LMS.utils.PaginationSupport;

import java.util.List;

public interface BookService {

    /**
     * 查找图书
     *
     * @param bookId
     *      图书id
     * @return
     *      图书信息
     */
    BookEntity findOne(long bookId);

    /**
     * 删除图书
     *
     * @param bookId
     *      图书id
     */
    void deleteBookItem(long bookId);

    /**
     *
     * 保存一个图书
     *
     * @param bookEntity
     *      图书信息
     * @return
     */
    BookEntity save(BookEntity bookEntity);

    /**
     * 更新图书信息
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
     * @param id
     *      图书id
     */
    void modifyInfo(String bookName, String author, String publisher, String publishTime, String description, long id);

    /**
     * 图书信息的页面输出
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      页面容积
     * @return
     *      图书页面显示信息
     */
    PaginationSupport<BookEntity> findPage(int pageNo, int pageSize);
}
