package ronsoftware.inputtemplate;

import static org.junit.Assert.*;
import static ronsoftware.inputtemplate.Utility.*;

import java.io.InputStream;

import org.junit.Test;

import ronsoftware.inputtemplate.Token;
import ronsoftware.inputtemplate.TokenReader;

public class TokenReaderTest {
	
	@Test
	public void testReadToken() {
		
		doTestReadToken(
			"[1] Empty.",
			"",
			new String[] { });
		
		doTestReadToken(
			"[2] Delimited by white space.",
			"a   b\tc\r\nd",
			new String[] {"a   b\tc\r\nd"});
		
		doTestReadToken(
			"[3] Parens.",
			"a [b]c",
			new String[] {"a ", "[", "b", "]", "c"});
		
		doTestReadToken(
			"[4] Nested parens.",
			"[a [b]c]",
			new String[] {"[", "a ", "[", "b", "]", "c", "]"});
		
		doTestReadToken(
			"[5] With attribute.",
			"[$a$a [$b$b]c]",
			new String[] {
				"[", "$", "a", "$", "a ", 
				"[", "$", "b", "$", "b", "]", "c", "]"});
		
		doTestReadToken(
			"[6] Escape.",
			"\\\\\\[\\$a\\$a\\][$b$b]\\]c\\$c\\$",
			new String[] {
				"\\[$a$a]", 
				"[", "$", "b", "$", "b", "]", 
				"]c$c$"});
	}
	
	private void doTestReadToken(
		String msg, String toRead, String[] expectedTokens) {
		
		InputStream in = createInput(toRead, DEFAULT_CHARSET_NAME);
		TokenReader reader = null;
		int tokCount = 0;
		
		try {
			
			reader = new TokenReader(in, DEFAULT_CHARSET_NAME);
			Token tok = null;
			
			while ((tok = reader.readToken()) != null) {
				
				assertEquals(
					msg, expectedTokens[tokCount], tok.toString());
				tokCount++;
				
			}
			
			assertEquals(msg, expectedTokens.length, tokCount);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
