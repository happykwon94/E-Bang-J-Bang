package com.ssafy.ebangjbang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ebangjbang.model.Dto.FreeDto;
import com.ssafy.ebangjbang.model.Dto.UserDto;
import com.ssafy.ebangjbang.model.service.admin.AdminService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private AdminService service;
	
	@GetMapping("/user/list")
	public ResponseEntity<List<UserDto>> getUserList() throws Exception{
		
		List<UserDto> list = null;
		
		list = service.getUserList();
		
		return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
		
	}
	
	// 유저 삭제
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") String id) throws Exception{
		
		if(service.deleteUser(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);			
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);			
		}
		
		
	}
//	[board] -------------------------------------------------------------------------------------	
	
	@GetMapping("/free/list")
	public ResponseEntity<List<FreeDto>> getFreeList() throws Exception{
		
		List<FreeDto> list = null;
		
		list = service.getFreeList();
		
		return new ResponseEntity<List<FreeDto>>(list, HttpStatus.OK);
		
	}

}
