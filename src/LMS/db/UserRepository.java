package LMS.db;

import LMS.domain.UserEntity;
import LMS.utils.PaginationSupport;

public interface UserRepository {

    long getAdminNumbers();

    long borrowerCount();

    UserEntity save(UserEntity userEntity);

    UserEntity findById(long userId);

    UserEntity findByAccount(String account);

    UserEntity findByAccount(String account, String password);

    PaginationSupport<UserEntity> findAdminPage(int pageNo, int pageSize);

    PaginationSupport<UserEntity> findBorrowerPage(int pageNo, int pageSize);

    long getAdminCount();

    void lock(String account);

    UserEntity modifyInfo(String fullName, String email, String phoneNo, long id);

    UserEntity modifyPassword(String password, long id);

    void deleteByAccount(String account);
}
