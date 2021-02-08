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

import com.project.domain.CommentBean;
import com.project.domain.PageBean;
import com.project.service.CommentService;

@Controller
public class CommentController {
	
//	@Inject
//	private CommentService commentService;			
//
//	//http://localhost:8080/TripSpring/comment/list　　          /comment/list　가상주소
//	//http://localhost:8080/TripSpring/comment/list?pageNum=1　　/comment/list　가상주소
//	@RequestMapping(value = "/comment/list", method = RequestMethod.GET)
//	public String list(Model model,HttpServletRequest request) {
//		PageBean pbBean=new PageBean();
//		// 한페이지에 보여줄 글개수 설정 pageSize
//		pbBean.setPageSize(10);
//	//  pageNum 파라미터 가져오기
//		String pageNum=request.getParameter("pageNum");
//		// pageNum없으면  "1" 페이지 설정
//		if(pageNum==null) {
//			pbBean.setPageNum("1");
//		}else {
//			pbBean.setPageNum(pageNum);
//		}
//		// List commentList   = getCommentList(pbBean) 메서드 만들 호출
//		List<CommentBean> commentList=commentService.getCommentList(pbBean);
//		
//		//setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
//		pbBean.setCount(commentService.getCommentCount());
//		
//		//model 데이터 담아서 보내기
//		model.addAttribute("commentList",commentList);
//		model.addAttribute("pbBean",pbBean);
//		
////		/WEB-INF/views/comment/list.jsp
//		return "comment/comment";
//	}
//	
//	//http://localhost:8080/TripSpring/comment/content?num=번호 /comment/content　가상주소
//	@RequestMapping(value = "/comment/content", method = RequestMethod.GET)
//	public String content(Model model,HttpServletRequest request) {
//		//request num 파라미터 가져오기
//		int num=Integer.parseInt(request.getParameter("num"));
//		// 조회수 1증가 
//		//update comment set readcount=readcount+1 where num=?
//		commentService.updateReadcount(num);
//		
//		CommentBean cb=commentService.getComment(num);
//		
//		//model 데이터 담아서 보내기
//		model.addAttribute("cb",cb);
//		
////		/WEB-INF/views/center/content.jsp
//		return "comment/comment-content";
//	}
//	
//	// /comment/write
//	//http://localhost:8080/TripSpring/comment/write　　/comment/write　가상주소
//	@RequestMapping(value = "/comment/write", method = RequestMethod.GET)
//	public String write() {
////		/WEB-INF/views/center/writeForm.jsp
//		return "comment/comment-insert";
//	}
//	
////	http://localhost:8080/TripSpring/comment/write　　　/comment/write　가상주소 POST방식
//	@RequestMapping(value = "/comment/write", method = RequestMethod.POST)
//	public String writePost(CommentBean cb) {
//		commentService.insertComment(cb);
//		
////		http://localhost:8080/FunWeb/comment/list 가상주소 이동
//		// response.sendRedirect() 같음
//		return "redirect:/comment/list";
//	}	
//	
//	// /comment/update?num=${cb.num }
//	//http://localhost:8080/TripSpring/comment/update?num=번호 /comment/update　가상주소
//	@RequestMapping(value = "/comment/update", method = RequestMethod.GET)
//	public String update(Model model,HttpServletRequest request) {
//		//request num 파라미터 가져오기
//		int num=Integer.parseInt(request.getParameter("num"));
//				
//		CommentBean cb=commentService.getComment(num);
//				
//		//model 데이터 담아서 보내기
//		model.addAttribute("cb",cb);
//				
////		/WEB-INF/views/center/updateForm.jsp
//		return "comment/comment-update";
//	}	
//
////	http://localhost:8080/TripSpring/comment/update　　　/comment/update　가상주소 POST방식
//	@RequestMapping(value = "/comment/update", method = RequestMethod.POST)
//	public String updatePost(CommentBean cb,Model model) {
//		// num pass 일치 여부 확인
//		// select * from comment where num=? and pass=?
//		CommentBean cb2=commentService.numCheck(cb);
//		if(cb2!=null) {
//			//update comment set name=?,subject=?,content=? where num=?
//			// // num pass 일치
//			commentService.updateComment(cb);
////			http://localhost:8080/TripSpring/comment/list 가상주소 이동
//			// response.sendRedirect() 같음
//			return "redirect:/comment/list";
//		}else {
//			// num pass 틀림
//			// msg =" 입력하신 정보는 틀립니다"  model 저장
//			model.addAttribute("msg","입력하신 정보는 틀립니다");
////			/WEB-INF/views/center/msg.jsp
//			return "comment/msg";
//		}
//	}
//
//	
//	// /comment/delete?num=${cb.num }
//	//http://localhost:8080/TripSpring/comment/delete?num=번호 /comment/delete　가상주소
//	@RequestMapping(value = "/comment/delete", method = RequestMethod.GET)
//	public String delete(Model model,HttpServletRequest request) {
//		//request num 파라미터 가져오기
//		int num=Integer.parseInt(request.getParameter("num"));
//				
//		//model 데이터 담아서 보내기
//		model.addAttribute("num",num);
//				
////		/WEB-INF/views/center/deleteForm.jsp
//		return "comment/comment-delete";
//	}	
//
//	
////	http://localhost:8080/FunWeb/comment/delete　　　/comment/delete　가상주소 POST방식
//	@RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
//	public String deletePost(CommentBean cb,Model model) {
//		// num pass 일치 여부 확인
//		// select * from comment where num=? and pass=?
//		CommentBean cb2=commentService.numCheck(cb);
//		if(cb2!=null) {
//			//delete from comment where num=?
//			// // num pass 일치
//			commentService.deleteComment(cb);
////			http://localhost:8080/FunWeb/comment/list 가상주소 이동
//			// response.sendRedirect() 같음
//			return "redirect:/comment/list";
//		}else {
//			// num pass 틀림
//			// msg =" 입력하신 정보는 틀립니다"  model 저장
//			model.addAttribute("msg","입력하신 정보는 틀립니다");
////			/WEB-INF/views/center/msg.jsp
//			return "comment/msg";
//		}
//	}	
}
