package LMS.domain;

public class BookItem {

    private long id;
    private String bookCode;
    private long bookId;
    private String location;
    private boolean status;
    private boolean delete;

    public BookItem(long id, String bookCode, long bookId, String location, boolean status,boolean delete) {
        this.id = id;
        this.bookCode = bookCode;
        this.bookId = bookId;
        this.location = location;
        this.status = status;
        this.delete = delete;
    }


    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "id=" + id +
                ", bookCode='" + bookCode + '\'' +
                ", bookId=" + bookId +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
