package com.project.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.service.GalleryService;
import com.project.service.MemberService;
import com.project.domain.GalleryBean;
import com.project.domain.MemberBean;


@Controller
public class MemberController {
	@Inject
	private	MemberService memberService;
	
	@Inject
	private GalleryService galleryService;
	
	//http://localhost:8080/TripSpring/main/main　　/main/main　가상주소
	@RequestMapping(value = "/main/main", method = RequestMethod.GET)
	public String main(Model model) {
		
		List<GalleryBean> mainGalleryList=galleryService.getMainGalleryList();
		model.addAttribute("mainGalleryList",mainGalleryList);
		
//		/WEB-INF/views/main/main.jsp
		return "main/main";
	}

	//http://localhost:8080/TripSpring/member/login　　/member/login　가상주소
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		
//		/WEB-INF/views/member/login.jsp
		return "member/login";
	}
	
//	　http://localhost:8080/TripSpring/member/login　　　/member/login　가상주소 POST방식
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String loginPost(MemberBean mb,HttpSession session,Model model) {
		System.out.println(mb.getId());
		System.out.println(mb.getPass());
		// MemberService 인터페이스 상속 클래스 MemberServiceImpl  @Service
		// MemberDAO 인터페이스 상속 클래스 MemberDAOImpl  @Repository
		MemberBean mb2=memberService.userCheck(mb);
		if(mb2!=null) {
			//세션값 생성 "id",id
			session.setAttribute("id", mb.getId());
			
//			http://localhost:8080/FunWeb/main/main 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/main/main";
		}else {
			model.addAttribute("msg","입력하신 정보 틀립니다.");
//			/WEB-INF/views/member/msg.jsp
			return "member/msg";
		}
	}		
	
	//http://localhost:8080/TripSpring/member/logout　　/member/logout　가상주소
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//세션값 전체삭제
		session.invalidate();
//		http://localhost:8080/FunWeb/main/main 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/main/main";
	}	
	
	//http://localhost:8080/TripSpring/member/registration　　/member/registration　가상주소
	@RequestMapping(value = "/member/registration", method = RequestMethod.GET)
	public String registration() {
		
//		/WEB-INF/views/member/login.jsp
		return "member/registration";
	}
	
//	　http://localhost:8080/TripSpring/member/registration　　　/member/registration　가상주소 POST방식
	@RequestMapping(value = "/member/registration", method = RequestMethod.POST)
	public String registrationPost(MemberBean mb,HttpServletRequest request) {
		String postcode=request.getParameter("postcode");
		String roadAddress=request.getParameter("roadAddress");
		String detailAddress=request.getParameter("detailAddress");
		String address = postcode+"/"+roadAddress+"/"+detailAddress;
		mb.setAddress(address);
		memberService.insertMember(mb);
		return "redirect:/member/login";
	}
	
	//http://localhost:8080/myweb2/member/update　　/member/update　가상주소 GET
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session,Model model) {
		// String id = 세션값 가져오기
		String id=(String)session.getAttribute("id");
		MemberBean mb=memberService.getMember(id);
		
		String address = mb.getAddress();
		
		String p1 = "/";
		String addArray0 = "";
		String addArray1 = "";
		String addArray2 = "";
		//정규표현식의 문자가 아니라면 그냥 대입해도 상관없다
		if(address!=null){
			String[] addArray = address.split(p1);
			for( int i = 0; i < addArray.length; i++ ){
				addArray0=addArray[0];
				addArray1=addArray[1];
				addArray2=addArray[2];
			}
		}
		
		// mb담아서 이동
		model.addAttribute("mb",mb);
		model.addAttribute("addArray0",addArray0);
		model.addAttribute("addArray1",addArray1);
		model.addAttribute("addArray2",addArray2);
//		/WEB-INF/views/member/registration-update.jsp
		return "member/registration-update";
	}
	
//	　http://localhost:8080/myweb2/member/update　　　/member/update　가상주소 POST방식
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String updatePost(MemberBean mb,HttpSession session,Model model,HttpServletRequest request) {
		System.out.println("아이디"+mb.getId());
		System.out.println("비번"+mb.getPass());
		System.out.println("이름"+mb.getName());
		// MemberService 인터페이스 상속 클래스 MemberServiceImpl  @Service
		// MemberDAO 인터페이스 상속 클래스 MemberDAOImpl  @Repository
		MemberBean mb2=memberService.userCheck(mb);
		if(mb2!=null) {			
			//수정작업 수정 set name=?  조건 id=?
			String postcode=request.getParameter("postcode");
			String roadAddress=request.getParameter("roadAddress");
			String detailAddress=request.getParameter("detailAddress");
			String address = postcode+"/"+roadAddress+"/"+detailAddress;
			mb.setAddress(address);
			
			memberService.updateMember(mb);
//			http://localhost:8080/myweb2/member/main 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/main/main";
		}else {
			model.addAttribute("msg","입력하신 정보 틀립니다.!!!!!!!!!!!!!!!!!!!!");
//			/WEB-INF/views/member/msg.jsp
			return "member/msg";
		}
	}	

	//http://localhost:8080/myweb2/member/delete　　/member/delete　가상주소 GET
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete(HttpSession session,Model model) {
//		/WEB-INF/views/member/deleteForm.jsp
		return "member/delete";
	}

//	　http://localhost:8080/myweb2/member/delete　　　/member/delete　가상주소 POST방식
	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
	public String deletePost(MemberBean mb,HttpSession session,Model model) {
		System.out.println(mb.getId());
		System.out.println(mb.getPass());
		// MemberService 인터페이스 상속 클래스 MemberServiceImpl  @Service
		// MemberDAO 인터페이스 상속 클래스 MemberDAOImpl  @Repository
		MemberBean mb2=memberService.userCheck(mb);
		if(mb2!=null) {
			//삭제작업 삭제  조건 id=?
			memberService.deleteMember(mb);
			//세션 초기화
			session.invalidate();
//			http://localhost:8080/myweb2/member/main 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/main/main";
		}else {
			model.addAttribute("msg","입력하신 정보 틀립니다.");
//			/WEB-INF/views/member/msg.jsp
			return "member/msg";
		}
	}
}
