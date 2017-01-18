package domini;

public class Equivalents{
	
	private Casella[][] taulell;
	
	
	public Equivalents(Casella [][]t) throws Exception{
		this.taulell=t;
		
		int quants= generarRandom(1,6); 
		int [] quins = sequenciaCanvis(quants); 
		System.out.println("Numero de canvis: "+quants);
		for(int i=0; i<quins.length; i++){
			quinIntercanvi(quins[i]); 
		}
		
		borrarModificables();
	}
	private void borrarModificables() throws Exception{
		for (int i = 0; i < taulell.length; i++) {
			for (int j = 0; j < taulell.length; j++) {
				if(taulell[i][j].getModificable()){
				taulell[i][j].setValor(0);		
			}
				}
		}
	}
	
	private void quinIntercanvi(int quin) throws Exception { 
		
		switch (quin) {
		case 1: //Intercanviar Files
			int[] respostaF = generarRandomCanvisFC(generarRandom(0, 8));
			invertirF(respostaF[0], respostaF[1]);
			System.out.println("Canvi de fila: Fila "+ respostaF[0]+" amb fila "+respostaF[1]);
			break;
		case 2: // Intercanviar Columnes
			int[] respostaC = generarRandomCanvisFC(generarRandom(0, 8));
			invertirC(respostaC[0], respostaC[1]);
			System.out.println("Canvi de columna: Columna "+ respostaC[0]+" amb columna "+respostaC[1]);
			break;
		case 3: // Transposar graella
			transposar();
			System.out.println("Transposta");

			break;
		case 4: // Girar la graella
			int graus=generarRandom(1,3);
			girar(graus);
			System.out.println("Girar (1=90º, 2=180º, 3=270º): "+graus);

			break;
		case 5: // Intercanviar regions horitzontalment
			int[] respostaRH = generarRandomZona(generarRandom(0, 2));
			regionsHoritzontal(respostaRH[0], respostaRH[1]);
			String[] pantallaH=pantallaRegions(respostaRH,"Horitzontal");
			System.out.println("Intercanvi regions Horitzontals, "+pantallaH[0]+" per "+pantallaH[1]);
			break;
		case 6: //Intercanviar regions verticalment
			int[] respostaRV = generarRandomZona(generarRandom(0, 2));
			regionsVertical(respostaRV[0], respostaRV[1]);
			String[] pantallaV=pantallaRegions(respostaRV,"Vertical");
			System.out.println("Intercanvi regions Verticals, "+pantallaV[0]+" per "+pantallaV[1]);
			break;

		default:
			break;
		}
		
	}
		
		
//MÈTODES PER GENERAR ELS RANDOM//
	private int generarRandom(int min, int max){ 
		int numA=(int) (Math.random()*max + min); 												
		return numA;
	}
	
	private int [] generarRandomCanvisFC( int random){
		int [][] num = {{0,1},{0,2},{1,2},{3,4},{3,5},{4,5},{6,7},{6,8},{7,8}};
		int [] resultat = new int [2];
		resultat[0]=num[random][0];
		resultat[1]=num[random][1];
		return resultat;
	}
	private int [] generarRandomZona (int random){
		
		int[][] num={{0,3},{0,6},{3,6}};
		int[] resultat = new int [2];
		resultat[0]=num[random][0];
		resultat[1]=num[random][1];
		return resultat;
	}
	
///////////////////////////////////////////////////////////////////////
	
	
//MÉTODES PER GENERAR ELS TAULELLS EQUIVALENTS//
		
		//1
		private void invertirF( int alfa, int beta) throws Exception{  
			int aux=0;
			boolean mod=true;
			for (int i = 0; i < taulell.length; i++) {				
					aux=taulell[alfa][i].getValor();
					mod=taulell[alfa][i].getModificable();
					taulell[alfa][i].setValor(taulell[beta][i].getValor());							
					taulell[alfa][i].setModificable(taulell[beta][i].getModificable());
					taulell[beta][i].setModificable(mod);
					taulell[beta][i].setValor(aux);	
				
			}
			
		}
		//2
		private void invertirC( int alfa, int beta) throws Exception{  
			int aux=0;
			boolean mod=true;
			for (int i = 0; i < taulell.length; i++) {
					aux=taulell[i][alfa].getValor();
					mod=taulell[i][alfa].getModificable();
					taulell[i][alfa].setValor(taulell[i][beta].getValor());
					taulell[i][alfa].setModificable(taulell[i][beta].getModificable());
					taulell[i][beta].setValor(aux);	
					taulell[i][beta].setModificable(mod);
				
			}
			
		}		
		//3
		private void transposar() throws Exception{ //files passen a columnes
			int aux=0;
			boolean mod=true;
			for(int i = 0; i < taulell.length; i++) {
				  for(int j = i+1; j < taulell.length; j++) {
					  aux=taulell[i][j].getValor();
					  mod=taulell[i][j].getModificable();
						taulell[i][j].setValor(taulell[j][i].getValor());
						taulell[i][j].setModificable(taulell[j][i].getModificable());
						taulell[j][i].setValor(aux);
						taulell[j][i].setModificable(mod);
				  }
				}
		
		}
		//4
		private void girar(int graus) throws Exception{
			switch (graus) {
			case 1: //90 graus
				transposar();
				reverseC();
				
				break;
			case 2: //180 graus
				reverseC();
				reverseF();
				
				break;
			case 3: //270 graus
				transposar();
				reverseF();	
				
				break;

			default:
				break;
			}
		}
		
		//5
		private void regionsHoritzontal(int alfa, int beta) throws Exception{ //alfa pot ser, 0,3,6
			int aux=0;
			boolean mod=true;
			int cont=0;
			while(cont<3){
			for (int i = 0; i < taulell.length; i++) {				
					aux=taulell[alfa+cont][i].getValor();
					mod=taulell[alfa+cont][i].getModificable();
					taulell[alfa+cont][i].setValor(taulell[beta+cont][i].getValor());
					taulell[alfa+cont][i].setModificable(taulell[beta+cont][i].getModificable());
					taulell[beta+cont][i].setValor(aux);
					taulell[beta+cont][i].setModificable(mod);
			}
			cont++;
			}
			
			
			
		}
		//6
		private void regionsVertical(int alfa, int beta) throws Exception{
			
			int aux=0;
			int cont=0;
			boolean mod=true;
			while(cont<3){
			for (int i = 0; i < taulell.length; i++) {
					aux=taulell[i][alfa+cont].getValor();
					mod=taulell[i][alfa+cont].getModificable();
					taulell[i][alfa+cont].setValor(taulell[i][beta+cont].getValor());
					taulell[i][alfa+cont].setModificable(taulell[i][beta+cont].getModificable());
					taulell[i][beta+cont].setValor(aux);	
					taulell[i][beta+cont].setModificable(mod);
			}
			cont++;
			}
			
			
			
		}
		
		
		
	//MÉTODES AUXILIARS
 		private void reverseC() throws Exception{
			int aux=0;
			boolean mod=true;
			for(int i = 0; i < taulell.length; i++) {
				  for(int  j = 0; j < taulell.length/2; j++) {
					  aux=taulell[i][j].getValor();
					  mod=taulell[i][j].getModificable();
						taulell[i][j].setValor(taulell[i][(taulell.length-j)-1].getValor());
						taulell[i][j].setModificable(taulell[i][(taulell.length-j)-1].getModificable());
						taulell[i][(taulell.length-j)-1].setValor(aux);
						taulell[i][(taulell.length-j)-1].setModificable(mod);
				  }
				}
		}
	
		private void reverseF() throws Exception{
			int aux=0;
			boolean mod=true;
			for(int i = 0; i < taulell.length/2; i++) {
				  for(int  j = 0; j < taulell.length; j++) {
					  aux=taulell[i][j].getValor();
					  mod=taulell[i][j].getModificable();
						taulell[i][j].setValor(taulell[(taulell.length-i)-1][j].getValor());
						taulell[i][j].setModificable(taulell[(taulell.length-i)-1][j].getModificable());
						taulell[(taulell.length-i)-1][j].setValor(aux);
						taulell[(taulell.length-i)-1][j].setModificable(mod);
				  }
				}
		}
	
		
	
	
	
	
	private int[] sequenciaCanvis(int quants){ 
		int[]quins= new int[quants];
		int plens=0;
		int random;
		while (plens <quants) {
			random=generarRandom(1, 6);
			if(plens==0){ //Array buit
			quins[plens]=random;
			plens++;
			}
			else {			
			if (!repe(quins,random,plens)){
				quins[plens]=random;
				plens++;
				
				}
			}
		}
		
		
		return quins;
	}
	private boolean repe(int[] numeros, int random, int plens){
		for (int i = 0; i < plens; i++) {
			if(numeros[i]==random)
				return true;
		}
	return false;
		
	}
	
	private String[] pantallaRegions(int[] numRegio, String HV){
		String pantalla[]=new String[2];
		for(int k=0;k<2;k++){
			if(numRegio[k]==0){
				if(HV.equals("Horitzontal"))
					pantalla[k]="1 2 3";
				else pantalla[k]="1 4 7";
			}
			if(numRegio[k]==3){
				if(HV.equals("Horitzontal"))
					pantalla[k]="4 5 6";
				else pantalla[k]="2 5 8";
			}
			if(numRegio[k]==6){
				if(HV.equals("Horitzontal"))
					pantalla[k]="7 8 9";
				else pantalla[k]="3 6 9";
			}
		}
		return pantalla;
	}
	

	
}
