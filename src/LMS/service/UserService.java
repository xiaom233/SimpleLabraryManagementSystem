package LMS.service;

import LMS.domain.UserEntity;
import LMS.utils.PaginationSupport;

public interface UserService {

    /**
     * 获取管理员数量
     *
     * @return
     */
    long getAdminCount();

    /**
     * 登录
     *
     * @param account
     *      用户名
     * @param password
     *      密码
     * @return
     *      用户实体
     */
    UserEntity login(String account, String password);

    /**
     * 查找用户
     *
     * @param account
     *      用户名
     * @return
     *      用户实体
     */
    UserEntity findOneByAccount(String account);

    /**
     * 查找用户,比对账户密码
     * @param account
     *      用户名
     * @param password
     *      密码
     * @return
     *      用户实体
     */
    UserEntity findOneByAccount(String account,String password);

    /**
     * 查找用户
     *
     * @param id
     *      用户id
     * @return
     *      用户实体
     */
    UserEntity findOneById(long id);

    /**
     * 查找所有借阅者
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      页码容量
     * @return
     *      页面
     */
    PaginationSupport<UserEntity> getBorrowerList(int pageNo,int pageSize);

    /**
     * 查找所有管理员
     *
     * @param pageNo
     *      页码
     * @param pageSize
     *      页码容量
     * @return
     *
     */
    PaginationSupport<UserEntity> findAdminPage(int pageNo, int pageSize);

    /**
     * 锁定账户，对于用户来说时封禁，对于管理员来说时删除
     *
     * @param account
     *      用户名
     */
    void lock(String account);

    /**
     * 更新信息
     *
     * @param fullName
     *      全名
     * @param email
     *      邮箱
     * @param phoneNo
     *      电话
     * @param id
     *      用户id
     */
    void modify(String fullName,String email,String phoneNo,long id);

    /**
     *
     * 添加管理员
     *
     * @param fullName
     *      全名
     * @param email
     *      邮箱
     * @param phoneNo
     *      电话
     */
    void addAdmin(String fullName,String email, String phoneNo);
}
