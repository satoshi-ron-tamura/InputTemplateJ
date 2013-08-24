package ronsoftware.inputtemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Template parser.
 * Read tokens from <code>InputStream</code>,
 * and build up <code>Content</code> structure.
 */
public class Parser {
	
	private TokenReader reader;
	
	public Parser(InputStream in, String charsetName)
		throws UnsupportedEncodingException {
		
		reader = new TokenReader(in, charsetName);
	}
	
	public ContentRoot parse()
		throws IOException, ParserException {
		
		ContentRoot root = new ContentRoot();
		Token tok = parse(root.getContents());
		
		if (tok != null) {
			throw new ParserException(
				"Missing opened parenthesis.");
		}
		
		return root;
	}
	
	private Token parse(ContentList list)
		throws IOException, ParserException {
		
		Token tok = null;
		
		while ((tok = reader.readToken()) != null) {
			
			int kind = tok.getKind();
			
			if (kind == Token.KIND_LPAR) {
				
				ContentList sub = new ContentList();
				tok = parse(sub);
				
				if (tok == null) {
					throw new ParserException(
						"Missing closed parenthesis.");
				}
				
				list.add(sub);
				
			} else if (kind == Token.KIND_RPAR) {
				
				return tok;
				
			} else if (kind == Token.KIND_ATTR) {
				
				tok = reader.readToken();
				
				if (tok == null || tok.getKind() != Token.KIND_CONTENT) {
					throw new ParserException(
						"Attribute value required.");
				}
				
				String attrs = tok.toString();
				list.setAttrs(attrs);
				
				tok = reader.readToken();
				
				if (tok == null || tok.getKind() != Token.KIND_ATTR) {
					throw new ParserException(
						"Missing closing attribute.");
				}
				
			} else {
				list.add(new ContentAtom(tok.toString()));
			}
		}
		
		return tok;
	}
}
