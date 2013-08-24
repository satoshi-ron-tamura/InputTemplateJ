package ronsoftware.inputtemplate;

/**
 * The instance implemented this interface is 
 * observing changes of a content.
 */
public interface ContentObserver {
	/**
	 * This method is to receive notification of changes of a content.
	 * When a content added, a parameter oldContent is set null.  
	 * When a content deleted, a parameter newContent is set null.
	 * @param oldContent a content before changed.
	 * @param newContent a content after changed.
	 */
	public void updateContent(Content oldContent, Content newContent);
}