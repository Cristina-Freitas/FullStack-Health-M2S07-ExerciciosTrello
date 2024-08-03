package br.senai.lab365.sistema_de_saude.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultaRequestDTO {
    private Long nutricionistaId;
    private Long pacienteId;
    private LocalDate data;
    private String observacoes;
}
