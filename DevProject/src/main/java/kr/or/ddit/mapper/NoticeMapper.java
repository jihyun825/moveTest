package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface NoticeMapper {

	public int insertNotice(NoticeVO notice);

	public void incrementHit(int boNo);

	public NoticeVO selectNotice(int boNo);

	public int updateNotice(NoticeVO notice);

	public int deleteNotice(int boNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVo);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVo);

}
