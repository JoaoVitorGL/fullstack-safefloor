package br.ifpe.shopfloorissuemanager.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {

    private int id;
    private String description;
}
