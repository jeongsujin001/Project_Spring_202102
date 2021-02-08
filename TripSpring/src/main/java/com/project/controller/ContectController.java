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
import com.project.domain.PageBean;
import com.project.service.BoardService;

@Controller
public class ContectController {

	//http://localhost:8080/TripSpring/contact/contact　　/contact/contact　가상주소
	@RequestMapping(value = "/contact/contact", method = RequestMethod.GET)
	public String contact() {
//		/WEB-INF/views/contact/contact.jsp
		return "contact/contact";
	}
	
}
