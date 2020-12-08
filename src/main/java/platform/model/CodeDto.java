package platform.model;

import java.util.Objects;

public class CodeDto {

    private String code;

    public CodeDto(String code) {
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
        CodeDto codeDto = (CodeDto) o;
        return Objects.equals(getCode(), codeDto.getCode());
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
