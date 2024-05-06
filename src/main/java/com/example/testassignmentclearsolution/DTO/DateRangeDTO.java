package com.example.testassignmentclearsolution.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DateRangeDTO {
    @NotBlank(message = "*required field")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fromDate;

    @NotBlank(message = "*required field")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate toDate;

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
