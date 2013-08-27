package ronsoftware.inputtemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttrsParser {

	private static final String INPUT_PAT_STR = "^\\s*(\\w*)\\.?(\\w*).*$";
	private static final Pattern inputPat = Pattern.compile(INPUT_PAT_STR);
	
	public void parse(String attrs, Content content) {
		Matcher m = inputPat.matcher(attrs);
		if (m.matches()) {
			if (!"".equals(m.group(1)))
				content.setId(m.group(1));
			if (!"".equals(m.group(2)))
				content.setClassAttr(m.group(2));
		}
	}
}
