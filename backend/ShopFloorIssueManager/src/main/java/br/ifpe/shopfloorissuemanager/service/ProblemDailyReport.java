package br.ifpe.shopfloorissuemanager.service;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemDailyReport {

    private Date reportDate;
    private String problemDescription;
    private int reportCount;
}
