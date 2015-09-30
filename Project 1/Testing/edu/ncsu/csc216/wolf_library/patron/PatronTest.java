package edu.ncsu.csc216.wolf_library.patron;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.wolf_library.inventory.Book;

/**
 * Tests the Patron Class.
 * @author Corey Colberg, Thomas Ortiz
 */
public class PatronTest {

	/**
	 * Test the getter method to get the patrons id
	 */
	@Test
	public void testGetID() {
		Patron p1 = new Patron("abc", "123", 5);

		assertEquals(p1.getId(), "abc");
	}
	
	/**
	 * Test check out reserve method and traverseCheckOut
	 */
	@Test
	public void testCheckedOut() {
		Patron p1 = new Patron("abc", "123", 3);
		Book b1 = new Book("5 Book1");
		Book b2 = new Book("2 Book2");
		
		p1.reserve(b1);
		p1.reserve(b2);
		
		assertEquals(p1.traverseCheckedOut(), "Book1\nBook2\n");
	}
	
	/**
	 * Test the reserveQueue with multiple books
	 */
	@Test
	public void testReserveQueueMultBook() {
		Patron p1 = new Patron("abc", "123", 2);
		Book b1 = new Book("5 Book1");
		Book b2 = new Book("3 Book2");
		Book b3 = new Book("1 Book3");
		Book b4 = new Book("2 Book4");

		p1.reserve(b1);
		p1.reserve(b2);
		p1.reserve(b3);
		p1.reserve(b4);

		assertEquals(p1.traverseReserveQueue(), "Book3\nBook4\n");
	}
	
	/**
	 * Test the move ahead one method in the reserveQueue
	 */
	@Test
	public void testMoveAheadOne() {
		Patron p1 = new Patron("abc", "123", 1);
		Book b1 = new Book("5 Book1");
		Book b2 = new Book("3 Book2");
		Book b3 = new Book("4 Book3");

		p1.reserve(b1);
		p1.reserve(b2);
		p1.reserve(b3);
		
		p1.moveAheadOneInReserve(1);

		assertEquals(p1.traverseReserveQueue(), "Book3\nBook2\n");
	}
	
	/**
	 * Test move ahead one with an invalid index
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testMoveAheadOneBad() {
		Patron p1 = new Patron("abc", "123", 5);
		Book b1 = new Book("5 Book1");
		Book b2 = new Book("3 Book2");

		p1.reserve(b1);
		p1.reserve(b2);
		
		p1.moveAheadOneInReserve(2);
	}
	
	/**
	 * Test the unReserve method
	 */
	@Test
	public void testUnReserve() {
		Patron p1 = new Patron("abc", "123", 1);
		Book b1 = new Book("5 Book1");
		Book b2 = new Book("3 Book2");
		Book b3 = new Book("3 Book3");

		p1.reserve(b1);
		p1.reserve(b2);
		p1.reserve(b3);
		
		p1.unReserve(1);
		
		assertEquals(p1.traverseReserveQueue(), "Book2\n");
	}
	
	/**
	 * Test the return method
	 */
	@Test
	public void testReturn() {
		Patron p1 = new Patron("abc", "123", 5);
		Book b1 = new Book("5 Book1");
		Book b2 = new Book("3 Book2");

		p1.reserve(b1);
		p1.reserve(b2);
				
		p1.returnBook(0);
	
		assertEquals(p1.traverseCheckedOut(), "Book2\n");
	}
	
	/**
	 * Test to see if the books are properly taken out of the book list
	 */
	@Test
	public void testBooksLeft() {
		Patron p1 = new Patron("abc", "123", 5);
		Book b1 = new Book("1 Book1 by Jeffrey Thomas");
		Book b2 = new Book("3 Book2 by Yes");

		p1.reserve(b1);
		p1.reserve(b2);
					
		assertEquals(b1.isAvailable(), false);
	}
	
	/**
	 * Test return book with an invalid index
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void badReturnBook() {
		Patron p1 = new Patron("abc", "123", 5);
		Book b1 = new Book("1 Book1");
		Book b2 = new Book("3 Book2");
		
		p1.reserve(b1);
		p1.reserve(b2);
		
		p1.returnBook(2);
	}
	
	/**
	 * Test unReserve with an invalid index
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void badUnReserveBook() {
		Patron p1 = new Patron("abc", "123", 1);
		Book b1 = new Book("1 Book1");
		Book b2 = new Book("3 Book2");
		
		p1.reserve(b1);
		p1.reserve(b2);
		
		p1.returnBook(5);
	}
	
	/**
	 * Test reserve with an invalid index
	 */
	@Test(expected=IllegalArgumentException.class)
	public void badReserve() {
		Patron p1 = new Patron("abc", "123", 5);
		Book b1 = null;
		
		p1.reserve(b1);
		
	}
	
	/**
	 * Test traverse checkout method.
	 */
	@Test
	public void testTraverseCheckedOut(){
		Patron p1 = new Patron("abc", "123", 2);
		Book b1 = new Book("5 Book1");
		Book b2 = new Book("3 Book2");
		Book b3 = new Book("1 Book3");
		Book b4 = new Book("2 Book4");

		p1.reserve(b1);
		p1.reserve(b2);
		p1.reserve(b3);
		p1.reserve(b4);

		assertEquals(p1.traverseCheckedOut(), "Book1\nBook2\n");
	}
}
