package domini;

public class Coordenada {

	private int fila;
	private int columna;

	public Coordenada(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public int getFila() {
		return this.fila;
	}

	public int getColumna() {
		return this.columna;
	}

	
	public Coordenada[] calculaFila() {
		Coordenada[] Cfiles = new Coordenada[8];
		int cont=0;
		for (int i = 0; i < 9; i++) {
			if(i!=this.columna){
				Cfiles[cont] = new Coordenada(this.fila, i);
				cont++;
			}
		}
		return Cfiles;
	}

	public Coordenada[] calculaColumna() {
		Coordenada[] Ccolumnes = new Coordenada[8];
		int cont=0;

		for (int i = 0; i < 9; i++) {
			if(i!=this.fila){
			Ccolumnes[cont] = new Coordenada(i, this.columna);
			cont++;
			}
		}
		return Ccolumnes;
	}

	public Coordenada[] calculaZona() {
		
		Coordenada[] Czona = new Coordenada[8];
		int i=(fila/3)*3;
		int j=(columna/3)*3;
		int cont=0;
		
		int i3=i+3;
		int j3=j+3;
		
		while (i<i3){
			while (j<j3){
				if(!(i==fila&&j==columna)) {			
					Czona[cont]=new Coordenada (i,j);
					cont++;
				}
				j++;
			}
			j=(columna/3)*3;
			i++;
		}
		return Czona;
	}

	public void validarCoordenada() throws Exception {  
		if (this.getFila() < 0 && 8 < this.getFila())
			throw new Exception("Fila fora de límits");
		if (this.getColumna() < 0 && 8 < this.getColumna())
			throw new Exception("Columna fora de límits");
	}
}
