package com.application.service.reporting;

import com.application.entity.dto.ReportingOrdersDTO;
import com.application.exception.ServiceException;

import java.time.LocalDateTime;
import java.util.List;

public interface IReportingOrdersService {

    List<ReportingOrdersDTO> getOrderReportYTD() throws ServiceException;

    List<ReportingOrdersDTO> getOrderReportByDate(LocalDateTime startDate, LocalDateTime endDate, boolean sorting) throws ServiceException;

}
