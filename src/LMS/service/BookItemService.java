package LMS.service;

import LMS.domain.BookItem;
import LMS.domain.Record;
import LMS.utils.PaginationSupport;

import java.util.List;

public interface BookItemService {

    /**
     * 通过图书id查找其图书副本
     *
     * @param bookId
     *      图书id
     * @return
     */
    List<BookItem> findBookItems(long bookId);

    /**
     * 查找图书副本
     *
     * @param bookItemId
     *      副本id
     * @return
     */
    BookItem findBookItem(long bookItemId);

    /**
     * 更新和查找信息
     *
     * @param location
     *      图书副本位置
     * @param id
     *      图书副本id
     * @return
     */
    BookItem modifyInfo( String location,long id);

    /**
     * 还书
     *
     * @param bookCode
     *      图书编码，不是isbn
     * @return
     */
    boolean returnBook(String bookCode);

    /**
     * 添加图书副本
     *
     * @param bookId
     *      图书id
     */
    void addBookItem(long bookId);

    /**
     * 删除图书副本
     *
     * @param bookItemId
     *      图书副本id
     */
    void deleteBookItem(long bookItemId);

    /**
     * 查询副本的借出情况（是否在库）
     *
     * @param account
     *      账号
     * @param bookItemId
     *      图书副本id
     * @return
     */
    boolean getBookItemLendingStatus(String account,long bookItemId);
}
