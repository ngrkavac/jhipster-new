package com.cr.jhipsternewapp.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Label_translation.
 */
@Entity
@Table(name = "label_translation")
public class Label_translation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "translation_language")
    private String translation_language;

    @Column(name = "version")
    private Integer version;

    @Column(name = "owner")
    private String owner;

    @Column(name = "created")
    private LocalDate created;

    @ManyToOne
    private Label label;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranslation_language() {
        return translation_language;
    }

    public Label_translation translation_language(String translation_language) {
        this.translation_language = translation_language;
        return this;
    }

    public void setTranslation_language(String translation_language) {
        this.translation_language = translation_language;
    }

    public Integer getVersion() {
        return version;
    }

    public Label_translation version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOwner() {
        return owner;
    }

    public Label_translation owner(String owner) {
        this.owner = owner;
        return this;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Label_translation created(LocalDate created) {
        this.created = created;
        return this;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Label getLabel() {
        return label;
    }

    public Label_translation label(Label label) {
        this.label = label;
        return this;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Label_translation label_translation = (Label_translation) o;
        if (label_translation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), label_translation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Label_translation{" +
            "id=" + getId() +
            ", translation_language='" + getTranslation_language() + "'" +
            ", version=" + getVersion() +
            ", owner='" + getOwner() + "'" +
            ", created='" + getCreated() + "'" +
            "}";
    }
}
