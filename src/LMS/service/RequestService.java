package LMS.service;

import LMS.domain.body.ShowRequestBody;
import LMS.utils.PaginationSupport;

public interface RequestService {

    void createRequest(String account,long bookItemId);

    void passRequest(long requestId, long checkerId);

    void denyRequest(long requestId, long checkerId);

    PaginationSupport<ShowRequestBody> getCurrentRequests(int pageNo, int pageSize);

    PaginationSupport<ShowRequestBody> getBorrowerRequests(String account,int pageNo,int pageSize);


}
