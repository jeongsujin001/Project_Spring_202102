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
import com.project.domain.EmailDTO;
import com.project.domain.PageBean;
import com.project.service.BoardService;
import com.project.service.CommentService;
import com.project.service.EmailService;

@Controller
public class EmailController {
	
	@Inject
	EmailService emailService;

	@RequestMapping("/email/write") // 이메일 쓰기를 누르면 이 메소드로 맵핑되어서
    public String write() {
        return "/email/write"; // 다시 write jsp 페이지로 이동하고 jsp페이지에서 내용을 다 채운 뒤에 확인 버튼을 누르면 send.do로 넘어감
    }
 
    @RequestMapping("/email/send") // 확인 (메일발송) 버튼을 누르면 맵핑되는 메소드
    public String send(EmailDTO dto, Model model) {
        try {
 
            emailService.sendMail(dto); // dto (메일관련 정보)를 sendMail에 저장함
            model.addAttribute("message", "성공적으로 전송되었습니다."); // 이메일이 발송되었다는 메시지를 출력시킨다.
 
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "발송 실패했습니다."); // 이메일 발송이 실패되었다는 메시지를 출력
        }
        return "/contact/contact-succeed"; // 실패했으므로 다시 write jsp 페이지로 이동함
    }

}
