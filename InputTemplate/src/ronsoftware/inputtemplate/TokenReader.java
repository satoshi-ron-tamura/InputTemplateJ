package ronsoftware.inputtemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.UnsupportedEncodingException;

/**
 * Read text from <code>InputStream</code>,
 * and separate it into tokens.
 */
public class TokenReader {
	
	private static final char	CHAR_ESCAPE	= '\\';

	private static final int	STAT_CONTENT	= 0;
	private static final int	STAT_ESCAPE		= 1;
	
	private PushbackReader input;
	private StringBuilder readChars = new StringBuilder();
	private int status = STAT_CONTENT;
	
	public TokenReader(InputStream in, String charsetName)
		throws UnsupportedEncodingException {
		
		input = 
			new PushbackReader(
				new BufferedReader(
					new InputStreamReader(in, charsetName)));
	}
	
	public Token readToken() throws IOException {
		
		int read;
		int readLen = readChars.length();
		Token tok = null;
		
		while ((read = input.read()) >= 0) {
			
			char c = (char) read;
			
			switch (c) {
				case CHAR_ESCAPE:
					
					if (status != STAT_ESCAPE) {
						status = STAT_ESCAPE;
					} else {
						
						readChars.append(c);
						readLen++;
						
						status = STAT_CONTENT;
					}
					
					break;
					
				case Token.CHAR_LPAR:
				case Token.CHAR_RPAR:
				case Token.CHAR_ATTR:
					
					if (status == STAT_ESCAPE) {
						
						readChars.append(c);
						readLen++;
						status = STAT_CONTENT;
						
					} else if (readLen == 0) {
						
						if (c == Token.CHAR_LPAR) {
							tok = Token.getLPar();
						} else if (c == Token.CHAR_RPAR) {
							tok = Token.getRPar();
						} else {
							tok = Token.getAttr();
						}
						
						return tok;
						
					} else {
						
						tok =
							Token.getContent(
								readChars.toString());
						readChars.delete(0, readLen);
						input.unread(c);
						
						return tok;
						
					}
					
					break;
					
				default:
					
					if (!Character.isWhitespace(c) &&
						Character.isISOControl(c)) {
						//do nothing.
					} else {
						
						readChars.append(c);
						readLen++;
						status = STAT_CONTENT;
						
					}
			}
		}
		
		if (readLen > 0) {
			
			tok =
				Token.getContent(
					readChars.toString());
			readChars.delete(0, readLen);
			status = STAT_CONTENT;
			return tok;
			
		} else {
			return null;
		}
	}
	
	
}
