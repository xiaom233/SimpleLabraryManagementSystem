package LMS.simpleTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class accountStatusTag extends SimpleTagSupport {

    private boolean status;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void doTag() throws JspException, IOException {

        JspWriter out = getJspContext().getOut();
        if(!status)
            out.println("<span style=\"color:green\">正常</span>");
        else
            out.println("<span style=\"color:red\">锁定</span>");
    }
}
