package ronsoftware.inputtemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a root of content structure.
 */
public class ContentRoot {
	
	private ContentList contents = new ContentList();
	private Map<String, Content> idMap = new HashMap<String, Content>();
	
	public ContentList getContents() {
		return contents;
	}

	protected Map<String, Content> getIdMap() {
		return idMap;
	}
	
}
