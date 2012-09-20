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
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContentAtom other = (ContentAtom) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return text;
	}

}
