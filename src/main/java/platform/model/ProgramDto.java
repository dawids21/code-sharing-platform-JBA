package platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProgramDto {

    private String code;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime date;

    public ProgramDto(String code, LocalDateTime date) {
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
