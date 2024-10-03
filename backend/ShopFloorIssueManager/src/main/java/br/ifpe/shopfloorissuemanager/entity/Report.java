package br.ifpe.shopfloorissuemanager.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    private int id;
    private Date date;
    private Employee employee;
    private Problem problem;
}
