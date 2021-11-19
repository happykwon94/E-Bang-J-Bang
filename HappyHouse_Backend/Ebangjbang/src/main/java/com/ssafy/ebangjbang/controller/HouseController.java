package com.ssafy.ebangjbang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ebangjbang.model.HouseDealDto;
import com.ssafy.ebangjbang.model.HouseInfoDto;
import com.ssafy.ebangjbang.model.SeoulStoreDto;
import com.ssafy.ebangjbang.model.SidoGugunCodeDto;
import com.ssafy.ebangjbang.model.service.HouseService;

@RestController
@RequestMapping("/house")
@CrossOrigin(origins = "*")
public class HouseController {
	
	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	
	@Autowired
	private HouseService houseService;

	@GetMapping("sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		List<SidoGugunCodeDto> list = houseService.getSido();
		logger.debug("sido");
		if(list != null) {
			return new ResponseEntity<List<SidoGugunCodeDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<SidoGugunCodeDto>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		List<SidoGugunCodeDto> list = houseService.getGugun(sido);
		
		if(list != null) {
			return new ResponseEntity<List<SidoGugunCodeDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<SidoGugunCodeDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
//		logger.debug("dong :"+gugun);
		List<HouseInfoDto> list = houseService.getDong(gugun);
		
		if(list != null) {
			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<HouseInfoDto>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("aptInfo")
	public ResponseEntity<HouseDealDto> aptInfo(@RequestParam("aptCode") String aptCode) throws Exception {
		HouseDealDto housDealDto = houseService.getAptInfo(aptCode);
		logger.debug("아파트코드: "+aptCode);
		if(housDealDto != null) {
			return new ResponseEntity<HouseDealDto>(housDealDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<HouseDealDto>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("aptList")
	public ResponseEntity<List<HouseInfoDto>> maxPrice(@RequestParam("dong") String dong, @RequestParam(value="maxPrice", required=false) String maxPrice) throws Exception {
		List<HouseInfoDto> list;
		if(maxPrice == null) {
			list = houseService.getAptList(dong);
		}else {
			list = houseService.getPriceList(dong, maxPrice);
		}
		if(list != null) {
			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<HouseInfoDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("addressName")
	public ResponseEntity<HouseInfoDto> addressName(@RequestParam("gugunCode") String gugunCode,@RequestParam("dongName") String dongName) throws Exception {
		HouseInfoDto houseInfoDto = houseService.getAddressName(gugunCode, dongName);
		
		if(houseInfoDto != null) {
			return new ResponseEntity<HouseInfoDto>(houseInfoDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<HouseInfoDto>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("store")
	public ResponseEntity<List<SeoulStoreDto>> getStoreList(@RequestParam("dong") String dong, @RequestParam("type") String type) throws Exception {
		List<SeoulStoreDto> list;
		list = houseService.getStoreList(dong, type);
		
		if(list != null) {
			return new ResponseEntity<List<SeoulStoreDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<SeoulStoreDto>>(HttpStatus.NO_CONTENT);
		}
	}

}
