package LMS.domain.body;

import LMS.domain.Request;

import java.util.Date;

public class ShowRequestBody {

    private long id;
    private String borrowerAccount;
    private String bookName;
    private String bookCode;
    private int status;
    private Date startTime;
    private Date endTime;
    private String checkerAccount;

    public ShowRequestBody(long id, String borrowerAccount, String bookName, String bookCode, int status, Date startTime, Date endTime, String checkerAccount) {
        this.id = id;
        this.borrowerAccount = borrowerAccount;
        this.bookName = bookName;
        this.bookCode = bookCode;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.checkerAccount = checkerAccount;
    }

    public ShowRequestBody(Request request,String bookName,String bookCode,String checkerAccount) {
        this.id = request.getId();
        this.borrowerAccount = request.getBorrowerAccount();
        this.bookName = bookName;
        this.bookCode=bookCode;
        this.status = request.getStatus();
        startTime = request.getStartTime();
        this.endTime = request.getCheckTime();
        this.checkerAccount = checkerAccount;
    }

    public String getCheckerAccount() {
        return checkerAccount;
    }

    public void setCheckerAccount(String checkerAccount) {
        this.checkerAccount = checkerAccount;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
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
}
