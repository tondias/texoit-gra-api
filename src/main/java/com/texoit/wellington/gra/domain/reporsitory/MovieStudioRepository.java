package com.texoit.wellington.gra.domain.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texoit.wellington.gra.domain.model.MovieStudio;
import com.texoit.wellington.gra.domain.model.MovieStudioId;

@Repository
public interface MovieStudioRepository extends JpaRepository<MovieStudio, MovieStudioId> {

}
