package it.polito.tdp.bar;

public class TestSimulatore {

	public static void main(String[] args) {
		
		Simulatore sim = new Simulatore();

		
		sim.init();
		
		sim.run();
		
		System.out.println("Num eventi: "+sim.getNumeroServiti());
		System.out.println("Num serviti: "+sim.getNumeroSoddisfatti());
		System.out.println("Num non serviti: "+sim.getNumeroAbbandoni());
	}

}
