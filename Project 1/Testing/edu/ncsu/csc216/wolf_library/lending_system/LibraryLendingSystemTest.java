package edu.ncsu.csc216.wolf_library.lending_system;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class JUnit tests LibraryLendingSystem.
 * @author Thomas Ortiz, Corey Colberg
 *
 */
public class LibraryLendingSystemTest {
	
	/**
	 * Tests the constructor method.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testConstructor(){
		@SuppressWarnings("unused")
		LibraryLendingSystem a = new LibraryLendingSystem("abc.txt");
	}
	
	/**
	 * Tests the login method.
	 */
	@Test
	public void testLogin(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 3);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		assertEquals(a.getCurrentUserId(), "patron1@ncsu.edu");
	}
	
	/**
	 * Tests the login method's exception.
	 */
	@Test(expected=IllegalStateException.class)
	public void testLoginException(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 3);
		a.login("patron1@ncsu.edu", "pw1");
	}

	/**
	 * Tests the login method's exception.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLoginException1(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
	
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 3);
		a.logout();
		a.login("asdf", "pw1");
	}

	/**
	 * Tests the addNewPatron method.
	 */
	@Test(expected=IllegalStateException.class)
	public void testAddNewPatron(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 3);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.addNewPatron("asdf", "asdf", 5);
	}

	/**
	 * Tests the cancelAccount method.
	 */
	@Test
	public void testCancelAccount(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 3);
		a.cancelAccount("patron1@ncsu.edu");
		assertEquals(-1, a.getAccounts().listAccounts().indexOf("patron1@ncsu.edu") );
	}

	/**
	 * Tests the cancelAccount method.
	 */
	@Test(expected=IllegalStateException.class)
	public void testCancelAccountException(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 3);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.cancelAccount("patron1@ncsu.edu");
	}

	/**
	 * Tests the showInventory method.
	 */
	@Test
	public void testShowInventory(){
		LibraryLendingSystem a = new LibraryLendingSystem("books.txt");
		String s = "The Elements of Style (4th Edition) by Strunk and White\n"
				+ "Love in the Time of Cholera by Gabriel Garcia Marquez\n"
				+ "Pride and Prejudice by Jane Austin\n";
		assertEquals(a.showInventory(), s);
	}

	/**
	 * Tests the getCurrentUserId method.
	 */
	@Test
	public void testGetCurrentUserId(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		assertEquals("admin", a.getCurrentUserId());
	}

	/**
	 * Tests the getCurrentUserId method.
	 */
	@Test
	public void testGetCurrentUserIdNoLogin(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		assertEquals("", a.getCurrentUserId());
	}

	/**
	 * Tests the reserveItem method.
	 */
	@Test
	public void testReserveItem(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.reserveItem(1);
		a.reserveItem(0);
		assertTrue(a.traverseReserveQueue().contains(a.getBookDB().findItemAt(0).toString()));
	}

	/**
	 * Tests the reserveItem method.
	 */
	@Test(expected=IllegalStateException.class)
	public void testReserveItemException(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.reserveItem(1);
	}

	/**
	 * Tests the reserveItem method.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testReserveItemException1(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.reserveItem(-12);
		a.reserveItem(Integer.MAX_VALUE);
	}

	/**
	 * Tests the reserveItem method.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testReserveItemException2(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.reserveItem(Integer.MAX_VALUE);
	}

	/**
	 * Tests the reserveMoveAheadOne method.
	 */
	@Test
	public void testReserveMoveAheadOne(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.reserveItem(0); // Checked out immediately
		a.reserveItem(1); // Love in the... now @ 0
		a.reserveItem(2); // The elements... now @ 1
 		a.reserveMoveAheadOne(1); // The Elements @ 0 and Love in @ 1
		String s = "The Elements of Style (4th Edition) by Strunk and White\n"
				+  "Love in the Time of Cholera by Gabriel Garcia Marquez\n";
		assertTrue(s.equals(a.traverseReserveQueue()));
	}

	/**
	 * Tests the removeSelectedFromReserve method.
	 */
	@Test
	public void testRemoveSelectedFromReserves(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.reserveItem(0); // Checked out immediately
		a.reserveItem(1); // Love in the... now @ 0
		a.reserveItem(2); // The elements... now @ 1
		a.reserveItem(3); // The Dark is.... now @ 2
 		a.removeSelectedFromReserves(2);
		String s = "Love in the Time of Cholera by Gabriel Garcia Marquez\n"
				+  "The Elements of Style (4th Edition) by Strunk and White\n";
		assertTrue(s.equals(a.traverseReserveQueue()));
	}

	/**
	 * Tests the removeSelectedFromReserve method.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveSelectedFromReservesException(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.reserveItem(0); // Checked out immediately
		a.reserveItem(1); // Love in the... now @ 0
		a.reserveItem(2); // The elements... now @ 1
		a.reserveItem(3); // The Dark is.... now @ 2
 		a.removeSelectedFromReserves(5);
	}

	/**
	 * Tests the traverseCheckedOut method.
	 */
	@Test
	public void testTraverseCheckedOut(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.reserveItem(0); // Checked out immediately
		a.reserveItem(1); // Love in the... now @ 0
		a.reserveItem(2); // The elements... now @ 1
		a.reserveItem(3); // The Dark is.... now @ 2
		
 		String s = "Pride and Prejudice by Jane Austin\n";
 		assertTrue(s.equals(a.traverseCheckedOut()));
	}

	/**
	 * Tests the returnItem method.
	 */
	@Test
	public void testReturnItem(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.logout();
		a.login("patron1@ncsu.edu", "pw1");
		a.reserveItem(0); // Checked out immediately
		a.reserveItem(1); // Love in the... now @ 0
		a.reserveItem(2); // The elements... now @ 1
		a.reserveItem(3); // The Dark is.... now @ 2
		a.returnItem(0);
		System.out.println(a.traverseCheckedOut());
 		String s = "* Love in the Time of Cholera by Gabriel Garcia Marquez\n";
 		assertTrue(s.equals(a.traverseCheckedOut()));
	}

	/**
	 * Tests the returnItem method's exception.
	 */
	@Test(expected=IllegalStateException.class)
	public void testReturnItemException(){
		LibraryLendingSystem a = new LibraryLendingSystem("Book.txt");
		a.login("admin", "admin");
		a.addNewPatron("patron1@ncsu.edu", "pw1", 1);
		a.returnItem(0);
	}
}