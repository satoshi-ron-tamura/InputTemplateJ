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

}
