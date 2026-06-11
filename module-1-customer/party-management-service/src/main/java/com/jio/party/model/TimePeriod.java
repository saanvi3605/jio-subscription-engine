package com.jio.party.model;

import jakarta.persistence.Embeddable;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;

/**
 * A period of time, either as a deadline (endDateTime only) or as a start/end range.
 * TMF632 common value object.
 */
@Embeddable
public class TimePeriod {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime endDateTime;

    public TimePeriod() {}

    public TimePeriod(OffsetDateTime startDateTime, OffsetDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime   = endDateTime;
    }

    public OffsetDateTime getStartDateTime() { return startDateTime; }
    public void setStartDateTime(OffsetDateTime startDateTime) { this.startDateTime = startDateTime; }

    public OffsetDateTime getEndDateTime() { return endDateTime; }
    public void setEndDateTime(OffsetDateTime endDateTime) { this.endDateTime = endDateTime; }
}
