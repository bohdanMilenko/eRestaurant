package com.application.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReportingMenuItemDTO {

    private String menuItemName;
    private long quantitySold;
    private long grossIncome;
}
