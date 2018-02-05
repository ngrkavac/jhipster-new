package com.cr.jhipsternewapp.repository;

import com.cr.jhipsternewapp.domain.Label_translation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Label_translation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Label_translationRepository extends JpaRepository<Label_translation, Long> {

}
