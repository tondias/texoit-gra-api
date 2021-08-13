package com.texoit.wellington.gra.domain.reporsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.texoit.wellington.gra.domain.model.MovieProducer;
import com.texoit.wellington.gra.domain.model.MovieProducerId;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducer, MovieProducerId> {

	@Query(name = "findMovieProducerWinner")
	List<MovieProducer> findMovieProducerWinner();

}
