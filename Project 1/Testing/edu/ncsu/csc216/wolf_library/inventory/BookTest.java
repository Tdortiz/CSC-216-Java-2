package edu.ncsu.csc216.wolf_library.inventory;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This class JUnit tests Book.java
 * @author Thomas Ortiz, Corey Colberg
 */
public class BookTest {
	
	/**
	 * Tests the Book Constructor by using the correct Book format.
	 */
	@Test
	public void testConstructor(){
		@SuppressWarnings("unused")
		Book a = new Book("0  Pride and Prejudice by Jane Austin");
	}
	
	/**
	 * Tests the Book Constructor by using the inccorect format. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorInt(){
		@SuppressWarnings("unused")
		Book a = new Book("abc  Pride and Prejudice by Jane Austin");
	}
	
	/**
	 * Tests The Constructor by using whitespace for a title.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorEmpty(){
		@SuppressWarnings("unused")
		Book a = new Book("abc  ");
	}
	
	/**
	 * Tests the ToString() of Book.
	 */
	@Test
	public void testToString(){
		Book a = new Book("3  Pride and Prejudice by Jane Austin");
		assertEquals("Pride and Prejudice by Jane Austin",a.toString());	
	}
	
	/**
	 * Tests the isAvaiable() method.
	 */
	@Test
	public void testIsAvailable(){
		Book a = new Book("3  Pride and Prejudice by Jane Austin");
		assertEquals(true, a.isAvailable());	
	}
	
	@Test
	public void testIsAvialableNone(){
		Book a = new Book("0  Pride and Prejudice by Jane Austin");
		assertEquals(false, a.isAvailable());
	}
	
	/** 
	 * Tests the backToInventory() method.
	 */
	@Test
	public void testBackToInventory(){
		Book a = new Book("3  Pride and Prejudice by Jane Austin");
		a.backToInventory();
		assertTrue(4 == a.getNumAvailable());
	}
	
	/** 
	 * Tests the removeOneCopy() method.
	 */
	@Test
	public void testRemoveOneCopy(){
		Book a = new Book("5  Pride and Prejudice by Jane Austin");
		a.removeOneCopyFromInventory();
		assertEquals(4, a.getNumAvailable());
	}
	
	/** 
	 * Tests the removeOneCopy() method by attempting to remove a copy 
	 * of a book with 0 copies left.
	 */
	@Test(expected=IllegalStateException.class)
	public void testRemoveOneCopyZero(){
		Book a = new Book("0  Pride and Prejudice by Jane Austin");
		a.removeOneCopyFromInventory();
	}
	
	/** 
	 * Tests the compateTo() method.
	 */
	@Test
	public void testCompareToGreater(){
		Book A = new Book("3  Pride and Prejudice by Jane Austin");
		Book D = new Book("5  A Day in the Past by Stephen E. Ambrose");
		int comp = A.compareTo(D);
		assertTrue(comp > 0);
	}
	
	/** 
	 * Tests the compateTo() method.
	 */
	@Test
	public void testCompareToLess(){
		Book A = new Book("3  Pride and Prejudice by Jane Austin");
		Book D = new Book("5  A Day in the Past by Stephen E. Ambrose");
		int comp = D.compareTo(A);
		assertTrue(comp < 0);
	}
	
	/** 
	 * Tests the compateTo() method.
	 */
	@Test
	public void testCompareToEquel(){
		Book A = new Book("3  Pride and Prejudice by Jane Austin");
		Book D = new Book("5  Pride and Prejudice by Jane Austin");
		int comp = D.compareTo(A);
		assertTrue(comp == 0);
	}

}
