package kr.or.ddit.controller.intercept;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter{
	private static final String USER_INFO = "userInfo";
	// 지정된 컨트롤러가 동적하기 이전에 가로채는 역할로 사용한다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandler...!");
		String requestURL = request.getRequestURL().toString(); //http://localhost/login1
		String requestURI = request.getRequestURI();
		
		log.info("requestURL: " + requestURL);
		log.info("requestURI: " + requestURI);
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean: " + method.getBean()); //kr.or.ddit.controller.login.LoginController@3213h123
		log.info("method: " + methodObj); 		//public java.lang.String kr.or.ddit.controller.login.LoginController.loginForm()
		
		//키 'userInfo'를 가지고있는 세션이 존재하면 삭제처리로 초기화
		HttpSession session = request.getSession();
		if(session.getAttribute(USER_INFO)!=null) {
			session.removeAttribute(USER_INFO);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			log.info("postHandle....!");
			
			String requestURL = request.getRequestURL().toString(); //http://localhost/login1
			String requestURI = request.getRequestURI();
			
			log.info("requestURL: " + requestURL);
			log.info("requestURI: " + requestURI);
			
			HandlerMethod method= (HandlerMethod) handler;
			
			Method methodObj = method.getMethod();
			
			log.info("Bean: " + method.getBean()); //kr.or.ddit.controller.login.LoginController@3213h123
			log.info("method: " + methodObj); 		//public java.lang.String kr.or.ddit.controller.login.LoginController.loginForm()
			HttpSession session = request.getSession();
			
			//컨트롤러 메소드를 거쳤다가 postHandle로 넘어오면서 전ㄷ달된 user라는 키에 value로 member가 담긴값이 Model 에 담겨져있다. 
			//그중에 'user'로 넘긴값이 로그인 후 인증된 회원 1명의 정보가 담긴 MemberVo자바빈즈객체가 되고,
			// 객체가 null이 아닌경우 메인화면으로 리다이렉트 처리한다.
			ModelMap modelMap = modelAndView.getModelMap();
			Object member = modelMap.get("user");
			
			if(member != null) {
				log.info("member: " + member);
				log.info("member != null ");
				
				session.setAttribute(USER_INFO,member);
				response.sendRedirect("/");
			}
			
	}

	//@DispatcherServlet의 화면 처리가 완료된 상태에서 처리한다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		log.info("afterCompletion..!");
		
		String requestURL = request.getRequestURL().toString(); //http://localhost/login1
		String requestURI = request.getRequestURI();
		
		log.info("requestURL: " + requestURL);
		log.info("requestURI: " + requestURI);
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean: " + method.getBean()); 
		log.info("method: " + methodObj); 	
	}

}
