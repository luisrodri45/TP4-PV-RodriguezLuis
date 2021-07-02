package are.edu.unju.fi.tp4.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Entity
@Table (name="CLIENTES")
@Component
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private Integer idCliente;
	@Column
	@Min(100000)
	@Max(9999999)
	@NotNull(message="Debe ingresar al menos un numero")
	private int nroDocumento;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	//Calendar
	//private Date fechaUltimCompra = new Date();
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltimaCompra;
	@Column
	@NotBlank(message="Debe incluir el tipo de documento")
	private String tipoDocumento;
	@Column
	private int codigoAreaTelefono;
	@Column
	private int numTelefono;
	@Column
	private String email;
	@Column
	@NotBlank(message="Debe incluir su nombre y apellido")
	private String nombreApellido;
	@Column
	@NotBlank(message="Debe ingresar una contraseña")
	private String password;
	private Integer idAux;
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
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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

	public Integer getIdAux() {
		return idAux;
	}

	public void setIdAux(Integer idAux) {
		this.idAux = idAux;
	}
}
