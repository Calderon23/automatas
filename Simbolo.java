package Comp;

public class Simbolo {
	private String nombre;
	private String tipo;
	private String atributo;
	private String valor;
	private String posicion;
	
	public Simbolo() {
	
	}
	
	public Simbolo(String nombre, String tipo, String atributo, String valor, String posicion) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.atributo = atributo;
		this.valor = valor;
		this.posicion = posicion;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	


}
