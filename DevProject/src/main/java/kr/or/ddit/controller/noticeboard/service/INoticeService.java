package kr.or.ddit.controller.noticeboard.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeService {

	public ServiceResult insertNotice(NoticeVO notice);

	public NoticeVO selectNotice(int boNo);

	public ServiceResult updateNotice(NoticeVO notice);

	public ServiceResult deleteNotice(int boNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVo);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVo);

}
