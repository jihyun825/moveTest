package kr.or.ddit.aop;

import java.util.Arrays;

import org.apache.commons.io.EndianUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import lombok.extern.slf4j.Slf4j;

// @component 는 스프링빈으로 등록하기 위한 어노테이션
// @Aspect는 어노테이션을 붙여 이클래스가 Aspect를 나타내는 클래스라는걸 명시
// AOPController 클래스 빈 등록시, aOPcontroller로 할지 aoP컨트롤러로 할지가 명확하지않을 수 있어서 aopController라는 이름을 명시해줌
@Component("aopController")
@Aspect 
@Slf4j
public class AOPController {
	
	/*
	 * 1.AOP설명
	 * 
	 * 
	 */
	@Before("execution(* kr.or.ddit.service.IBoardService.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("[@Before] startLog  ");
		// getSignature : 어떤클래스의 어떤메소드가 실행되었는지를 보여줍니다. 
		//					파라미터 타입은 무엇인지 보여줍니다.
		log.info("[@Before] startLog : " + jp.getSignature());
		// getArgs() : 전달된 파라미터 정보를 보여준다.
		// 예)[BoardVO [boardNo=127, title=개똥이]]
		log.info("[@Before] startLog : " + Arrays.toString(jp.getArgs()));
		
		//8.메서드 정보 획득시 사용
		//프록시가 입혀지기전의 원본대상 객체를 가져온다.
		Object targetObject = jp.getTarget();
		log.info("targetObject : " + targetObject);
		//프록시를 가져온다.
		Object thisObject = jp.getThis();
		log.info("thisObject : " + thisObject);
		// 인수를 가져온다.
		Object[] args = jp.getArgs();
		log.info("args.length : " + args.length);
		
	}

	@AfterReturning("execution(* kr.or.ddit.service.IBoardService.*(..))")
	public void logReturning(JoinPoint jp){
		log.info("[@AfterReturning] logReturning  ");
		log.info("[@AfterReturning] logReturning  " + jp.getSignature());
	}
	
	@AfterThrowing(pointcut = "execution(* kr.or.ddit.service.IBoardService.*(..))", throwing = "e")
	public void logThrowing(JoinPoint jp, Exception e){
		log.info("[@AfterThrowing] logException ");
		log.info("[@AfterThrowing] logException  " + jp.getSignature());
		log.info("[@AfterThrowing] logException  " + e);
	}
	
	@After("execution(* kr.or.ddit.service.IBoardService.*(..))")
	public void endLog(JoinPoint jp){
		log.info("[@After] endLog ");
		log.info("[@After] endLog  " + jp.getSignature());
		log.info("[@After] endLog  " + Arrays.toString(jp.getArgs()));
	}
	@Around("execution(* kr.or.ddit.service.IBoardService.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		
		//메소드 실행 직전 시간 체킹
		long startTime = System.currentTimeMillis();
		log.info("[@Around] : " + Arrays.toString(pjp.getArgs()));
		
		//메소드 실행
		Object result = pjp.proceed();
		
		//메소드 실행 직후 시간 체킹
		long endTime = System.currentTimeMillis();
		log.info("[@Around] pjpEnd : " + Arrays.toString(pjp.getArgs()));
		log.info("[@Around]  : " +pjp.getSignature().getName()+"(메소드실행시간 : " + (endTime - startTime)+")");
		return result;
		
	}
	
}
