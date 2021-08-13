package com.texoit.wellington.gra.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MovieProducerId implements Serializable {

	private static final long serialVersionUID = 1868353578901644039L;

	private Long movieId;

	private Long producerId;

}
