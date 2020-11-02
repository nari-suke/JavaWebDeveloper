package com.udacity.jdnd.course1;

import com.udacity.jdnd.course1.service.FizzBuzzService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FizzBuzzServiceTest {

	@Test
	void testFizzBuzz(){
		FizzBuzzService fbs = new FizzBuzzService();

		// check non-divisible numbers return themselves
		assertEquals("1", fbs.fizzBuzz(1));
		assertEquals("47", fbs.fizzBuzz(47));
		assertEquals("121", fbs.fizzBuzz(121));

		// check numbers divisible by 3
		assertEquals("Fizz", fbs.fizzBuzz(3));
		assertEquals("Fizz", fbs.fizzBuzz(333));

		//check numbers divisible by 5
		assertEquals("Buzz", fbs.fizzBuzz(5));
		assertEquals("Buzz", fbs.fizzBuzz(85));

		//check numbers divisible by 3 and 5
		assertEquals("FizzBuzz", fbs.fizzBuzz(15));
		assertEquals("FizzBuzz", fbs.fizzBuzz(75));

		//check invalid inputs
		assertThrows(IllegalArgumentException.class, () -> fbs.fizzBuzz(0));
		assertThrows(IllegalArgumentException.class, () -> fbs.fizzBuzz(-1));
	}

	@Test
	void testBuzzFizz(){
		FizzBuzzService bfs = new FizzBuzzService();

		assertEquals(1, bfs.buzzFizz("1",1));
		assertEquals(101, bfs.buzzFizz("101",1));

		assertEquals(3, bfs.buzzFizz("Fizz",1));
		assertEquals(9, bfs.buzzFizz("Fizz",3));

		assertEquals(5, bfs.buzzFizz("Buzz",1));
		assertEquals(10, bfs.buzzFizz("Buzz",2));

		assertEquals(15, bfs.buzzFizz("FizzBuzz",1));
		assertEquals(30, bfs.buzzFizz("FizzBuzz",2));

	}


	@Test
	void testBuzzFizz_unclearRepetition() {
		FizzBuzzService fbs = new FizzBuzzService();

		// requirements unclear - does "FizzBuzz" count as a "Fizz" and a "Buzz" as well?
		// both these tests fail because they return '15', which is "FizzBuzz"
		assertEquals(18, fbs.buzzFizz("Fizz", 5));
		assertEquals(20, fbs.buzzFizz("Buzz", 3));
	}

	@Test
	void testBuzzFizz_invalidStrings() {
		FizzBuzzService fbs = new FizzBuzzService();

		// should this be case insensitive?
		assertEquals(3, fbs.buzzFizz("fizz", 1)); //throws number format exception

		// what to do about nonsense input?
		assertThrows(IllegalArgumentException.class, () -> fbs.buzzFizz("tacocat", 1));
	}

	@Test
	void testBuzzFizz_boundaryChecking() {
		FizzBuzzService fbs = new FizzBuzzService();

		// how should the program represent that no input produces the output. This example would
		// return the integer -1, which is incorrect. Should we throw an exception, return 0 or some other value?
		assertThrows(IllegalArgumentException.class, () -> fbs.buzzFizz("-1", 1));

		// what about integers recurrence? There should never be a second occurrence
		// of "1", so what do we expect the program to do?
		assertThrows(IllegalArgumentException.class, () -> fbs.buzzFizz("1", 2));

		// we can also enter invalid occurrence param for "Fizz" or "Buzz", getting back 0 or negative numbers
		assertThrows(IllegalArgumentException.class, () -> fbs.buzzFizz("Fizz", 0)); // returns 0
		assertThrows(IllegalArgumentException.class, () -> fbs.buzzFizz("Buzz", -1)); // returns -5
	}

}
