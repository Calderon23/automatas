package Comp;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sun.java.swing.*;

class java1417 extends JPanel {
  private JTable tabla;
  private JScrollPane panelScroll;
  private String titColumna[];
  private String datoColumna[][];
  
  public java1417() {
    setLayout( new BorderLayout() );
    // Creamos las columnas y las cargamos con los datos que van a
    // aparecer en la pantalla
    CreaColumnas();
    CargaDatos();
    // Creamos una instancia del componente Swing
    tabla = new JTable( datoColumna,titColumna );
    // Aquí se configuran algunos de los parámetros que permite 
    // variar la JTable
    tabla.setShowHorizontalLines( false );
    tabla.setRowSelectionAllowed( true );
    tabla.setColumnSelectionAllowed( true );
    Font fuente2=new Font("Dialog", Font.ITALIC, 16);
    tabla.setFont(fuente2);
    // Cambiamos el color de la zona seleccionada (rojo/blanco)
    tabla.setSelectionForeground( Color.white );
    tabla.setSelectionBackground( Color.red );
    // Incorporamos la tabla a un panel que incorpora ya una barra
    // de desplazamiento, para que la visibilidad de la tabla sea
    // automática
    panelScroll = new JScrollPane( tabla );
    add( panelScroll, BorderLayout.CENTER );
  }
  
  
  // Creamos las etiquetas que sirven de título a cada una de
  // las columnas de la tabla
  public void CreaColumnas() {
    titColumna = new String[5];
    
    
     titColumna[0] = "Name ";
     titColumna[1] = "Type ";
     titColumna[2] = "Attribute ";
     titColumna[3] = "Value ";
     titColumna[4] = "Position ";
    
  }
  
  // Creamos los datos para cada uno de los elementos de la tabla
  public void CargaDatos() {
    datoColumna = new String[100][8];
       
    
    
    Enumeration<Simbolo> enumeration = Ventana.tabla.elements();
	Simbolo s= new Simbolo();
	int i=0,j=0;
	while (enumeration.hasMoreElements()) {
		s=enumeration.nextElement();
		datoColumna[i][j] = s.getNombre();
		j++;		
		datoColumna[i][j] = s.getTipo();
		j++;
		datoColumna[i][j] = s.getAtributo();
		j++;
		datoColumna[i][j] = s.getValor();
		j++;
		datoColumna[i][j] = s.getPosicion();		
		i++;
		j=0;
	}
	
    
  }
  
  
 
}