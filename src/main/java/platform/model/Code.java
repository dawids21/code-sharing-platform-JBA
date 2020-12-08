package platform.model;

import java.util.Objects;

public class Code {

    private long id;

    private String code;

    public Code() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Code code1 = (Code) o;
        return getId() == code1.getId() && Objects.equals(getCode(), code1.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode());
    }

    @Override
    public String toString() {
        return "Code{" + "id=" + id + ", code='" + code + '\'' + '}';
    }
}
