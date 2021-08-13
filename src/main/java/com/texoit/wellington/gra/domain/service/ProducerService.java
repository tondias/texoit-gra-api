package com.texoit.wellington.gra.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.texoit.wellington.gra.domain.dto.ProducerAwardsDTO;
import com.texoit.wellington.gra.domain.dto.ProducerMinMaxAwardsDTO;
import com.texoit.wellington.gra.domain.model.Movie;
import com.texoit.wellington.gra.domain.model.MovieProducer;
import com.texoit.wellington.gra.domain.model.Producer;
import com.texoit.wellington.gra.domain.reporsitory.MovieProducerRepository;
import com.texoit.wellington.gra.domain.reporsitory.ProducerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProducerService {

	private ProducerRepository producerRepository;
	private MovieProducerRepository movieProducerRepository;

	@Transactional
	public void saveProducers(Movie movie, String producers) {
		for (String nameProducer : producers.split(",|\\ and ")) {
			Producer producer = new Producer(nameProducer.trim());

			Example<Producer> example = Example.of(producer);

			if (producerRepository.exists(example)) {
				producer = producerRepository.findByName(nameProducer.trim());
			} else {
				producer = producerRepository.save(producer);
			}

			movieProducerRepository.save(new MovieProducer(movie, producer));
		}
	}

	public ProducerMinMaxAwardsDTO findMaxAndMinAwards() {
		Integer minInterval = movieProducerRepository.findMinInterval();
		Integer maxInterval = movieProducerRepository.findMaxInterval();

		List<ProducerAwardsDTO> producersMinAwards = movieProducerRepository.findProducersMinAwards(minInterval);
		List<ProducerAwardsDTO> producersMaxAwards = movieProducerRepository.findProducersMaxAwards(maxInterval);

		ProducerMinMaxAwardsDTO producerMinMaxAwardsDTO = new ProducerMinMaxAwardsDTO();
		producersMinAwards.forEach(p -> producerMinMaxAwardsDTO.addMin(p));
		producersMaxAwards.forEach(p -> producerMinMaxAwardsDTO.addMax(p));

		return producerMinMaxAwardsDTO;
	}
}
