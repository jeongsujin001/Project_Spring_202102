package com.project.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.PageBean;
import com.project.domain.CommentBean;
import com.project.domain.DownloadBean;
import com.project.service.DownloadService;

@Controller
public class DownloadController {

	@Inject
	private DownloadService downloadService;	

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	//http://localhost:8080/TripSpring/download/list　　          /download/list　가상주소
	//http://localhost:8080/TripSpring/download/list?pageNum=1　　/download/list　가상주소
	@RequestMapping(value = "/download/list", method = RequestMethod.GET)
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
		// List downloadList   = getDownloadList(pbBean) 메서드 만들 호출
		List<DownloadBean> downloadList=downloadService.getDownloadList(pbBean);
		
		//setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
		pbBean.setCount(downloadService.getDownloadCount());
		
		//model 데이터 담아서 보내기
		model.addAttribute("downloadList",downloadList);
		model.addAttribute("pbBean",pbBean);
		
//		/WEB-INF/views/download/list.jsp
		return "download/download";
	}	
	
	//http://localhost:8080/TripSpring/download/content?num=번호 /download/content　가상주소
	@RequestMapping(value = "/download/content", method = RequestMethod.GET)
	public String content(Model model,HttpServletRequest request, HttpSession session) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
//		String id = session.getId();
//		String id = "admin";
//	    String id = session.getAttribute("id").toString();
		
		String id = "";

		if(session.getAttribute("id")!=null) {
			id = session.getAttribute("id").toString();
		}else {
		}
	    
		// 조회수 1증가 
		//update download set readcount=readcount+1 where num=?
		downloadService.updateReadcount(num);
		
		DownloadBean db=downloadService.getDownload(num);
		
		//model 데이터 담아서 보내기
		model.addAttribute("db",db);
		
		//=======================================================
		//======================== 좋아요 =========================
		//=======================================================
		int likeChk = downloadService.likeChk(num,id);
		model.addAttribute("likeChk",likeChk);
		
		int likeCount = downloadService.likeCount(num);
		model.addAttribute("likeCount",likeCount);
		
//		/WEB-INF/views/center/content.jsp
		return "download/download-content";
	}	

	// /download/write
	//http://localhost:8080/TripSpring/download/write　　/download/write　가상주소
	@RequestMapping(value = "/download/write", method = RequestMethod.GET)
	public String write() {
//		/WEB-INF/views/center/writeForm.jsp
		return "download/download-insert";
	}
		
//	　http://localhost:8080/TripSpring/download/write　　　/download/write　가상주소 POST방식
	@RequestMapping(value = "/download/write", method = RequestMethod.POST)
	public String fwritePost(HttpServletRequest request,MultipartFile file,MultipartFile dfile) throws Exception{
		// 파일이름  랜덤문자_파일이름
		UUID uid=UUID.randomUUID();
		String saveName=uid.toString()+"_"+file.getOriginalFilename();
		System.out.println(uploadPath+":"+saveName);
		
		String d_saveName=uid.toString()+"_"+dfile.getOriginalFilename();
		System.out.println(uploadPath+":"+d_saveName);
		// 실제파일 => upload폴더에 복사
		File target=new File(uploadPath,saveName);
		FileCopyUtils.copy(file.getBytes(), target);
		
		File d_target=new File(uploadPath,d_saveName);
		FileCopyUtils.copy(dfile.getBytes(), d_target);
		
		DownloadBean db=new DownloadBean();
		db.setName(request.getParameter("name"));
		db.setPass(request.getParameter("pass"));
		db.setSubject(request.getParameter("subject"));
		db.setContent(request.getParameter("content"));
		db.setContent2(request.getParameter("content2"));
		db.setContent3(request.getParameter("content3"));
		db.setTag(request.getParameter("tag"));
		db.setFile(saveName);
		db.setDfile(d_saveName);
				
		downloadService.insertDownload(db);
		
//		http://localhost:8080/FunWeb/download/flist 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/download/list";
	}	

	//  /download/fupdate?num=${bb.num}
	//http://localhost:8080/TripSpring/download/update?num=번호 /download/update　가상주소
	@RequestMapping(value = "/download/update", method = RequestMethod.GET)
	public String fupdate(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
				
		DownloadBean db=downloadService.getDownload(num);
				
		//model 데이터 담아서 보내기
		model.addAttribute("db",db);
				
//		/WEB-INF/views/center/fupdateForm.jsp
		return "download/download-update";
	}	

//	http://localhost:8080/TripSpring/download/fupdate　　　/download/fupdate　가상주소 POST방식
	@RequestMapping(value = "/download/update", method = RequestMethod.POST)
	public String fupdatePost(HttpServletRequest request,MultipartFile file, MultipartFile dfile, Model model) throws Exception{				
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

		String d_saveName="";
		if(!dfile.isEmpty()) { //첨부파일 있으면 
			// 파일이름  랜덤문자_파일이름
			UUID uid=UUID.randomUUID();
			d_saveName=uid.toString()+"_"+dfile.getOriginalFilename();
			System.out.println(uploadPath+":"+d_saveName);
			// 실제파일 => upload폴더에 복사
			File d_target=new File(uploadPath,d_saveName);
			FileCopyUtils.copy(file.getBytes(), d_target);
		}else { //첨부파일 없으면 
			d_saveName=request.getParameter("doldfile");
		}		
		
		
		DownloadBean db=new DownloadBean();
		db.setNum(Integer.parseInt(request.getParameter("num")));
		db.setName(request.getParameter("name"));
		db.setPass(request.getParameter("pass"));
		db.setSubject(request.getParameter("subject"));
		db.setContent(request.getParameter("content"));
		db.setContent2(request.getParameter("content2"));
		db.setContent3(request.getParameter("content3"));
		db.setTag(request.getParameter("tag"));
		db.setFile(saveName);
		db.setDfile(d_saveName);
				
		DownloadBean db2=downloadService.numCheck(db);
		if(db2!=null) {
			//update download set name=?,subject=?,content=?,file=? where num=?
			// // num pass 일치
			downloadService.updateDownload(db);
//			http://localhost:8080/FunWeb/download/flist 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/download/list";
		}else {
			// num pass 틀림
			// msg =" 입력하신 정보는 틀립니다"  model 저장
			model.addAttribute("msg","입력하신 정보는 틀립니다");
//			/WEB-INF/views/center/msg.jsp
			return "download/msg";
		}
	}	

	// /board/fdelete?num=${bb.num}
	//http://localhost:8080/TripSpring/download/fdelete?num=번호 /download/delete　가상주소
	@RequestMapping(value = "/download/delete", method = RequestMethod.GET)
	public String fdelete(Model model,HttpServletRequest request) {
		//request num 파라미터 가져오기
		int num=Integer.parseInt(request.getParameter("num"));
				
		//model 데이터 담아서 보내기
		model.addAttribute("num",num);
				
//		/WEB-INF/views/download/download-delete.jsp
		return "download/download-delete";
	}	
	
	//　http://localhost:8080/TripSpring/download/delete　　　/download/delete　가상주소 POST방식
	@RequestMapping(value = "/download/delete", method = RequestMethod.POST)
	public String fdeletePost(DownloadBean db,Model model) {
		// num pass 일치 여부 확인
		// select * from board where num=? and pass=?
		DownloadBean db2=downloadService.numCheck(db);
		if(db2!=null) {
			//delete from board where num=?
			// // num pass 일치
			downloadService.deleteDownload(db);
//			http://localhost:8080/TripSpring/download/list 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/download/list";
		}else {
			// num pass 틀림
			// msg =" 입력하신 정보는 틀립니다"  model 저장
			model.addAttribute("msg","입력하신 정보는 틀립니다");
//			/WEB-INF/views/center/msg.jsp
			return "download/msg";
		}
	}	

//	http://localhost:8080/TripSpring/download/insertLike　　　/download/insertLike　가상주소 POST방식
	@RequestMapping(value = "/download/insertLike", method = RequestMethod.GET)
	public String insertLikePost(HttpServletRequest request) {
		int bnum=Integer.parseInt(request.getParameter("bnum"));
		int like=Integer.parseInt(request.getParameter("like"));
		String id = request.getParameter("id");
		
		downloadService.insertLike(bnum,id,like);
		
//		http://localhost:8080/TripSpring/download/likeInsert 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/download/content?num="+bnum;
	}	
	
	// /download/filedown?file_name=${bb.file }
//	//http://localhost:8080/TripSpring/download/filedown?file_name=파일 /board/filedown　가상주소
	@RequestMapping(value = "/download/filedown", method = RequestMethod.GET)
	public void fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fileName = request.getParameter( "file_name" );
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File(uploadPath,fileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(fileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}	
	
	
}
