package com.texoit.wellington.gra.domain.model;

import java.util.Objects;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import com.texoit.wellington.gra.domain.dto.ProducerAwardsDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedNativeQuery(
	    name = "findProducersMinAwards",
	    query =
	    		"select subquery.producer,											  " + 
			    "       subquery.intervals,											  " + 
			    "       subquery.previouswin,										  " + 
			    "       subquery.followingwin										  " + 
			    "  from (select p.name as producer,									  " + 
			    "               (max(m.year) - min(m.year)) as intervals,			  " + 
			    "               min(m.year) as previouswin,							  " + 
			    "               max(m.year) as followingwin							  " + 
			    "	      from movie_producer mp									  " + 
			    "          join movie m on m.id_movie = mp.movie_id_movie			  " + 
			    "          join producer p on p.id_producer = mp.producer_id_producer " + 
			    "         where m.winner = true										  " + 
			    "         group by p.name										      " + 
			    "		having count(*) > 1) subquery								  " +
			    " where subquery.intervals = :minInterval							  ",
	    resultSetMapping = "producerAwardsDTO"
)

@NamedNativeQuery(
	    name = "findProducersMaxAwards",
	    query =
	    		"select subquery.producer,											  " + 
			    "       subquery.intervals,											  " + 
			    "       subquery.previouswin,										  " + 
			    "       subquery.followingwin										  " + 
			    "  from (select p.name as producer,									  " + 
			    "               (max(m.year) - min(m.year)) as intervals,     		  " + 
			    "               min(m.year) as previouswin,							  " + 
			    "               max(m.year) as followingwin							  " + 
			    "	      from movie_producer mp									  " + 
			    "          join movie m on m.id_movie = mp.movie_id_movie			  " + 
			    "          join producer p on p.id_producer = mp.producer_id_producer " + 
			    "         where m.winner = true										  " + 
			    "         group by p.name										      " + 
			    "		having count(*) > 1) subquery								  " +
			    " where subquery.intervals = :maxInterval							  ",
	    resultSetMapping = "producerAwardsDTO"
)

@NamedNativeQuery(
		name = "findMinInterval",
		query = 
				" select distinct min(years) years                  	 " + 
		   	    "   from (select (max(m.year) - min(m.year)) years       " + 
		   	    "           from movie_producer mp                       " + 
		   	    "           join movie m								 " + 
		   	    "             on m.id_movie = mp.movie_id_movie		     " + 
		   	    "           join producer p							     " + 
		   	    "             on p.id_producer = mp.producer_id_producer " + 
		   	    "          where m.winner = true						 " + 
		   	    "          group by p.id_producer						 " + 
		   	    "         having count(*) > 1) subquery				     "
)

@NamedNativeQuery(
		name = "findMaxInterval",
		query = 
				" select distinct max(years) years                  	 " + 
		   	    "   from (select (max(m.year) - min(m.year)) years       " + 
		   	    "           from movie_producer mp                       " + 
		   	    "           join movie m								 " + 
		   	    "             on m.id_movie = mp.movie_id_movie		     " + 
		   	    "           join producer p							     " + 
		   	    "             on p.id_producer = mp.producer_id_producer " + 
		   	    "          where m.winner = true						 " + 
		   	    "          group by p.id_producer						 " + 
		   	    "         having count(*) > 1) subquery				     "
)

@SqlResultSetMapping(
	    name = "producerAwardsDTO",
	    classes = @ConstructorResult(
	        targetClass = ProducerAwardsDTO.class,
	        columns = {
	            @ColumnResult(name = "producer", type = String.class),
	            @ColumnResult(name = "intervals", type = Integer.class),
	            @ColumnResult(name = "previousWin", type = Integer.class),
	            @ColumnResult(name = "followingWin", type = Integer.class)
	        }
	    )
	)

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
