package LMS.service;

import LMS.domain.Record;
import LMS.domain.body.ShowRecordBody;
import LMS.utils.PaginationSupport;

import java.util.List;

public interface RecordService {

    List<ShowRecordBody> getBorrowerLendingRecords(String account);

    PaginationSupport<ShowRecordBody> getBorrowerHistoryRecords(String account,int pageNo,int pageSize);

    PaginationSupport<Record> findRecordPage(long bookItemId, int pageNo, int pageSize);

    Record getBorrowingRecord(long bookItemId);
}
