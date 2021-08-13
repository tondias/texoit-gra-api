package com.texoit.wellington.gra.domain.reporsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.texoit.wellington.gra.domain.dto.ProducerAwardsDTO;
import com.texoit.wellington.gra.domain.model.MovieProducer;
import com.texoit.wellington.gra.domain.model.MovieProducerId;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducer, MovieProducerId> {

	@Query(name = "findMinInterval", nativeQuery = true)
	Integer findMinInterval();

	@Query(name = "findMaxInterval", nativeQuery = true)
	Integer findMaxInterval();

	@Query(name = "findProducersMinAwards", nativeQuery = true)
	List<ProducerAwardsDTO> findProducersMinAwards(@Param("minInterval") Integer minInterval);

	@Query(name = "findProducersMaxAwards", nativeQuery = true)
	List<ProducerAwardsDTO> findProducersMaxAwards(@Param("maxInterval") Integer maxInterval);
}
