package br.senai.lab365.sistema_de_saude.controllers;


import br.senai.lab365.sistema_de_saude.dto.EnderecoRequestDTO;
import br.senai.lab365.sistema_de_saude.dto.EnderecoResponseDTO;
import br.senai.lab365.sistema_de_saude.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> criaEndereco(@Valid @RequestBody EnderecoRequestDTO enderecoRequest) {
        EnderecoResponseDTO createdEndereco = enderecoService.createEndereco(enderecoRequest);
        return new ResponseEntity<>(createdEndereco, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizaEndereco(@PathVariable Long id,
                                                              @Valid @RequestBody EnderecoRequestDTO enderecoRequest) {
        EnderecoResponseDTO updatedEndereco = enderecoService.updateEndereco(id, enderecoRequest);
        return new ResponseEntity<>(updatedEndereco, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaEndereco(@PathVariable Long id) {
        enderecoService.deleteEndereco(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> getEnderecoById(@PathVariable Long id) {
        EnderecoResponseDTO enderecoResponse = enderecoService.getEnderecoById(id);
        return new ResponseEntity<>(enderecoResponse, HttpStatus.OK);
    }
}
