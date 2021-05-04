package are.edu.unju.fi.tp4.model;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Cliente {
	private int nroDocumento;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;

	//Calendar
	//private Date fechaUltimCompra = new Date();
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltimaCompra;
	private String tipoDocumento;
	private int codigoAreaTelefono;
	private int numTelefono;
	private String email;
	private String nombreApellido;
	private String password;
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public int getEdad() {		
		int edad = 0;
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, hoy);
		edad = periodo.getYears();		
		return edad;
	}
	public String getTiempoDesdeUltimaCompra() {
		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(fechaUltimaCompra, fechaActual);
		return " T Desde Ultima Compra  Año: " + periodo.getYears() + " Mes: " + periodo.getMonths() + " Dia: " + periodo.getDays() ;		
	}
	public String getTiempoDesdeCumple() {
		LocalDate fechaActual=LocalDate.now();
		int years;
		if(fechaActual.getMonthValue()<=fechaNacimiento.getMonthValue()){
			
			Period periodo=Period.between(fechaNacimiento,fechaActual);
			years=periodo.getYears();
			LocalDate nuevo=fechaNacimiento.plusYears(years+1);
			Period periodo2=Period.between(fechaActual, nuevo);
			return "Año hasta tu cumpleaños: "+periodo2.getYears()+ " Mes: "+periodo2.getMonths()+" Dias: "+ periodo2.getDays();
		}
		else {
			Period periodo=Period.between(fechaNacimiento,fechaActual);
			years=periodo.getYears();
			LocalDate nuevo=fechaNacimiento.plusYears(years+1);
			Period periodo2=Period.between(fechaActual, nuevo);
			
			return "Año hasta tu cumpleaños: "+periodo2.getYears()+ " Mes: "+periodo2.getMonths()+" Dias: "+ periodo2.getDays();
		}
	}
	
}
