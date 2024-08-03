package br.senai.lab365.sistema_de_saude.controllers;


import br.senai.lab365.sistema_de_saude.dto.ConsultaRequestDTO;
import br.senai.lab365.sistema_de_saude.dto.ConsultaResponseDTO;
import br.senai.lab365.sistema_de_saude.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> criaConsulta(@Valid @RequestBody ConsultaRequestDTO consultaRequest) {
        ConsultaResponseDTO createdConsulta = consultaService.criaConsulta(consultaRequest);
        return new ResponseEntity<>(createdConsulta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizaConsulta(@PathVariable Long id,
                                                              @Valid @RequestBody ConsultaRequestDTO consultaRequest) {
        ConsultaResponseDTO updatedConsulta = consultaService.atualizaConsulta(id, consultaRequest);
        return new ResponseEntity<>(updatedConsulta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        consultaService.deletaConsulta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> getConsultaById(@PathVariable Long id) {
        ConsultaResponseDTO consulta = consultaService.getConsultaById(id);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> getAllConsultas() {
        List<ConsultaResponseDTO> consultas = consultaService.getAllConsultas();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }
}