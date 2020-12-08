package platform.model;

import java.util.Objects;

public class ProgramDto {

    private String code;

    public ProgramDto(String code) {
        this.code = code;
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
        ProgramDto programDto = (ProgramDto) o;
        return Objects.equals(getCode(), programDto.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    @Override
    public String toString() {
        return "CodeDto{" + "code='" + code + '\'' + '}';
    }
}
