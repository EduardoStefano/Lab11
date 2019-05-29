package it.polito.tdp.bar;

import java.util.Random;

public class ProvaNumeri {

	public static void main(String[] args) {
		Random rand = new Random();
		for(int i=0; i<20; i++) {
			float numero = (float)(rand.nextInt(9+1))/10;
			System.out.println(numero);
		}
		
	}

}
