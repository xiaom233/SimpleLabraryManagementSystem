package LMS.db;

import LMS.domain.BookEntity;
import LMS.utils.PaginationSupport;


/**
 * id       integer identity,
 *     isbn varchar(13) unique not null,
 *     bookName varchar(50) not null,
 *     author varchar(50),
 *     publisher varchar(50),
 *     publishTime varchar(30),
 *     description varchar(100),
 *     copyNumber integer default 0,
 *     remain integer default 0,
 *     bookCover varchar(100)
 */
public interface BookRepository {

    /**
     * 总数
     *
     * @return
     */
    long count();

    /**
     * 借出图书，减少库存
     *
     * @param bookId
     */
    void reduceRemain(long bookId);

    /**
     * 查询对应id的图书信息
     *
     * @param bookId
     *      图书id
     * @return
     *      图书信息
     */
    BookEntity findOne(long bookId);

    /**
     * 保存图书
     *
     * @param bookEntity
     *      图书信息实体
     * @return
     *      图书信息实体
     */
    BookEntity save(BookEntity bookEntity);

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

    /**
     * 删除图书
     * @param id
     *      需要删除的图书id
     */
    void deleteBookItem(long id);

    /**
     * 通过请求
     * @param id
     *      请求对应的id
     */
    void passRequest(long id);

    /**
     * 更新图书实体
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
     * @return
     *      更新后的图书实体
     */
    BookEntity modifyInfo(String bookName, String author, String publisher, String publishTime, String description, long id);

    /**
     * 查找书名
     * @param bookItemId
     *      具体图书的id
     * @return
     *      书名
     */
    String getBookNameByBookItemId(long bookItemId);

    /**
     * 添加图书
     * @param bookId
     *      图书id
     */
    void addRemain(long bookId);

    /**
     * 添加具体的实体图书
     * @param bookId
     *      图书实体id
     */
    void addBookItem(long bookId);

}
