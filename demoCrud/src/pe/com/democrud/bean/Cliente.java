package pe.com.democrud.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Cliente implements Serializable{

	private static final long serialVersionUID= 1L;

	private Long codCliente;
	private String nombres;
	private String apellidos;
	private String direccion;
	
}
