package LMS.db.jdbc;

import LMS.db.BookItemRepository;
import LMS.db.BookRepository;
import LMS.db.RequestRepository;
import LMS.db.UserRepository;
import LMS.domain.Request;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcRequestRepository implements RequestRepository {

    private JdbcTemplate jdbc;
    private static final String SELECT_CHECKING_REQUEST = "select * from BorrowingRequest where status=0 " +
            "order by id limit ? offset ?";

    private static final String SELECT_REQUEST = "select * from BorrowingRequest ";

    @Autowired
    public JdbcRequestRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long count() {
        return jdbc.queryForLong("select count(id) from BorrowingRequest where status=0 ");
    }

    public long borrowerRequestsCount(String account) {
        return jdbc.queryForLong("select count(id) from BorrowingRequest where borrowerAccount=?",account);
    }

    @Override
    public Request getRequestById(long id) {
        return jdbc.queryForObject(SELECT_REQUEST + "where id=?", new JdbcRequestRepository.RequestRowMapper(), id);
    }

    @Override
    public void CreateBorrowingRequest(String account, long bookItemId) {

        jdbc.update("insert into BorrowingRequest(borrowerAccount,bookItemId,status,startTime) values(?,?,?,?) ", account, bookItemId, 0, new Date());
    }


    @Override
    public PaginationSupport<Request> getCurrentRequests(int pageNo, int pageSize) {

        int totalCount = (int) count();
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<Request>(new ArrayList<Request>(0), 0);

        List<Request> items = jdbc.query(SELECT_CHECKING_REQUEST, new JdbcRequestRepository.RequestRowMapper(), pageSize, startIndex);
        PaginationSupport<Request> ps = new PaginationSupport<Request>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public PaginationSupport<Request> getBorrowerRequests(String account,int pageNo,int pageSize) {
        int totalCount = (int) borrowerRequestsCount(account);
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<Request>(new ArrayList<Request>(0), 0);

        List<Request> items = jdbc.query("select * from borrowingRequest where borrowerAccount=? order by id desc limit ? offset ?",
                new JdbcRequestRepository.RequestRowMapper(), account,pageSize, startIndex);
        PaginationSupport<Request> ps = new PaginationSupport<Request>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public boolean passRequest(long requestId, long checkerId) {

        jdbc.update("update BorrowingRequest set status=1,checkerId=?,checkTime=? where status=0 and id=?",
                checkerId,new Date(),requestId);
        Request request = getRequestById(requestId);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, +14);
        jdbc.update("insert into BorrowingRecord (borrowerAccount,bookItemId,status,startTime,endTime) values(?,?,?,?,?)",
                request.getBorrowerAccount(), request.getBookItemId(), false, new Date(), cal.getTime());//建立借书记录
        return true;
    }

    @Override
    public void denyRequest(long requestId, long checkerId) {
        jdbc.update("update BorrowingRequest set status=2, checkerId=?, checkTime=? where id=?",
                checkerId,new Date(),requestId);//拒绝请求
    }

    @Override
    public boolean deleteOtherRequests(long bookItemId,long checkerId) {
        jdbc.update("update BorrowingRequest set status=2, checkerId=?, checkTime=? where status=0 and bookItemId=?",
                checkerId,new Date(),bookItemId);//拒绝其他 请求
        return true;
    }

    @Override
    public List<Request> getRequest(String borrowerAccount, long bookItemId) {
        return jdbc.query(SELECT_REQUEST + " where borrowerAccount=? and bookItemId=?",
                new JdbcRequestRepository.RequestRowMapper(), borrowerAccount, bookItemId);
    }


    private static class RequestRowMapper implements RowMapper<Request> {
        public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Request(rs.getLong("id"), rs.getString("borrowerAccount"), rs.getLong("bookItemId"), rs.getInt("status"),
                    rs.getTimestamp("startTime"), rs.getTimestamp("checkTime"), rs.getLong("checkerId"));
        }
    }

}
