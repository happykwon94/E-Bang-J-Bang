package com.ssafy.ebangjbang.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ebangjbang.model.HouseDealDto;
import com.ssafy.ebangjbang.model.HouseInfoDto;
import com.ssafy.ebangjbang.model.SeoulStoreDto;
import com.ssafy.ebangjbang.model.SidoGugunCodeDto;
import com.ssafy.ebangjbang.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugun(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getGugun(sido);
	}

	@Override
	public List<HouseInfoDto> getDong(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getDong(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptList(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getAptList(dong);
	}

	@Override
	public HouseDealDto getAptInfo(String aptCode) throws Exception {
		return sqlSession.getMapper(HouseMapper.class).getAptInfo(aptCode);
	}

	@Override
	public List<HouseInfoDto> getPriceList(String dong, String maxPrice) throws Exception {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("dong", Integer.parseInt(dong));
		map.put("maxPrice", Integer.parseInt(maxPrice));
		return sqlSession.getMapper(HouseMapper.class).getPriceList(map);
	}

	@Override
	public HouseInfoDto getAddressName(String gugunCode, String dongName) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("gugunCode", gugunCode);
		map.put("dongName", dongName);
		return sqlSession.getMapper(HouseMapper.class).getAddressName(map);
	}

	@Override
	public List<SeoulStoreDto> getStoreList(String dongName, String classes) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dongName", dongName);
		map.put("classes", classes);
		return sqlSession.getMapper(HouseMapper.class).getStoreList(map);
	}
}
