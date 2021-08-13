package com.texoit.wellington.gra.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.wellington.gra.domain.dto.MovieDTO;
import com.texoit.wellington.gra.domain.dto.MovieUpdateDTO;
import com.texoit.wellington.gra.domain.model.Movie;
import com.texoit.wellington.gra.domain.service.MovieService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

	private MovieService movieService;

	@GetMapping
	public ResponseEntity<List<MovieDTO>> findAll() {
		List<MovieDTO> movies = movieService.findAll();

		HttpStatus status = HttpStatus.OK;
		if (movies.isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<List<MovieDTO>>(movies, status);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		movieService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MovieUpdateDTO> update(@PathVariable Long id, @RequestBody MovieUpdateDTO movieUpdateDTO) {
		if (!movieService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Movie movie = movieService.findById(id);
		movie.setTitle(movieUpdateDTO.getTitle());
		movie.setYear(movieUpdateDTO.getYear());
		movie.setWinner(movieUpdateDTO.getWinner());

		return ResponseEntity.ok(new MovieUpdateDTO(movieService.save(movie)));
	}
}
