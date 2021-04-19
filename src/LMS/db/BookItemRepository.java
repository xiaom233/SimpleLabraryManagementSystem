package LMS.db;

import LMS.domain.BookEntity;
import LMS.domain.BookItem;

import java.util.List;

public interface BookItemRepository {

    BookItem findBookItem(long bookItemId);

    BookItem findBookItem(String bookCode);

    List<BookItem> findBookItems(long bookId);

    BookItem modifyInfo( String location,long id);

    void getOut(long bookItemId);

    boolean returnBook(String bookCode);

    void addBookItem(String bookCode,long bookId);

    void deleteBookItem(long bookItemId);

    long getBookItemCount(long bookId);

}
