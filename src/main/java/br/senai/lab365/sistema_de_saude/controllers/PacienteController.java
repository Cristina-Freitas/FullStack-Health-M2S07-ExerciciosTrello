package br.senai.lab365.sistema_de_saude.controllers;


import br.senai.lab365.sistema_de_saude.dto.PacienteRequestDTO;
import br.senai.lab365.sistema_de_saude.dto.PacienteResponseDTO;
import br.senai.lab365.sistema_de_saude.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criaPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequest) {
        PacienteResponseDTO createdPaciente = pacienteService.criaPaciente(pacienteRequest);
        return new ResponseEntity<>(createdPaciente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizaPaciente(@PathVariable Long id,
                                                              @Valid @RequestBody PacienteRequestDTO pacienteRequest) {
        PacienteResponseDTO updatedPaciente = pacienteService.atualizaPaciente(id, pacienteRequest);
        return new ResponseEntity<>(updatedPaciente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deletaPaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> getPacienteById(@PathVariable Long id) {
        PacienteResponseDTO paciente = pacienteService.getPacienteById(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> getAllPacientes() {
        List<PacienteResponseDTO> pacientes = pacienteService.getAllPacientes();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }
}
