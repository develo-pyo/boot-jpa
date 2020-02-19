package Calculator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	private Calculator cal;
	
	@Before
	public void setup() {
		cal = new Calculator();
		System.out.println("setup !!!");
	}
	
	@Test
	public void add() {
		int r = cal.add(2, 3);
		assertEquals("sc", 5, r);
		System.out.println("add !!!");
	}

	@Test
	public void devide() {
		int r = cal.devide(3, 3);
		assertEquals("success", 1, r);
		System.out.println("devide !!!");
	}

	@After
	public void teardown() {
		System.out.println("teardown !!!");
	}
	
}
