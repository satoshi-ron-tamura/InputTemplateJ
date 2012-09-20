package ronsoftware.inputtemplate;

/**
 * Thrown when a parser error has occurred.
 * Typically, when a syntax error has occurred,
 * a <code>Parser</code> throws an instance of this class.
 */
@SuppressWarnings("serial")
public class ParserException extends Exception {
	
	public ParserException(String msg) {
		super(msg);
	}
}
