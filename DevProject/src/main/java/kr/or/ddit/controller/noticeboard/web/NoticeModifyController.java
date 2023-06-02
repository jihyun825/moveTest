package kr.or.ddit.controller.noticeboard.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeModifyController {
	@Inject
	private INoticeService noticeService;
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String noticeModifyForm(int boNo, Model model) {
		NoticeVO notice = noticeService.selectNotice(boNo);
		model.addAttribute("notice",notice);
		model.addAttribute("status", "u");
		return "notice/form";
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String noticeModify(NoticeVO notice, Model model) {
		String goPage = "";
		
		ServiceResult result = noticeService.updateNotice(notice);
		if(result == ServiceResult.OK) {
			goPage = "redirect:/notice/detail.do?boNo="+notice.getBoNo();
			
		}else {
			model.addAttribute("message","수정에 실패했습니다.");
			model.addAttribute("notice",notice);
			model.addAttribute("status","u");
			goPage  = "notice/form";
		}
	return goPage;
	}

}
