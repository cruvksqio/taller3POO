package logica;

import dominio.*;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Hola chicken");
		
		FileManager fm = FileManager.getGestor();
		
		for (Usuario u : fm.getUsuarios()) {
			System.out.println(u);
		}
		
	}

}
