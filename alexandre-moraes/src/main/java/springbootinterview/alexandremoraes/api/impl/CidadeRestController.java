package springbootinterview.alexandremoraes.api.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springbootinterview.alexandremoraes.api.CidadeApi;
import springbootinterview.alexandremoraes.contract.CidadeContract;
import springbootinterview.alexandremoraes.contract.EstadoEnum;
import springbootinterview.alexandremoraes.service.CidadeServiceInterface;

@RestController
public class CidadeRestController implements CidadeApi {

	@Autowired
	private CidadeServiceInterface cidadeServiceInterface;

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.api.CidadeApi#cadastrar(springbootinterview.alexandremoraes.contract.CidadeContract)
	 */
	@Override
	@PostMapping(path = "/cidade", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody CidadeContract cadastrar(@Valid @RequestBody final CidadeContract cidadeContract) {
		return cidadeServiceInterface.salvar(cidadeContract);
	}

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.api.CidadeApi#consultar(java.lang.String, java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	@GetMapping(path = "/cidade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<CidadeContract> consultar(
			@RequestParam(name = "nome", required = false) final String nome,
			@RequestParam(name = "estado", required = false) final String estado,
			@PageableDefault(page = 0, size = 100, sort = { "estado",
					"nome" }, direction = Direction.ASC) final Pageable pageable) {

		final CidadeContract cidadeContract = CidadeContract
				.builder()
				.nome(nome)
				.estado(estado == null ? null : EstadoEnum.valueOf(estado))
				.build();

		return cidadeServiceInterface.consultar(cidadeContract, pageable);
	}
}
