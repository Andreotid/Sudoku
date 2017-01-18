	package domini;

public class Casella {

	private static final int BUIDA = 0;
	private int valor;
	private boolean modificable;
	
		public Casella (int valor, boolean modificable) throws Exception{
			if (this.validarContingut()){
				this.valor=valor;
				this.modificable=modificable;
			}else{
				throw new Exception ("impossible crear una nova Casella");
			}
		}
		
		public int getValor() {
			return valor;
		}

		public void setValor(int valor) throws Exception {
		//if(this.validarContingut()&&this.getModificable())
				this.valor = valor;
			//else throw new Exception ("Valor incorrecte");
		}

		public boolean getModificable() {
			return modificable;
		}
		public void setModificable(boolean modificable) {
			this.modificable=modificable;
		}
		
		public boolean validarContingut(){	
			return (0<=this.valor&&this.valor<=9);
		}
		
		public boolean casellaBuida(){
			return (this.valor==BUIDA);
		}
}