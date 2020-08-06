package com.application.controller;

import com.application.entity.dto.ReportingMenuItemDTO;
import com.application.entity.dto.ReportingOrdersDTO;
import com.application.exception.ServiceException;
import com.application.service.reporting.IReportingMenuItemService;
import com.application.service.reporting.IReportingOrdersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reports")
@Slf4j
@AllArgsConstructor
public class ReportController {

    private IReportingOrdersService reportingOrdersService;
    private IReportingMenuItemService reportingMenuItemService;


    @GetMapping(value = "/order")
    public ResponseEntity<List<ReportingOrdersDTO>> getOrdersReport(@RequestParam(name ="startDate", defaultValue = "") LocalDateTime startDate,
                                                                    @RequestParam(name ="endDate", defaultValue = "")  LocalDateTime endDate,
                                                                    @RequestParam(name ="isDateSortedDesc", defaultValue = "false")  boolean isDateSortedDesc) {
        if (startDate != null && endDate != null) {
            try {
                log.info("ReportingController uses getOrdersReport(startDate = {}, endDate = {}, isDateSortedDesc ={} )", startDate.toString(), endDate.toString(), isDateSortedDesc);
                return new ResponseEntity<>(reportingOrdersService.getOrderReportByDate(startDate, endDate, isDateSortedDesc), HttpStatus.OK);
            } catch (ServiceException e) {
                log.error("ReportingController cannot getOrderReport(startDate = {}, endDate = {} )", startDate.toString(), endDate.toString());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                log.info("ReportingController uses getOrdersReport without any params, returning YTD report");
                return new ResponseEntity<>(reportingOrdersService.getOrderReportYTD(), HttpStatus.OK);
            } catch (ServiceException e) {
                log.error("ReportingController cannot getOrderReport() - YTD performance");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @GetMapping("/menuCategories")
    public ResponseEntity<List<ReportingMenuItemDTO>> getMenuItemReport(@RequestParam(name ="startDate", defaultValue = "") LocalDate startDate, @RequestParam(name ="endDate", defaultValue = "") LocalDate endDate, @RequestParam(name ="dateSorting", defaultValue = "false") boolean dateSorting) {
        if (startDate != null && endDate != null) {
            log.info("ReportingController uses getMenuItemReport(startDate = {}, endDate = {} )", startDate.toString(), endDate.toString());
            return new ResponseEntity<>(reportingMenuItemService.getMenuItemReport(startDate, endDate, dateSorting), HttpStatus.OK);

        } else {
            log.error("ReportingController cannot getMenuItemReport() - parameters are nulls");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}




