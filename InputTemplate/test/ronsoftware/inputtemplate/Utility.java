package ronsoftware.inputtemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;

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
	
	public static boolean areDifferentTree(ContentList xs, ContentList ys) {
		
		if (xs == null && ys == null) {
			return false;
		} else if (xs == null || ys == null) {
			return true;
		}
		
		Iterator<Content> xi = xs.iterator();
		Iterator<Content> yi = ys.iterator();
		
		while (xi.hasNext() && yi.hasNext()) {
			
			Content x = xi.next();
			Content y = yi.next();
			
			if (x == y) {
				return false;
			} else if (x.isList() && y.isList()) {
				
				if (!areDifferentTree(x.asList(), y.asList())) {
					return false;
				}
				
			}
		}
		
		return true;
	}
	
	public static ContentList createStandardCase() {
		
		ContentBuilder builder = new ContentBuilder();
		builder
			.addAtom("a b c\n")
			.addList()
				.addList()
					.addAtom("a\n")
					.getParent()
				.addList()
					.addAtom("b\n")
					.getParent()
				.addList()
					.addAtom("c\n")
					.getParent()
				.getParent()
			.addAtom(" a b c ")
			.addList()
				.addAtom("a b c");
		
		return builder.getRoot();
	}
	
	public static ContentList createEscapeCase() {
		
		ContentBuilder builder = new ContentBuilder();
		builder
			.addAtom("a b c\n[")
			.addList()
				.addAtom("a\n")
				.getParent()
			.addList()
				.addAtom("b\n")
				.getParent()
			.addList()
				.addAtom("c\n")
				.getParent()
			.addAtom("] a b c [a b c]");
		
		return builder.getRoot();
	}
}
