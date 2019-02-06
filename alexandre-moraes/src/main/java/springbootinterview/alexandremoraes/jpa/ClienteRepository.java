package springbootinterview.alexandremoraes.jpa;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springbootinterview.alexandremoraes.entities.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, BigInteger>{

	List<ClienteEntity> findByNomeCompletoContainingIgnoreCase(@Param("nomeCompleto") final String nomeCompleto, final Pageable pageable);

}
