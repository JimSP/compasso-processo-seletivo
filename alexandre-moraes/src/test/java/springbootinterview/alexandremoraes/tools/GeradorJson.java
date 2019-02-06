package springbootinterview.alexandremoraes.tools;

import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import springbootinterview.alexandremoraes.contracts.CidadeContract;
import springbootinterview.alexandremoraes.contracts.ClienteContract;
import springbootinterview.alexandremoraes.contracts.EstadoEnum;
import springbootinterview.alexandremoraes.contracts.SexoEnum;

@Slf4j
public class GeradorJson {
	
	final ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void gerarJsonClienteContract() throws JsonProcessingException, ParseException {
		
		log.info("m=gerarJsonClienteContract, payload={}", objectMapper.writeValueAsString(ClienteContract
				.builder()
				.cidadeOndeMoraId(BigInteger.ONE)
				.nomeCompleto("Zézinho do Teste")
				.dataNascimento(Date.valueOf(LocalDate.of(1999, Month.MAY, 1)))
				.idade(20)
				.sexo(SexoEnum.M)
				.build()));
	}
	
	@Test
	public void gerarJsonCidadeContract() throws JsonProcessingException, ParseException {
		
		log.info("m=gerarJsonCidadeContract, payload={}", objectMapper.writeValueAsString(CidadeContract
				.builder()
				.nome("São Paulo")
				.estado(EstadoEnum.SP)
				.build()));
	}

}
