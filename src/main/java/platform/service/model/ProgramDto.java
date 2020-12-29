package platform.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ProgramDto {

    private String code;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String date;

    private int time;

    private int views;

    @JsonIgnore
    private boolean timeRestricted;

    @JsonIgnore
    private boolean viewsRestricted;

    public ProgramDto(String code, String date, int time, int views,
                      boolean timeRestricted, boolean viewsRestricted) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
        this.timeRestricted = timeRestricted;
        this.viewsRestricted = viewsRestricted;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public void setTimeRestricted(boolean timeRestricted) {
        this.timeRestricted = timeRestricted;
    }

    public boolean isViewsRestricted() {
        return viewsRestricted;
    }

    public void setViewsRestricted(boolean viewsRestricted) {
        this.viewsRestricted = viewsRestricted;
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
        return getTime() == that.getTime() && getViews() == that.getViews() &&
               isTimeRestricted() == that.isTimeRestricted() &&
               isViewsRestricted() == that.isViewsRestricted() &&
               Objects.equals(getCode(), that.getCode()) &&
               Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getDate(), getTime(), getViews(),
                            isTimeRestricted(), isViewsRestricted());
    }

    @Override
    public String toString() {
        return "ProgramDto{" + "code='" + code + '\'' + ", date='" + date + '\'' +
               ", time=" + time + ", views=" + views + ", timeRestricted=" +
               timeRestricted + ", viewsRestricted=" + viewsRestricted + '}';
    }
}
