package ronsoftware.inputtemplate;

/**
 *	A dumper of template content structure.
 */
public class ContentDumper {

	private String indentStr = " ";
	
	public String getDump(ContentList list) {
		
		StringBuilder out = new StringBuilder();
		dump(list, 0, out);
		return out.toString();
		
	}
	
	private void dump(
		ContentList list, 
		int level, 
		StringBuilder out) {
		
		for (Content content : list) {
			
			if (content.isList()) {
				dump(content.asList(), level + 1, out);
			} else if (content.isAtom()){
				dump(content.asAtom().toString(), level, out);
			}
		}
	}
	
	private void dump(
		String content,
		int level,
		StringBuilder out) {
		
		for (int i = 0; i < level; i++) {
			out.append(indentStr);
		}
		
		out.append(content);
		out.append("\n");
	}

	public String getIndentStr() {
		return indentStr;
	}

	public void setIndentStr(String indentStr) {
		this.indentStr = indentStr;
	}

}
