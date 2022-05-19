package michelin.model;

import java.util.Arrays;

public class Restaurante {
	private String nombre;
	private String Region;
	private String Ciudad;
	private int distincion;
	private String direccion;
	private double precio_min;
	private double precio_max;
	private String cocina;
	private String telefono;
	private String web;
	
	
	public Restaurante(String nombre, String region, String ciudad, int distincion, String direccion, double precio_min,
			double precio_max, String cocina, String telefono, String web) {
		this.nombre = nombre;
		Region = region;
		Ciudad = ciudad;
		this.distincion = distincion;
		this.direccion = direccion;
		this.precio_min = precio_min;
		this.precio_max = precio_max;
		this.cocina = cocina;
		this.telefono = telefono;
		this.web = web;
	}


	@Override
	public String toString() {
		return "Restaurante [nombre=" + nombre + ", Region=" + Region + ", Ciudad=" + Ciudad + ", distincion="
				+ distincion + ", direccion=" + direccion + ", precio_min=" + precio_min + ", precio_max=" + precio_max
				+ ", cocina=" + cocina + ", telefono=" + telefono + ", web=" + web + "]";
	}


	public String getNombre() {
		return nombre;
	}


	public String getRegion() {
		return Region;
	}


	public String getCiudad() {
		return Ciudad;
	}


	public int getDistincion() {
		return distincion;
	}


	public String getDireccion() {
		return direccion;
	}


	public double getPrecio_min() {
		return precio_min;
	}


	public double getPrecio_max() {
		return precio_max;
	}


	public String getCocina() {
		return cocina;
	}


	public String getTelefono() {
		return telefono;
	}


	public String getWeb() {
		return web;
	}



	

	

}
