package automatas2;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class Compilador extends JFrame {
	//Declaración de variables
	File abre;
	ArbolRecur arbol;
	private static final long serialVersionUID = 1L;
	JLabel label=new JLabel("Cargar archivo");
	JTextArea t=new JTextArea();
	JScrollPane scroll;
	JButton b2=new JButton ("Compilar");
	JButton b1=new JButton ("Cargar archivo");
	JButton b3=new JButton ("Salir");
	Oy1 o=new Oy1();
	public static Compilador c;

	public Compilador() {
		setResizable(false);
		setTitle("Compilador");
		setVisible(true);
		setSize(600,500);
		JPanel j=new JPanel(); 
		setLayout(null);
		j.setSize(600,500);
		j.setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//finaliza el programa cuando se da click en la X
		setBounds(350,100 , 600,500);
		label.setBounds(new Rectangle(220,50,400,21));  
		label.setFont(new java.awt.Font("Arial", 0, 17));
		label.setForeground(Color.blue);
		t.setBounds(50, 100, 500, 200);
		t.setBackground(Color.WHITE);
		t.repaint();
		b1.setBounds(50, 350,150,20);
		b1.setBackground(Color.blue);
		b1.setMnemonic('l');
		b1.setForeground(Color.white);
		b1.addActionListener(o);
		b2.setBounds(270, 350,100,20);
		b2.setBackground(Color.GREEN);
		b2.setMnemonic('c');
		b2.setForeground(Color.white);
		b2.addActionListener(o);
		b3.setBounds(450, 350,100,20);
		b3.setBackground(Color.red);
		b3.setMnemonic('e');
		b3.setForeground(Color.white);
		b3.addActionListener(o);
		scroll = new JScrollPane(t);
		scroll.setBounds(50, 100, 500, 200);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//t.setLineWrap(true);
		this.add(b1);
		this.add(b2);
		this.add(b3);		//sp.add(t);
		this.add(scroll);
		this.add(label);
		t.repaint();
		repaint();
	}
	final JFileChooser fc = new JFileChooser();
	public class Oy1 implements ActionListener	{
		public void actionPerformed( ActionEvent e ){
			//Si "GUARDAR" es el command que le pasamos es porque se habrá pulsado el botón nuevo_bt 
			if ("Compilar".equals(e.getActionCommand())){//				label.setBounds(new Rectangle(200,50,400,21));
				//System.out.println("compilado\n||-----\n"+t.getText()+"-----||");				//-------------------------Modificar
				if(t.getText().equals("")){
					c.setTitle("Caja de texto vacía");
					JOptionPane.showMessageDialog(c, "No hay cadenas en este archivo!!!","Está vacío",JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						//Parser p=new Parser(t.getText());
						Pars p=new Pars(t.getText());
						if (p.daval()==true) {
							JOptionPane.showMessageDialog(c, "\tCompilación exitosa \n"
									+ "\tLos resultados estan en el siguiente archivo\nC:/Users/BadMf/Desktop/Resultados.java","Done",JOptionPane.INFORMATION_MESSAGE);
							//label.setText(c.getTitle());
			                @SuppressWarnings("recursos")
							PrintStream DDescritor = new PrintStream("C:/Users/BadMf/Desktop/Resultados.java");
			                DDescritor.println(t.getText());
			                c.setTitle("Archivo compilado "+abre.getName());
						}else {
							if(p.mensaje.equals("Se esperaba un boleano")){
								JOptionPane.showMessageDialog(c, "Error de token: "+p.error+"\nTipo: " +p.getMensaje(),"Falla",JOptionPane.ERROR_MESSAGE);
							}else if(p.mensaje.equals("No es el mismo tipo de variable")){
								JOptionPane.showMessageDialog(c, "Error de tokens: "+p.auxiliar+", " +p.auxiliar2+"\nTipo: " +p.getMensaje(),"Failure",JOptionPane.ERROR_MESSAGE);
								}else{
							JOptionPane.showMessageDialog(c, "Error de tokens: "+p.getTok()+"\nTipo: " +p.getMensaje(),"Falla",JOptionPane.ERROR_MESSAGE);
							}
							c.setTitle("No se puede abrir este archivo "+abre.getName());
						}
					} catch (Exception e2) {			 
					}			
				}
			} 
			if ("Cargar ARCHIVO".equals(e.getActionCommand())){
				abrirArchivo();
				//System.out.println("Load file");
				
			} 
			if ("Salir".equals(e.getActionCommand())){
				//System.out.println("Exit");
				System.exit(0);
			} 
		}
	 }
	 private String abrirArchivo() {
		
		 String aux="";   
		 String texto="";
		 try {
			 /**llamamos el metodo que permite cargar la ventana*/
			 JFileChooser file=new JFileChooser();
			 file.showOpenDialog(this);
			 /**abrimos el archivo seleccionado*/
			 abre=file.getSelectedFile();
			 c.setTitle("Intentando compilar "+abre.getName());
			 /**recorremos el archivo, lo leemos para plasmarlo
			 *en el area de texto*/
			 
			 if(abre!=null){  
				 FileReader archivos=new FileReader(abre);
				 BufferedReader lee=new BufferedReader(archivos);
				 while((aux=lee.readLine())!=null){
					 texto+= aux+ "\n";
			         t.setText(texto);
				 }
			     lee.close();
			 }    
		 } catch(Exception ex){
		     JOptionPane.showMessageDialog(c,"No hay nada",
		     "Warning!!!",JOptionPane.WARNING_MESSAGE);
		 }
		 return texto;//El texto se almacena en el JTextArea
	}
	public static void main(String[] args) {
		try {
			c=new Compilador();
		} catch (Exception e) {
			  JOptionPane.showMessageDialog(null,e+"" +
					     "\nError",
					     "Revisa el código!!!",JOptionPane.WARNING_MESSAGE);
			
		}
		
	}

}
