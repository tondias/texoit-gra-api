package com.texoit.wellington.gra.domain.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texoit.wellington.gra.domain.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

	Producer findByName(String name);

}
