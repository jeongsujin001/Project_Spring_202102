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

import com.project.domain.BoardBean;
import com.project.domain.CommentBean;
import com.project.domain.PageBean;
import com.project.service.BoardService;
import com.project.service.CommentService;

@Controller
public class BoardController {
	
	@Inject
	private BoardService boardService;			
	
	@Inject
	private CommentService commentService;
	
	//http://localhost:8080/TripSpring/board/list　　          /board/list　가상주소
	//http://localhost:8080/TripSpring/board/list?pageNum=1　　/board/list　가상주소
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
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
		// List boardList   = getBoardList(pbBean) 메서드 만들 호출
		List<BoardBean> boardList=boardService.getBoardList(pbBean);
		
		//setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
		pbBean.setCount(boardService.getBoardCount());
		
		//model 데이터 담아서 보내기
		model.addAttribute("boardList",boardList);
		model.addAttribute("pbBean",pbBean);

		//=======================================================	
		//=========================댓글===========================
		//=======================================================		
//		int ccount = commentService.getCommentCount(num);
//		
//		model.addAttribute("ccount",ccount);
		//=======================================================		
		
//		/WEB-INF/views/board/list.jsp
		return "board/board";
	}
	
	//http://localhost:8080/TripSpring/board/content?num=번호 /board/content　가상주소
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)
	public String content(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
		// 조회수 1증가 
		//update board set readcount=readcount+1 where num=?
		boardService.updateReadcount(num);
		
		BoardBean bb=boardService.getBoard(num);
		
		//model 데이터 담아서 보내기
		model.addAttribute("bb",bb);

		//=======================================================	
		//=========================댓글===========================
		//=======================================================
		List<CommentBean> commentList=commentService.getCommentList(num);
		
		model.addAttribute("commentList",commentList);
		
		model.addAttribute("num",num);
		
		int count = commentService.getCommentCount(num);
		
		model.addAttribute("count",count);
		//=======================================================
		
//		/WEB-INF/views/center/content.jsp
		return "board/board-content";
	}
	
	// /board/write
	//http://localhost:8080/TripSpring/board/write　　/board/write　가상주소
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
//		/WEB-INF/views/center/writeForm.jsp
		return "board/board-insert";
	}
	
//	http://localhost:8080/TripSpring/board/write　　　/board/write　가상주소 POST방식
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String writePost(BoardBean bb) {
		boardService.insertBoard(bb);
		
//		http://localhost:8080/FunWeb/board/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/board/list";
	}	
	
	// /board/update?num=${bb.num }
	//http://localhost:8080/TripSpring/board/update?num=번호 /board/update　가상주소
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String update(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
				
		BoardBean bb=boardService.getBoard(num);
				
		//model 데이터 담아서 보내기
		model.addAttribute("bb",bb);
				
//		/WEB-INF/views/center/updateForm.jsp
		return "board/board-update";
	}	

//	http://localhost:8080/TripSpring/board/update　　　/board/update　가상주소 POST방식
	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String updatePost(BoardBean bb,Model model) {
		// num pass 일치 여부 확인
		// select * from board where num=? and pass=?
		BoardBean bb2=boardService.numCheck(bb);
		if(bb2!=null) {
			//update board set name=?,subject=?,content=? where num=?
			// // num pass 일치
			boardService.updateBoard(bb);
//			http://localhost:8080/TripSpring/board/list 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/board/list";
		}else {
			// num pass 틀림
			// msg =" 입력하신 정보는 틀립니다"  model 저장
			model.addAttribute("msg","입력하신 정보는 틀립니다");
//			/WEB-INF/views/center/msg.jsp
			return "board/msg";
		}
	}

	
	// /board/delete?num=${bb.num }
	//http://localhost:8080/TripSpring/board/delete?num=번호 /board/delete　가상주소
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
				
		//model 데이터 담아서 보내기
		model.addAttribute("num",num);
				
//		/WEB-INF/views/center/deleteForm.jsp
		return "board/board-delete";
	}	

	
//	http://localhost:8080/FunWeb/board/delete　　　/board/delete　가상주소 POST방식
	@RequestMapping(value = "/board/delete", method = RequestMethod.POST)
	public String deletePost(BoardBean bb,Model model) {
		// num pass 일치 여부 확인
		// select * from board where num=? and pass=?
		BoardBean bb2=boardService.numCheck(bb);
		if(bb2!=null) {
			//delete from board where num=?
			// // num pass 일치
			boardService.deleteBoard(bb);
//			http://localhost:8080/FunWeb/board/list 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/board/list";
		}else {
			// num pass 틀림
			// msg =" 입력하신 정보는 틀립니다"  model 저장
			model.addAttribute("msg","입력하신 정보는 틀립니다");
//			/WEB-INF/views/center/msg.jsp
			return "board/msg";
		}
	}
	
	
	//==================================================================
	//======================= 댓글 ======================================
	//==================================================================
	
//	http://localhost:8080/TripSpring/board/commentWrite　　　/board/commentWrite　가상주소 POST방식
	@RequestMapping(value = "/board/commentWrite", method = RequestMethod.POST)
	public String commentWritePost(CommentBean cb) {
		commentService.insertComment(cb);
		boardService.updateCcount(cb.getComment_bnum(),1);
//		http://localhost:8080/FunWeb/board/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/board/content?num="+cb.getComment_bnum();
	}
	
//	http://localhost:8080/TripSpring/board/commentReWrite　　　/board/commentReWrite　가상주소 POST방식
	@RequestMapping(value = "/board/commentReWrite", method = RequestMethod.POST)
	public String commentReWritePost(CommentBean cb) {
		commentService.insertReComment(cb);
		boardService.updateCcount(cb.getComment_bnum(),1);
//		http://localhost:8080/FunWeb/board/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/board/content?num="+cb.getComment_bnum();
	}

//	http://localhost:8080/TripSpring/board/commentUpdate　　　/board/commentUpdate　가상주소 POST방식
	@RequestMapping(value = "/board/commentUpdate", method = RequestMethod.POST)
	public String commentUpdatePost(CommentBean cb) {
		commentService.updateComment(cb);
		
//		http://localhost:8080/FunWeb/board/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/board/content?num="+cb.getComment_bnum();
	}	

//	http://localhost:8080/TripSpring/board/commentUpdate　　　/board/commentUpdate　가상주소 POST방식
	@RequestMapping(value = "/board/commentDelete", method = RequestMethod.GET)
	public String commentDeletePost(CommentBean cb) {
		commentService.deleteComment(cb);
		boardService.updateCcount(cb.getComment_bnum(),-1);
//		http://localhost:8080/FunWeb/board/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/board/content?num="+cb.getComment_bnum();
	}	
	
}
