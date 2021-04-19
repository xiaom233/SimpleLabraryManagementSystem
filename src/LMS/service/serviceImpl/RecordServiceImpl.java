package LMS.service.serviceImpl;

import LMS.db.BookItemRepository;
import LMS.db.BookRepository;
import LMS.db.RecordRepository;
import LMS.domain.Record;
import LMS.domain.body.ShowRecordBody;
import LMS.service.RecordService;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookItemRepository bookItemRepository;

    @Override
    public List<ShowRecordBody> getBorrowerLendingRecords(String account) {
        List<Record> recordList = recordRepository.findLendingRecordPage(account);
        List<ShowRecordBody> records = new ArrayList<>(recordList.size());

        for (int i = 0; i < recordList.size(); i++) {
            String name = bookRepository.getBookNameByBookItemId(recordList.get(i).getBookItemId());
            String code = bookItemRepository.findBookItem(recordList.get(i).getBookItemId()).getBookCode();
            records.add(new ShowRecordBody(recordList.get(i), name, code));
        }
        return records;
    }

    @Override
    public PaginationSupport<ShowRecordBody> getBorrowerHistoryRecords(String account,int pageNo,int pageSize) {

        PaginationSupport<Record> recordList = recordRepository.findHistoryRecordPage(account, pageNo, pageSize);

        List<ShowRecordBody> records = new ArrayList<>(recordList.getItems().size());

        for (int i = 0; i < recordList.getItems().size(); i++) {
            String name = bookRepository.getBookNameByBookItemId(recordList.getItems().get(i).getBookItemId());
            String code = bookItemRepository.findBookItem(recordList.getItems().get(i).getBookItemId()).getBookCode();
            records.add(new ShowRecordBody(recordList.getItems().get(i), name, code));
        }

        return new PaginationSupport<ShowRecordBody>(records,recordList.getTotalCount(),recordList.getPageSize(),recordList.getStartIndex());
    }

    @Override
    public PaginationSupport<Record> findRecordPage(long bookItemId, int pageNo, int pageSize) {
        return recordRepository.findRecordPage(bookItemId,pageNo,pageSize);
    }

    @Override
    public Record getBorrowingRecord(long bookItemId) {
        return recordRepository.getBorrowingRecord(bookItemId);
    }
}
