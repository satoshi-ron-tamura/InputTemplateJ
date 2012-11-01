package ronsoftware.inputtemplate;

import static org.junit.Assert.*;

import org.junit.Test;

import static ronsoftware.inputtemplate.Utility.*;

public class ContentTest {

	@Test
	public void testClone() {
		
		ContentList orign = createStandardCase();
		ContentList cloned = orign.clone();
		
		assertEquals("orign equals cloned.", orign, cloned);
		assertFalse("orign and orign are same.", areDifferentTree(orign, orign));
		assertFalse("cloned and cloned are same.", areDifferentTree(cloned, cloned));
		assertTrue("orign and cloned are different.", areDifferentTree(orign, cloned));
	}
	
	@Test
	public void testId() {
		
		ContentRoot root = new ContentRoot();
		ContentList orign = createStandardCase();
		orign.setId("id1");
		root.getContents().add(orign);
		Content got = root.getContentById("id1");
		assertNotNull("[1] getContentById returns the instance.", got);
		assertSame("[1] getContentById returns the same instance.", orign, got);
		
		orign.setId("id2");
		got = root.getContentById("id1");
		assertNull("after the id changed, getContentById returns null.", got);
		got = root.getContentById("id2");
		assertNotNull("after the id changed, getContentById returns the instance.", got);
		assertSame("after the id changed, getContentById returns the same instance.", orign, got);
		
		boolean success = root.getContents().remove(orign);
		assertTrue("remove success.", success);
		got = root.getContentById("id2");
		assertNull("[1] after the content removed, getContentById returns null.", got);
		
		root.getContents().add(orign);
		got = root.getContentById("id2");
		assertNotNull("[2] getContentById returns the instance.", got);
		assertSame("[2] getContentById returns the same instance.", orign, got);
		
		got = root.getContents().remove(0);
		assertNotNull("the removed is an instance.", got);
		assertSame("the removed is the same instance.", orign, got);
		assertEquals("the removed has the same identifier.", "id2", got.getId());
		
		got = root.getContentById("id2");
		assertNull("[2] after the content removed, getContentById returns null.", got);
		
		success = root.getContents().addAll(orign);
		assertTrue("addAll success.", success);
		assertEquals("after added all, the contents size is the orign size.",
			4, root.getContents().size());
		
		success = root.getContents().removeAll(orign);
		assertTrue("removeAll success.", success);
		assertEquals("after removed all, the contents size is zero.",
			0, root.getContents().size());
		
		
	}

}
