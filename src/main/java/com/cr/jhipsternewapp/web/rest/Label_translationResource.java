package com.cr.jhipsternewapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cr.jhipsternewapp.domain.Label_translation;

import com.cr.jhipsternewapp.repository.Label_translationRepository;
import com.cr.jhipsternewapp.web.rest.errors.BadRequestAlertException;
import com.cr.jhipsternewapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Label_translation.
 */
@RestController
@RequestMapping("/api")
public class Label_translationResource {

    private final Logger log = LoggerFactory.getLogger(Label_translationResource.class);

    private static final String ENTITY_NAME = "label_translation";

    private final Label_translationRepository label_translationRepository;

    public Label_translationResource(Label_translationRepository label_translationRepository) {
        this.label_translationRepository = label_translationRepository;
    }

    /**
     * POST  /label-translations : Create a new label_translation.
     *
     * @param label_translation the label_translation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new label_translation, or with status 400 (Bad Request) if the label_translation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/label-translations")
    @Timed
    public ResponseEntity<Label_translation> createLabel_translation(@RequestBody Label_translation label_translation) throws URISyntaxException {
        log.debug("REST request to save Label_translation : {}", label_translation);
        if (label_translation.getId() != null) {
            throw new BadRequestAlertException("A new label_translation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Label_translation result = label_translationRepository.save(label_translation);
        return ResponseEntity.created(new URI("/api/label-translations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /label-translations : Updates an existing label_translation.
     *
     * @param label_translation the label_translation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated label_translation,
     * or with status 400 (Bad Request) if the label_translation is not valid,
     * or with status 500 (Internal Server Error) if the label_translation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/label-translations")
    @Timed
    public ResponseEntity<Label_translation> updateLabel_translation(@RequestBody Label_translation label_translation) throws URISyntaxException {
        log.debug("REST request to update Label_translation : {}", label_translation);
        if (label_translation.getId() == null) {
            return createLabel_translation(label_translation);
        }
        Label_translation result = label_translationRepository.save(label_translation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, label_translation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /label-translations : get all the label_translations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of label_translations in body
     */
    @GetMapping("/label-translations")
    @Timed
    public List<Label_translation> getAllLabel_translations() {
        log.debug("REST request to get all Label_translations");
        return label_translationRepository.findAll();
        }

    /**
     * GET  /label-translations/:id : get the "id" label_translation.
     *
     * @param id the id of the label_translation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the label_translation, or with status 404 (Not Found)
     */
    @GetMapping("/label-translations/{id}")
    @Timed
    public ResponseEntity<Label_translation> getLabel_translation(@PathVariable Long id) {
        log.debug("REST request to get Label_translation : {}", id);
        Label_translation label_translation = label_translationRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(label_translation));
    }

    /**
     * DELETE  /label-translations/:id : delete the "id" label_translation.
     *
     * @param id the id of the label_translation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/label-translations/{id}")
    @Timed
    public ResponseEntity<Void> deleteLabel_translation(@PathVariable Long id) {
        log.debug("REST request to delete Label_translation : {}", id);
        label_translationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
