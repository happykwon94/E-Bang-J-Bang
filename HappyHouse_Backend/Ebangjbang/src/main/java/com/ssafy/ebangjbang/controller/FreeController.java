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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ebangjbang.model.Dto.FreeDto;
import com.ssafy.ebangjbang.model.service.free.FreeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/free")
public class FreeController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private static final Logger logger = LoggerFactory.getLogger(FreeController.class);
	
	@Autowired
	private FreeService service;
	
	@PostMapping
	private ResponseEntity<String> doRegist(@RequestBody FreeDto free) throws Exception {
				
		if(service.registFree(free) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<FreeDto>> list() throws Exception {
		logger.info("list - 호출");

		List<FreeDto> list = service.getFreeList();
		return new ResponseEntity<List<FreeDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{no}")
	private ResponseEntity<FreeDto> doDetail(@PathVariable("no") String no) throws Exception {
		service.updateHit(no);
		return new ResponseEntity<FreeDto>(service.getFree(no), HttpStatus.OK);
	}
	
	@DeleteMapping("/{no}")
	private ResponseEntity<String> doDelete(@PathVariable("no") String no) throws Exception{
		if(service.resignFree(no) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
}
