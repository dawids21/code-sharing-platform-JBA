package platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ProgramDto {

    private String code;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String date;

    public ProgramDto(String code, String date) {
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProgramDto that = (ProgramDto) o;
        return Objects.equals(getCode(), that.getCode()) &&
               Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getDate());
    }

    @Override
    public String toString() {
        return "ProgramDto{" + "code='" + code + '\'' + ", date=" + date + '}';
    }
}
