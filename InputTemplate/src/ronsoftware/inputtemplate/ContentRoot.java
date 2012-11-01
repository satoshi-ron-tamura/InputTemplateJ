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
	
	/**
	 * Get a content list instance.
	 * @return The content list instance.
	 */
	public ContentList getContents() {
		return contents;
	}

	/**
	 * Get a content instance by an identifier.
	 * @param id    An identifier.
	 * @return The content instance.
	 *          If the identifier couldn't be found,
	 *          returns null.
	 */
	public Content getContentById(String id) {
		
		if (idMap.containsKey(id)) {
			return idMap.get(id);
		} else {
			return null;
		}
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
