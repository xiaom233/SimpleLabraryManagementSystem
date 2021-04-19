package LMS.domain;

/**
 * LMS图书馆管理系统 书籍类
 *
 * @author liuziyu
 */
public class BookEntity {

    private long id;
    private String isbn;
    private String bookName;
    private String author;
    private String publisher;
    private String publishTime;
    private String description;
    private int copyNumber;
    private int remain;
    private String bookCover;

    public BookEntity() {
    }

    public BookEntity(String isbn, String bookName, String author, String publisher, String publishTime, String description) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.publishTime = publishTime;
        this.description = description;
    }

    public BookEntity(long id, String isbn, String bookName, String author, String publisher, String publishTime, String description, int copyNumber, int remain, String bookCover) {
        this.id = id;
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.publishTime = publishTime;
        this.description = description;
        this.copyNumber = copyNumber;
        this.remain = remain;
        this.bookCover = bookCover;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
}
