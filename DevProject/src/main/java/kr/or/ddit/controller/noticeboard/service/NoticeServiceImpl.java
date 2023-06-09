package kr.or.ddit.controller.noticeboard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.web.TelegramSendController;
import kr.or.ddit.mapper.LoginMapper;
import kr.or.ddit.mapper.NoticeMapper;
import kr.or.ddit.mapper.ProfileMapper;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeFileVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService {
	@Inject
	public NoticeMapper noticeMapper;
	
	@Inject
	private LoginMapper loginMapper;
	
	@Inject
	private ProfileMapper profileMapper;
	
	TelegramSendController tst = new TelegramSendController();
	
	@Override
	public ServiceResult insertNotice(HttpServletRequest req,NoticeVO notice) {
		ServiceResult result = null;
		int status = noticeMapper.insertNotice(notice);
		
		if(status > 0) {
			List<NoticeFileVO> noticeFileList = notice.getNoticeFileList();
			try {
				noticeFileUpload(noticeFileList,notice.getBoNo(),req);
			} catch (IllegalStateException | IOException e1) {
				e1.printStackTrace();
			}
			//성공했다는 메시지를 텔레그램 API를 이용하여 알림!
			try {
				tst.sendGet("정수는 호락호락하지않은 바보", notice.getBoTitle());
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
					
		}
		return result;
	}
	
	private void noticeFileUpload(List<NoticeFileVO> noticeFileList, int boNo, HttpServletRequest req) throws IllegalStateException, IOException {

		String savePath = "/resources/notice/";
		if(noticeFileList != null && noticeFileList.size() > 0) {
			for(NoticeFileVO noticeFileVo:noticeFileList) {
				String saveName = UUID.randomUUID().toString();
				saveName = saveName + "_" + noticeFileVo.getFileName().replace(" ","_");
//				String endFileName = noticeFileVo.getFileName().split("\\.")[1]; // 확장자(디버깅및 확장자 추출 참고)
				
				
				// /resources/notice/1
				String saveLocate = req.getServletContext().getRealPath(savePath + boNo);
				File file = new File(saveLocate);
				if(!file.exists()) {
					file.mkdirs();
				}
				
				saveLocate += "/" + saveName;
				File saveFile = new File(saveLocate);
				
				noticeFileVo.setBoNo(boNo);
				noticeFileVo.setFileSavepath(saveLocate);
				noticeMapper.insertNoticeFile(noticeFileVo);
				
				noticeFileVo.getItem().transferTo(saveFile); // 파일복사
			}
		}
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeMapper.incrementHit(boNo); //조회수 증가
		return noticeMapper.selectNotice(boNo);
	}
	@Override
	public ServiceResult updateNotice(HttpServletRequest req,NoticeVO notice) {
		ServiceResult result = null;
		
		int status = noticeMapper.updateNotice(notice);
		if(status > 0) {
			List<NoticeFileVO> noticeFileList = notice.getNoticeFileList();
			try {
				
				// 1.수정을 위해서 새로 추가된 파일데이터를 먼저 업로드 처리한다.
				noticeFileUpload(noticeFileList, notice.getBoNo(), req);
				// 2.기존 추가되었던 파일들 중, 삭제를 원하는 파일번호들을 가져온다.
				
				Integer[] delNoticeNo = notice.getDelNoticeNo();
				if(delNoticeNo != null) {
					// 3. 삭제를 원하는 파일번호들 하나하나씩을 데이터베이스로 던져서 파일정보를 얻어온다음에
					// 데이터베이스에서 데이터를 삭제하고 서버 업로드 경로에 업로드되어있는 파일데이터를 삭제한다.
					for(int i=0; i<delNoticeNo.length; i++) {
						NoticeFileVO noticeFileVo = noticeMapper.selectNoticeFile(delNoticeNo[i]);
						noticeMapper.deleteNoticeFile(delNoticeNo[i]);
						File file = new File(noticeFileVo.getFileSavepath());
						file.delete();
					}
				}
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	@Override
	public ServiceResult deleteNotice(HttpServletRequest req,int boNo) {
		ServiceResult result = null;
		
		//아래에서 boNo에 해당하는 파일 데이터를 삭제할때 사용할 객체(noticeVo)
		NoticeVO notice = noticeMapper.selectNotice(boNo);
		// 본체인 Notice를 삭제하기전에 File데이터를  먼저삭제하기위한 요청
		noticeMapper.deleteNoticeFileByBoNo(boNo); 
		int status = noticeMapper.deleteNotice(boNo);
		
		if(status > 0) {
			//파일데이터를 완벽하게 삭제처리하면
			List<NoticeFileVO> noticeFileList = notice.getNoticeFileList();
			if(noticeFileList != null && noticeFileList.size() > 0) {
				
				//'/'를 기준으로 잘라진다.
				String[] filePath = noticeFileList.get(0).getFileSavepath().split("/");
				
				// \resources\notice\{bo_no}/..형태를 알고있기에 filePath[0]으로 설정가능
				String path = filePath[0];
				deleteFolder(req,path);
			}
			result = ServiceResult.OK;
			
		}else {
			result = ServiceResult.FAILED;
			
		}
		return result;
	}
	public static void deleteFolder(HttpServletRequest req, String path) {
		File folder = new File(path);
		if(folder.exists()) {
			File[] fileList = folder.listFiles();
			
			for(int i=0; i < fileList.length; i++) {
				if(fileList[i].isFile()) { //폴더안의 파일이 파일 일때,
					fileList[i].delete(); //폴더안에있는 파일을 차례로 삭제
				}else { //폴더안에 있는 파일이 폴더 일때 재귀함수 호출 ( 폴더안으로 들어가려고~)
					deleteFolder(req,fileList[i].getPath());
				}
			}
			folder.delete();
		}
	}

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVo) {
		
		return noticeMapper.selectNoticeCount(pagingVo);
	}
	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVo) {
		// TODO Auto-generated method stub
		return noticeMapper.selectNoticeList(pagingVo);
		// 안녕
	}
	
	@Override
	public ServiceResult idCheck(String memId) {
		ServiceResult result = null;
		DDITMemberVO member = loginMapper.idCheck(memId);
		
		if(member != null) {
			result = ServiceResult.EXIST;
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}
	@Override
	public ServiceResult signup(HttpServletRequest req, DDITMemberVO memberVo) {
		ServiceResult result = null;
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		File file = new File(uploadPath);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String proFileImg = "";
		MultipartFile proFileImgFile = memberVo.getImgFile();
		
		if(proFileImgFile.getOriginalFilename() != null && !proFileImgFile.getOriginalFilename().equals("")) {
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + proFileImgFile.getOriginalFilename();
			uploadPath += "/" +fileName;
			try {
				proFileImgFile.transferTo(new File(uploadPath));
			} catch (IllegalStateException |IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			proFileImg = "/resources/profile/" + fileName; 
			
		}
		memberVo.setMemProfileImg(proFileImg);
		
		int status = loginMapper.signup(memberVo);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	@Override
	public DDITMemberVO loginCheck(DDITMemberVO memberVo) {
		return loginMapper.loginCheck(memberVo);
	}
	
	@Override
	public String idForgetProcess(DDITMemberVO member) {
		// TODO Auto-generated method stub
		return loginMapper.idForgetProcess(member);
	}
	
	@Override
	public String pwForgetProcess(DDITMemberVO member) {
		// TODO Auto-generated method stub
		return loginMapper.pwForgetProcess(member);
	}
	
	@Override
	public NoticeFileVO noticeDownload(int fileNo) {
		NoticeFileVO noticeFileVo = noticeMapper.noticeDownload(fileNo);
		
		if(noticeFileVo == null) {
			throw new RuntimeException();
		}
		noticeMapper.incrementNoticeDowncount(fileNo);
		
		return noticeFileVo;
	}
	
	@Override
	public DDITMemberVO selectMember(DDITMemberVO sessionMember) {
		// TODO Auto-generated method stub
		return profileMapper.selectMember(sessionMember);
	}
	
	@Override
	public ServiceResult profileUpdate(HttpServletRequest req, DDITMemberVO member) {
		ServiceResult result = null;
		
		// 사용자가 수정한 프로필 이미지 정보에 따라서 프로필 이미지정보값을 설정 후 memberVO에 셋팅해서 전달한다.
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String proFileImg = "";
		MultipartFile proFileImgFile = member.getImgFile();
		
		if(proFileImgFile.getOriginalFilename() != null && !proFileImgFile.getOriginalFilename().equals("")) {
			String fileName = UUID.randomUUID().toString();
			fileName += "_" +proFileImgFile.getOriginalFilename();
			uploadPath += "/" + fileName ;
			try {
				proFileImgFile.transferTo(new File(uploadPath));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			proFileImg = "/resources/profile/" + fileName;
					
		}
		
		//선택한 프로필 이미지가 존재하지않으면 "" 공백이 넘어가고, 
		// 프로필 이미지가 존재하면 업로드 경로와 파일명으로 구성된 경로가 넘어간다.
		member.setMemProfileImg(proFileImg);
		int status = profileMapper.profileUpdate(member);
		
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
