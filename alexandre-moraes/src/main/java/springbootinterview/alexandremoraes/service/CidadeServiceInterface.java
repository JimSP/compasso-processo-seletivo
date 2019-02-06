package springbootinterview.alexandremoraes.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import springbootinterview.alexandremoraes.contract.CidadeContract;

/***
 * Interface de serviço de Cidades.
 * 
 * @author alexandre
 *
 */
public interface CidadeServiceInterface {

	/***
	 * efetua a persistencia do tipo CidadeContract.
	 * 
	 * @param cidadeContract
	 * @return List<CidadeContract>
	 */
	CidadeContract salvar(CidadeContract cidadeContract);

	/***
	 * efetua uma consulta paginada utilizando o tipo CidadeContract como exemplo de
	 * busca, objetos com os critérios concidentes devem ser retornados.
	 * 
	 * @param cidadeContract
	 * @param pageable
	 * @return List<CidadeContract>
	 */
	List<CidadeContract> consultar(CidadeContract cidadeContract, Pageable pageable);

}