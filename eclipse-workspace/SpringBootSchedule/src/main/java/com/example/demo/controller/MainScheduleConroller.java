package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.example.demo.json.Events;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class MainScheduleConroller {
	@RequestMapping("/Main")
    public ModelAndView setSchedule(ModelAndView mv) {
		mv.setViewName("main");
        Date setDate = new Date();//このコードを実行した日時のデータが入る
        SimpleDateFormat date1 =new SimpleDateFormat("yyyy-MM-dd");
        String date2 = date1.format(setDate).toString();//setDateを書式化した処理日付を入れる
        mv.addObject("initialDate", date2);
        
        List<Events> events = new ArrayList<Events>();
        Events ev = new Events() {{
        	title = "タイトル"; description="概要";
        	start = "2020-08-15T11:30:00";
        	end = "2020-08-15T17:30:00";
        }};
        events.add(ev);
       try { //jsonは簡易版XML
        ObjectMapper mapper = new ObjectMapper(); 
        mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        String json = mapper.writeValueAsString(events);
        mv.addObject("events", json);//json化した文字列を配置
       } catch(JsonProcessingException e){
           e.printStackTrace();
       }
       return mv;
      
        	 
        

	}
	

	
}
