package ronsoftware.inputtemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

public class Utility {

	public static final String DEFAULT_CHARSET_NAME = "UTF-8";
	
	public static InputStream createInput(
		String s, String charsetName) {
		
		Charset cs = Charset.forName(charsetName);
		InputStream in =
			new ByteArrayInputStream(
				cs.encode(s).array());
		return in;
		
	}
}
