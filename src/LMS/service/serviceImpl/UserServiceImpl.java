package LMS.service.serviceImpl;

import LMS.db.UserRepository;
import LMS.domain.UserEntity;
import LMS.service.UserService;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity login(String account, String password) {
        return userRepository.findByAccount(account,password);
    }

    @Override
    public long getAdminCount() {
        return userRepository.getAdminCount();
    }

    @Override
    public UserEntity findOneByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public UserEntity findOneByAccount(String account, String password) {
        return userRepository.findByAccount(account,password);
    }

    @Override
    public UserEntity findOneById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public PaginationSupport<UserEntity> getBorrowerList(int pageNo, int pageSize) {
        return userRepository.findBorrowerPage(pageNo, pageSize);
    }

    @Override
    public void lock(String account) {
        userRepository.lock(account);
    }

    @Override
    public void modify(String fullName, String email, String phoneNo, long id) {
        userRepository.modifyInfo(fullName, email, phoneNo, id);
    }

    @Override
    public void addAdmin(String fullName, String email, String phoneNo) {
        final String STR_FORMAT = "0000";//生成管理员账号后缀

        UserEntity admin = new UserEntity();
        admin.setEmail(email);
        admin.setFullName(fullName);
        admin.setPhoneNo(phoneNo);
        admin.setLock(false);
        admin.setRole("admin");

        long accountNumber = getAdminCount() + 1;
        String account = "admin" + new DecimalFormat(STR_FORMAT).format(accountNumber);

        admin.setAccount(account);
        admin.setPassword(account);

        userRepository.save(admin);
    }

    @Override
    public PaginationSupport<UserEntity> findAdminPage(int pageNo, int pageSize) {
        return userRepository.findAdminPage(pageNo,pageSize);
    }
}
