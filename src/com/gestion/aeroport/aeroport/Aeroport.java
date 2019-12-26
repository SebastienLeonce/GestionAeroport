package com.gestion.aeroport.aeroport;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Aeroport {


	public static void main (String[] args) throws IOException {

		ArrayList<Compagnie> compagnies = Compagnie.generate(2);
		System.out.println(compagnies);
		
		
		
	
	}
}
