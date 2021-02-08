package com.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.NoticeBean;
import com.project.domain.PageBean;
import com.project.service.NoticeService;

@Controller
public class NoticeController {
	
	@Inject
	private NoticeService noticeService;			

	//http://localhost:8080/TripSpring/notice/list　　          /notice/list　가상주소
	//http://localhost:8080/TripSpring/notice/list?pageNum=1　　/notice/list　가상주소
	@RequestMapping(value = "/notice/list", method = RequestMethod.GET)
	public String list(Model model,HttpServletRequest request) {
		PageBean pbBean=new PageBean();
		// 한페이지에 보여줄 글개수 설정 pageSize
		pbBean.setPageSize(10);
	//  pageNum 파라미터 가져오기
		String pageNum=request.getParameter("pageNum");
		// pageNum없으면  "1" 페이지 설정
		if(pageNum==null) {
			pbBean.setPageNum("1");
		}else {
			pbBean.setPageNum(pageNum);
		}
		// List noticeList   = getNoticeList(pbBean) 메서드 만들 호출
		List<NoticeBean> noticeList=noticeService.getNoticeList(pbBean);
		
		//setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
		pbBean.setCount(noticeService.getNoticeCount());
		
		//model 데이터 담아서 보내기
		model.addAttribute("noticeList",noticeList);
		model.addAttribute("pbBean",pbBean);
		
//		/WEB-INF/views/notice/list.jsp
		return "notice/notice";
	}
	
	//http://localhost:8080/TripSpring/notice/content?num=번호 /notice/content　가상주소
	@RequestMapping(value = "/notice/content", method = RequestMethod.GET)
	public String content(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
		// 조회수 1증가 
		//update notice set readcount=readcount+1 where num=?
		noticeService.updateReadcount(num);
		
		NoticeBean nb=noticeService.getNotice(num);
		
		//model 데이터 담아서 보내기
		model.addAttribute("nb",nb);
		
//		/WEB-INF/views/center/content.jsp
		return "notice/notice-content";
	}
	
	// /notice/write
	//http://localhost:8080/TripSpring/notice/write　　/notice/write　가상주소
	@RequestMapping(value = "/notice/write", method = RequestMethod.GET)
	public String write() {
//		/WEB-INF/views/center/writeForm.jsp
		return "notice/notice-insert";
	}
	
//	http://localhost:8080/TripSpring/notice/write　　　/notice/write　가상주소 POST방식
	@RequestMapping(value = "/notice/write", method = RequestMethod.POST)
	public String writePost(NoticeBean nb) {
		noticeService.insertNotice(nb);
		
//		http://localhost:8080/FunWeb/notice/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/notice/list";
	}	
	
	// /notice/update?num=${nb.num }
	//http://localhost:8080/TripSpring/notice/update?num=번호 /notice/update　가상주소
	@RequestMapping(value = "/notice/update", method = RequestMethod.GET)
	public String update(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
				
		NoticeBean nb=noticeService.getNotice(num);
				
		//model 데이터 담아서 보내기
		model.addAttribute("nb",nb);
				
//		/WEB-INF/views/center/updateForm.jsp
		return "notice/notice-update";
	}	

//	http://localhost:8080/TripSpring/notice/update　　　/notice/update　가상주소 POST방식
	@RequestMapping(value = "/notice/update", method = RequestMethod.POST)
	public String updatePost(NoticeBean nb,Model model) {
		// num pass 일치 여부 확인
		// select * from notice where num=? and pass=?
		NoticeBean nb2=noticeService.numCheck(nb);
		if(nb2!=null) {
			//update notice set name=?,subject=?,content=? where num=?
			// // num pass 일치
			noticeService.updateNotice(nb);
//			http://localhost:8080/TripSpring/notice/list 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/notice/list";
		}else {
			// num pass 틀림
			// msg =" 입력하신 정보는 틀립니다"  model 저장
			model.addAttribute("msg","입력하신 정보는 틀립니다");
//			/WEB-INF/views/center/msg.jsp
			return "notice/msg";
		}
	}

	
	// /notice/delete?num=${nb.num }
	//http://localhost:8080/TripSpring/notice/delete?num=번호 /notice/delete　가상주소
	@RequestMapping(value = "/notice/delete", method = RequestMethod.GET)
	public String delete(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
				
		//model 데이터 담아서 보내기
		model.addAttribute("num",num);
				
//		/WEB-INF/views/center/deleteForm.jsp
		return "notice/notice-delete";
	}	

	
//	http://localhost:8080/FunWeb/notice/delete　　　/notice/delete　가상주소 POST방식
	@RequestMapping(value = "/notice/delete", method = RequestMethod.POST)
	public String deletePost(NoticeBean nb,Model model) {
		// num pass 일치 여부 확인
		// select * from notice where num=? and pass=?
		NoticeBean nb2=noticeService.numCheck(nb);
		if(nb2!=null) {
			//delete from notice where num=?
			// // num pass 일치
			noticeService.deleteNotice(nb);
//			http://localhost:8080/FunWeb/notice/list 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/notice/list";
		}else {
			// num pass 틀림
			// msg =" 입력하신 정보는 틀립니다"  model 저장
			model.addAttribute("msg","입력하신 정보는 틀립니다");
//			/WEB-INF/views/center/msg.jsp
			return "notice/msg";
		}
	}	
}
