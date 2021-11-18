package com.ssafy.ebangjbang.model.service;

import java.util.List;

import com.ssafy.ebangjbang.model.HouseDealDto;
import com.ssafy.ebangjbang.model.HouseInfoDto;
import com.ssafy.ebangjbang.model.SeoulStoreDto;
import com.ssafy.ebangjbang.model.SidoGugunCodeDto;

public interface HouseService {
	List<SidoGugunCodeDto> getSido() throws Exception;

	List<SidoGugunCodeDto> getGugun(String sido) throws Exception;

	List<HouseInfoDto> getDong(String gugun) throws Exception;

	List<HouseInfoDto> getAptList(String dong) throws Exception;

	HouseDealDto getAptInfo(String aptCode) throws Exception;

	List<HouseInfoDto> getPriceList(String dong, String maxPrice) throws Exception;

	HouseInfoDto getAddressName(String gugunCode, String dongName) throws Exception;

	List<SeoulStoreDto> getStoreList(String dong, String type) throws Exception;
}