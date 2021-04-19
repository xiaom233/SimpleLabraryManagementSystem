package LMS.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常统一处理
 *
 * @author lhzhian
 * @date 2016年4月28日
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static String ERROR_PAGE = "error";

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.setViewName(ERROR_PAGE);
        return mv;
    }

}