package prova;

import aplicacio.Crear;
import domini.Taulell;


public class GraellaInicial extends Crear{

	public void crearTaulellA (Taulell t) throws Exception {
		
		t.canviarValor(0, 0, 5, false); 
		t.canviarValor(0, 1, 3, false); 
		t.canviarValor(0, 4, 7, false); 
		t.canviarValor(1, 0, 6, false);
		t.canviarValor(1, 3, 1, false);
		t.canviarValor(1, 4, 9, false);
		t.canviarValor(1, 5, 5, false);
		t.canviarValor(2, 1, 9, false);
		t.canviarValor(2, 2, 8, false);
		t.canviarValor(2, 7, 6, false);
		t.canviarValor(3, 0, 8, false);
		t.canviarValor(3, 4, 6, false);
		t.canviarValor(3, 8, 3, false);
		t.canviarValor(4, 0, 4, false);
		t.canviarValor(4, 3, 8, false);
		t.canviarValor(4, 5, 3, false);
		t.canviarValor(4, 8, 1, false);
		t.canviarValor(5, 0, 7, false);
		t.canviarValor(5, 4, 2, false);
		t.canviarValor(5, 8, 6, false);
		t.canviarValor(6, 1, 6, false);
		t.canviarValor(6, 6, 2, false);
		t.canviarValor(6, 7, 8, false);
		t.canviarValor(7, 3, 4, false);
		t.canviarValor(7, 4, 1, false);
		t.canviarValor(7, 5, 9, false);
		t.canviarValor(7, 8, 5, false);
		t.canviarValor(8, 4, 8, false);
		t.canviarValor(8, 7, 7, false);
		t.canviarValor(8, 8, 9, false);
	}
}
