package ronsoftware.inputtemplate;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.Test;

import ronsoftware.inputtemplate.ContentPrinter;
import ronsoftware.inputtemplate.ContentList;
import ronsoftware.inputtemplate.Parser;
import ronsoftware.inputtemplate.ParserException;

import static ronsoftware.inputtemplate.Utility.*;

public class ParserTest {

	
	@Test
	public void testParse() {
		
		int caseNo = 0;
		
		doTestParse(
			String.format("[%1$d] Standard case.", ++caseNo), 
			"a b c\n[[a\n][b\n][c\n]] a b c [a b c]", 
			createStandardCase());
		
		doTestParse(
			String.format("[%1$d] Escape case.", ++caseNo), 
			"a b c\n\\[[a\n][b\n][c\n]\\] a b c \\[a b c\\]", 
			createEscapeCase());
		
		try {
			
			doTestParse(
				String.format("[%1$d] Syntax error case.", ++caseNo), 
				"[a b c", 
				null);
			
		} catch (Exception e) {
			assertTrue(
				String.format("[%1$d] Syntax error case.", caseNo),
				e.getCause() instanceof ParserException);
		}
		
		try {
			
			doTestParse(
				String.format("[%1$d] Syntax error case.", ++caseNo), 
				"a b c]", 
				null);
			
		} catch (Exception e) {
			assertTrue(
				String.format("[%1$d] Syntax error case.", caseNo),
				e.getCause() instanceof ParserException);
		}
	}
	
	@Test
	public void testParseWithIdAndClass() {
		
		int caseNo = 0;
		
		doTestParse(
				String.format("[%1$d] Case with ids and classes.", ++caseNo), 
				"$id1.class1$a b c\n[[$id2.class1$a\n][$id3.class2$b\n]" +
				"[$id4.class2$c\n]] a b c [$id5$a b c][$.class3$a]", 
				createIdAndClassCase(),
				5, 3);
		
	}
	
	private ContentRoot doTestParse(
		String msg,
		String toParse,
		ContentList expected) {
		
		ContentPrinter printer = new ContentPrinter();
		
		ContentRoot parsed = doParse(msg, toParse);
		
		assertEquals(msg,
			printer.getPrint(expected), 
			printer.getPrint(parsed.getContents()));
		assertTrue(msg, expected.equalsByValue(parsed.getContents()));
		
		return parsed;
			
	}
	
	private void doTestParse(
			String msg,
			String toParse,
			ContentList expected,
			int expectedIdCount, 
			int expectedClassCount) {
		
		ContentRoot parsed = doTestParse(msg, toParse, expected);
		assertEquals(msg, expectedIdCount, parsed.getIdCount());
		assertEquals(msg, expectedClassCount, parsed.getClassCount());
	}
	
	private ContentRoot doParse(
			String msg,
			String toParse) {
			
			InputStream in = createInput(toParse, DEFAULT_CHARSET_NAME);
			
			try {
				Parser ps = new Parser(in, DEFAULT_CHARSET_NAME);
				ContentRoot parsed = ps.parse();
				return parsed;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}
}
