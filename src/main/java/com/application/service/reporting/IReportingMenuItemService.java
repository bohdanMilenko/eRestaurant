package com.application.service.reporting;

import com.application.entity.dto.ReportingMenuItemDTO;

import java.time.LocalDate;
import java.util.List;

public interface IReportingMenuItemService {


    List<ReportingMenuItemDTO> getMenuItemReport(LocalDate startDate, LocalDate endDate, boolean sorting);
}
