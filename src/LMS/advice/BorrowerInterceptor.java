package LMS.advice;

import LMS.domain.UserEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * �����������Է���·�����е�¼У��
 * 
 * @author liyong
 * @version 1.0
 *
 */
public class BorrowerInterceptor implements HandlerInterceptor {

	private final static Log log = LogFactory.getLog(BorrowerInterceptor.class);

	/**
	 * 
	 * afterCompletion��������һ���������֮�󱻵��� ������Ⱦ��ͼ֮��
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception exception) throws Exception {
		log.debug("3.Called after rendering the view");

	}

	/**
	 * 
	 * postHandle��������controller��Ĵ���������֮�� ��������Ⱦ��ͼ֮ǰ���С�
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView model)
			throws Exception {
		log.debug("2.Called after handler method request completion, before rendering the view");

	}

	/**
	 * 
	 * preHandle����һ����������Ĵ�����������֮ǰִ�д˷���
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		log.debug("1.Called before handler method");
		// ��ȡsession
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity)session.getAttribute("user");
		// �ж�session���Ƿ����û����ݣ�����У��򷵻�true����������ִ��
		if (user != null && user.getRole() != null) {
			return true;
		}
		// ������������ת������¼ҳ��
		response.sendRedirect(request.getContextPath()+"/");
		return false;
	}

}
