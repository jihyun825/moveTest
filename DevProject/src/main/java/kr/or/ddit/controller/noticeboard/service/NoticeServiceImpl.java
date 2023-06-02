package kr.or.ddit.controller.noticeboard.service;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.web.TelegramSendController;
import kr.or.ddit.mapper.NoticeMapper;
import kr.or.ddit.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements INoticeService {
	@Inject
	public NoticeMapper noticeMapper;
	
	TelegramSendController tst = new TelegramSendController();
	
	@Override
	public ServiceResult insertNotice(NoticeVO notice) {
		ServiceResult result = null;
		int status = noticeMapper.insertNotice(notice);
		
		if(status > 0) {
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
	
	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeMapper.incrementHit(boNo); //조회수 증가
		return noticeMapper.selectNotice(boNo);
	}
	@Override
	public ServiceResult updateNotice(NoticeVO notice) {
		ServiceResult result = null;
		
		int status = noticeMapper.updateNotice(notice);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return null;
	}

}
