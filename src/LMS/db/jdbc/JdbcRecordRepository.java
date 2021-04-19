package LMS.db.jdbc;

import LMS.db.RecordRepository;
import LMS.domain.Record;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcRecordRepository implements RecordRepository {

    private JdbcTemplate jdbc;

    private static final String SELECT_HISTRORY_RECORDS = "select * from BorrowingRecord where status=true and bookItemId=? order by id desc limit ? offset ?";

    @Autowired
    public JdbcRecordRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long getBookItemHistoryNumber(long bookItemId) {
        return jdbc.queryForLong("select count(id) from BorrowingRecord where status=true and bookItemId=?", bookItemId);
    }

    @Override
    public long getLendingRecordNumber(String account) {
        return jdbc.queryForLong("select count(id) from BorrowingRecord where status=false and borrowerAccount=?", account);
    }

    public long getHistoryLendingRecordNumber(String account) {
        return jdbc.queryForLong("select count(id) from BorrowingRecord where status=true and borrowerAccount=?", account);

    }

    @Override
    public PaginationSupport<Record> findHistoryRecordPage(String borrowerAccount, int pageNo, int pageSize) {
        int totalCount = (int) getHistoryLendingRecordNumber(borrowerAccount);
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<Record>(new ArrayList<Record>(0), 0);

        List<Record> items = jdbc.query("select * from BorrowingRecord where status=true and borrowerAccount=? order by id desc limit ? offset ?",
                new JdbcRecordRepository.RecordRowMapper(), borrowerAccount, pageSize, startIndex);
        PaginationSupport<Record> ps = new PaginationSupport<>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public void returnBook(long bookItemId) {
        jdbc.update("update borrowingRecord set status=true,endTime=? where bookItemId=? and status=false", new Date(),bookItemId);
    }

    @Override
    public Record getBorrowingRecord(long bookItemId) {
        List<Record> records = jdbc.query("select * from BorrowingRecord where status=false and bookItemId=?",
                new JdbcRecordRepository.RecordRowMapper(), bookItemId);
        if (records.size() == 1)
            return records.get(0);
        return null;
    }

    @Override
    public PaginationSupport<Record> findRecordPage(long bookItemId, int pageNo, int pageSize) {

        int totalCount = (int) getBookItemHistoryNumber(bookItemId);
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<Record>(new ArrayList<Record>(0), 0);

        List<Record> items = jdbc.query(SELECT_HISTRORY_RECORDS, new JdbcRecordRepository.RecordRowMapper(), bookItemId, pageSize, startIndex);
        PaginationSupport<Record> ps = new PaginationSupport<>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public List<Record> findLendingRecordPage(String account) {
        int totalCount = (int) getLendingRecordNumber(account);

        if (totalCount < 1)
            return new ArrayList<>(10);

        List<Record> items = jdbc.query("select * from borrowingRecord where status = false and borrowerAccount=?",
                new JdbcRecordRepository.RecordRowMapper(), account);

        return items;
    }

    private static class RecordRowMapper implements RowMapper<Record> {
        public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Record(rs.getLong("id"), rs.getString("borrowerAccount"),
                    rs.getLong("bookItemId"), rs.getBoolean("status"), rs.getTimestamp("startTime"), rs.getTimestamp("endTime"));
        }
    }
}
