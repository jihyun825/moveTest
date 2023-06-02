package kr.or.ddit.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeVO {
	
	private int boNo;
	private String boTitle;
	private String boContent;
	private String boWriter;
	private String boDate;
	private int boHit;
	
	private Integer[] delNoticeNo; //삭제할 파일에 대한 번호
	private MultipartFile[] boFile; // 게시글 등록시 여러파일을 선택할 변수
	
	

}
