package springbootinterview.alexandremoraes.services.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springbootinterview.alexandremoraes.contracts.ClienteContract;
import springbootinterview.alexandremoraes.entities.CidadeEntity;
import springbootinterview.alexandremoraes.entities.ClienteEntity;
import springbootinterview.alexandremoraes.exceptions.ClienteNaoEncontradoException;
import springbootinterview.alexandremoraes.jpa.ClienteRepository;
import springbootinterview.alexandremoraes.services.ClienteServiceInterface;

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
	public List<ClienteContract> consultarPorNomeCompleto(final String nomeCompleto, final Pageable pageable) {
		return clienteRepository.findByNomeCompletoContainingIgnoreCase(nomeCompleto, pageable)
				.get()
				.map(clienteEntity -> modelMapper.map(clienteEntity, ClienteContract.class))
				.collect(Collectors.toList());
	}
	
	public List<ClienteContract> consultar(final ClienteContract clienteContract, final Pageable pageable){
		return clienteRepository.findAll(example(clienteContract), pageable)
				.get()
				.map(clienteEntity -> modelMapper.map(clienteEntity, ClienteContract.class))
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
	
	public Example<ClienteEntity> example(final ClienteContract clienteContract) {
		final ClienteEntity probe = ClienteEntity
				.builder()
				.nomeCompleto(clienteContract.getNomeCompleto())
				.cidadeOndeMora(clienteContract.getCidadeOndeMoraId() == null ? null : CidadeEntity.builder().id(clienteContract.getCidadeOndeMoraId()).build())
				.dataNascimento(clienteContract.getDataNascimento())
				.idade(clienteContract.getIdade())
				.sexo(clienteContract.getSexo())
				.build();
		
		return Example.of(probe, ExampleMatcher.matchingAny());
	}
}
