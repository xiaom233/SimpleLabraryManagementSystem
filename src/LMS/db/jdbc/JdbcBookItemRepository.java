package LMS.db.jdbc;

import LMS.db.BookItemRepository;
import LMS.db.BookRepository;
import LMS.db.RecordRepository;
import LMS.domain.BookItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcBookItemRepository implements BookItemRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcBookItemRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public BookItem findBookItem(long bookItemId) {
        return jdbc.queryForObject("select * from BookItem where Id=?", new JdbcBookItemRepository.BookItemRowMapper(), bookItemId);
    }

    @Override
    public BookItem findBookItem(String bookCode) {

        List<BookItem> list = jdbc.query("select * from BookItem where bookCode=?", new JdbcBookItemRepository.BookItemRowMapper(), bookCode);
        if (list == null || list.size() == 0)
            return null;
        else
            return list.get(0);
    }

    @Override
    public List<BookItem> findBookItems(long bookId) {
        return jdbc.query("select * from BookItem where delete=false and bookId=?", new JdbcBookItemRepository.BookItemRowMapper(), bookId);
    }

    @Override
    public BookItem modifyInfo(String location, long id) {
        jdbc.update("update BookItem set location=? where id=?", location, id);
        return null;
    }

    @Override
    public void getOut(long bookItemId) {
        jdbc.update("update BookItem set status=false where id=?", bookItemId);
    }

    @Override
    public boolean returnBook(String bookCode) {

        BookItem bookItem = findBookItem(bookCode);
        if (bookItem == null || bookItem.isStatus()) {//输错了或者没借出
            return false;
        } else {
            jdbc.update("update BookItem set status=true where bookCode=?", bookCode);
            bookRepository.addRemain(bookItem.getBookId());
            recordRepository.returnBook(bookItem.getId());
            return true;
        }

    }

    @Override
    public void addBookItem(String bookCode, long bookId) {
        jdbc.update("insert into bookItem(bookId,bookCode,status) values(?,?,true)", bookId, bookCode);
    }

    @Override
    public void deleteBookItem(long bookItemId) {
        jdbc.update("update bookItem set delete=true where id=?", bookItemId);
    }

    @Override
    public long getBookItemCount(long bookId) {
        return jdbc.queryForLong("select count(id) from BookItem where bookId=?", bookId);
    }

    private static class BookItemRowMapper implements RowMapper<BookItem> {
        public BookItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BookItem(rs.getLong("id"), rs.getString("bookCode"),
                    rs.getLong("bookId"), rs.getString("location"), rs.getBoolean("status"), rs.getBoolean("delete"));
        }
    }
}
