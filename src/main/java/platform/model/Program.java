package platform.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Program {

    private long id;

    private String code;

    private final LocalDateTime created;

    public Program() {
        created = LocalDateTime.now();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Program program = (Program) o;
        return getId() == program.getId() &&
               Objects.equals(getCode(), program.getCode()) &&
               Objects.equals(getCreated(), program.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getCreated());
    }

    @Override
    public String toString() {
        return "Program{" + "id=" + id + ", code='" + code + '\'' + ", created=" +
               created + '}';
    }
}
