package com.texoit.wellington.gra.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Studio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_STUDIO")
	private Long id;

	@Column(length = 100)
	private String name;

	@OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MovieStudio> movies;

	public Studio(String name) {
		this.name = name;
	}
}
