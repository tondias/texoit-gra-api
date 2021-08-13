package com.texoit.wellington.gra.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.texoit.wellington.gra.domain.dto.MovieDTO;
import com.texoit.wellington.gra.domain.exception.ResourceNotFoundException;
import com.texoit.wellington.gra.domain.model.Movie;
import com.texoit.wellington.gra.domain.reporsitory.MovieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MovieService {

	private MovieRepository movieRepository;

	@Transactional
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	public List<MovieDTO> findAll() {
		List<Movie> movies = movieRepository.findAll();

		if (movies == null || movies.isEmpty()) {
			return new ArrayList<>();
		}

		List<MovieDTO> moviesDTO = new ArrayList<>();
		for (Movie movie : movies) {
			moviesDTO.add(new MovieDTO(movie));
		}

		return moviesDTO;
	}

	@Transactional
	public void delete(Long id) {
		Optional<Movie> optional = movieRepository.findById(id);

		if (!optional.isPresent()) {
			throw new ResourceNotFoundException();
		}

		Movie movie = optional.get();
		movieRepository.delete(movie);
	}

	public boolean existsById(Long id) {
		return movieRepository.existsById(id);
	}

	public Movie findById(Long id) {
		Optional<Movie> optional = movieRepository.findById(id);

		if (!optional.isPresent()) {
			throw new ResourceNotFoundException();
		}

		return optional.get();
	}
}
