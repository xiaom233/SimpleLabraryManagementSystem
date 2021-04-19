package LMS.domain;


import java.sql.Timestamp;
import java.util.Date;

/**
 * LMS图书馆管理系统 系统管理员类
 * @author liuziyu
 */
public class UserEntity {

    private long id;
    private String account;
    private String password;
    private String fullName;
    private String email;
    private String phoneNo;
    private String role;
    private boolean lock;
    private Timestamp registerTime;



    public UserEntity() {
    }

    public UserEntity(long id, String account, String password, String fullName, String email, String phoneNo, String role, boolean lock, Timestamp registerTime) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.role = role;
        this.lock = lock;
        this.registerTime = registerTime;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean isLock() {
        return lock;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

}
