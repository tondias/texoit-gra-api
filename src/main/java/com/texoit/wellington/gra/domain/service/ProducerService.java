package com.texoit.wellington.gra.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.texoit.wellington.gra.domain.dto.ProducerAwardsDTO;
import com.texoit.wellington.gra.domain.dto.ProducerMinMaxAwardsDTO;
import com.texoit.wellington.gra.domain.enumeration.AwardClassification;
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
		List<MovieProducer> movieProducerList = movieProducerRepository.findMovieProducerWinner();

		ProducerAwardsDTO previous = findPreviousOrFollowingAwards(movieProducerList, AwardClassification.PREVIOUS);
		ProducerAwardsDTO following = findPreviousOrFollowingAwards(movieProducerList, AwardClassification.FOLLOWING);

		ProducerMinMaxAwardsDTO producerMinMaxAwardsDTO = new ProducerMinMaxAwardsDTO();
		producerMinMaxAwardsDTO.addMin(previous);
		producerMinMaxAwardsDTO.addMax(following);

		return producerMinMaxAwardsDTO;
	}

	private ProducerAwardsDTO findPreviousOrFollowingAwards(List<MovieProducer> movieProducerList,
			AwardClassification classification) {
		ProducerAwardsDTO producerAwardsDTO = new ProducerAwardsDTO(null, null, null, null);

		if (classification == AwardClassification.FOLLOWING) {
			producerAwardsDTO.setInterval(Integer.MIN_VALUE);
		} else {
			producerAwardsDTO.setInterval(Integer.MAX_VALUE);
		}

		for (int i = 0; i < movieProducerList.size() - 1; i++) {
			for (int j = i + 1; j < movieProducerList.size(); j++) {

				MovieProducer previous = movieProducerList.get(i);
				MovieProducer following = movieProducerList.get(j);

				if (previous.getProducer().equals(following.getProducer())) {
					Integer interval = Math.abs(previous.getMovie().getYear() - following.getMovie().getYear());

					if (classification == AwardClassification.FOLLOWING) {
						if (interval > producerAwardsDTO.getInterval()) {
							producerAwardsDTO.setInterval(interval);
							producerAwardsDTO.setProducer(previous.getProducer().getName());
							producerAwardsDTO.setPreviousWin(previous.getMovie().getYear());
							producerAwardsDTO.setFollowingWin(following.getMovie().getYear());

							break;
						}
					} else {
						if (interval < producerAwardsDTO.getInterval()) {
							producerAwardsDTO.setInterval(interval);
							producerAwardsDTO.setProducer(previous.getProducer().getName());
							producerAwardsDTO.setPreviousWin(previous.getMovie().getYear());
							producerAwardsDTO.setFollowingWin(following.getMovie().getYear());

							break;
						}
					}
				}
			}
		}

		return producerAwardsDTO;
	}
}
