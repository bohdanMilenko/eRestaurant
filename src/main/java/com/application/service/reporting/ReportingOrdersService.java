package com.application.service.reporting;

import com.application.entity.dto.ReportingOrdersDTO;
import com.application.exception.ServiceException;
import com.application.service.IDishService;
import com.application.service.IOrderService;
import com.application.service.IOrderStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

@Service
@AllArgsConstructor
@Slf4j
public class ReportingOrdersService implements IReportingOrdersService {

    private IOrderService orderService;
    private IOrderStatusService orderStatusService;
    private IDishService dishService;


    public List<ReportingOrdersDTO> getOrderReportYTD() throws ServiceException {
        LocalDateTime startDate = LocalDateTime.now().with(firstDayOfYear());
        LocalDateTime endDate = LocalDateTime.now();
        try {
            return orderService.getOrderReport(startDate, endDate, false);
        } catch (ServiceException e) {
            log.error("ReportingOrdersService is unable to process the request getOrderReportYTD() - exception: {}", e.toString());
            throw new ServiceException("ReportingOrdersService is unable to process the request getOrderReportYTD()", e);
        }
    }

    public List<ReportingOrdersDTO> getOrderReportByDate(LocalDateTime startDate, LocalDateTime endDate, boolean sorting) throws ServiceException {
        try {
            return orderService.getOrderReport(startDate, endDate, sorting);
        } catch (ServiceException e) {
            log.error("ReportingOrdersService is unable to process the request getOrderReportByDate(startDate = {}, endDate = {},sorting = {} ) - exception: {}", startDate.toString(),
                    endDate.toString(), sorting, e.toString());
            throw new ServiceException("ReportingOrdersService is unable to process the request getOrderReportYTD()", e);
        }
    }


}
