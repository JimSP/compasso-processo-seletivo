package springbootinterview.alexandremoraes.api.impl;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import springbootinterview.alexandremoraes.api.ClienteApi;
import springbootinterview.alexandremoraes.contract.ClienteContract;
import springbootinterview.alexandremoraes.service.ClienteServiceInterface;

@RestController
public class ClienteRestController implements ClienteApi {

	@Autowired
	private ClienteServiceInterface clienteServiceInterface;
	
	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.api.ClienteApi#cadastrar(springbootinterview.alexandremoraes.contract.ClienteContract)
	 */
	@Override
	@PostMapping(path = "/cliente", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody ClienteContract cadastrar(@Valid @RequestBody final ClienteContract clienteContract) {
		return clienteServiceInterface.salvar(clienteContract);
	}

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.api.ClienteApi#consultar(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	@GetMapping(path = "/cliente", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody List<ClienteContract> consultar(
			@RequestParam(name = "nomeCompleto", required = false) final String nomeCompleto,
			@PageableDefault(page = 0, size = 100, sort = {
					"nomeCompleto" }, direction = Direction.ASC) final Pageable pageable) {

		final ClienteContract clienteContract = ClienteContract.builder().nomeCompleto(nomeCompleto).build();

		return clienteServiceInterface.consultar(clienteContract, pageable);
	}

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.api.ClienteApi#buscarPorId(java.math.BigInteger)
	 */
	@Override
	@GetMapping(path = "{id}/cliente", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody ClienteContract buscarPorId(@PathVariable(name = "id", required = true) final BigInteger id) {
		return clienteServiceInterface.consultar(id);
	}

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.api.ClienteApi#alterar(java.lang.String, java.math.BigInteger)
	 */
	@Override
	@PatchMapping(path = "{id}/cliente/{nomeCompleto}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public @ResponseBody ClienteContract alterar(
			@PathVariable(name = "id", required = true) final BigInteger id,
			@PathVariable(name = "nomeCompleto", required = true) final String nomeCompleto) {
		
		final ClienteContract clienteContract = clienteServiceInterface.consultar(id);
		
		return clienteServiceInterface.salvar(clienteContract
				.toBuilder()
				.nomeCompleto(nomeCompleto)
				.build());
	}
	
	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.api.ClienteApi#alterar(springbootinterview.alexandremoraes.contract.ClienteContract, java.math.BigInteger)
	 */
	@Override
	@PutMapping(path = "{id}/cliente", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public @ResponseBody ClienteContract alterar(
			@PathVariable(name = "id", required = true) final BigInteger id,
			@Valid @RequestBody final ClienteContract clienteContract) {
		return clienteServiceInterface.salvar(clienteContract
				.toBuilder()
				.id(id)
				.build());
	}

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.api.ClienteApi#remover(java.math.BigInteger)
	 */
	@Override
	@DeleteMapping(path = "{id}/cliente")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void remover(@PathVariable(name = "id", required = true) final BigInteger id) {
		clienteServiceInterface.deletar(id);
	}
}
