package springbootinterview.alexandremoraes.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import springbootinterview.alexandremoraes.contracts.ClienteContract;
import springbootinterview.alexandremoraes.entities.CidadeEntity;
import springbootinterview.alexandremoraes.entities.ClienteEntity;
import springbootinterview.alexandremoraes.exceptions.CidadeNotFoundException;
import springbootinterview.alexandremoraes.exceptions.ClienteNaoEncontradoException;
import springbootinterview.alexandremoraes.jpa.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {

	private static final String NOME_COMPLETO = "NOME_COMPLETO";

	@Autowired
	private ClienteService clienteService;

	@MockBean
	private ClienteRepository clienteRepository;
	
	@MockBean
	private CidadeService cidadeService;
	
	@Test
	public void salvarTeste() {
		final CidadeEntity cidadeOndeMora = CidadeEntity.builder().id(BigInteger.ONE).build();
		Mockito.when(cidadeService.consultar(BigInteger.ONE)).thenReturn(cidadeOndeMora);
		
		final ClienteEntity clienteEntity = ClienteEntity.builder().cidadeOndeMora(cidadeOndeMora).build();
		Mockito.when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
		
		final ClienteContract clienteContract = ClienteContract.builder().cidadeOndeMoraId(BigInteger.ONE).build();
		final ClienteContract result =  clienteService.salvar(clienteContract);
		
		assertEquals(result, clienteContract);
	}
	
	@Test(expected=CidadeNotFoundException.class)
	public void salvarCidadeNotFound() {		
		Mockito.when(cidadeService.consultar(BigInteger.ONE)).thenThrow(CidadeNotFoundException.class);
		final ClienteContract clienteContract = ClienteContract.builder().cidadeOndeMoraId(BigInteger.ONE).build();
		clienteService.salvar(clienteContract);
	}
	
	@Test
	public void consultar() {
		final Pageable pageable = PageRequest.of(0, 10);
		final ClienteEntity clienteEntity = ClienteEntity.builder().build();
		Mockito.when(clienteRepository.findByNomeCompletoContainingIgnoreCase(NOME_COMPLETO, pageable)).thenReturn(new PageImpl<ClienteEntity>(Arrays.asList(clienteEntity)));
		
		final List<ClienteContract> result = clienteService.consultarPorNomeCompleto(NOME_COMPLETO, pageable);
		assertThat(result.size(), Is.is(1));
	}
	
	@Test
	public void consultarEmptyResult() {
		final Pageable pageable = PageRequest.of(0, 10);
		Mockito.when(clienteRepository.findByNomeCompletoContainingIgnoreCase(NOME_COMPLETO, pageable)).thenReturn(new PageImpl<ClienteEntity>(Arrays.asList()));
		
		final List<ClienteContract> result = clienteService.consultarPorNomeCompleto(NOME_COMPLETO, pageable);
		assertThat(result.size(), Is.is(0));
	}
	
	@Test
	public void consultarPorId() {
		final ClienteEntity clienteEntity = ClienteEntity.builder().build();
		Mockito.when(clienteRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(clienteEntity));
		
		
		final ClienteContract result = clienteService.consultar(BigInteger.ONE);
		assertEquals(result, ClienteContract.builder().build());
	}
	
	@Test(expected=ClienteNaoEncontradoException.class)
	public void consultarPorIdNotFound() {
		Mockito.when(clienteRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());
		clienteService.consultar(BigInteger.ONE);
	}
}
