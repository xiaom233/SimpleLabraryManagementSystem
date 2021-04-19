package LMS.domain;

import java.util.Date;

public class Record {

    private long id;
    private String borrowerAccount;
    private long bookItemId;
    private boolean status;
    private Date startTime;
    private Date endTime;

    public Record() {
    }

    public Record(long id, String borrowerAccount, long bookItemId, boolean status,
                  Date startTime, Date endTime) {
        this.id = id;
        this.borrowerAccount = borrowerAccount;
        this.bookItemId = bookItemId;
        this.status = status;
        this.startTime = startTime;
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

    public long getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(long bookItemId) {
        this.bookItemId = bookItemId;
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

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", borrowerAccount=" + borrowerAccount +
                ", bookItemId=" + bookItemId +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
