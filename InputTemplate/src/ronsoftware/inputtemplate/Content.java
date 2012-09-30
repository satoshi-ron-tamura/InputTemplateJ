package ronsoftware.inputtemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a template content element.
 */
public abstract class Content implements Cloneable {
	
	private String id;
	private List<ContentObserver> observers = new ArrayList<ContentObserver>();
	
	public String getId() {
		return id;
	}

	protected void setId(String id) {
		
		String oldId = this.id;
		this.id = id;
		
		if (oldId == null || !oldId.equals(this.id)) {
			try {
				notifyUpdateId(oldId, this);
			} catch (Exception e) {
				this.id = oldId;
				throw new RuntimeException(e);
			}
		}
	}

	public boolean isAtom() {
		return this instanceof ContentAtom;
	}
	
	public boolean isList() {
		return this instanceof ContentList;
	}
	
	public ContentAtom asAtom() {
		return (ContentAtom) this;
	}
	
	public ContentList asList() {
		return (ContentList) this;
	}

	protected abstract Content doClone();
	
	protected void addObserver(ContentObserver ob) {
		observers.add(ob);
	}
	
	protected void removeObserver(ContentObserver ob) {
		observers.remove(ob);
	}
	
	protected void appendObserversTo(Content item) {
		
		if (item == null) {
			return;
		}
		
		if (item.isList()) {
			ContentList itemList = item.asList();
			for (Content c : itemList) {
				appendObserversTo(c);
			}
		}
		
		for (ContentObserver ob : observers) {
			item.addObserver(ob);
		}
	}
	
	protected void removeObserversFrom(Object o) {
		
		if (o == null || !(o instanceof Content)) {
			return;
		}
		
		Content item = (Content) o;
		
		if (item.isList()) {
			ContentList itemList = item.asList();
			for (Content c : itemList) {
				removeObserversFrom(c);
			}
		}
		
		for (ContentObserver ob : observers) {
			item.removeObserver(ob);
		}
	}
	
	protected void notifyUpdateId(String oldId, Content newContent) {
		
		for (ContentObserver ob : observers) {
			ob.updateId(oldId, newContent);
		}
		
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public Content clone() {
		Content newItem = doClone();
		newItem.id = this.id;
		return newItem;
	}
}
