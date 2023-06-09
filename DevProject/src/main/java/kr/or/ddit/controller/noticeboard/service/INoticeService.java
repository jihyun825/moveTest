package kr.or.ddit.controller.noticeboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeFileVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeService {

	public ServiceResult insertNotice(HttpServletRequest req,NoticeVO notice);

	public NoticeVO selectNotice(int boNo);

	public ServiceResult updateNotice(HttpServletRequest req,NoticeVO notice);

	public ServiceResult deleteNotice(HttpServletRequest req,int boNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVo);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVo);

	public ServiceResult idCheck(String memId);

	public ServiceResult signup(HttpServletRequest req, DDITMemberVO memberVo);

	public DDITMemberVO loginCheck(DDITMemberVO memberVo);

	public String idForgetProcess(DDITMemberVO member);

	public String pwForgetProcess(DDITMemberVO member);

	public NoticeFileVO noticeDownload(int fileNo);

	public DDITMemberVO selectMember(DDITMemberVO sessionMember);

	public ServiceResult profileUpdate(HttpServletRequest req, DDITMemberVO member);

}
