package springbootinterview.alexandremoraes.entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springbootinterview.alexandremoraes.contract.SexoEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClienteEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(length=255)
	private String nomeCompleto;
	
	@Column(length=1)
	private SexoEnum sexo;
	
	@Column
	private Date dataNascimento;
	
	@Column
	private Integer idade;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	private CidadeEntity cidadeOndeMora;
}
