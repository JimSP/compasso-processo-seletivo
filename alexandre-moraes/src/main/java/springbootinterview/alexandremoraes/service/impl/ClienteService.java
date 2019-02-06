package springbootinterview.alexandremoraes.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springbootinterview.alexandremoraes.contract.ClienteContract;
import springbootinterview.alexandremoraes.entities.CidadeEntity;
import springbootinterview.alexandremoraes.entities.ClienteEntity;
import springbootinterview.alexandremoraes.exceptions.ClienteNaoEncontradoException;
import springbootinterview.alexandremoraes.jpa.ClienteRepository;
import springbootinterview.alexandremoraes.service.ClienteServiceInterface;

@Service
public class ClienteService implements ClienteServiceInterface {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeService cidadeService;

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.service.ClienteServiceInterface#salvar(springbootinterview.alexandremoraes.contract.ClienteContract)
	 */
	@Override
	@Transactional
	public ClienteContract salvar(final ClienteContract clienteContract) {
		final CidadeEntity cidadeEntity = cidadeService.consultar(clienteContract.getCidadeOndeMoraId());
		final ClienteEntity clienteEntity = modelMapper.map(clienteContract, ClienteEntity.class);
		clienteEntity.setCidadeOndeMora(cidadeEntity);
		
		final ClienteEntity clienEntitySaved = clienteRepository.save(clienteEntity);
		return modelMapper.map(clienEntitySaved, ClienteContract.class);
	}

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.service.ClienteServiceInterface#consultar(springbootinterview.alexandremoraes.contract.ClienteContract, org.springframework.data.domain.Pageable)
	 */
	@Override
	public List<ClienteContract> consultar(final ClienteContract clienteContract, final Pageable pageable) {
		return clienteRepository.findByNomeCompletoContainingIgnoreCase(clienteContract.getNomeCompleto(), pageable)
				.stream()
				.map(cidadeEntity -> modelMapper.map(cidadeEntity, ClienteContract.class))
				.collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.service.ClienteServiceInterface#consultar(java.math.BigInteger)
	 */
	@Override
	public ClienteContract consultar(final BigInteger id) {
		final Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);
		
		return modelMapper.map(clienteEntityOptional.orElseThrow(()->new ClienteNaoEncontradoException(id)), ClienteContract.class);
	}

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.service.ClienteServiceInterface#deletar(java.math.BigInteger)
	 */
	@Override
	@Transactional
	public void deletar(final BigInteger id) {
		clienteRepository.deleteById(id);
	}
}
