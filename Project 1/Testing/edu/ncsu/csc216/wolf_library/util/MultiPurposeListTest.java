package edu.ncsu.csc216.wolf_library.util;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.ncsu.csc216.wolf_library.inventory.Book;
/**
 * This class JUnit tests MultiPurposeList.java.
 * @author Thomas Ortiz, Corey Colberg
 */
public class MultiPurposeListTest {

	/**
	 * Tests the Constructor method.
	 */
	@Test
	public void testConstructor(){
		MultiPurposeList a = new MultiPurposeList();
		assertEquals(0, a.size());
	}

	/**
	 * Tests the resetIterator method.
	 */
	@Test
	public void testResetIterator(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		list.lookAtItem(0);
		list.resetIterator();
		assertEquals(0,list.getIndex());	
	}

	/**
	 * Tests the hasNext method.
	 */
	@Test
	public void testHasNext(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		
		list.next(); //Book a
		list.next(); //Book b
		list.next(); //Book c: no books left 
		assertEquals(false, list.hasNext());
	}

	/**
	 * Tests the next method.
	 */
	@Test
	public void testNextException(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		assertEquals(b,list.next());
	}

	/**
	 * Tests the next method.
	 */
	@Test
	public void testNextException1(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		list.next();
		assertEquals(c,list.next());
	}

	/**
	 * Tests the addItem method.
	 */
	@Test
	public void testAddItem(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		assertEquals(b,list.lookAtItem(0));
	}

	/**
	 * Tests the addItem method.
	 */
	@Test
	public void testAddItem1(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		assertEquals(c,list.lookAtItem(1));
		//System.out.println(list);
	}

	/**
	 * Tests the addItem method.
	 */
	@Test
	public void testAddItem2(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  BBB");
		Book c = new Book("3 CCC");
		Book d = new Book("5 DDD");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		list.addItem(1, d);
		// [b,d,c,d] should insert last d and move c back.
		//System.out.println(list);
		assertEquals(d,list.lookAtItem(3));
	}

	/**
	 * Tests the isEmpty method.
	 */
	@Test
	public void testIsEmpty(){
		MultiPurposeList list = new MultiPurposeList();
		assertEquals(true, list.isEmpty());
	}

	/**
	 * Tests the addToRear method.
	 */
	@Test
	public void testAddToRear(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		Book e = new Book("2 Zachary Sharps and the Amazing Peach");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		list.addToRear(e);
		assertEquals(e, list.lookAtItem(list.size() - 1));
	}

	/**
	 * Tests the remove method.
	 */
	@Test
	public void testRemove(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		Book e = new Book("2 Zachary Sharps and the Amazing Peach");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		list.addToRear(e);
		list.remove(0);
		//Book b is removed and Book c is moved to index 0
		assertEquals(c, list.lookAtItem(0));
	}

	/**
	 * Tests the moveAheadOne method.
	 */
	@Test 
	public void testMoveAheadOne(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		Book e = new Book("2 Zachary Sharps and the Amazing Peach");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		list.addToRear(e);
		//Nothing should change
		list.moveAheadOne(0);
		assertEquals(b, list.lookAtItem(0));
	}

	/**
	 * Tests the moveAheadOne method.
	 */
	@Test
	public void testMoveAheadOne1(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		Book e = new Book("2 Zachary Sharps and the Amazing Peach");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		list.addToRear(e);
		list.moveAheadOne(1);
		assertEquals(c, list.lookAtItem(0));
	}

	/**
	 * Tests the moveAheadOne method.
	 */
	@Test
	public void testMoveAheadOne2(){
		MultiPurposeList list = new MultiPurposeList();
		Book b = new Book("0  Pride and Prejudice by Jane Austin");
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		Book e = new Book("2 Zachary Sharps and the Amazing Peach");
		list.addItem(0, b);
		list.addItem(1, c);
		list.addItem(2, d);
		list.addToRear(e);
		list.moveAheadOne(1);
		//System.out.println(list);
		assertEquals(b, list.lookAtItem(1));
	}

	/**
	 * Tests the indexOf method.
	 */
	@Test
	public void testIndexOf(){
		MultiPurposeList list = new MultiPurposeList();
		Book c = new Book("3 Harry Potter");
		Book d = new Book("5 Harris Lot");
		Book e = new Book("2 Zachary Sharps and the Amazing Peach");
		list.addItem(0, c);
		list.addToRear(e);
		assertEquals(-1, list.indexOf(d));
	}
	
}
