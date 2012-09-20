package ronsoftware.inputtemplate;

/**
 *	Represents a token.
 */
public class Token {
	
	public static final int KIND_CONTENT = 0;
	public static final int KIND_LPAR = 1;
	public static final int KIND_RPAR = 2;
	public static final int KIND_ATTR = 3;

	public static final char CHAR_LPAR = '[';
	public static final char CHAR_RPAR = ']';
	public static final char CHAR_ATTR = '$';
	
	private static final Token TOKEN_LPAR =
		new Token(String.valueOf(CHAR_LPAR), KIND_LPAR);
	private static final Token TOKEN_RPAR =
		new Token(String.valueOf(CHAR_RPAR), KIND_RPAR);
	private static final Token TOKEN_ATTR =
		new Token(String.valueOf(CHAR_ATTR), KIND_ATTR);
	
	private String tokenStr;
	private int tokenKind;
	
	private Token(String s, int k) {
		tokenStr = s; 
		tokenKind = k;
	}
	
	public static Token getContent(String s) {
		return new Token(s, KIND_CONTENT);
	}
	
	public static Token getLPar() {
		return TOKEN_LPAR;
	}
	
	public static Token getRPar() {
		return TOKEN_RPAR;
	}
	
	public static Token getAttr() {
		return TOKEN_ATTR;
	}
	
	public int getKind() {
		return tokenKind;
	}
	
	@Override
	public String toString() {
		return tokenStr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tokenKind;
		result = prime * result
			+ ((tokenStr == null) ? 0 : tokenStr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (tokenKind != other.tokenKind)
			return false;
		if (tokenStr == null) {
			if (other.tokenStr != null)
				return false;
		} else if (!tokenStr.equals(other.tokenStr))
			return false;
		return true;
	}

}
