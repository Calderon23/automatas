package Comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana extends JFrame {
	static String dup=" ";
	static boolean repetido=false;
	static Hashtable<String, Simbolo> tabla;
	static String[] tokens;
	static int col=0;
	static int ren=0;
	static TextArea t1;
	static TextArea t2;
	static comp analizador;
	static int con=0;
	static String noDeclarada;
	public Ventana(String title) {		
		tokens= new String[500];
		tabla= new Hashtable();
		InputStream stream = new ByteArrayInputStream( "".getBytes(StandardCharsets.UTF_8));
		analizador = new comp(stream) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setLayout(null);
		this.setResizable(false);
		t1 = new TextArea();
		this.setVisible(true);
		this.setLocation(230, 80);
		this.setSize(800, 600);										
		t1.setLocation(0, 0);	
		t1.setSize(550, 350);	
		t1.setBackground(Color.WHITE);
		JButton btn1= new JButton("Correr");
		Correr run = new Correr();
		btn1.addActionListener(run);
		btn1.setSize(120, 60);
		btn1.setLocation(600, 20);
		JButton btn2= new JButton("Abrir  archivo");
		AdjuntarArchivo adj= new AdjuntarArchivo();
		btn2.addActionListener(adj);
		btn2.setSize(120, 60);
		btn2.setLocation(600, 100);
		JButton btn3= new JButton("Borrar");
		Borrar borrar = new Borrar();
		btn3.addActionListener(borrar);
		btn3.setSize(120, 60);
		btn3.setLocation(600, 180);
		Font fuente=new Font("Dialog", Font.ROMAN_BASELINE, 18);
		t1.setFont(fuente);
		JButton btn4= new JButton("Tabla");
		Tabla tabla = new Tabla();
		btn4.addActionListener(tabla);
		btn4.setSize(120, 60);
		btn4.setLocation(600, 260);
		this.add(t1);
		this.add(btn1);		
		this.add(btn2);
		this.add(btn3);
		this.add(btn4);
		t2 = new TextArea();
		Font fuente2=new Font("Dialog", Font.ITALIC, 16);
		t2.setFont(fuente2);		
		t2.setSize(780, 205);	
		t2.setLocation(5, 355);			
		t2.setEditable(false);
		t2.setBackground(Color.WHITE);		
		t2.setForeground(Color.RED);
		this.add(t2);
		this.repaint();
		
	}
	public static Simbolo buscar(String tok) {
		String regexp = "^(.)+(int|String|char|boolean|double)(.)*"+tok+"(\\s)*(=|;)(.)*$";		
		int x=0;
		String r=" ";	
		for (int i = 0; i < tokens.length; i++) {				
			if (Pattern.matches(regexp, tokens[i])) {				
				x = tokens[i].indexOf(tok);			
				if (x!=-1) {
					con=i;
					r="Line " +String.valueOf(++i)+", Column "+String.valueOf(++x);
					return new Simbolo(null,null,null,null,r);
				}						
			}
				
		}
		return new Simbolo(null,null,null,null,null);	
	}
	public static Simbolo buscarNoDec(String tok) {
		String regexp = "^(\\s)*"+tok+"(.)*$";		
		int x=0;
		String r=" ";	
		for (int i = 0; i < tokens.length; i++) {				
			if (Pattern.matches(regexp, tokens[i])) {				
				x = tokens[i].indexOf(tok);			
				if (x!=-1) {
					con=i;
					r="Line " +String.valueOf(++i)+", Column "+String.valueOf(++x);
					return new Simbolo(null,null,null,null,r);
				}						
			}
				
		}
		return new Simbolo(null,null,null,null,null);	
	}
	public static Simbolo buscarNoDecNum(String tok) {
		String regexp = "^(.)+=(\\s)*"+tok+"(.)+$";		
		int x=0;
		String r=" ";	
		for (int i = 0; i < tokens.length; i++) {				
			if (Pattern.matches(regexp, tokens[i])) {				
				x = tokens[i].indexOf(tok);			
				if (x!=-1) {
					con=i;
					r="Line " +String.valueOf(++i)+", Column "+String.valueOf(++x);
					return new Simbolo(null,null,null,null,r);
				}						
			}
				
		}
		return new Simbolo(null,null,null,null,null);	
	}
	public static Simbolo buscarNoDecNumD(String tok) {
		String regexp = "^(.)+(\\+|\\-)(\\s)*"+tok+"(.)*$";			
		int x=0;
		String r=" ";	
		for (int i = 0; i < tokens.length; i++) {				
			if (Pattern.matches(regexp, tokens[i])) {				
				x = tokens[i].indexOf(tok);			
				if (x!=-1) {
					con=i;
					r="Line " +String.valueOf(++i)+", Column "+String.valueOf(++x);
					return new Simbolo(null,null,null,null,r);
				}						
			}
				
		}
		return new Simbolo(null,null,null,null,null);	
	}
	public static String buscarDuplicados(String tok) {		
		String res=" ";
		int contador=0;
		String regexp = "^(.)+(int|String|char|boolean|double)(.)*"+tok+"(\\s)*(=|;)(.)*$";		
		int x=0;
		buscar(tok);
		for (int i = 0; i < tokens.length; i++) {				
			if (Pattern.matches(regexp, tokens[i])) {
				contador++;
				x = tokens[i].indexOf(tok);			
				if (x!=-1&&(con!=i)) {
					res ="Line " +String.valueOf(++i)+", Column "+String.valueOf(++x);					
				}
				if (contador>1) {
					repetido=true;					
				}
			}
				
		}
		con=0;		
		return res;
			
	}
	
	
	class Borrar implements ActionListener{	
		public void actionPerformed(ActionEvent arg0) {			
			t1.setText(null);
			t2.setText(null);
			tabla.clear();
			dup=" ";
			
		}
		
	}
	class AdjuntarArchivo implements ActionListener {	
		public void actionPerformed(ActionEvent e) {
			 JFileChooser chooser = new JFileChooser();
			 chooser.setDialogTitle("Abrir archivo");
			
			 File rut=new File("C:\\Users\\Abel RM\\Desktop\\tec\\GitHub\\Compilador\\Compilador\\src\\Comp");
			 chooser.setCurrentDirectory(rut);
       	  	chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );        	  
             FileNameExtensionFilter filtro = new FileNameExtensionFilter( ".java","java" ); 
             chooser.setFileFilter( filtro );
             int estado=chooser.showOpenDialog(null);
            String cadena=" ";            
             if(estado==JFileChooser.APPROVE_OPTION)
             {	            	  	            	            	  
           	  try
                 { 	            		 
                     File archivoelegido=chooser.getSelectedFile();
                     String ruta=archivoelegido.getPath();          
                     FileReader f = new FileReader(ruta);
                     BufferedReader b = new BufferedReader(f);                                                     		 
				     String g="";         	 
                     while((cadena = b.readLine())!=null) {
                    	 g+=cadena+"\n";
                     }
                     t1.setText(g);
                     b.close();
                 }                 
                 catch(Exception es)
                 {}           	            	  
           	  
             }		     		
			
		}

	}
	class Correr implements ActionListener {	
		public void actionPerformed(ActionEvent arg0) {	
			noDeclarada=" ";
			tokens=t1.getText().split("\n");
			t2.setText(null);
			tabla.clear();
			dup=" ";
			try {
				iniciar();
				t2.setText(t2.getText()+"\n Analizador ha terminado\n");
			} catch (Exception e) {  
				t2.setText(e.getMessage());
			}
						
			t2.setText(t2.getText()+dup+"\n"+noDeclarada);			
					
			
		}		
	}
	
	class Tabla implements ActionListener{	
		public void actionPerformed(ActionEvent arg0) {			
		
			JFrame ventana = new JFrame( "Tabla de simbolos" );
		    
		    ventana.addWindowListener( new WindowAdapter() {
		      
		    } );
		    ventana.getContentPane().add( new java1417(),BorderLayout.CENTER );
		    ventana.setSize( 800,250 );
		    ventana.setLocation(230, 400);
		    ventana.setVisible( true );
		}
		
	}
	static void iniciar(){
		try
		{		
			String g=t1.getText();
			InputStream stream = new ByteArrayInputStream( g.getBytes(StandardCharsets.UTF_8));
			analizador.ReInit(stream);     		
			analizador.Programa();			
		}
		catch(ParseException e)
		{
			t2.setText(e.getMessage());			
		}
	}
}

