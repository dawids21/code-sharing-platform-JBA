package platform.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String code;

    private LocalDateTime created;

    private LocalDateTime validUntil;

    private Integer views;

    private boolean restricted;

    public Program() {
    }

    public Program(UUID id, String code, LocalDateTime created, LocalDateTime validUntil,
                   int views, boolean restricted) {
        this.id = id;
        this.code = code;
        this.created = created;
        this.validUntil = validUntil;
        this.views = views;
        this.restricted = restricted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Program program = (Program) o;
        return getViews() == program.getViews() &&
               isRestricted() == program.isRestricted() &&
               Objects.equals(getId(), program.getId()) &&
               Objects.equals(getCode(), program.getCode()) &&
               Objects.equals(getCreated(), program.getCreated()) &&
               Objects.equals(getValidUntil(), program.getValidUntil());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getCreated(), getValidUntil(), getViews(),
                            isRestricted());
    }

    @Override
    public String toString() {
        return "Program{" + "id=" + id + ", code='" + code + '\'' + ", created=" +
               created + ", validUntil=" + validUntil + ", views=" + views +
               ", restricted=" + restricted + '}';
    }
}
