package com.texoit.wellington.gra.domain.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texoit.wellington.gra.domain.model.Studio;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

	Studio findByName(String name);

}
