package springbootinterview.alexandremoraes.contracts;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder(toBuilder=true)
public class CidadeContract {
	
	@JsonProperty
	private BigInteger id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private EstadoEnum estado;

	@JsonCreator
	public CidadeContract(
			final BigInteger id,
			@JsonProperty final String nome,
			@JsonProperty final EstadoEnum estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}
}
