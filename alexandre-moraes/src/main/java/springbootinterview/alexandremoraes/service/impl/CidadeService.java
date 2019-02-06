package springbootinterview.alexandremoraes.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import springbootinterview.alexandremoraes.contract.CidadeContract;
import springbootinterview.alexandremoraes.entities.CidadeEntity;
import springbootinterview.alexandremoraes.exceptions.CidadeNotFoundException;
import springbootinterview.alexandremoraes.jpa.CidadeRepository;
import springbootinterview.alexandremoraes.service.CidadeServiceInterface;

@Service
public class CidadeService implements CidadeServiceInterface {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CidadeRepository cidadeRepository;

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.service.CidadeServiceInterface#salvar(springbootinterview.alexandremoraes.contract.CidadeContract)
	 */
	@Override
	public CidadeContract salvar(final CidadeContract cidadeContract) {
		final CidadeEntity cidadeEntity = modelMapper.map(cidadeContract, CidadeEntity.class);
		final CidadeEntity cidadeEntitySaved = cidadeRepository.save(cidadeEntity);
		return modelMapper.map(cidadeEntitySaved, CidadeContract.class);
	}

	/* (non-Javadoc)
	 * @see springbootinterview.alexandremoraes.service.CidadeServiceInterface#consultar(springbootinterview.alexandremoraes.contract.CidadeContract, org.springframework.data.domain.Pageable)
	 */
	@Override
	public List<CidadeContract> consultar(final CidadeContract cidadeContract, final Pageable pageable) {
		return cidadeRepository.findAll(example(cidadeContract), pageable)
				.get()
				.map(cidadeEntity->modelMapper.map(cidadeEntity, CidadeContract.class))
				.collect(Collectors.toList());
	}

	protected CidadeEntity consultar(final BigInteger id) {
		return cidadeRepository.findById(id).orElseThrow(()->new CidadeNotFoundException(id));
	}
	
	private Example<CidadeEntity> example(final CidadeContract cidadeContract) {
		final CidadeEntity probe = CidadeEntity
				.builder()
				.nome(cidadeContract.getNome())
				.estado(cidadeContract.getEstado())
				.build();
		
		return Example.of(probe, ExampleMatcher.matchingAny());
	}
}
