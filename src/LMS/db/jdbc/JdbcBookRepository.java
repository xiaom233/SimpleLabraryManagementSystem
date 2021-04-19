package LMS.db.jdbc;

import LMS.domain.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import LMS.db.BookRepository;
import LMS.utils.PaginationSupport;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
    id       integer identity,
    isbn varchar(13) unique not null,
    bookName varchar(50) not null,
    author varchar(50),
    publisher varchar(50),
    publishTime varchar(30),
    description varchar(100),
    copyNumber integer default 0,
    remain integer default 0,
    bookCover varchar(100),
    primary key(id)
 */
@Repository
public class JdbcBookRepository implements BookRepository {


    private JdbcTemplate jdbc;
    private static final String INSERT_BOOK = "insert into book (isbn, bookName, author, publisher, publishTime,description,copyNumber,remain,bookCover) " +
            "values (?,?, ?, ?, ?, ?,?,?,?)";

    private static final String SELECT_BOOK = "select * from book ";

    private static final String SELECT_BOOKS = "select * " +
            "from book order by id desc limit ? offset  ?";


    @Autowired
    public JdbcBookRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long count() {
        return jdbc.queryForLong("select count(id) from book");
    }

    @Override
    public void reduceRemain(long bookId) {
        jdbc.update("update Book set remain=remain-1 where id=?",bookId);
    }

    @Override
    public BookEntity findOne(long bookId) {
        return jdbc.queryForObject(SELECT_BOOK + " where id=?", new JdbcBookRepository.BookRowMapper(), bookId);
    }

    @Override
    public BookEntity save(BookEntity bookEntity) {
        if (jdbc.query(SELECT_BOOK + "where isbn=?", new JdbcBookRepository.BookRowMapper(), bookEntity.getIsbn()).size()!=0)
            return null;

        jdbc.update(INSERT_BOOK, bookEntity.getIsbn(), bookEntity.getBookName(), bookEntity.getAuthor(),
                bookEntity.getPublisher(), bookEntity.getPublishTime(), bookEntity.getDescription(), 0, 0, bookEntity.getBookCover());
        return jdbc.queryForObject(SELECT_BOOK + "where isbn=?", new JdbcBookRepository.BookRowMapper(), bookEntity.getIsbn());
    }

    @Override
    public PaginationSupport<BookEntity> findPage(int pageNo, int pageSize) {
        int totalCount = (int) count();
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<BookEntity>(new ArrayList<BookEntity>(0), 0);

        List<BookEntity> items = jdbc.query(SELECT_BOOKS, new JdbcBookRepository.BookRowMapper(), pageSize, startIndex);
        PaginationSupport<BookEntity> ps = new PaginationSupport<BookEntity>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public void deleteBookItem(long id) {
        jdbc.update("update book set remain=remain-1,copyNumber=copyNumber-1 where id=?", id);
    }

    @Override
    public void passRequest(long id) {
        jdbc.update("update book set remain=remain-1 where id=?", id);
    }

    @Override
    public BookEntity modifyInfo(String bookName, String author, String publisher, String publishTime, String description, long id) {
        jdbc.update("update book set bookName=?,author=?,publisher=?,publishTime=?,description=? where id=?",
                bookName, author, publisher, publishTime, description, id);
        return null;
    }

    @Override
    public String getBookNameByBookItemId(long bookItemId) {
        return jdbc.queryForObject("select * from book ,bookItem where book.id=bookItem.bookId and bookItem.id=?",
                new JdbcBookRepository.BookRowMapper(), bookItemId).getBookName();
    }


    @Override
    public void addRemain(long bookId) {
        jdbc.update("update book set remain=remain+1 where id=?", bookId);
    }

    @Override
    public void addBookItem(long bookId) {
        jdbc.update("update book set remain=remain+1,copyNumber=copyNumber+1 where id=?", bookId);
    }

    private static class BookRowMapper implements RowMapper<BookEntity> {
        public BookEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BookEntity(rs.getLong("id"), rs.getString("isbn"), rs.getString("bookName"), rs.getString("author"),
                    rs.getString("publisher"), rs.getString("publishTime"), rs.getString("description"), rs.getInt("copyNumber"),
                    rs.getInt("remain"), rs.getString("bookCover"));
        }
    }


}
