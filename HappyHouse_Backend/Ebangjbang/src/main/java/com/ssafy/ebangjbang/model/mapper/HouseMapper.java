package com.ssafy.ebangjbang.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ebangjbang.model.BookMarkDto;
import com.ssafy.ebangjbang.model.ClinicDto;
import com.ssafy.ebangjbang.model.HouseDealDto;
import com.ssafy.ebangjbang.model.HouseInfoDto;
import com.ssafy.ebangjbang.model.SeoulStoreDto;
import com.ssafy.ebangjbang.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapper {
	
	List<SidoGugunCodeDto> getSido() throws SQLException;

	List<SidoGugunCodeDto> getGugun(String sido) throws SQLException;

	List<HouseInfoDto> getDong(String gugun) throws SQLException;

	List<HouseInfoDto> getAptList(String dong) throws SQLException;

	HouseDealDto getAptInfo(String aptCode) throws SQLException;

	List<HouseInfoDto> getPriceList(HashMap<String, Integer> map) throws SQLException;

	HouseInfoDto getAddressName(String dongName) throws SQLException;

	List<SeoulStoreDto> getStoreList(HashMap<String, String> map) throws SQLException;

	List<ClinicDto> getClinicList(String gugunName) throws SQLException;

	int addBookMark(BookMarkDto newBookMark) throws SQLException;

	List<HouseDealDto> getbookMarkerList(String userNo)throws SQLException;
}
