package com.fxdestination.dao;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

		logger.debug("This is a debug message");
		logger.info("This is a info message");
		
		try {
			System.out.println("title is: " + 10 / 0);
		} catch (Exception e) {
			logger.error("Error happen:", e); 
		}
		logger.warn("Warn message");
	}
}