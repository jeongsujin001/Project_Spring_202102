package com.project.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.PageBean;
import com.project.domain.CommentBean;
import com.project.domain.GCommentBean;
import com.project.domain.GalleryBean;
import com.project.service.GCommentService;
import com.project.service.GalleryService;

@Controller
public class GalleryController {

	@Inject
	private GalleryService galleryService;	

	@Inject
	private GCommentService gcommentService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	//http://localhost:8080/TripSpring/gallery/list　　          /gallery/list　가상주소
	//http://localhost:8080/TripSpring/gallery/list?pageNum=1　　/gallery/list　가상주소
	@RequestMapping(value = "/gallery/list", method = RequestMethod.GET)
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
		// List galleryList   = getGalleryList(pbBean) 메서드 만들 호출
		List<GalleryBean> galleryList=galleryService.getGalleryList(pbBean);
		
		//setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
		pbBean.setCount(galleryService.getGalleryCount());
		
		//model 데이터 담아서 보내기
		model.addAttribute("galleryList",galleryList);
		model.addAttribute("pbBean",pbBean);
		
//		/WEB-INF/views/gallery/list.jsp
		return "gallery/gallery";
	}	
	
	//http://localhost:8080/TripSpring/gallery/content?num=번호 /gallery/content　가상주소
	@RequestMapping(value = "/gallery/content", method = RequestMethod.GET)
	public String content(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
		// 조회수 1증가 
		//update gallery set readcount=readcount+1 where num=?
		galleryService.updateReadcount(num);
		
		GalleryBean gb=galleryService.getGallery(num);
		
		//model 데이터 담아서 보내기
		model.addAttribute("gb",gb);

		//=======================================================	
		//=========================댓글===========================
		//=======================================================
		List<GCommentBean> gcommentList=gcommentService.getCommentList(num);
		
		model.addAttribute("gcommentList",gcommentList);
		
		model.addAttribute("num",num);
		
		int count = gcommentService.getCommentCount(num);
		
		model.addAttribute("count",count);
		//=======================================================
		
//		/WEB-INF/views/center/content.jsp
		return "gallery/gallery-content";
	}	

	// /gallery/write
	//http://localhost:8080/TripSpring/gallery/write　　/gallery/write　가상주소
	@RequestMapping(value = "/gallery/write", method = RequestMethod.GET)
	public String write() {
//		/WEB-INF/views/center/writeForm.jsp
		return "gallery/gallery-insert";
	}
		
//	　http://localhost:8080/TripSpring/gallery/fwrite　　　/gallery/write　가상주소 POST방식
	@RequestMapping(value = "/gallery/write", method = RequestMethod.POST)
	public String fwritePost(HttpServletRequest request,MultipartFile file) throws Exception{
		// 파일이름  랜덤문자_파일이름
		UUID uid=UUID.randomUUID();
		String saveName=uid.toString()+"_"+file.getOriginalFilename();
		System.out.println(uploadPath+":"+saveName);
		// 실제파일 => upload폴더에 복사
		File target=new File(uploadPath,saveName);
		FileCopyUtils.copy(file.getBytes(), target);
		
		GalleryBean gb=new GalleryBean();
		gb.setName(request.getParameter("name"));
		gb.setPass(request.getParameter("pass"));
		gb.setSubject(request.getParameter("subject"));
		gb.setContent(request.getParameter("content"));
		gb.setFile(saveName);
				
		galleryService.insertGallery(gb);
		
//		http://localhost:8080/FunWeb/gallery/flist 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/gallery/list";
	}	

	//  /gallery/fupdate?num=${bb.num}
	//http://localhost:8080/TripSpring/gallery/update?num=번호 /gallery/update　가상주소
	@RequestMapping(value = "/gallery/update", method = RequestMethod.GET)
	public String fupdate(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
				
		GalleryBean gb=galleryService.getGallery(num);
				
		//model 데이터 담아서 보내기
		model.addAttribute("gb",gb);
				
//		/WEB-INF/views/center/fupdateForm.jsp
		return "gallery/gallery-update";
	}	

//	http://localhost:8080/TripSpring/gallery/fupdate　　　/gallery/fupdate　가상주소 POST방식
	@RequestMapping(value = "/gallery/update", method = RequestMethod.POST)
	public String fupdatePost(HttpServletRequest request,MultipartFile file, Model model) throws Exception{				
		String saveName="";
		if(!file.isEmpty()) { //첨부파일 있으면 
			// 파일이름  랜덤문자_파일이름
			UUID uid=UUID.randomUUID();
			saveName=uid.toString()+"_"+file.getOriginalFilename();
			System.out.println(uploadPath+":"+saveName);
			// 실제파일 => upload폴더에 복사
			File target=new File(uploadPath,saveName);
			FileCopyUtils.copy(file.getBytes(), target);
		}else { //첨부파일 없으면 
			saveName=request.getParameter("oldfile");
		}
				
		GalleryBean gb=new GalleryBean();
		gb.setNum(Integer.parseInt(request.getParameter("num")));
		gb.setName(request.getParameter("name"));
		gb.setPass(request.getParameter("pass"));
		gb.setSubject(request.getParameter("subject"));
		gb.setContent(request.getParameter("content"));
		gb.setFile(saveName);
				
		GalleryBean gb2=galleryService.numCheck(gb);
		if(gb2!=null) {
			//update gallery set name=?,subject=?,content=?,file=? where num=?
			// // num pass 일치
			galleryService.updateGallery(gb);
//			http://localhost:8080/FunWeb/gallery/flist 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/gallery/list";
		}else {
			// num pass 틀림
			// msg =" 입력하신 정보는 틀립니다"  model 저장
			model.addAttribute("msg","입력하신 정보는 틀립니다");
//			/WEB-INF/views/center/msg.jsp
			return "gallery/msg";
		}
	}	

	// /gallery/fdelete?num=${bb.num}
	//http://localhost:8080/TripSpring/gallery/fdelete?num=번호 /gallery/delete　가상주소
	@RequestMapping(value = "/gallery/delete", method = RequestMethod.GET)
	public String fdelete(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
				
		//model 데이터 담아서 보내기
		model.addAttribute("num",num);
				
//		/WEB-INF/views/gallery/fgallery-delete.jsp
		return "gallery/gallery-delete";
	}	
	
	//　http://localhost:8080/TripSpring/gallery/delete　　　/gallery/delete　가상주소 POST방식
	@RequestMapping(value = "/gallery/delete", method = RequestMethod.POST)
	public String fdeletePost(GalleryBean gb,Model model) {
		// num pass 일치 여부 확인
		// select * from board where num=? and pass=?
		GalleryBean gb2=galleryService.numCheck(gb);
		if(gb2!=null) {
			//delete from board where num=?
			// // num pass 일치
			galleryService.deleteGallery(gb);
//			http://localhost:8080/TripSpring/gallery/list 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/gallery/list";
		}else {
			// num pass 틀림
			// msg =" 입력하신 정보는 틀립니다"  model 저장
			model.addAttribute("msg","입력하신 정보는 틀립니다");
//			/WEB-INF/views/center/msg.jsp
			return "gallery/msg";
		}
	}	

	//==================================================================
	//======================= 댓글 ======================================
	//==================================================================
	
//	http://localhost:8080/TripSpring/gallery/commentWrite　　　/gallery/commentWrite　가상주소 POST방식
	@RequestMapping(value = "/gallery/commentWrite", method = RequestMethod.POST)
	public String commentWritePost(GCommentBean cb) {
		gcommentService.insertComment(cb);
//		boardService.updateCcount(cb.getComment_bnum(),1);
//		http://localhost:8080/FunWeb/gallery/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/gallery/content?num="+cb.getComment_bnum();
	}
	
//	http://localhost:8080/TripSpring/gallery/commentReWrite　　　/gallery/commentReWrite　가상주소 POST방식
	@RequestMapping(value = "/gallery/commentReWrite", method = RequestMethod.POST)
	public String commentReWritePost(GCommentBean cb) {
		gcommentService.insertReComment(cb);
//		boardService.updateCcount(cb.getComment_bnum(),1);
//		http://localhost:8080/FunWeb/gallery/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/gallery/content?num="+cb.getComment_bnum();
	}

//	http://localhost:8080/TripSpring/gallery/commentUpdate　　　/gallery/commentUpdate　가상주소 POST방식
	@RequestMapping(value = "/gallery/commentUpdate", method = RequestMethod.POST)
	public String commentUpdatePost(GCommentBean cb) {
		gcommentService.updateComment(cb);
		
//		http://localhost:8080/FunWeb/gallery/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/gallery/content?num="+cb.getComment_bnum();
	}	

//	http://localhost:8080/TripSpring/gallery/commentUpdate　　　/gallery/commentUpdate　가상주소 POST방식
	@RequestMapping(value = "/gallery/commentDelete", method = RequestMethod.GET)
	public String commentDeletePost(GCommentBean cb) {
		gcommentService.deleteComment(cb);
//		boardService.updateCcount(cb.getComment_bnum(),-1);
//		http://localhost:8080/FunWeb/gallery/list 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/gallery/content?num="+cb.getComment_bnum();
	}	
}
