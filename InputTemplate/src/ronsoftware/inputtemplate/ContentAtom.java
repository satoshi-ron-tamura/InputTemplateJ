package ronsoftware.inputtemplate;

/**
 * Represents an atomic element of template content
 * having no more structure.
 */
public class ContentAtom extends Content {
	
	private String text;
	
	public ContentAtom(String s) {
		text = s;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (!super.equals(obj))
			return false;
		
		ContentAtom other = (ContentAtom) obj;
		if (!testEqual(this.text, other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public ContentAtom clone() {
		return (ContentAtom) super.clone();
	}

	@Override
	protected Content doClone() {
		ContentAtom newItem = new ContentAtom(this.text);
		return newItem;
	}
	
}
