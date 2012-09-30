package ronsoftware.inputtemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a root of content structure.
 */
public class ContentRoot implements ContentObserver {
	
	private ContentList contents;
	private Map<String, Content> idMap = new HashMap<String, Content>();
	
	public ContentRoot() {
		contents = new ContentList();
		contents.addObserver(this);
	}
	
	public ContentList getContents() {
		return contents;
	}

	@Override
	public void updateId(String oldId, Content newContent) {
		
		if (oldId != null && idMap.containsKey(oldId)) {
			idMap.remove(oldId);
		}
		
		if (newContent != null) {
			String newId = newContent.getId();
			if (newId != null) {
				idMap.put(newId, newContent);
			}
		}
	}
	
}
