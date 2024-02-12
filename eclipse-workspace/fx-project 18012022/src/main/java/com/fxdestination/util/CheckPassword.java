package com.fxdestination.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPassword {
	
	public static boolean isValid(String password) {
		
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);
        
        if (hasDigit.find() && hasSpecial.find() 
        		&& password.length()>=8 
        		&&!password.toLowerCase().equals(password) 
        		&& !password.toUpperCase().equals(password)) {
        	return true;
        }
        return false;
	}

}
