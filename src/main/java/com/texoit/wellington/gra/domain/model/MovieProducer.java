package com.texoit.wellington.gra.domain.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQuery(
		name = "findMovieProducerWinner",
		query= 
			   " select mp from MovieProducer mp "
			 + "   join mp.movie m 				 "
			 + "   join mp.producer p			 "
			 + "  where m.winner = true			 "
			 + "  order by p.id, m.year			 ")

public class MovieProducer {

	@EmbeddedId
	private MovieProducerId id;

	@ManyToOne
	@MapsId("movieId")
	private Movie movie;

	@ManyToOne
	@MapsId("producerId")
	private Producer producer;

	public MovieProducer(Movie movie, Producer producer) {
		this.movie = movie;
		this.producer = producer;
		this.id = new MovieProducerId(movie.getId(), producer.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		MovieProducer other = (MovieProducer) obj;
		return Objects.equals(movie, other.getMovie()) && Objects.equals(producer, other.getProducer());
	}

	@Override
	public int hashCode() {
		return Objects.hash(movie, producer);
	}
}
