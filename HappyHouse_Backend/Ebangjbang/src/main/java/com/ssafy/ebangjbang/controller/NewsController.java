package com.ssafy.ebangjbang.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ebangjbang.model.Dto.NewsDto;

@CrossOrigin("*")
@RestController
@RequestMapping("/news")
public class NewsController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@GetMapping("/headline")
	public ResponseEntity<List<NewsDto>> getHeadline() throws IOException {
		
		logger.info("news list - 호출");
		
		List<NewsDto> list = new ArrayList<NewsDto>();
		
		Document doc = Jsoup.connect("https://land.naver.com/news/headline.naver").get();
		
		// dl 태그에 해당하는 태그 가져옴
		Elements dl = doc.select(".headline_list dl");
				
		// 가져올 헤드라인 수
		int headlineNum = 5;
//		int headlineNum = dl.size();
		
		for(int i = 0; i < headlineNum; i++) {
			NewsDto news = new NewsDto();
			Element dlEl = dl.get(i);	// i번째에 해당하는 dl 태그
			Elements DtEl = dlEl.select("dt"); // dl 태그 안에서 dt 태그들을 선택
			
			// 첫번째 dt 태그 안에 첫 번째 a태그를 선택하고 href 속성값을 출력
			Elements aEl = DtEl.get(0).select("a");
			news.setUrl("https://land.naver.com" + aEl.attr("href"));
						
			if(aEl.select("img").size() != 0) {
				// 이미지가 존재하면 처리
				Element imgEl = aEl.get(0).select("img").get(0);
				news.setTitle(DtEl.get(1).select("a").get(0).ownText());
				news.setImgUrl(imgEl.attr("src"));
			} else {
				// 이미지가 없을 때 처리
				// 태그 내부에 text만 가져옴
				news.setTitle(aEl.get(0).ownText());
			}
			
			// dd 태그를 가져옴
			Element ddEl = dlEl.select("dd").get(0);
			news.setSummary(ddEl.ownText());
			
			// dd 태그 내부에 span 태그 가져옴
			Elements spanEl = ddEl.select("span");
			news.setAgency(spanEl.get(0).ownText());
			news.setPubDate(spanEl.get(1).ownText());
			
			list.add(news);
		}
		
		return new ResponseEntity<List<NewsDto>>(list, HttpStatus.OK);
	}
	
}
