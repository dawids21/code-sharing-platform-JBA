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

    private Integer countViews;

    private Integer countAllowed;

    private boolean restricted;

    public Program() {
    }

    public Program(UUID id, String code, LocalDateTime created, LocalDateTime validUntil,
                   Integer countViews, Integer countAllowed, boolean restricted) {
        this.id = id;
        this.code = code;
        this.created = created;
        this.validUntil = validUntil;
        this.countViews = countViews;
        this.countAllowed = countAllowed;
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

    public Integer getCountViews() {
        return countViews;
    }

    public void setCountViews(Integer views) {
        this.countViews = views;
    }

    public Integer getCountAllowed() {
        return countAllowed;
    }

    public void setCountAllowed(Integer countAllowed) {
        this.countAllowed = countAllowed;
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
        return isRestricted() == program.isRestricted() &&
               Objects.equals(getId(), program.getId()) &&
               Objects.equals(getCode(), program.getCode()) &&
               Objects.equals(getCreated(), program.getCreated()) &&
               Objects.equals(getValidUntil(), program.getValidUntil()) &&
               Objects.equals(getCountViews(), program.getCountViews()) &&
               Objects.equals(getCountAllowed(), program.getCountAllowed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getCreated(), getValidUntil(),
                            getCountViews(), getCountAllowed(), isRestricted());
    }

    @Override
    public String toString() {
        return "Program{" + "id=" + id + ", code='" + code + '\'' + ", created=" +
               created + ", validUntil=" + validUntil + ", countViews=" + countViews +
               ", countAllowed=" + countAllowed + ", restricted=" + restricted + '}';
    }
}
