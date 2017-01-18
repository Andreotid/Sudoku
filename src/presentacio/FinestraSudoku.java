package presentacio;

import aplicacio.ControlJoc;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class FinestraSudoku implements ActionListener {

	
	private JFrame frame;
	private static FinestraSudoku fin;
	private ControlJoc cj;
	
	
	private int [][] cadena = null;
	private JPanel panel = new JPanel();
	private String a=null;
	private JPanel sudoku = new JPanel(new GridLayout(0,9));
	private JTextField [][] JTF= new JTextField [9][9];
	private boolean graellaBuida=false;
	
	public FinestraSudoku() {
		
		try{			
			
		frame=new JFrame("Sudoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		JOptionPane.showMessageDialog(frame,
			       "Benvingut, has d'omplir el taulell sense repetir cap número "
			       + "a la fila, columna o zona!  BONA SORT! ",
			       "BENVINGUT",
			       JOptionPane.INFORMATION_MESSAGE);
	
		 JDialog nouSudoku=new JDialog(frame,"Tria tipus de graella",true);
		 ferJDialog(nouSudoku, " Pots triar entre obtenir una graella del joc generada automàticament o entrar una nova graella inicial ", " Generar graella automàticament "," Crear graella ",true);  
		
		 sudoku.setBorder(new LineBorder(Color.BLACK));
		
		JButton reset=new JButton("Nova graella");
		reset.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e)
			  {
			   JDialog nouSudoku=new JDialog(frame,"Nova graella",true);
			   ferJDialog(nouSudoku," Si optes per una nova graella, perdràs l'actual sudoku, i no el podràs recuperar ", "Continuar partida actual", "Generar nova graella",false);
			  }
			});
		JButton finalitzar=new JButton("Començar el joc");
		finalitzar.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  { int count=0;
				  cadena = cj.mostrarTaula();
				  for(int i=0;i<9;i++)
						for(int j=0;j<9;j++){
							if(cadena[i][j]!=0)count++;
						}
				  if(count<17){
					  JOptionPane.showMessageDialog(frame,
			   			       "Com ha minim ha d'haver 17 caselles plenes",
			   			       "Error:",
			   			       JOptionPane.ERROR_MESSAGE);
				  }
				  else{
					  cj.crearNovaGraella();
					  borrarGraellaActual();
					  panel.remove(finalitzar);
					  panel.add(reset);
					  novaGraella(true);
					  
				  }
			   }
			});
		if(graellaBuida){
			cj=new ControlJoc("Buida");
			panel.add(finalitzar);
		}
		else{
			cj=new ControlJoc();
			panel.add(reset);
		}
		cadena = cj.mostrarTaula();
		pintarGraella(a);
		panel.add(sudoku);	
		
		}catch (Exception e){
			JOptionPane.showMessageDialog(frame,
	   			       e.getMessage(),
	   			       "Error:",
	   			       JOptionPane.ERROR_MESSAGE);
		}
	}
	private void novaGraella(boolean buida){
		try {
			if(!buida)
				cj.ferCanvis();
			this.cadena=cj.mostrarTaula();
			panel.remove(sudoku);
			pintarGraella(a);
			panel.add(sudoku);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame,
	   			       e1.getMessage(),
	   			       "Error:",
	   			       JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void main(String[] args)  {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try {
					fin = new FinestraSudoku();
					fin.frame.setBounds(750, 300, 400, 470);
					fin.frame.setVisible(true);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(fin.frame,
			   			       e.getMessage(),
			   			       "Error:",
			   			       JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	private void pintarRecuadre(int i, int j, JTextField JTF[][]){
		 Color bg=JTF[(int)i-1][(int)j-1].getBackground();
		 JTF[(int)i-1][(int)j-1].setBackground(Color.RED);
		Timer timer = new Timer (2000, new ActionListener () 
		   { 
		       public void actionPerformed(ActionEvent e) 
		       { 
		    	  
		    	   JTF[(int)i-1][(int)j-1].setBackground(bg);
		        } 
		   }); 
		timer.start();
		
	}
	private void pintarRecuadreValidant(JTextField b){
		 b.setBackground(Color.GREEN);
	}
	private void pintarRecuadreBlanc(JTextField b){
		 b.setBackground(Color.WHITE);
	}
	private void borrarGraellaActual(){
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++){
		sudoku.remove(JTF[i][j]);
		sudoku.revalidate();
		sudoku.repaint();
		}
	}
	private void pintarBarres(int i, int j, JTextField b){
		if(i==2 || i==5){
		     b.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.BLACK));
		     if(j==2 || j==5){
		      b.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.BLACK));
		     }
		    }
		    else if(j==2 || j==5){
		     b.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 5, Color.BLACK));
		    }
		    
		    else{
		     b.setBorder(new LineBorder(Color.BLACK,1));
		    }
		b.setPreferredSize( new Dimension(40, 40) );
		b.setFont(new Font("Tahoma",Font.PLAIN,22));
		b.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	private void Win(){
		JOptionPane.showMessageDialog(frame,
			       "Has guanyat moltes felicitats!",
			       "Has Gunyat!",
			       JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	@Override
	public void actionPerformed(ActionEvent e) {		
	}
	private void ferJDialog(JDialog sudo, String etiqueta, String botoEsq, String botoDrt,boolean buida){
		sudo.setResizable(true);
		sudo.getContentPane().setLayout(new BoxLayout(sudo.getContentPane(), BoxLayout.X_AXIS));
		   JLabel msgNouSudoku=new JLabel(etiqueta,SwingConstants.CENTER);
		   
		  
		   JButton continuar= new JButton(botoEsq);
		   JButton novaGraella=new JButton(botoDrt);
		   continuar.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e){
				   sudo.dispose();}
		   });
		   novaGraella.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e){
				   if(buida){	
					 graellaBuida=true;
				   }
				   else{
					   borrarGraellaActual();
					   novaGraella(false);
					}
				   
				   sudo.dispose();
			   }
		   });
		   sudo.add(msgNouSudoku);
		   sudo.add(continuar);
		   sudo.add(novaGraella);
		   
		   sudo.setBounds(500, 400, 950, 100); //finestra de nou sudoku
		   sudo.setVisible(true);
	}
	private void pintarGraella (String a){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				try{
					int x=i;
					int y=j;
				JTextField b=new JTextField();
				
				if(cadena[i][j]!=0){
					a = ""+cadena[i][j];
					b.setEditable(false);
					b.setEnabled(false);
					b.setBackground(Color.GRAY);
				    b.setForeground(Color.YELLOW);
				}else{
					a="";
					b.setBackground(Color.WHITE);
				}		
				b.setText(a);
				pintarBarres(i,j,b);
				sudoku.add(b);
				JTF[i][j]=b;
				b.addFocusListener(new FocusListener() {
				      public void focusGained(FocusEvent f) {
				    	  pintarRecuadreValidant(b);
				        }

				      public void focusLost(FocusEvent f) {
				    	  pintarRecuadreBlanc(b);
				    	  String text=b.getText();
				    	  if(text.equals("-1")){
				    		  JOptionPane.showMessageDialog(frame,
					   			       "T'has rendit",
					   			       "FI JOC:",
					   			       JOptionPane.INFORMATION_MESSAGE);
				    		  System.exit(0);  
				    	  }
				    	  try{
				    		 if(text.equals("") || text.equals("0")){
				    			 cj.borrarCasella(x, y);
				    			 b.setText("");
				    		 }
				    		 else{
				    			 
				    			 cj.llegirDades(x,y,text);
				    				 
				    			 
				    		 }
				    		 
					    	if(cj.casellesPlenes()) Win();
				    	  }catch(IllegalArgumentException ie){
				    		  String error=ie.getMessage();
				    		  JOptionPane.showMessageDialog(frame,
					   			       ie.getMessage(),
					   			       "Error:",
					   			       JOptionPane.ERROR_MESSAGE);
				    		  b.setText("");
				    	  }catch(Exception e){
				    		  String error=e.getMessage();
				    		  int [] ultimError=new int[2];
				    		  ultimError=cj.getCoordenadesUltimError();
							  if(0<ultimError[0] && ultimError[0]<10)
							  pintarRecuadre(ultimError[0], ultimError[1], JTF);
				    		  JOptionPane.showMessageDialog(frame,
					   			       e.getMessage(),
					   			       "Error:",
					   			       JOptionPane.ERROR_MESSAGE);
				    		  b.setText("");
				    		  try{cj.borrarCasella(x, y);
						     }catch(Exception e1){
						    	 JOptionPane.showMessageDialog(frame,
						    			 e.getMessage(),
						    			 "Error:",
						    			 JOptionPane.ERROR_MESSAGE);
						     }
				    	  }
				    }  	        			      
				});
				
				}catch(Exception e){
					JOptionPane.showMessageDialog(frame,
			   			       e.getMessage(),
			   			       "Error:",
			   			       JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
