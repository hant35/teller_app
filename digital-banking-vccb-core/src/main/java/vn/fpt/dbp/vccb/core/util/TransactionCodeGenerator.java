package vn.fpt.dbp.vccb.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

public class TransactionCodeGenerator {
	
	/*
	 * Generate transaction code with format: yyMMdd + random number(5) --> length = 11
	 */
	public static String generate(){
		
		StringBuilder sb = new StringBuilder();
		Date currentDate = new Date();
		DateFormat formatter = new SimpleDateFormat("yyMMdd");
		
		sb.append(formatter.format(currentDate));
		sb.append(RandomStringUtils.randomNumeric(5));
		
		return sb.toString();
	}

}
