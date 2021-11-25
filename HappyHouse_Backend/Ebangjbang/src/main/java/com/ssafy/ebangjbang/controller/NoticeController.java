package com.ssafy.ebangjbang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ebangjbang.model.Dto.NoticeDto;
import com.ssafy.ebangjbang.model.service.notice.NoticeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService service;

	@PostMapping
	private ResponseEntity<String> doRegist(@RequestBody NoticeDto notice) throws Exception {
		
		if(service.registNotice(notice) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<NoticeDto>> list() throws Exception {
		logger.info("list - 호출");

		List<NoticeDto> list = service.getNoticeList();
		return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{no}")
	private ResponseEntity<NoticeDto> doDetail(@PathVariable("no") String no) throws Exception {
		service.updateHit(no);
		return new ResponseEntity<NoticeDto>(service.getNotice(no), HttpStatus.OK);
	}
	
	@DeleteMapping("/{no}")
	private ResponseEntity<String> doDelete(@PathVariable("no") String no) throws Exception{
		if(service.resignNotice(no) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping
	private ResponseEntity<String> doModify(@RequestBody NoticeDto notice) throws Exception {
		
		if(service.modifyNotice(notice) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
}
