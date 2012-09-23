package ronsoftware.inputtemplate;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.Test;

import ronsoftware.inputtemplate.ContentDumper;
import ronsoftware.inputtemplate.ContentList;
import ronsoftware.inputtemplate.Parser;
import ronsoftware.inputtemplate.ParserException;

import static ronsoftware.inputtemplate.Utility.*;

public class ParserTest {

	
	@Test
	public void testParse() {
		
		doTestParse(
			"[1] Standard case.", 
			"a b c\n[[a\n][b\n][c\n]] a b c [a b c]", 
			createStandardCase());
		
		doTestParse(
			"[2] Escape case.", 
			"a b c\n\\[[a\n][b\n][c\n]\\] a b c \\[a b c\\]", 
			createEscapeCase());
		
		try {
			
			doTestParse(
				"[3] Syntax error case.", 
				"[a b c", 
				null);
			
		} catch (Exception e) {
			assertTrue(
				"[3] Syntax error case.",
				e.getCause() instanceof ParserException);
		}
		
		try {
			
			doTestParse(
				"[4] Syntax error case.", 
				"a b c]", 
				null);
			
		} catch (Exception e) {
			assertTrue(
				"[4] Syntax error case.",
				e.getCause() instanceof ParserException);
		}
	}
	
	private void doTestParse(
		String msg,
		String toParse,
		ContentList expected) {
		
		InputStream in = createInput(toParse, DEFAULT_CHARSET_NAME);
		ContentDumper dumper = new ContentDumper();
		dumper.setIndentStr(">");
		
		try {
			
			Parser ps = new Parser(in, DEFAULT_CHARSET_NAME);
			ContentRoot parsed = ps.parse();
			assertEquals(msg,
				dumper.getDump(expected), 
				dumper.getDump(parsed.getContents()));
			assertEquals(msg, expected, parsed.getContents());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
