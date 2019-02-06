package springbootinterview.alexandremoraes.contracts;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public final class ClienteContract {

	@JsonProperty
	private BigInteger id;
	
	@NotBlank
	private String nomeCompleto;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	@NotNull
	private Date dataNascimento;
	
	@NotNull
	@Min(value=0L)
	@Max(value=199L)
	private Integer idade;
	
	@NotNull
	private BigInteger cidadeOndeMoraId;
	
	@JsonCreator
	public ClienteContract(
			final BigInteger id,
			@JsonProperty final String nomeCompleto,
			@JsonProperty final SexoEnum sexo,
			@JsonProperty final Date dataNascimento,
			@JsonProperty final Integer idade,
			@JsonProperty final BigInteger cidadeOndeMoraId) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.cidadeOndeMoraId = cidadeOndeMoraId;
	}
}
