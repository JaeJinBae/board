package com.dgit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dgit.domain.BoardVO;
import com.dgit.service.BoardService;

@Controller
public class BoardController {
	private static final Logger logger=LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("home입니다.");
		
		List<BoardVO> list=service.selectAll();
		model.addAttribute("list",list);
		
		return "home";
	}
	
	@RequestMapping(value="/read/{bno}")
	public String read(@PathVariable("bno") int bno, Model model){
		logger.info("read");
		
		BoardVO vo=service.selectOne(bno);
		model.addAttribute("item",vo);
		
		return "readForm";
	}
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerGet(){
		logger.info("registerGet");
		
		return "registerForm";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String registerPost(BoardVO vo){
		logger.info("registerPost");
		service.insert(vo);
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("imgUpload")
	public Map<String,Object> imgaeUpload(HttpServletRequest req/*, HttpServletResponse res*/, @RequestParam MultipartFile upload, Model model) throws Exception{
		logger.info("image upload!!!!!");
		//http header 설정
		/*res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset-utf-8");*/
		
		//http body
		OutputStream out=null;
		//PrintWriter printWriter=null;//웹페이지에 html 넣음
	
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		//업로드한 파일 이름
		String fileName=upload.getOriginalFilename();
		//바이트 배열로 변환
		byte[] bytes=upload.getBytes();
		//이미지를 업로드할 디렉토리(배포경로로 설정)
		String innerUploadPath="resources/upload/";
		String uploadPath= (req.getSession().getServletContext().getRealPath("/"))+ innerUploadPath;
		logger.info(uploadPath);
		
		out=new FileOutputStream(new File(uploadPath+fileName));//서버에 파일 저장
		//서버에 저장됨
		out.write(bytes);
		
		//클라이언트에 업로드 결과를 표시
		String callback = req.getParameter("CKEditorFuncNum");
		//printWriter=res.getWriter();
		String fileUrl=innerUploadPath+fileName;
		
		/*logger.info(req.getContextPath());
		String script ="<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(1,'"+
				fileUrl+"','이미지가 업로드되었습니다.');"+"</script>";*/
		
		//model.addAttribute("fileUrl",fileUrl);
		System.out.println(fileUrl);
		
		map.put("uploaded", 1);
		map.put("fileName", fileName);
		map.put("url", fileUrl);
		/*printWriter.println(script);
		logger.info(script);  
		//스트림 닫기 
		printWriter.flush(); */
		return map;
	}
	
	@RequestMapping("/delete/{bno}")
	public String delete(@PathVariable("bno") int bno){
		logger.info("delete");
		service.delete(bno);
		
		return "redirect:/";
	}

	
	
	
	
	
	
	
	
	
}


