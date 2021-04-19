package LMS.service.serviceImpl;

import LMS.db.BookItemRepository;
import LMS.db.BookRepository;
import LMS.db.RequestRepository;
import LMS.domain.BookEntity;
import LMS.domain.BookItem;
import LMS.domain.Request;
import LMS.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookItemServiceImpl implements BookItemService {

    @Autowired
    private BookItemRepository bookItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<BookItem> findBookItems(long bookId) {
        return bookItemRepository.findBookItems(bookId);
    }

    @Override
    public BookItem findBookItem(long bookItemId) {
        return bookItemRepository.findBookItem(bookItemId);
    }

    @Override
    public BookItem modifyInfo( String location, long id) {

        bookItemRepository.modifyInfo(location, id);
        return null;
    }

    @Override
    public boolean returnBook(String bookCode) {

        return bookItemRepository.returnBook(bookCode);
    }

    @Override
    public void addBookItem(long bookId) {

        bookRepository.addBookItem(bookId);
        BookEntity book = bookRepository.findOne(bookId);

        String newBookCode = book.getIsbn() + "-" + (bookItemRepository.getBookItemCount(bookId) + 1);
        bookItemRepository.addBookItem(newBookCode, bookId);
    }

    @Override
    public void deleteBookItem(long bookItemId) {
        bookItemRepository.deleteBookItem(bookItemId);
    }

    @Override
    public boolean getBookItemLendingStatus(String account, long bookItemId) {
        List<Request> request = requestRepository.getRequest(account, bookItemId);
        if (request != null) {
            for (Request r : request) {
                if (r.getStatus() == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
