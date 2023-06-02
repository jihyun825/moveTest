package kr.or.ddit.controller.noticeboard.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class ImageUpload {
	@RequestMapping(value="/imageUpload.do")
	public String imageUpload( HttpServletRequest req, HttpServletResponse resp, MultipartHttpServletRequest multiFile) throws IOException {
		JsonObject json = new JsonObject();
		
		PrintWriter printWriter = null;
		OutputStream out = null;
		long limitSize = 1024*1024*2; // 2MB //사진크기 제한
		
		MultipartFile file = multiFile.getFile("upload"); //ck에디터에서 파일이 넘어올때 이름 이upload임
		
		if(file != null && file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) { //파일이 있을때
			if(file.getContentType().toLowerCase().startsWith("image/")) { //contentType이 image로 시작해야 이미지야
				if(file.getSize() > limitSize ) { //내가 위에서 제한한 이미지 크기보다 받은파일이 클때
					JsonObject jsonMsg = new JsonObject();// jsonObect로 메세지 전달하는게 CKeditor 의 규칙이야
					JsonArray  jsonArr = new JsonArray();
					jsonMsg.addProperty("message", "2MB미만의 이미지만 업로드가능합니다.");
					jsonArr.add(jsonMsg);
					json.addProperty("uploaded", 0);
					json.add("error", jsonArr.get(0));
					
					resp.setCharacterEncoding("UTF-8");
					printWriter = resp.getWriter();
					printWriter.println(json);
				}else { //사진크기가 정상적이고 이미지파일인애들
					try {
					String fileName = file.getName(); //파일이름꺼내고
					byte[] bytes = file.getBytes(); // 크기꺼내고
					String uploadPath = req.getServletContext().getRealPath("/resources/img");//업로드 경로 (서버) 설정하기
					
					File uploadFile = new File(uploadPath);
					if(!uploadFile.exists()) {
						uploadFile.mkdirs(); //위에서 설정한 uploadPath가 존재하지않는 경우 폴더 구조를 만들어준다.
						
					}
					
					fileName = UUID.randomUUID().toString(); //UUID를 활용하여 랜덤으로 발생되어 만들어진 문자열값을 저장한다.
					uploadPath = uploadPath + "/" + fileName + ".jpg";  // resources/img/UUID.jpg
					
					//파일을 복사한다.
					out = new FileOutputStream(new File(uploadPath));
					//아까 꺼낸크기만큼 출력한다
					out.write(bytes);
					
					printWriter = resp.getWriter();
					resp.setContentType("text/html");
					String fileUrl = req.getContextPath()+"/resources/img/"+fileName + ".jpg";
					
					
					//ckeditor의 이미지 출력 규칙이야
					json.addProperty("uploaded", 1);
					json.addProperty("fileName", fileName);
					json.addProperty("url", fileUrl);
					
					printWriter.println(json);
					}catch(IOException e) {
						e.printStackTrace();
					}finally {
						if(out!=null) {
							out.close();
						}
						if(printWriter != null) {
							printWriter.close();
						}
					}
				}
			}
		}
		return null;
	}
}
