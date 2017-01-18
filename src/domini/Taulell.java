package domini;

import javax.swing.SwingWorker;

import aplicacio.Crear;

public class Taulell{
	private static final int DIM=9;
	private int[] coordenadesError=new int[2];
	
	
	private Casella[][] taulell = new Casella [DIM][DIM];
	
	public Taulell () throws Exception {
		initTaulell();
		this.obtenirGraella();
	}
	public Taulell(String buit) throws Exception {
		initTaulell();
	}
	private void initTaulell() throws Exception {
		for (int i=0; i<DIM; i++)
			for (int j=0; j<DIM; j++)
				taulell[i][j]=new Casella(0,true);
	}
	private void obtenirGraella() throws Exception{
		Crear.crearTaulell(this);
	}
	public void crearGraella(){
		for (int i=0; i<DIM; i++)
			for (int j=0; j<DIM; j++)
				if(taulell[i][j].getValor()!=0)taulell[i][j].setModificable(false);
	}
	public String valorTaula(){
	String cadena=null;
		
		for (int i=0;i<taulell.length;i++){		
			 for (int j=0;j<taulell[i].length;j++){				
				if(taulell[i][j].getValor()==0){
					cadena+="?";					
				}else cadena+=taulell[i][j].getValor();	
			}	
		}
		return cadena; 
	}
	public int [][] valorsTaula(){
		
		int [][] valors= new int [DIM][DIM];
		for (int i=0;i<9;i++){		
			for (int j=0;j<9;j++){
				valors[i][j]=taulell[i][j].getValor();
			}
		}
		return valors;
		
	}
	public boolean casellesPlenes(){
		for(int i=0;i<taulell.length;i++)
			for (int j=0;j<taulell[i].length;j++)
				if (taulell[i][j].getValor()==0)
					return false;
		return true;
	}
	
	public void borrarCasella(int fila, int columna) throws Exception{
		//new Coordenada (fila,columna).validarCoordenada(); //validem que estigui dins dels límits NO CAL
		if(taulell[fila][columna].getModificable())
			if(!(taulell[fila][columna].casellaBuida())) //comprovem que sigui modificable i no estigui buida
				taulell[fila][columna].setValor(0);
			//else throw new Exception ("Aquesta casella ja està buida");
		//else throw new Exception ("Aquesta casella no es pot borrar");
	}
	
	public void canviarValor(int fila, int columna, int valor)throws Exception {
			this.coordenadesError[0]=0;
			this.coordenadesError[1]=0;
			if(0>=valor || valor>9)
					throw new Exception ("El valor no està entre 1 i 9");//valor que volem passar	
		
				
			
			if(!(taulell[fila][columna].validarContingut()))
				
				throw new Exception ("El valor no està entre 1 i 9");//valor que volem passar	
		
			
			if(!(taulell[fila][columna].getModificable())) 
				throw new Exception ("El valor no és modificable");
		
			Coordenada coordenada = new Coordenada (fila, columna);
			if(valor!=0){
				coordenada.validarCoordenada();
				this.comprovar(coordenada, valor);
			}
			taulell[fila][columna].setValor(valor);
	}

	public void canviarValor(int fila, int columna, int valor, boolean modificable) throws Exception{
		try {
			this.canviarValor(fila, columna, valor);
		}catch(Exception e){
			taulell[fila][columna].setValor(0);
		}
		taulell[fila][columna].setModificable(modificable); 
	}
	
	
	private void comprovar (Coordenada coordenada, int valor) throws Exception{
		Coordenada[] cf=coordenada.calculaFila();
		Coordenada[] cc=coordenada.calculaColumna();
		Coordenada[] cz=coordenada.calculaZona(); 
		
		comprovarAjuda(cf, valor, "fila");
		comprovarAjuda(cc, valor, "columna");
		comprovarAjuda(cz, valor, "zona");
		
	}
	private void comprovarAjuda(Coordenada [] c, int v, String s) throws Exception{
		for(int i=0;i<8;i++){
			if(taulell[c[i].getFila()][c[i].getColumna()].getValor()==v){
				this.coordenadesError[0]=c[i].getFila()+1;
				this.coordenadesError[1]=c[i].getColumna()+1;
				throw new Exception ("número repetit a "+s+" en la posició: ("+(c[i].getFila()+1)+","+(c[i].getColumna()+1)+")");
			}
			}
	}
	
	public int[] getCoordenadesUltimError(){
		return coordenadesError;
	
	}
	
	public void canvis() throws Exception{  		
		
		Equivalents e= new Equivalents (this.taulell);
		
		
	}
	
}