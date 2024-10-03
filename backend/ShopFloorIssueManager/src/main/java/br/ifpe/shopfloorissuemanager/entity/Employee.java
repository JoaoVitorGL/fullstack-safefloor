package br.ifpe.shopfloorissuemanager.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    private int id;
    private String name;
    private Sector sector;
}
