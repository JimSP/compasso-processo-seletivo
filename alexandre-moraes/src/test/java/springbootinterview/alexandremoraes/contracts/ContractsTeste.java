package springbootinterview.alexandremoraes.contracts;

import static org.junit.Assert.assertNotEquals;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import com.google.common.testing.EqualsTester;

import springbootinterview.alexandremoraes.contracts.CidadeContract;
import springbootinterview.alexandremoraes.contracts.ClienteContract;
import springbootinterview.alexandremoraes.contracts.EstadoEnum;
import springbootinterview.alexandremoraes.contracts.SexoEnum;

public class ContractsTeste {

	@Test
	public void testeComMesmaReferencia() {
		final CidadeContract cidadeContract = CidadeContract.builder().build();
		final ClienteContract clienteContract = ClienteContract.builder().build();

		new EqualsTester().addEqualityGroup(cidadeContract, cidadeContract)
				.addEqualityGroup(clienteContract, clienteContract).testEquals();
	}

	@Test
	public void testeComValorVazio() {
		new EqualsTester().addEqualityGroup(CidadeContract.builder().build(), CidadeContract.builder().build())
				.addEqualityGroup(ClienteContract.builder().build(), ClienteContract.builder().build()).testEquals();
	}

	@Test
	public void testeComMesmoValor() {
		
		final CidadeContract cidadeContract = CidadeContract
				.builder()
				.id(BigInteger.ONE)
				.nome("NOME CIDADE")
				.estado(EstadoEnum.AC)
				.build();
		
		final ClienteContract clienteContract = ClienteContract
				.builder()
				.cidadeOndeMoraId(BigInteger.TEN)
				.dataNascimento(Date.valueOf(LocalDate.MAX))
				.id(BigInteger.ZERO)
				.idade(55)
				.nomeCompleto("NOME COMPLENTO")
				.sexo(SexoEnum.F)
				.build();
		
		new EqualsTester()
				.addEqualityGroup(
						cidadeContract,
						cidadeContract.toBuilder().build())
				.addEqualityGroup(
						clienteContract,
						clienteContract.toBuilder().build())
				.testEquals();
	}
	
	@Test
	public void testeComMesmoValorUsandoSetters() {
		
		final CidadeContract cidadeContract = new CidadeContract();
		cidadeContract.setEstado(EstadoEnum.AC);
		cidadeContract.setId(BigInteger.ZERO);
		cidadeContract.setNome("NOME CIDADE");
		
		final ClienteContract clienteContract = new ClienteContract(); 
		clienteContract.setCidadeOndeMoraId(BigInteger.ONE);
		clienteContract.setDataNascimento(Date.valueOf(LocalDate.MAX));
		clienteContract.setIdade(55);
		clienteContract.setNomeCompleto("NOME COMPLETO");
		clienteContract.setSexo(SexoEnum.F);
		clienteContract.setId(BigInteger.TEN);

		
		new EqualsTester()
				.addEqualityGroup(
						cidadeContract,
						cidadeContract.toBuilder().build())
				.addEqualityGroup(
						clienteContract,
						clienteContract.toBuilder().build())
				.testEquals();
	}
	
	@Test
	public void testeComIdDiferentes() {
		final CidadeContract cidadeContract = CidadeContract.builder().build();
		final CidadeContract outraCidadeContract = CidadeContract
				.builder()
				.id(BigInteger.ONE)
				.build();
		
		final ClienteContract clienteContract = ClienteContract.builder().build();
		final ClienteContract outroClienteContract = ClienteContract
				.builder()
				.id(BigInteger.ZERO)
				.build();

		assertNotEquals(cidadeContract, outraCidadeContract);
		assertNotEquals(clienteContract, outroClienteContract);
	}
	
	@Test
	public void testeComNomeDiferentes() {
		final CidadeContract cidadeContract = CidadeContract.builder().build();
		final CidadeContract outraCidadeContract = CidadeContract
				.builder()
				.nome("NOME CIDADE")
				.build();
		
		final ClienteContract clienteContract = ClienteContract.builder().build();
		final ClienteContract outroClienteContract = ClienteContract
				.builder()
				.nomeCompleto("NOME COMPLENTO")
				.build();

		assertNotEquals(cidadeContract, outraCidadeContract);
		assertNotEquals(clienteContract, outroClienteContract);
	}
	
	@Test
	public void testeComEstadoDiferenteDiferentes() {
		final CidadeContract cidadeContract = CidadeContract.builder().build();
		final CidadeContract outraCidadeContract = CidadeContract
				.builder()
				.estado(EstadoEnum.AC)
				.build();

		assertNotEquals(cidadeContract, outraCidadeContract);
	}
	
	@Test
	public void testeComCidadeOndeMoraIdDiferentesa() {
		
		final ClienteContract clienteContract = ClienteContract.builder().build();
		final ClienteContract outroClienteContract = ClienteContract
				.builder()
				.cidadeOndeMoraId(BigInteger.ONE)
				.build();

		assertNotEquals(clienteContract, outroClienteContract);
	}
	
	@Test
	public void testeDataNascimentoDiferentesa() {
		
		final ClienteContract clienteContract = ClienteContract.builder().build();
		final ClienteContract outroClienteContract = ClienteContract
				.builder()
				.dataNascimento(Date.valueOf(LocalDate.MAX))
				.build();

		assertNotEquals(clienteContract, outroClienteContract);
	}
	
	@Test
	public void testeIdadeDiferentesa() {
		
		final ClienteContract clienteContract = ClienteContract.builder().build();
		final ClienteContract outroClienteContract = ClienteContract
				.builder()
				.idade(66)
				.build();

		assertNotEquals(clienteContract, outroClienteContract);
	}
	
	@Test
	public void testeSexoDiferentesa() {
		
		final ClienteContract clienteContract = ClienteContract.builder().build();
		final ClienteContract outroClienteContract = ClienteContract
				.builder()
				.sexo(SexoEnum.F)
				.build();

		assertNotEquals(clienteContract, outroClienteContract);
	}
}
