package LMS.simpleTag;

import LMS.db.BookRepository;
import LMS.db.jdbc.JdbcBookRepository;
import LMS.domain.BookEntity;
import LMS.domain.BookItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class borrowingCartTag extends SimpleTagSupport {


    public void doTag() throws JspException, IOException {


        JspWriter out = getJspContext().getOut();

        PageContext pageContext = (PageContext) this.getJspContext();
        HttpSession session = pageContext.getSession();

        Integer itemCount = (Integer) session.getAttribute("itemCount");
        if (itemCount == null)
            itemCount = new Integer(0);
        if (itemCount == 0)
            out.println("nothing!");
        else{
            for (int i = 0; i < itemCount; i++) {
                BookItem bookItem = (BookItem) session.getAttribute("item" + i);
                String name = (String) session.getAttribute("name" + i);
                getJspContext().setAttribute("bookItem", bookItem);
                getJspContext().setAttribute("name", name);
                getJspBody().invoke(null);
            }
        }

    }
}

