package LMS.db;

import LMS.domain.Request;
import LMS.utils.PaginationSupport;

import java.util.List;

public interface RequestRepository {

    /**
     * 查询所有借书请求的数量
     *
     * @return
     */
    long count();

    /**
     * 获取借书请求
     *
     * @param id
     *      请求id
     * @return
     *      请求实体
     */
    Request getRequestById(long id);

    /**
     * 创建一个新的请求
     *
     * @param account
     *      用户名
     * @param bookItemId
     *      图书副本id
     */
    void CreateBorrowingRequest(String account,long bookItemId);

    /**
     * 获取当前未处理的所有请求
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      页面容量
     * @return
     *      页面
     */
    PaginationSupport<Request> getCurrentRequests(int pageNo, int pageSize);

    /**
     * 获取某个用户借书请求页面
     *
     * @param account
     *      用户名
     * @param pageNo
     *      页码
     * @param pageSize
     *      容量
     * @return
     *      页面
     */
    PaginationSupport<Request> getBorrowerRequests(String account,int pageNo,int pageSize);

    /**
     *
     * 通过请求
     *
     * @param requestId
     *      请求id
     * @param checkerId
     *      管理员id
     * @return
     *      是否成功
     */
    boolean passRequest(long requestId,long checkerId);

    /**
     * 拒绝其他请求
     *
     * @param bookItemId
     * @return
     */
    boolean deleteOtherRequests(long bookItemId,long checkerId);

    /**
     * 拒绝请求
     *
     * @param requestId
     *      请求id
     * @param checkerId
     *      管理员id
     */
    void denyRequest(long requestId, long checkerId);

    /**
     * 查询某个用户对图书副本的借阅请求
     *
     * @param borrowerAccount
     *      借阅者用户名
     * @param bookItemId
     *      图书副本id
     * @return
     */
    List<Request> getRequest(String borrowerAccount, long bookItemId);


}
