package com.texoit.wellington.gra.domain.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.texoit.wellington.gra.domain.model.Movie;
import com.texoit.wellington.gra.domain.model.MovieStudio;
import com.texoit.wellington.gra.domain.model.Studio;
import com.texoit.wellington.gra.domain.reporsitory.MovieStudioRepository;
import com.texoit.wellington.gra.domain.reporsitory.StudioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudioService {

	private StudioRepository studioRepository;
	private MovieStudioRepository movieStudioRepository;

	@Transactional
	public void saveStudios(Movie movie, String studios) {
		for (String strStudio : studios.split(",|\\ and ")) {
			Studio studio = new Studio(strStudio.trim());

			Example<Studio> example = Example.of(studio);

			if (studioRepository.exists(example)) {
				studio = studioRepository.findByName(strStudio.trim());
			} else {
				studio = studioRepository.save(studio);
			}

			movieStudioRepository.save(new MovieStudio(movie, studio));
		}
	}
}
