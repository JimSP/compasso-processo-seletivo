package springbootinterview.alexandremoraes.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;

import springbootinterview.alexandremoraes.contract.ClienteContract;

/***
 * Interface de serviço de Clientes.
 * 
 * @author alexandre
 *
 */
public interface ClienteServiceInterface {

	/***
	 * efetua a persistencia do tipo ClienteContract
	 * 
	 * @param clienteContract
	 * @return ClienteContract
	 */
	ClienteContract salvar(ClienteContract clienteContract);

	/***
	 * efetua uma consulta paginada utilizando o tipo ClienteContract como exemplo de
	 * busca, objetos com os critérios concidentes devem ser retornados.
	 * 
	 * @param clienteContract
	 * @param pageable
	 * @return List<ClienteContract>
	 */
	List<ClienteContract> consultar(ClienteContract clienteContract, Pageable pageable);

	/***
	 * efetua uma consulta de ClienteContract pelo seu id (identificador único).
	 * 
	 * @param id
	 * @return ClienteContract
	 */
	ClienteContract consultar(BigInteger id);

	/***
	 * delete um ClienteContract pelo seu id (identificador único).
	 * 
	 * @param id
	 */
	void deletar(BigInteger id);

}