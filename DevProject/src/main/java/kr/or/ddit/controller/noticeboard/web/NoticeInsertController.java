package kr.or.ddit.controller.noticeboard.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeInsertController {

	@Inject
	private INoticeService noticeService;

	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String noticeInsertForm() {
		return "notice/form";
	}

	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String noticeInsert(
			HttpServletRequest req,
			RedirectAttributes ra,
			NoticeVO notice, Model model) {

		String goPage = "";

		Map<String, String> errors = new HashMap<String, String>();
		if (StringUtils.isBlank(notice.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");

		}
		if (StringUtils.isBlank(notice.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}

		if (errors.size() > 0) { // 에러가 발생하면
			model.addAttribute("errors", errors);
			model.addAttribute("noticeVO", notice);
			goPage = "notice/form";

		} else {
			HttpSession session = req.getSession();
			DDITMemberVO member = (DDITMemberVO) session.getAttribute("SessionInfo");
			if(member != null) {
				notice.setBoWriter(member.getMemId()); // 임시로 넣어둔다(로그인 처리후 재설정 예정)
				ServiceResult result = noticeService.insertNotice(req,notice);
			if (result == ServiceResult.OK) {
				goPage = "redirect:/notice/detail.do?boNo=" + notice.getBoNo();
			} else {
				model.addAttribute("message", "서버에러, 다시시도해주세요!");
				goPage = "notice/form";
			}
			}else {
				ra.addFlashAttribute("message","로그인후에 시도해주세요");
				goPage = "redirect:/notice/login.do";
			}
		}

		return goPage;
	}
	
//	//요청 URI : /notice/generalForm
//	@RequestMapping(value="/generalForm", method = RequestMethod.GET)
//	public String generalForm() {
//		return "notice/generalForm";
//	}
	
//	@ResponseBody
//	@RequestMapping(value="/generalFormPost", method = RequestMethod.POST)
//	public String generalFormPost(NoticeVO notice, Model model) {
//		
//		String uploadFolder = "D:\\A_TeachingMaterial\\6.JspSpring\\02.SPRING2\\workspace_spring2\\DevProject\\src\\main\\webapp\\resources\\upload";
//		
//		MultipartFile[] boFile = notice.getBoFile();
//		for (MultipartFile multipartFile : boFile) {
//			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
//			
//			try {
//				multipartFile.transferTo(saveFile);
//				ServiceResult result = noticeService.insertNotice(notice);
//				return "sucess";
//			} catch (IllegalStateException | IOException e) {
//				return "Filed";
//			}
//		}
//		return "";
//	}
	
}
