package com.cr.jhipsternewapp.web.rest;

import com.cr.jhipsternewapp.JhipsterNewApp;

import com.cr.jhipsternewapp.domain.Label_translation;
import com.cr.jhipsternewapp.repository.Label_translationRepository;
import com.cr.jhipsternewapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.cr.jhipsternewapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Label_translationResource REST controller.
 *
 * @see Label_translationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterNewApp.class)
public class Label_translationResourceIntTest {

    private static final String DEFAULT_TRANSLATION_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSLATION_LANGUAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final String DEFAULT_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_OWNER = "BBBBBBBBBB";

    @Autowired
    private Label_translationRepository label_translationRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLabel_translationMockMvc;

    private Label_translation label_translation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Label_translationResource label_translationResource = new Label_translationResource(label_translationRepository);
        this.restLabel_translationMockMvc = MockMvcBuilders.standaloneSetup(label_translationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Label_translation createEntity(EntityManager em) {
        Label_translation label_translation = new Label_translation()
            .translation_language(DEFAULT_TRANSLATION_LANGUAGE)
            .version(DEFAULT_VERSION)
            .owner(DEFAULT_OWNER);
        return label_translation;
    }

    @Before
    public void initTest() {
        label_translation = createEntity(em);
    }

    @Test
    @Transactional
    public void createLabel_translation() throws Exception {
        int databaseSizeBeforeCreate = label_translationRepository.findAll().size();

        // Create the Label_translation
        restLabel_translationMockMvc.perform(post("/api/label-translations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(label_translation)))
            .andExpect(status().isCreated());

        // Validate the Label_translation in the database
        List<Label_translation> label_translationList = label_translationRepository.findAll();
        assertThat(label_translationList).hasSize(databaseSizeBeforeCreate + 1);
        Label_translation testLabel_translation = label_translationList.get(label_translationList.size() - 1);
        assertThat(testLabel_translation.getTranslation_language()).isEqualTo(DEFAULT_TRANSLATION_LANGUAGE);
        assertThat(testLabel_translation.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testLabel_translation.getOwner()).isEqualTo(DEFAULT_OWNER);
    }

    @Test
    @Transactional
    public void createLabel_translationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = label_translationRepository.findAll().size();

        // Create the Label_translation with an existing ID
        label_translation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLabel_translationMockMvc.perform(post("/api/label-translations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(label_translation)))
            .andExpect(status().isBadRequest());

        // Validate the Label_translation in the database
        List<Label_translation> label_translationList = label_translationRepository.findAll();
        assertThat(label_translationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLabel_translations() throws Exception {
        // Initialize the database
        label_translationRepository.saveAndFlush(label_translation);

        // Get all the label_translationList
        restLabel_translationMockMvc.perform(get("/api/label-translations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(label_translation.getId().intValue())))
            .andExpect(jsonPath("$.[*].translation_language").value(hasItem(DEFAULT_TRANSLATION_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].owner").value(hasItem(DEFAULT_OWNER.toString())));
    }

    @Test
    @Transactional
    public void getLabel_translation() throws Exception {
        // Initialize the database
        label_translationRepository.saveAndFlush(label_translation);

        // Get the label_translation
        restLabel_translationMockMvc.perform(get("/api/label-translations/{id}", label_translation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(label_translation.getId().intValue()))
            .andExpect(jsonPath("$.translation_language").value(DEFAULT_TRANSLATION_LANGUAGE.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.owner").value(DEFAULT_OWNER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLabel_translation() throws Exception {
        // Get the label_translation
        restLabel_translationMockMvc.perform(get("/api/label-translations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLabel_translation() throws Exception {
        // Initialize the database
        label_translationRepository.saveAndFlush(label_translation);
        int databaseSizeBeforeUpdate = label_translationRepository.findAll().size();

        // Update the label_translation
        Label_translation updatedLabel_translation = label_translationRepository.findOne(label_translation.getId());
        // Disconnect from session so that the updates on updatedLabel_translation are not directly saved in db
        em.detach(updatedLabel_translation);
        updatedLabel_translation
            .translation_language(UPDATED_TRANSLATION_LANGUAGE)
            .version(UPDATED_VERSION)
            .owner(UPDATED_OWNER);

        restLabel_translationMockMvc.perform(put("/api/label-translations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLabel_translation)))
            .andExpect(status().isOk());

        // Validate the Label_translation in the database
        List<Label_translation> label_translationList = label_translationRepository.findAll();
        assertThat(label_translationList).hasSize(databaseSizeBeforeUpdate);
        Label_translation testLabel_translation = label_translationList.get(label_translationList.size() - 1);
        assertThat(testLabel_translation.getTranslation_language()).isEqualTo(UPDATED_TRANSLATION_LANGUAGE);
        assertThat(testLabel_translation.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testLabel_translation.getOwner()).isEqualTo(UPDATED_OWNER);
    }

    @Test
    @Transactional
    public void updateNonExistingLabel_translation() throws Exception {
        int databaseSizeBeforeUpdate = label_translationRepository.findAll().size();

        // Create the Label_translation

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLabel_translationMockMvc.perform(put("/api/label-translations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(label_translation)))
            .andExpect(status().isCreated());

        // Validate the Label_translation in the database
        List<Label_translation> label_translationList = label_translationRepository.findAll();
        assertThat(label_translationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLabel_translation() throws Exception {
        // Initialize the database
        label_translationRepository.saveAndFlush(label_translation);
        int databaseSizeBeforeDelete = label_translationRepository.findAll().size();

        // Get the label_translation
        restLabel_translationMockMvc.perform(delete("/api/label-translations/{id}", label_translation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Label_translation> label_translationList = label_translationRepository.findAll();
        assertThat(label_translationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Label_translation.class);
        Label_translation label_translation1 = new Label_translation();
        label_translation1.setId(1L);
        Label_translation label_translation2 = new Label_translation();
        label_translation2.setId(label_translation1.getId());
        assertThat(label_translation1).isEqualTo(label_translation2);
        label_translation2.setId(2L);
        assertThat(label_translation1).isNotEqualTo(label_translation2);
        label_translation1.setId(null);
        assertThat(label_translation1).isNotEqualTo(label_translation2);
    }
}
