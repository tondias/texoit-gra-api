package com.texoit.wellington.gra.domain.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MovieStudio {

	@EmbeddedId
	private MovieStudioId id;

	@ManyToOne
	@MapsId("movieId")
	private Movie movie;

	@ManyToOne
	@MapsId("studioId")
	private Studio studio;

	public MovieStudio(Movie movie, Studio studio) {
		this.movie = movie;
		this.studio = studio;
		this.id = new MovieStudioId(movie.getId(), studio.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		MovieStudio other = (MovieStudio) obj;
		return Objects.equals(movie, other.getMovie()) && Objects.equals(studio, other.getStudio());
	}

	@Override
	public int hashCode() {
		return Objects.hash(movie, studio);
	}
}
