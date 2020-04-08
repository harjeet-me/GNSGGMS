package org.gnsg.gms.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Entity
@Table(name = "sevadar")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "sevadar")
public class Sevadar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "seva_start_date")
    private Instant sevaStartDate;

    @Column(name = "seva_end_date")
    private Instant sevaEndDate;

    @Column(name = "is_valid")
    private Boolean isValid;

    @OneToMany(mappedBy = "sevadar")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Program> programs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Sevadar name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Sevadar email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Sevadar phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getSevaStartDate() {
        return sevaStartDate;
    }

    public Sevadar sevaStartDate(Instant sevaStartDate) {
        this.sevaStartDate = sevaStartDate;
        return this;
    }

    public void setSevaStartDate(Instant sevaStartDate) {
        this.sevaStartDate = sevaStartDate;
    }

    public Instant getSevaEndDate() {
        return sevaEndDate;
    }

    public Sevadar sevaEndDate(Instant sevaEndDate) {
        this.sevaEndDate = sevaEndDate;
        return this;
    }

    public void setSevaEndDate(Instant sevaEndDate) {
        this.sevaEndDate = sevaEndDate;
    }

    public Boolean isIsValid() {
        return isValid;
    }

    public Sevadar isValid(Boolean isValid) {
        this.isValid = isValid;
        return this;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public Sevadar programs(Set<Program> programs) {
        this.programs = programs;
        return this;
    }

    public Sevadar addProgram(Program program) {
        this.programs.add(program);
        program.setSevadar(this);
        return this;
    }

    public Sevadar removeProgram(Program program) {
        this.programs.remove(program);
        program.setSevadar(null);
        return this;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sevadar)) {
            return false;
        }
        return id != null && id.equals(((Sevadar) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Sevadar{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", sevaStartDate='" + getSevaStartDate() + "'" +
            ", sevaEndDate='" + getSevaEndDate() + "'" +
            ", isValid='" + isIsValid() + "'" +
            "}";
    }
}
