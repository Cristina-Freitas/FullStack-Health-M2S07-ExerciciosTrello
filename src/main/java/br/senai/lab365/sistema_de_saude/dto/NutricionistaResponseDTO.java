package br.senai.lab365.sistema_de_saude.dto;

import lombok.Data;

@Data
public class NutricionistaResponseDTO {
    private Long id;
    private String nome;
    private String crn;
    private String especialidade;
}
