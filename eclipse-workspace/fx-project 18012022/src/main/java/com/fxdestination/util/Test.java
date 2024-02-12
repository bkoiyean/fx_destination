package com.fxdestination.util;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fxdestination.constant.Constant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class Test {

	/* Get the class name to be printed on */
	private static Logger logger = LogManager.getLogger(Test.class);

	public static void main(String[] args) throws IOException, SQLException {
		
		//BasicConfigurator.configure();
		String password = "7";
		String hassPass = "$2a$12$/2GS3c15sIWy5BMEMYvyY.zPWluOaFa9wErCYWtyqQlGtOhxcHxfy";
		
		//System.out.println(BCrypt.hashpw(password, BCrypt.gensalt(Constant.GENSALT_DEFAULT_LOG2_ROUNDS)));

		
		//System.out.println(BCrypt.checkpw(password, hassPass));


		logger.debug("Hello this is a debug message");
		
		logger.info("Entering application.");
		try {
			System.out.println("title is: " + 10 / 0);
		} catch (Exception e) {
			logger.error("Error happen!", e); // this will put all the details in log file configured earlier
			logger.warn("Warn happen!", e); // this will put all the details in log file configured earlier

		}

		logger.info("Exiting application.");
		
		//Document doc = Jsoup.connect("https://buy.travelex.com.au/au/purchase?productCode1=VND").timeout(30000).get();
		//String title = doc.title();
		//System.out.println("title is: " + title);

		//String ownText = doc.body().ownText();
		//String text = doc.body().text();

		//System.out.println(ownText);
		//System.out.println(text);

		//Elements span = doc.select("span.currate-left");
		//Elements p = doc.select("p.result__BigRate-sc-1bsijpp-1");
		
		String rate ="";
		
		
		//for (Element item : span) {
			//System.out.println(item.text());
	//		rate +=item.ownText();

	//	}
	//	System.out.println(span.get(0).ownText());
		
		
		
		
	}
	
	public static int getLevel(double totalAUD) {
		if (totalAUD < 10000) {
			return 0;
		} else if (totalAUD >= 10000 && totalAUD < 20000) {
			return 1;
		} else if (totalAUD >= 20000 && totalAUD < 50000) {
			return 2;
		} else if (totalAUD >= 50000 && totalAUD < 100000) {
			return 3;
		} else if (totalAUD >= 100000 && totalAUD < 200000) {
			return 4;
		} else {
			return 5;
		}
	}
}