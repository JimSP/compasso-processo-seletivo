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

import springbootinterview.alexandremoraes.contracts.CidadeContract;
import springbootinterview.alexandremoraes.entities.CidadeEntity;
import springbootinterview.alexandremoraes.exceptions.CidadeNotFoundException;
import springbootinterview.alexandremoraes.jpa.CidadeRepository;
import springbootinterview.alexandremoraes.services.impl.CidadeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CidadeServiceTest {

	private static final String NOME_CIDADE = "NOME CIDADE";

	@Autowired
	private CidadeService cidadeService;

	@MockBean
	private CidadeRepository cidadeRepository;
	
	@Test
	public void salvarTeste() {
		final CidadeEntity cidadeEntity = CidadeEntity.builder().nome(NOME_CIDADE).build();
		final CidadeContract cidadeContract = CidadeContract.builder().nome(NOME_CIDADE).build();
		Mockito.when(cidadeRepository.save(cidadeEntity)).thenReturn(cidadeEntity);
		
		final CidadeContract result = cidadeService.salvar(cidadeContract);
		assertEquals(result, cidadeContract);
	}

	@Test
	public void consultarTeste() {
		final Pageable pageable = PageRequest.of(0, 10);
		final CidadeEntity cidadeEntity = CidadeEntity.builder().build();
		final CidadeContract cidadeContract = CidadeContract.builder().nome(NOME_CIDADE).build();
		Mockito.when(cidadeRepository.findAll(cidadeService.example(cidadeContract), pageable)).thenReturn(new PageImpl<CidadeEntity>(Arrays.asList(cidadeEntity)));
		
		final List<CidadeContract> result = cidadeService.consultar(cidadeContract, pageable);
		assertThat(result.size(), Is.is(1));
	}
	
	@Test
	public void consultarPorIdTeste() {
		final CidadeEntity cidadeEntity = CidadeEntity.builder().build();
		Mockito.when(cidadeRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(CidadeEntity.builder().build()));
		
		final CidadeEntity result = cidadeService.consultar(BigInteger.ONE);
		assertEquals(result, cidadeEntity);
	}
	
	@Test(expected=CidadeNotFoundException.class)
	public void consultarPorIdTesteEmptyResult() {
		final CidadeEntity cidadeEntity = CidadeEntity.builder().build();
		Mockito.when(cidadeRepository.findById(BigInteger.ONE)).thenReturn(Optional.empty());
		
		final CidadeEntity result = cidadeService.consultar(BigInteger.ONE);
		assertEquals(result, cidadeEntity);
	}
	
	@Test
	public void consultarTesteEmptyResult() {
		final Pageable pageable = PageRequest.of(0, 10);
		final CidadeContract cidadeContract = CidadeContract.builder().nome(NOME_CIDADE).build();
		Mockito.when(cidadeRepository.findAll(cidadeService.example(cidadeContract), pageable)).thenReturn(new PageImpl<CidadeEntity>(Arrays.asList()));
		
		final List<CidadeContract> result = cidadeService.consultar(cidadeContract, pageable);
		assertThat(result.size(), Is.is(0));
	}
}
