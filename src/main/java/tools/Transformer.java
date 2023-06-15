package tools;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


/**
 * 
 * Transforms a text by replacing a set of tokens
 * by pre-defined values
 * 
 * Example :
 * 
 * I am {name} => I am seb
 * 
 * A token should be a name
 * @author Administrateur
 *
 */
public class Transformer {
	
	private static Logger logger = LoggerFactory.getLogger(Transformer.class);
	private Map<String, String> tokens = new HashMap<>();
	
	/**
	 * Adds a new token which will used when transforming texts.
	 * 
	 * @param token The identifier of token, without the brackets : "name"
	 * @param value the value which will replace the token
	 */
	public void addToken(String token, String value) {
		tokens.put(token, value);
	}
	
	
	/**
	 * Transforms a text by replacing the token with their corresponding values
	 * Each token should be between brackets, for example :
	 * 
	 * Hello {name}, how are uou doing?
	 * @param text
	 * @return return 
	 */	
	public String transform(String text) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		Pattern pattern = Pattern.compile("\\{(.*?)\\}");
		Matcher matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			
			String token = matcher.group(1);
			
			String value = tokens.get(token);
			
			if( value != null)
				matcher.appendReplacement(stringBuilder,value);
			else
				matcher.appendReplacement(stringBuilder, "{" + token + "}");
		}
		matcher.appendTail(stringBuilder);
		return stringBuilder.toString();
		
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Transformer transformer = new Transformer();
		transformer.addToken("name", "seb");
		
		String text = "Bonjour {name}, comment sa va {name}?";
		
		String test = transformer.transform(text);
		
		logger.info(test);
		logger.warn(test);
		logger.error(test);
		
	}

}
