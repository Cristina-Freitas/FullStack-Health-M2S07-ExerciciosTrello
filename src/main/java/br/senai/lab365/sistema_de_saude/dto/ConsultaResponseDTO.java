package br.senai.lab365.sistema_de_saude.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultaResponseDTO {
    private Long id;
    private NutricionistaResponseDTO nutricionista;
    private PacienteResponseDTO paciente;
    private LocalDate data;
    private String observacoes;

    public void setNutricionistaId(Long id) {
    }

    public void setPacienteId(Long id) {
    }
}
