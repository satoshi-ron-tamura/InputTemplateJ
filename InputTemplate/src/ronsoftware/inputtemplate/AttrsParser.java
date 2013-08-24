package ronsoftware.inputtemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttrsParser {

	private static final String INPUT_PAT_STR = "^\\s*(\\w*)\\.?(\\w*).*$";
	private static final Pattern inputPat = Pattern.compile(INPUT_PAT_STR);
	
	public void parse(String attrs, Content content) {
		Matcher m = inputPat.matcher(attrs);
		int gc = m.groupCount();
		int gi = 0;
		if (gi < gc)
			content.setId(m.group(gi));
		gi++;
		if (gi < gc)
			;
	}
}
