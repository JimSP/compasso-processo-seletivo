package springbootinterview.alexandremoraes.tools;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import springbootinterview.alexandremoraes.contract.ClienteContract;
import springbootinterview.alexandremoraes.contract.SexoEnum;

@Slf4j
public class GeradorJson {
	
	final ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void gerarJson() throws JsonProcessingException, ParseException {
		
		log.info("m=gerarJson, payload={}", objectMapper.writeValueAsString(ClienteContract
				.builder()
				.cidadeOndeMoraId(BigInteger.ONE)
				.nomeCompleto("Zézinho do Teste")
				.dataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1999"))
				.idade(20)
				.sexo(SexoEnum.M)
				.build()));
	}

}
