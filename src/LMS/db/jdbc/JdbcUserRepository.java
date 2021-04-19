package LMS.db.jdbc;


import LMS.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import LMS.db.UserRepository;
import LMS.utils.PaginationSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbc;
    private static final String INSERT_USER = "insert into user (account, password, fullName, email, phoneNo,lock,role,registerTime) values (?,?, ?, ?, ?, ?,?,?)";

    private static final String SELECT_USER = "select * from user ";

    private static final String SELECT_ADMINS = "select * " +
            "from user where lock='0' and role='admin' order by id desc limit ? offset  ?";

    private static final String SELECT_BORROWERS = "select * " +
            "from user where role='borrower' order by id desc limit ? offset  ?";

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long getAdminNumbers() {

        return jdbc.queryForLong("select count(id) from user where lock='0' and role='admin' ");
    }

    @Override
    public long getAdminCount() {

        return jdbc.queryForLong("select count(id) from user where role='admin' ");
    }

    @Override
    public long borrowerCount() {

        return jdbc.queryForLong("select count(id) from user where role='borrower' ");
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        jdbc.update(INSERT_USER, userEntity.getAccount(), userEntity.getPassword(), userEntity.getFullName(),
                userEntity.getEmail(), userEntity.getPhoneNo(), 0, userEntity.getRole(), new Timestamp(new Date().getTime()));
        return null;
    }

    @Override
    public UserEntity findById(long userId) {

        return jdbc.queryForObject(SELECT_USER + " where id=?", new JdbcUserRepository.UserRowMapper(), userId);
    }

    @Override
    public UserEntity findByAccount(String account) {
        UserEntity userEntity = null;
        try {
            userEntity = jdbc.queryForObject(SELECT_USER + " where account=?", new UserRowMapper(), account);
        } catch (DataAccessException e) {
        }

        return userEntity;
    }

    @Override
    public UserEntity findByAccount(String account, String password) {
        UserEntity userEntity = null;
        try {
            userEntity = jdbc.queryForObject(SELECT_USER + " where account=? and password=? ", new UserRowMapper(), account, password);
        } catch (DataAccessException e) {
        }

        return userEntity;
    }

    @Override
    public PaginationSupport<UserEntity> findAdminPage(int pageNo, int pageSize) {
        int totalCount = (int) getAdminNumbers();
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<UserEntity>(new ArrayList<UserEntity>(0), 0);

        List<UserEntity> items = jdbc.query(SELECT_ADMINS, new UserRowMapper(), pageSize, startIndex);
        PaginationSupport<UserEntity> ps = new PaginationSupport<UserEntity>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public PaginationSupport<UserEntity> findBorrowerPage(int pageNo, int pageSize) {
        int totalCount = (int) borrowerCount();
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<UserEntity>(new ArrayList<UserEntity>(0), 0);

        List<UserEntity> items = jdbc.query(SELECT_BORROWERS, new UserRowMapper(), pageSize, startIndex);
        PaginationSupport<UserEntity> ps = new PaginationSupport<UserEntity>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public void lock(String account) {
        UserEntity user = findByAccount(account);
        if (user.isLock())
            jdbc.update("update user set lock=false  where account=?", account);
        else
            jdbc.update("update user set lock=true  where account=?", account);
    }

    @Override
    public UserEntity modifyInfo(String fullName, String email, String phoneNo, long id) {
        jdbc.update("update user set fullName=?,email=?,phoneNo=? where id=? ", fullName, email, phoneNo, id);
        return jdbc.queryForObject(SELECT_USER + " where id=?", new UserRowMapper(), id);
    }

    @Override
    public UserEntity modifyPassword(String password, long id) {
        jdbc.update("update user set password=? where id=?", password, id);
        return null;
    }

    @Override
    public void deleteByAccount(String account) {
        jdbc.update("delete from user where account=?", account);
    }

    private static class UserRowMapper implements RowMapper<UserEntity> {
        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new UserEntity(rs.getLong("id"), rs.getString("account"), rs.getString("password"), rs.getString("fullName"),
                    rs.getString("email"), rs.getString("phoneNo"), rs.getString("role"), rs.getBoolean("lock"), rs.getTimestamp("registerTime"));
        }
    }

}
