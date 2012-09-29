package ronsoftware.inputtemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a root of content structure.
 */
public class ContentRoot implements ContentObserver {
	
	private ContentList contents = new ContentList();
	private Map<String, Content> idMap = new HashMap<String, Content>();
	
	public ContentList getContents() {
		return contents;
	}

	protected Map<String, Content> getIdMap() {
		return idMap;
	}
	
	public ContentAtom createAtom(String text) {
		
		ContentAtom item = new ContentAtom(text);
		item.addObserver(this);
		return item;
	}
	
	public ContentList createList() {
		
		ContentList itemList = new ContentList();
		itemList.addObserver(this);
		return itemList;
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
