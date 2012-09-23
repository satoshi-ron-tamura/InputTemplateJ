package ronsoftware.inputtemplate;

/**
 * Represents a template content element.
 */
public abstract class Content implements Cloneable {
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		try {
			return (Content) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
