package ronsoftware.inputtemplate;

/**
 * A builder of template content structure.
 */
public class ContentBuilder {
	
	private ContentBuilder parentBuilder;
	private ContentList root;
	
	public ContentBuilder() {
		root = new ContentList();
	}
	
	public ContentBuilder(ContentList cs) {
		root = cs;
	}
	
	public ContentBuilder(ContentBuilder parent, ContentList cs) {
		parentBuilder = parent;
		root = cs;
	}
	
	public ContentBuilder getParent() {
		return parentBuilder;
	}
	
	public ContentList getRoot() {
		return root;
	}
	
	public ContentBuilder addAtom(String s) {
		root.add(new ContentAtom(s));
		return this;
	}
	
	public ContentBuilder addList() {
		ContentList sub = new ContentList();
		root.add(sub);
		return new ContentBuilder(this, sub);
	}
	
	public ContentBuilder addAttrs(String attrs) {
		root.setAttrs(attrs);
		return this;
	}
}
