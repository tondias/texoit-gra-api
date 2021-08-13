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
public class MovieStudioId implements Serializable {

	private static final long serialVersionUID = 7625544296041586209L;

	private Long movieId;

	private Long studioId;

}
