package springbootinterview.alexandremoraes.api;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;

import springbootinterview.alexandremoraes.contract.ClienteContract;

/***
 * API de acesso do recurso ClienteContract.
 * 
 * @author alexandre
 *
 */
public interface ClienteApi {

	ClienteContract cadastrar(ClienteContract clienteContract);

	/***
	 * efetua uma consulta paginada nos recursos de ClienteContract por nome.
	 * 
	 * @param nome
	 * @param pageable
	 * @return List<ClienteContract>
	 */
	List<ClienteContract> consultar(String nome, Pageable pageable);

	/***
	 * acessa um recurso de ClienteContract pelo seu id (identificador único).
	 * 
	 * @param id
	 * @return ClienteContract
	 */
	ClienteContract buscarPorId(BigInteger id);

	/***
	 * acessa um recurso de ClienteContract pelo seu id (identificador único) e
	 * efetua alteracao do nomeCompleto.
	 * 
	 * @param id
	 * @param nomeCompleto
	 * @return ClienteContract
	 */
	ClienteContract alterar(BigInteger id, String nomeCompleto);

	/***
	 * acessa um recurso de ClienteContract pelo seu id (identificador único) e
	 * efetua alteracao nos campos conforme clienteContract solicitado.
	 * 
	 * @param id
	 * @param clienteContract
	 * @return
	 */
	ClienteContract alterar(BigInteger id, ClienteContract clienteContract);

	/***
	 * remove um ClienteContract pelo seu id (identificador único).
	 * 
	 * @param id
	 */
	void remover(BigInteger id);

}