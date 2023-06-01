package kr.or.ddit.controller.database;

public class DatabaseConnectController {
	/*
	 * 11장 데이터베이스 연동
	 * 
	 * 1. oracle 11g설치(클라이언트, 서버)
	 * 2. oracle SQLDeveloper설치
	 * 3. 데이터소스 설정
	 *  - 애플리케이션이 데이터베이스에 접근하기 위한 추상화된 연결을 제공하는 역할을 한다.
	 *  
	 *   스프링에서 설정할 수 있는 데이터소스
	 *    - JDBC드라이버를 통해 선언된 데이터 소스
	 *     - JNDI 에 등록된 데이터소스
	 *      - DBCP2에 등록된 데이터 소스
	 *      
	 *      ** JNDI란? 
	 *      
	 *      -JNDI(java naming and Directory Interface)
	 *       >  디렉토리 서비스에서 제공하는 데이터 및 객체를 발견(discover)하고 참고하기위한 자바api이다.
	 *       
	 *       1) 데이터베이스JDBC라이브러리 설정 
	 *       - pom.xml 에서 의존성추가
	 *       2) 데이터소스 설정
	 *        - root-context.xml설정\
	 *  4. CRUD게시판
	 *   - 데이터베이스 데이터에 접근하여 처리하는 방식(게시판구성)
	 *    > 스프링 JDBC
	 *    > JPA
	 *    > 마이바티스
	 *    
	 *    ** 우리는 마이바티스를 이용한 CRUD 게시판을 진행하도록 합니다.
	 *    
	 *    1) Oracle Database에서 운용할 계정을 생성한다.
	 *    2) 생성 SQL을 통해 관련 테이블을 생성한다.
	 *    3) 게시판 작성을 위한 페이지(기본화면)
	 *     - 등록화면
	 *     - 등록 처리 후 화면
	 *     - 목록화면
	 *     - 상세보기화면
	 *     - 수정화면
	 *     - 수정처리 후 화면
	 *     - 삭제처리 후 화면
	 *     
	 *   5. JDBC 스프링
	 *    - SQL로만 데이터베이스를 쉽게 처리할 수 있도록 도와주는JDBCTemplate클래스를 제공한다.
	 *     1. JDBCTemplate 클래스가 제공하는 주요 메서드
	 *      queryForObject 
	 *       - 하나의 결과 레코드 중에서 하나의 컬럼값을 가져온다.
	 *       
	 *       queryForMap
	 *       - 하나의 결과 레코드 정보를 Map형태로 매핑할 수 있다.
	 *       
	 *      queryForList
	 *       - 여러개의 결과 레코드를 처리할 수 있다.
	 *       
	 *       query 
	 *       - ResultSetExtractor, RowCallvackHAndler와 함께 조회할때 사용한다.
	 *       update
	 *        - 데이터를 변경하는 SQL을 실행할 때 사용한다.
	 *        
	 *2. 스프링 JDBC설정
	 *        
	 * 
	 */
}
