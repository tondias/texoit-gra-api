package com.texoit.wellington.gra.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.texoit.wellington.gra.domain.model.Movie;
import com.texoit.wellington.gra.domain.model.MovieProducer;
import com.texoit.wellington.gra.domain.model.MovieStudio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {

	private Long id;

	private Integer year;

	private String title;

	private List<String> studios = new ArrayList<>();

	private List<String> producers = new ArrayList<>();

	private Boolean winner;

	public MovieDTO(Movie movie) {
		this.id = movie.getId();
		this.year = movie.getYear();
		this.title = movie.getTitle();
		this.winner = movie.getWinner();

		for (MovieStudio ms : movie.getStudios()) {
			this.studios.add(ms.getStudio().getName());
		}

		for (MovieProducer mp : movie.getProducers()) {
			this.producers.add(mp.getProducer().getName());
		}
	}
}
