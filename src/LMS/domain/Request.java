package LMS.domain;

import java.util.Date;

public class Request {

    private long id;
    private String borrowerAccount;
    private long bookItemId;
    private int status;
    private Date startTime;
    private Date checkTime;
    private long checkerId;

    public Request() {
    }

    public Request(long id, String borrowerAccount, long bookItemId, int status, Date startTime,
                   Date checkTime, long checkerId) {
        this.id = id;
        this.borrowerAccount = borrowerAccount;
        this.bookItemId = bookItemId;
        this.status = status;
        this.startTime = startTime;
        this.checkTime = checkTime;
        this.checkerId = checkerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBorrowerAccount() {
        return borrowerAccount;
    }

    public void setBorrowerAccount(String borrowerAccount) {
        this.borrowerAccount = borrowerAccount;
    }

    public long getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(long bookItemId) {
        this.bookItemId = bookItemId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(long checkerId) {
        this.checkerId = checkerId;
    }
}
