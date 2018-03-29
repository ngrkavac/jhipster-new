package com.cr.jhipsternewapp.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Label.
 */
@Entity
@Table(name = "label")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "label_key")
    private String label_key;

    @Column(name = "label_value")
    private String label_value;

    @Column(name = "version")
    private Integer version;

    @Column(name = "country")
    private String country;

    @Column(name = "owner")
    private String owner;

    @Column(name = "created")
    private LocalDate created;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel_key() {
        return label_key;
    }

    public Label label_key(String label_key) {
        this.label_key = label_key;
        return this;
    }

    public void setLabel_key(String label_key) {
        this.label_key = label_key;
    }

    public String getLabel_value() {
        return label_value;
    }

    public Label label_value(String label_value) {
        this.label_value = label_value;
        return this;
    }

    public void setLabel_value(String label_value) {
        this.label_value = label_value;
    }

    public Integer getVersion() {
        return version;
    }

    public Label version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCountry() {
        return country;
    }

    public Label country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOwner() {
        return owner;
    }

    public Label owner(String owner) {
        this.owner = owner;
        return this;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public LocalDate getCreated() {
        return created;
    }

    public Label created(LocalDate created) {
        this.created = created;
        return this;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
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
        Label label = (Label) o;
        if (label.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), label.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Label{" +
            "id=" + getId() +
            ", label_key='" + getLabel_key() + "'" +
            ", label_value='" + getLabel_value() + "'" +
            ", version=" + getVersion() +
            ", country='" + getCountry() + "'" +
            ", owner='" + getOwner() + "'" +
            ", created='" + getCreated() + "'" +
            "}";
    }
}
