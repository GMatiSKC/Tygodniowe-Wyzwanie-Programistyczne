package application;

import service.SandwichService;
import service.SandwichServiceImpl;

public class Main {
	static SandwichService sandwichService;
	
	public static void main(String[] args) {
		Init();

		System.out.println(sandwichService.getSandwich("chlebjajkochleb"));
		System.out.println(sandwichService.getSandwich("xxchlebszynkachlebyy"));
		System.out.println(sandwichService.getSandwich("xxchlebyy"));
	}
	
	private static void Init(){
		sandwichService = new SandwichServiceImpl();		
	}

}
