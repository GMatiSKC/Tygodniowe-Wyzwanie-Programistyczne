package kanapka;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import service.SandwichService;
import service.SandwichServiceImpl;

public class SandwichServiceTest {
	private final static String EGG = "jajko";
	private final static String HAM = "szynka";
	private final static String EMPTY_RESULT = "";
	
	@Test
	public void getSandwichTestShouldReturnEggValue(){
		SandwichService sandwichService = new SandwichServiceImpl();
		
		String result = sandwichService.getSandwich("chlebjajkochleb");
		
		assertNotNull(result);
		assertEquals(EGG, result);
	}
	
	@Test
	public void getSandwichTestShouldReturnHamValue(){
		SandwichService sandwichService = new SandwichServiceImpl();
		
		String result = sandwichService.getSandwich("xxchlebszynkachlebyy");
		
		assertNotNull(result);
		assertEquals(HAM, result);
	}
	
	@Test
	public void getSandwichTestShouldReturnEmpty(){
		SandwichService sandwichService = new SandwichServiceImpl();
		
		String result = sandwichService.getSandwich("xxchlebyy");
		
		assertEquals(EMPTY_RESULT, result);
	}
}
