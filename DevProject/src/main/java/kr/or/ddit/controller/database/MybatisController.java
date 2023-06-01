package kr.or.ddit.controller.database;

public class MybatisController {
		/*
		 *  12장 마이바티스
		 *   1. 마이바티스란?
		 *   	1) 뭔데?
		 *    - 마이바티스는 자바 퍼시스턴스 프레임워크의 하나로 XML 서술자나 어노테이션을 사용하여 저장ㅍ프로시저나 SQL문으로 객체들을 연결시킨다.
		 *    마이바티스는 Apache 라이선스 2.0배포되는 자유 소프트웨어입니다.
		 *    
		 *     2) 마이바티스를 사용함으로써 얻을 수 있는 이점
		 *      - SQL 의 체계적인 관리
		 *      - 자바 객체와 SQL 입출력값의 투명한 바인딩
		 *      - 동적 SQL조합
		 *      
		 *      3) 마이바티스 설정
		 *       3-1 의존관계정의
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 3. 별칭적용
		 * 
		 *  - TypeAlias 로 맵핑 파일에서 반복적으로 사용될 패키지의 이름을 정의한다.
		 *  1) 마이바티스 설정
		 *  
		 *   1-1) mybatisAlias.xml 설정
		 *    - typeAlias 설정을한다.
		 *   1-2) boardMapper_SQL.xml 의 type의 설정을 별칭으로 설정 
		 *   - mybatisAlias가 설정되어있지않는 경우엔 타입으로 설정하고자 하는 타입 형태를 패키지명이 포함되어있는 구조로 설정해야한다.
		 *   -  쿼리 태그에 각각 셋팅한 패키지명 대신 alias로 설정한 별칭으로 대체합니다.
		 *   
		 * 4. '_'로 구분된 컬럼명 자동매핑
		 * 
		 *  - 마이바티스 설정의 mapUnderscoreToCamelCase 프로퍼티 값을 true로 지정하면 '_' 로 구분된 컬럼명을 소문자 낙타표기법의 프로퍼티명으로 자동 매핑 할수있다.
		 *   '_' 가 포함되어있는 데이터베이스 컬러명을 camel긱법 셋팅으로 인해서 bo_no가 boNo로 처리된다.
		 *   
		 *    1) 마이바티스 설정 
		 *     1-1) mybatisAlias.xml
		 *      - <setting>
		 *      	<setting name="mapUnderscoreToCamelCase" value="true"> 설정추가
		 *      - </setting>
		 *      
		 *      1-2) 매핑 파일 수정(기존 Mapper xml에 설정된 컬럼은 board_no와 같은 형태로 되어있다고 가정)
		 *       - as boardNo, as regDate 로 컬럼명을 대체하는 alias가 설정되어있는 경우엔 as부분을 삭제
		 *       
		 * 5. 기본키 취득
		 *  - 마이바티스는 userGeneratedKeys속성을 이용하여 insert할때 데이터베이스 측에서 채번된 기본키를 취득할 수 있다.
		 *    1) 마이바티스 설정
		 *    
		 *     1-1) 매핑 파일 수정(boardNaooer_SQL.xml)
		 *      - create부분의 속성 추가
		 *       > useGeneratedKeys = "trye" keyProperty = "boardNo" 속성을 추가로 사용
		 *       
		 *       
		 *       아래  insert 쿼리가 실행되기전 selectKey태그 내에 있는 쿼리가 먼저 실행되어 최신의 boardNo를 생성하고
		 *       생성된 boardNo를 slq쿼리가 담겨있는  insert태그까지 넘어올때 넘겨주고 있는 파라미터(board)의 property인 boardNo에 셋팅한다.
		 *       셋팅된 boardNo를 아래에서 insert시 등록값으로 사용하고 board라는 자바빈즈 객체에 담겨 최종 컨트롤러까지 넘어간다.
		 *       
		 *       *** seq_board.nextval 대신 현재의 seq번호를 가져오기위해 currval를 사용하게되는 경우가 있다.
		 *        이때 사용시 주의사항이 있다.
		 *        
		 *        -select seq_board.currbval from dual
		 *        >  위 셀렉트 쿼리 사용시, currval 를 사용하는데 있어서 사용불가에 대한 에러가 발생할 수있다.
		 *        currval를 사용할때에는 select seq_board.nextvalfrom dual로 먼저 최초로 실행한 후,
		 *        select seq)board.currval from dual 로 사용하면 에러가없음
		 *        같은 세션내에서의 실행이 이뤄어지지않았기때문에 currval로 데이터를 가져오는데 에러가 발생한다.
		 *        
		 *        #그럼에도 나는 가져와야겠다! 다필요없고 걍 내놔 하고싶으면
		 *         > select last_number from user_sequences where sequence)name="시퀀스명";
		 *         > select last_number from user_sequences where sequence)name="seq_board";
		 *         쿼리로 가져오면 된다.(권고사항ㅎ아님(
		 *        2-2 동적 SQL
		 *         - 마이바티스는 동적SQl을 조립하는 구조를 지원하고 있으며, SQL조립 규칙을 매핑 파일에 정의할수있다.
		 *         1) 동적으로  sql을 조립하기위한 sql요소
		 *          = <where>
		 *           > where절 앞뒤에 내용을 더 추가하거나 삭제할때 사용하는 요소
		 *           -<if>
		 *            > 조건을 만족할때만 SQL을 조립할 수 있게 만드는요소
		 *           - <choose> 
		 *           >  여러선택 항목에서 조건에 만족할때만 조립할수있게 만드는 요소
		 *           - <foreach>
		 *           > 컬렉션이나 배열에 대해 반복처리를 하기위한 요소
		 *            - <set>
		 *            > set절 앞 뒤에 내용을 더 추가하거나 삭제할때 사용하는 요소
		 *            7. 일대다 관계 테이블 매핑
		 *             - 마이바티스 기능을 활용하여 매핑 파일을 '적절하게 정의하면 일대다 관곙테이블 매핑을 쉽게 처리할 수 있다.
		 *             
		 *              1) 게시판 구현 설명
		 *              
		 *              	-회원등록 화면 컨트롤러 만들기 (
		 *           
		 *         
		 *     
		 */

}
