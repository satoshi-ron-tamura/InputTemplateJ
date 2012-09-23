package ronsoftware.inputtemplate;

/**
 *	A printer of template content structure.
 */
public class ContentPrinter {

	public String getPrint(ContentList list) {
		
		StringBuilder out = new StringBuilder();
		print(list, 0, out);
		return out.toString();
		
	}
	
	private void print(
		ContentList list, 
		int level, 
		StringBuilder out) {
		
		for (Content content : list) {
			
			if (content.isList()) {
				print(content.asList(), level + 1, out);
			} else if (content.isAtom()){
				print(content.asAtom().toString(), level, out);
			}
		}
	}
	
	private void print(
		String content,
		int level,
		StringBuilder out) {
		
		out.append(content);
	}

}
