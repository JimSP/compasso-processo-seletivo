package springbootinterview.alexandremoraes.entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springbootinterview.alexandremoraes.contract.EstadoEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CidadeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;

	@Column(length = 255)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private EstadoEnum estado;
}
