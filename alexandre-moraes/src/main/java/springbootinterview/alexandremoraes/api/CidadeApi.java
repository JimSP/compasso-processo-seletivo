package springbootinterview.alexandremoraes.api;

import java.util.List;

import org.springframework.data.domain.Pageable;

import springbootinterview.alexandremoraes.contract.CidadeContract;

/***
 * API de acesso do recurso CidadeContract.
 * 
 * @author alexandre
 *
 */
public interface CidadeApi {

	/***
	 * cria um recurso de CidadeContract.
	 * 
	 * @param cidadeContract
	 * @return CidadeContract
	 */
	CidadeContract cadastrar(CidadeContract cidadeContract);

	/***
	 * efetua uma consulta paginada nos recursos de CidadeContract por nome e/ou estado.
	 * 
	 * @param nome
	 * @param estado
	 * @param pageable
	 * @return List<CidadeContract>
	 */
	List<CidadeContract> consultar(String nome, String estado, Pageable pageable);

}