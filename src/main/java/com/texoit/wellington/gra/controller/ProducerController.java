package com.texoit.wellington.gra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.wellington.gra.domain.dto.ProducerMinMaxAwardsDTO;
import com.texoit.wellington.gra.domain.service.ProducerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/producers")
public class ProducerController {

	private ProducerService producerService;

	@GetMapping
	public ResponseEntity<ProducerMinMaxAwardsDTO> findMaxAndMinAwards() {
		ProducerMinMaxAwardsDTO dto = producerService.findMaxAndMinAwards();

		HttpStatus status = HttpStatus.OK;
		if (dto.getMax().isEmpty() && dto.getMin().isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<ProducerMinMaxAwardsDTO>(dto, status);
	}
}
