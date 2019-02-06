package springbootinterview.alexandremoraes.jpa;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springbootinterview.alexandremoraes.entities.CidadeEntity;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, BigInteger>{

}
