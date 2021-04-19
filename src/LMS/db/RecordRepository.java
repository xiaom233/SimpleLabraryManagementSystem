package LMS.db;

import LMS.domain.Record;
import LMS.utils.PaginationSupport;

import java.util.List;

public interface RecordRepository {

    /**
     * 该图书被借出的总数
     *
     * @param bookItemId
     * @return
     */
    long getBookItemHistoryNumber(long bookItemId);

    /**
     * 查询正在借出的图书实体
     *
     * @param bookItemId
     *      图书实体id
     * @return
     *      借出记录
     */
    Record getBorrowingRecord(long bookItemId);

    /**
     * 显示借出记录页面
     *
     * @param bookItemId
     *      图书id
     * @param pageNo
     *      页码
     * @param pageSize
     *      页面容量
     * @return
     *      页面
     */
    PaginationSupport<Record> findRecordPage(long bookItemId,int pageNo,int pageSize);


    /**
     * 查询
     * @param account
     * @return
     */
    List<Record> findLendingRecordPage(String account);

    long getLendingRecordNumber(String account);

    PaginationSupport<Record> findHistoryRecordPage(String borrowerAccount,int pageNo,int pageSize);

    void returnBook(long bookItemId);
}
