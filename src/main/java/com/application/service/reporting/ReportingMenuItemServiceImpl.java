package com.application.service.reporting;

import com.application.entity.dto.ReportingMenuItemDTO;
import com.application.service.IMenuCategoryService;
import com.application.service.IMenuItemService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ReportingMenuItemServiceImpl implements IReportingMenuItemService {

    private IMenuItemService menuItemService;
    private IMenuCategoryService menuCategoryService;


    @Override
    public List<ReportingMenuItemDTO> getMenuItemReport(LocalDate startDate, LocalDate endDate, boolean sorting) {

        return null;
    }
}
