package com.texoit.wellington.gra.domain.dto;

import com.texoit.wellington.gra.domain.model.Movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieUpdateDTO {

	private Integer year;
	private String title;
	private Boolean winner;

	public MovieUpdateDTO(Movie movie) {
		this.year = movie.getYear();
		this.title = movie.getTitle();
		this.winner = movie.getWinner();
	}
}
