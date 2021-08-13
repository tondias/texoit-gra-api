package com.texoit.wellington.gra.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProducerAwardsDTO {

	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;
}
