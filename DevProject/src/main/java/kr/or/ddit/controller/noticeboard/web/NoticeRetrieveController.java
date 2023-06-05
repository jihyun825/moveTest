package kr.or.ddit.controller.noticeboard.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.controller.noticeboard.service.NoticeServiceImpl;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/notice")
public class NoticeRetrieveController {
	@Inject 
	private INoticeService noticeService;

	@RequestMapping(value = "/list.do")
	public String noticeList(
			@RequestParam(name="page",required=false, defaultValue="1")int currentPage, 
			@RequestParam(required=false, defaultValue="title")String searchType,
			@RequestParam(required=false)String searchWord,
			Model model
			) {
		log.info("noticeList() 실행...!");
		PaginationInfoVO<NoticeVO> pagingVo = new PaginationInfoVO<NoticeVO>();
		
		if(StringUtils.isNoneBlank(searchWord)) {
			pagingVo.setSearchType(searchType);
			pagingVo.setSearchWord(searchWord);
			model.addAttribute("searchType",searchType);
			model.addAttribute("searchWord",searchWord);
			
		}
		
		pagingVo.setCurrentPage(currentPage);
		int totalRecord = noticeService.selectNoticeCount(pagingVo);
		pagingVo.setTotalRecord(totalRecord);
		List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVo);
		pagingVo.setDataList(dataList);
		
		model.addAttribute("pagingVo",pagingVo);
		
		return "notice/list";
	}
	
	@RequestMapping(value = "/detail.do")
	public String noticeDetail(int boNo, Model model) {
		NoticeVO notice = noticeService.selectNotice(boNo);
		model.addAttribute("notice", notice);
		return "notice/detail";
	}
	
	
}
