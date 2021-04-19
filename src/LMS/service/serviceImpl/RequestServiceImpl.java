package LMS.service.serviceImpl;

import LMS.db.BookItemRepository;
import LMS.db.BookRepository;
import LMS.db.RequestRepository;
import LMS.db.UserRepository;
import LMS.domain.BookItem;
import LMS.domain.Request;
import LMS.domain.UserEntity;
import LMS.domain.body.ShowRequestBody;
import LMS.service.RequestService;
import LMS.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookItemRepository bookItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createRequest(String account, long bookItemId) {
        requestRepository.CreateBorrowingRequest(account, bookItemId);
    }

    @Override
    public void passRequest(long requestId, long checkerId) {
        Request request = requestRepository.getRequestById(requestId);
        BookItem bookItem = bookItemRepository.findBookItem(request.getBookItemId());

        requestRepository.passRequest(requestId, checkerId);//通过请求
        requestRepository.deleteOtherRequests(request.getBookItemId(),checkerId);//拒绝对同一本书副本的请求
        bookRepository.reduceRemain(bookItem.getBookId());//库存减少1
        bookItemRepository.getOut(request.getBookItemId());//对应实体书出库
    }

    @Override
    public void denyRequest(long requestId, long checkerId) {
        requestRepository.denyRequest(requestId,checkerId);
    }

    @Override
    public PaginationSupport<ShowRequestBody> getCurrentRequests(int pageNo, int pageSize) {

        List<Request> requestList = requestRepository.getCurrentRequests(pageNo, pageSize).getItems();
        List<ShowRequestBody> requests = new ArrayList<>(requestList.size());

        for (Request r : requestList) {
            String bookName = bookRepository.getBookNameByBookItemId(r.getBookItemId());
            BookItem bookItem = bookItemRepository.findBookItem(r.getBookItemId());
//            UserEntity checker=userRepository.findById(r.getCheckerId());
            requests.add(new ShowRequestBody(r, bookName, bookItem.getBookCode(),null));
        }

        return new PaginationSupport<ShowRequestBody>(requests,requestList.size());
    }

    @Override
    public PaginationSupport<ShowRequestBody> getBorrowerRequests(String account,int pageNo, int pageSize) {

        PaginationSupport<Request> requestList = requestRepository.getBorrowerRequests(account,pageNo,pageSize);
        List<ShowRequestBody> requests = new ArrayList<>(requestList.getItems().size());

        for (Request r : requestList.getItems()) {
            String bookName = bookRepository.getBookNameByBookItemId(r.getBookItemId());
            BookItem bookItem = bookItemRepository.findBookItem(r.getBookItemId());
            if(r.getCheckerId()==0){
                requests.add(new ShowRequestBody(r, bookName, bookItem.getBookCode(),null));
            }
            else{
                UserEntity checker=userRepository.findById(r.getCheckerId());
                requests.add(new ShowRequestBody(r, bookName, bookItem.getBookCode(),checker.getAccount()));
            }
        }

        return new PaginationSupport<ShowRequestBody>(requests,requests.size(),pageNo,pageSize);
    }

}
