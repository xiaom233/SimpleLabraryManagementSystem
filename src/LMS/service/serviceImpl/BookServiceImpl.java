package LMS.service.serviceImpl;

import LMS.db.BookRepository;
import LMS.domain.BookEntity;
import LMS.service.BookService;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public BookEntity findOne(long bookId) {
        return bookRepository.findOne(bookId);
    }

    @Override
    public void deleteBookItem(long bookId) {
        bookRepository.deleteBookItem(bookId);
    }

    @Override
    public BookEntity save(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public void modifyInfo(String bookName, String author, String publisher, String publishTime, String description, long id) {
        bookRepository.modifyInfo(bookName, author, publisher, publishTime, description, id);
    }

    @Override
    public PaginationSupport<BookEntity> findPage(int pageNo, int pageSize) {
        return bookRepository.findPage(pageNo,pageSize);
    }
}
