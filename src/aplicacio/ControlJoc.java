package aplicacio;

import domini.Taulell;

public class ControlJoc{
	 private int fila, columna, valor;
	private Taulell taulell =null;
	
	public ControlJoc() throws Exception{
		taulell = new Taulell();
	}
	public ControlJoc(String buit) throws Exception {
		taulell= new Taulell(buit);
	}
	public Taulell getTaulell(){return taulell;}
	
	public void llegirDades(int fila, int columna, String valor) throws Exception{
		int valorI = 0;
		try{	
			valorI = Integer.parseInt(valor);
		
		}catch (Exception e){
			throw new IllegalArgumentException(" Error d'entrada: "+valor);
		}
		taulell.canviarValor(fila, columna, valorI);
					
				
	}
	
	public void crearNovaGraella(){
		taulell.crearGraella();
	}
	public void borrarCasella(int fila, int columna)throws Exception{
		taulell.borrarCasella(fila, columna);
	}
	
	public boolean casellesPlenes(){
		return taulell.casellesPlenes();
	}
	
	public int[][] mostrarTaula (){
		return taulell.valorsTaula();
	}
	public int[] getCoordenadesUltimError(){
		return taulell.getCoordenadesUltimError();
	}
	public void ferCanvis () throws Exception{
		taulell.canvis();
	}
	
}
