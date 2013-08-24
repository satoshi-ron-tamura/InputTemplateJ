package ronsoftware.inputtemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a template content element.
 */
public abstract class Content implements Cloneable {
	
	private static final AttrsParser attrsParser = new AttrsParser();
	
	private String id;
	private String classAttr;
	private String attrs;
	private List<ContentObserver> observers = new ArrayList<ContentObserver>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		
		Content oldContent = this.clone();
		String oldId = oldContent.getId();
		this.id = id;
		
		if (oldId == null || !oldId.equals(this.id)) {
			try {
				notifyUpdate(oldContent, this);
			} catch (Exception e) {
				this.id = oldId;
				throw new RuntimeException(e);
			}
		}
	}
	
	public String getClassAttr() {
		return classAttr;
	}
	
	public void setClassAttr(String classAttr) {
		this.classAttr = classAttr;
	}
	
	public String getAttrs() {
		return attrs;
	}
	
	public void setAttrs(String attrs) {
		this.attrs = attrs;
		if (attrs != null)
			attrsParser.parse(attrs, this);
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
	
	protected void notifyUpdate(Content oldContent, Content newContent) {
		
		for (ContentObserver ob : observers) {
			ob.updateContent(oldContent, newContent);
		}
		
	}
	
	public boolean equalsByValue(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Content other = (Content) obj;
		if (!testEqual(this.id, other.id))
			return false;
		if (!testEqual(this.classAttr, other.classAttr))
			return false;
		if (!testEqual(this.attrs, other.attrs))
			return false;
		return true;
	}
	
	static protected boolean testEqual(Object x, Object y) {
		if (x == null) {
			if (y != null)
				return false;
		} else if (!x.equals(y))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public Content clone() {
		Content newItem = doClone();
		newItem.id = this.id;
		newItem.classAttr = this.classAttr;
		newItem.attrs = this.attrs;
		return newItem;
	}
}
