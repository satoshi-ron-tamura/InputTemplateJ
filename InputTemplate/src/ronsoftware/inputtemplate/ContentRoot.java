package ronsoftware.inputtemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a root of content structure.
 */
public class ContentRoot implements ContentObserver {
	
	private ContentList contents;
	private Map<String, Content> idMap = new HashMap<String, Content>();
	private Map<String, List<Content>> classMap = new HashMap<String, List<Content>>();
	
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
	
	/**
	 * Get a content list instance by a class name.
	 * @param name	a class name.
	 * @return	a content list instance
	 */
	public List<Content> getContentsByClass(String name) {
		
		List<Content> entries = null;
		if (classMap.containsKey(name)) {
			entries = classMap.get(name);
		}
		
		List<Content> results = new ArrayList<Content>();
		if (entries != null)
			for (Content c: entries)
				results.add(c);
		
		return results;
	}
	
	protected void updateId(Content oldContent, Content newContent) {
		String oldId = null;
		if (oldContent != null)
			oldId = oldContent.getId();
		String newId = null;
		if (newContent != null)
			newId = newContent.getId();
		
		if (oldId != null && idMap.containsKey(oldId)) {
			idMap.remove(oldId);
		}
		
		if (newId != null) {
			idMap.put(newId, newContent);
		}
	}

	protected void updateClass(Content oldContent, Content newContent) {
		String oldClass = null;
		if (oldContent != null)
			oldClass = oldContent.getClassAttr();
		String newClass = null;
		if (newContent != null)
			newClass = newContent.getClassAttr();
		
		if (oldClass != null)
			removeClassEntry(oldClass, oldContent);
		
		if (newClass != null)
			putClassEntry(newClass, newContent);
	}
	
	private void removeClassEntry(String name, Content content) {
		if (!classMap.containsKey(name))
			return;
		List<Content> entries = classMap.get(name);
		if (entries == null)
			return;
		entries.remove(content);
	}
	
	private void putClassEntry(String name, Content content) {
		List<Content> entries = null;
		if (classMap.containsKey(name)) {
			entries = classMap.get(name);
			for (Content c: entries)
				if (c == content)
					return;
		} else {
			entries = new ArrayList<Content>();
			classMap.put(name, entries);
		}
		entries.add(content);
	}
	
	@Override
	public void updateContent(Content oldContent, Content newContent) {
		updateId(oldContent, newContent);
		updateClass(oldContent, newContent);
	}
	
}
