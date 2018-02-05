package com.cr.jhipsternewapp.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Attachment.
 */
@Entity
@Table(name = "attachment")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "file_name")
    private String file_name;

    @Column(name = "created_by")
    private String created_by;

    @Lob
    @Column(name = "jhi_file")
    private byte[] file;

    @Column(name = "jhi_file_content_type")
    private String fileContentType;

    @Column(name = "version")
    private Integer version;

    @Column(name = "created_on")
    private LocalDate created_on;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public Attachment file_name(String file_name) {
        this.file_name = file_name;
        return this;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getCreated_by() {
        return created_by;
    }

    public Attachment created_by(String created_by) {
        this.created_by = created_by;
        return this;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public byte[] getFile() {
        return file;
    }

    public Attachment file(byte[] file) {
        this.file = file;
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public Attachment fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Integer getVersion() {
        return version;
    }

    public Attachment version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDate getCreated_on() {
        return created_on;
    }

    public Attachment created_on(LocalDate created_on) {
        this.created_on = created_on;
        return this;
    }

    public void setCreated_on(LocalDate created_on) {
        this.created_on = created_on;
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
        Attachment attachment = (Attachment) o;
        if (attachment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attachment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Attachment{" +
            "id=" + getId() +
            ", file_name='" + getFile_name() + "'" +
            ", created_by='" + getCreated_by() + "'" +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            ", version=" + getVersion() +
            ", created_on='" + getCreated_on() + "'" +
            "}";
    }
}
