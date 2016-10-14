package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SandwichServiceImpl implements SandwichService {
	private final static String BREAD = "chleb";
	private final static String PATTERN_REGEX = "(.*?)";
	
	public String getSandwich(String sandwich) {
		Pattern pattern = Pattern.compile(BREAD + PATTERN_REGEX + BREAD);
		Matcher matcher = pattern.matcher(sandwich);
		
		if (matcher.find()){
			return matcher.group(1);
		}
		
		return "";
	}
}
