package platform.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;

    private LocalDateTime created;

    private LocalDateTime validUntil;

    private boolean restricted;

    public Program() {
    }

    public Program(long id, String code, LocalDateTime created, LocalDateTime validUntil,
                   boolean restricted) {
        this.id = id;
        this.code = code;
        this.created = created;
        this.validUntil = validUntil;
        this.restricted = restricted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return getId() == program.getId() && isRestricted() == program.isRestricted() &&
               Objects.equals(getCode(), program.getCode()) &&
               Objects.equals(getCreated(), program.getCreated()) &&
               Objects.equals(getValidUntil(), program.getValidUntil());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getCreated(), getValidUntil(),
                            isRestricted());
    }

    @Override
    public String toString() {
        return "Program{" + "id=" + id + ", code='" + code + '\'' + ", created=" +
               created + ", validUntil=" + validUntil + ", restricted=" + restricted +
               '}';
    }
}
