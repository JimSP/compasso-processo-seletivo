package springbootinterview.alexandremoraes.entities;

import static org.junit.Assert.assertNotEquals;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import com.google.common.testing.EqualsTester;

import springbootinterview.alexandremoraes.contracts.EstadoEnum;
import springbootinterview.alexandremoraes.contracts.SexoEnum;

public class EntitiesTeste {
	
	@Test
	public void testeComMesmaReferencia() {
		final CidadeEntity cidadeEntity = CidadeEntity.builder().build();
		final ClienteEntity clienteEntity = ClienteEntity.builder().build();

		new EqualsTester().addEqualityGroup(cidadeEntity, cidadeEntity)
				.addEqualityGroup(clienteEntity, clienteEntity).testEquals();
	}

	@Test
	public void testeComValorVazio() {
		new EqualsTester().addEqualityGroup(CidadeEntity.builder().build(), CidadeEntity.builder().build())
				.addEqualityGroup(ClienteEntity.builder().build(), ClienteEntity.builder().build()).testEquals();
	}

	@Test
	public void testeComMesmoValor() {
		
		final CidadeEntity cidadeEntity = CidadeEntity
				.builder()
				.id(BigInteger.ONE)
				.nome("NOME CIDADE")
				.estado(EstadoEnum.AC)
				.build();
		
		final ClienteEntity clienteEntity = ClienteEntity
				.builder()
				.cidadeOndeMora(cidadeEntity)
				.dataNascimento(Date.valueOf(LocalDate.MAX))
				.id(BigInteger.ZERO)
				.idade(55)
				.nomeCompleto("NOME COMPLENTO")
				.sexo(SexoEnum.F)
				.build();
		
		new EqualsTester()
				.addEqualityGroup(
						cidadeEntity,
						cidadeEntity.toBuilder().build())
				.addEqualityGroup(
						clienteEntity,
						clienteEntity.toBuilder().build())
				.testEquals();
	}
	
	@Test
	public void testeComMesmoValorUsandoSetters() {
		
		final CidadeEntity CidadeEntity = new CidadeEntity();
		CidadeEntity.setEstado(EstadoEnum.AC);
		CidadeEntity.setId(BigInteger.ZERO);
		CidadeEntity.setNome("NOME CIDADE");
		
		final ClienteEntity clienteEntity = new ClienteEntity(); 
		clienteEntity.setCidadeOndeMora(CidadeEntity);
		clienteEntity.setDataNascimento(Date.valueOf(LocalDate.MAX));
		clienteEntity.setIdade(55);
		clienteEntity.setNomeCompleto("NOME COMPLETO");
		clienteEntity.setSexo(SexoEnum.F);
		clienteEntity.setId(BigInteger.TEN);

		
		new EqualsTester()
				.addEqualityGroup(
						CidadeEntity,
						CidadeEntity.toBuilder().build())
				.addEqualityGroup(
						clienteEntity,
						clienteEntity.toBuilder().build())
				.testEquals();
	}
	
	@Test
	public void testeComIdDiferentes() {
		final CidadeEntity cidadeEntity = CidadeEntity.builder().build();
		final CidadeEntity outraCidadeEntity = CidadeEntity
				.builder()
				.id(BigInteger.ONE)
				.build();
		
		final ClienteEntity clienteEntity = ClienteEntity.builder().build();
		final ClienteEntity outroClienteEntity = ClienteEntity
				.builder()
				.id(BigInteger.ZERO)
				.build();

		assertNotEquals(cidadeEntity, outraCidadeEntity);
		assertNotEquals(clienteEntity, outroClienteEntity);
	}
	
	@Test
	public void testeComNomeDiferentes() {
		final CidadeEntity cidadeEntity = CidadeEntity.builder().build();
		final CidadeEntity outraCidadeEntity = CidadeEntity
				.builder()
				.nome("NOME CIDADE")
				.build();
		
		final ClienteEntity clienteEntity = ClienteEntity.builder().build();
		final ClienteEntity outroClienteEntity = ClienteEntity
				.builder()
				.nomeCompleto("NOME COMPLENTO")
				.build();

		assertNotEquals(cidadeEntity, outraCidadeEntity);
		assertNotEquals(clienteEntity, outroClienteEntity);
	}
	
	@Test
	public void testeComEstadoDiferenteDiferentes() {
		final CidadeEntity cidadeEntity = CidadeEntity.builder().build();
		final CidadeEntity outraCidadeEntity = CidadeEntity
				.builder()
				.estado(EstadoEnum.AC)
				.build();

		assertNotEquals(cidadeEntity, outraCidadeEntity);
	}
	
	@Test
	public void testeComCidadeOndeMoraDiferentesa() {
		
		final ClienteEntity clienteEntity = ClienteEntity.builder().build();
		final ClienteEntity outroClienteEntity = ClienteEntity
				.builder()
				.cidadeOndeMora(CidadeEntity.builder().build())
				.build();

		assertNotEquals(clienteEntity, outroClienteEntity);
	}
	
	@Test
	public void testeDataNascimentoDiferentesa() {
		
		final ClienteEntity clienteEntity = ClienteEntity.builder().build();
		final ClienteEntity outroClienteEntity = ClienteEntity
				.builder()
				.dataNascimento(Date.valueOf(LocalDate.MAX))
				.build();

		assertNotEquals(clienteEntity, outroClienteEntity);
	}
	
	@Test
	public void testeIdadeDiferentesa() {
		
		final ClienteEntity clienteEntity = ClienteEntity.builder().build();
		final ClienteEntity outroClienteEntity = ClienteEntity
				.builder()
				.idade(66)
				.build();

		assertNotEquals(clienteEntity, outroClienteEntity);
	}
	
	@Test
	public void testeSexoDiferentesa() {
		
		final ClienteEntity clienteEntity = ClienteEntity.builder().build();
		final ClienteEntity outroClienteEntity = ClienteEntity
				.builder()
				.sexo(SexoEnum.F)
				.build();

		assertNotEquals(clienteEntity, outroClienteEntity);
	}

}
