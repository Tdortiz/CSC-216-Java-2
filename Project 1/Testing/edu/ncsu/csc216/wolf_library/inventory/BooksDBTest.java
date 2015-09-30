package edu.ncsu.csc216.wolf_library.inventory;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;

/**
 * This class JUnit tests BookDB
 * @author Thomas Ortiz, Corey Colberg
 */
public class BooksDBTest {
	
	/**
	 * Tests the BookDB constructor by checking to see if it will throw an 
	 * exception when the invalid Book format is used.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorException(){
		@SuppressWarnings("unused")
		BookDB b = new BookDB("xyz");
	}

	/**
	 * Tests the FindItemAt method.
	 */
	@Test
	public void testFindItemAt(){
		File f = new File("Test.txt");
		PrintStream out = null;
		try{
			out = new PrintStream(f);
		} catch(FileNotFoundException NFE){
			NFE.getMessage();
		}
		Book a = new Book("3  Pride and Prejudice by Jane Austin");
		out.println("3  Pride and Prejudice by Jane Austin");
		out.println("5  A Day in the Past by Stephen E. Ambrose");
		out.println("1  Harry Potter by J.K. Rowling");
		out.println("5  Harry Potter 5 by J.K. Rowling");
		BookDB b = new BookDB("Test.txt");
		
		assertEquals(a.toString(),b.getList().lookAtItem(0).toString());
		out.close();
	}
	
	/**
	 * Tests the FindItemAt() method.
	 */
	@Test
	public void testFindItemAt2(){
		File f = new File("Test.txt");
		PrintStream out = null;
		try{
			out = new PrintStream(f);
		} catch(FileNotFoundException NFE){
			NFE.getMessage();
		}
		Book a = new Book("5  A Day in the Past by Stephen E. Ambrose");
		out.println("3  Pride and Prejudice by Jane Austin");
		out.println("5  A Day in the Past by Stephen E. Ambrose");
		out.println("1  Harry Potter by J.K. Rowling");
		out.println("5  Harry Potter 5 by J.K. Rowling");
		BookDB b = new BookDB("Test.txt");
		
		assertEquals(a.toString(),b.getList().lookAtItem(1).toString());
		out.close();
	}
	
	/**
	 * Tests the FindItemAt() method.
	 */
	@Test
	public void testFindItemAt3(){
		File f = new File("Test.txt");
		PrintStream out = null;
		try{
			out = new PrintStream(f);
		} catch(FileNotFoundException NFE){
			NFE.getMessage();
		}
		Book a = new Book("5  Harry Potter 5 by J.K. Rowling");
		out.println("3  Pride and Prejudice by Jane Austin");
		out.println("5  A Day in the Past by Stephen E. Ambrose");
		out.println("1  Harry Potter by J.K. Rowling");
		out.println("5  Harry Potter 5 by J.K. Rowling");
		BookDB b = new BookDB("Test.txt");
		
		assertEquals(a.toString(),b.getList().lookAtItem(3).toString());
		out.close();
	}
	
	/**
	 * Tests the traverse() method.
	 */
	@Test
	public void testTraverse(){
		File f = new File("Test.txt");
		PrintStream out = null;
		try{
			out = new PrintStream(f);
		} catch(FileNotFoundException NFE){
			NFE.getMessage();
		}
		out.println("3 Z");
		out.println("5 S");
		out.println("1 Y");
		out.println("5 B");
		out.println("3 Q");
		out.println("5 T");
		out.println("1 Y");
		out.println("5 B");
		out.println("2 F");
		out.println("3 O");
		out.println("5 A");
		out.println("1 Y");
		out.println("5 P");
		out.println("2 K");
		out.println("3 D");
		out.println("5 R");
		out.println("1 Y");
		out.println("5 J");
		out.println("2 M");
		out.println("3 Z");
		out.println("5 U");
		out.println("1 Y");
		out.println("5 B");
		out.println("2 M");
		
		BookDB b = new BookDB("Test.txt");
		String s = "A\nB\nB\nB\nD\nF\nJ\nK\nM\nM\nO\nP\nQ\nR\nS\nT\nU\nY\nY\nY\nY\nY\nZ\nZ\n";
		assertEquals(s, b.traverse());
	}
	
	/**
	 * Tests the traverse() method.
	 */
	@Test
	public void testTraverse1(){
		File f = new File("Test.txt");
		PrintStream out = null;
		try{
			out = new PrintStream(f);
		} catch(FileNotFoundException NFE){
			NFE.getMessage();
		}
		out.println("0  Pride and Prejudice by Jane Austin");
		out.println("5  A Day in the Past by Stephen E. Ambrose");
		out.println("1  Harry Potter by J.K. Rowling");
		out.println("5  Harry Potter 5 by J.K. Rowling");
		BookDB b = new BookDB("Test.txt");
	
		String s = "A Day in the Past by Stephen E. Ambrose\nHarry Potter 5 by J.K. Rowling\nHarry Potter by J.K. Rowling\n* Pride and Prejudice by Jane Austin\n";
		assertEquals(s, b.traverse());
	}
}
