package com.andreis.pet.project.btpapp.filters;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportFilter {

    public Long employeeId;
    public Long projectId;
    public LocalDateTime dateFrom;
    public LocalDateTime dateTo;
}
