package com.fxdestination.util;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fxdestination.constant.Constant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class GetRate {
	
	public static String getXE(String codeXE) throws IOException, SQLException {
		
		Document doc = Jsoup.connect("https://www.xe.com/currencyconverter/convert/?Amount=1&From=AUD&To="+codeXE).get();
		
		Elements p = doc.select("p.result__BigRate-sc-1bsijpp-1");
		
		String rate = p.get(0).text().substring(0, p.get(0).text().indexOf(" "));
		
		return rate;
	}
	
	public static String getTravelex(String codeXE) throws IOException, SQLException {
		
		Document doc = Jsoup.connect("https://buy.travelex.com.au/au/purchase?productCode1="+codeXE).get();
		
		Elements span = doc.select("span.currate-left");
		
		String rate = span.get(0).ownText();
		
		return rate;
	}
	
}
