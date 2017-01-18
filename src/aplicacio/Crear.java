package aplicacio;

import domini.Taulell;
import prova.GraellaInicial;

public abstract class Crear {
	
	public static void crearTaulell (Taulell t) throws Exception {
		GraellaInicial gi= new GraellaInicial();
		gi.crearTaulellA(t);
	}
	
	public abstract void crearTaulellA(Taulell t) throws Exception;
}