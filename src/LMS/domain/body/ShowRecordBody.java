package LMS.domain.body;

import LMS.domain.Record;

import java.util.Date;

public class ShowRecordBody {

    private long id;
    private String borrowerAccount;
    private String bookName;
    private String bookCode;
    private boolean status;
    private Date startTime;
    private Date endTime;

    public ShowRecordBody(Record record,String bookName,String bookCode){

        this.id= record.getId();
        this.borrowerAccount= record.getBorrowerAccount();
        this.bookName=bookName;
        this.bookCode = bookCode;
        this.status = record.isStatus();
        this.startTime=record.getStartTime();
        this.endTime=record.getEndTime();
    }

    @Override
    public String toString() {
        return "ShowRecordBody{" +
                "id=" + id +
                ", borrowerAccount='" + borrowerAccount + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookCode='" + bookCode + '\'' +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
