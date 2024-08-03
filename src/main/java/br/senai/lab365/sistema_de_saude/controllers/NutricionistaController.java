package br.senai.lab365.sistema_de_saude.controllers;


import br.senai.lab365.sistema_de_saude.dto.NutricionistaRequestDTO;
import br.senai.lab365.sistema_de_saude.dto.NutricionistaResponseDTO;
import br.senai.lab365.sistema_de_saude.services.NutricionistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaService nutricionistaService;

    @PostMapping
    public ResponseEntity<NutricionistaResponseDTO> criaNutricionista(@RequestBody NutricionistaRequestDTO nutricionistaRequest) {
        NutricionistaResponseDTO createdNutricionista = nutricionistaService.criaNutricionista(nutricionistaRequest);
        return new ResponseEntity<>(createdNutricionista, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaResponseDTO> atualizaNutricionista(@PathVariable Long id,
                                                                        @Valid @RequestBody NutricionistaRequestDTO nutricionistaRequest) {
        NutricionistaResponseDTO updatedNutricionista = nutricionistaService.atualizaNutricionista(id, nutricionistaRequest);
        return new ResponseEntity<>(updatedNutricionista, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Long id) {
        nutricionistaService.deletaNutricionista(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutricionistaResponseDTO> getNutricionistaById(@PathVariable Long id) {
        NutricionistaResponseDTO nutricionista = nutricionistaService.getNutricionistaById(id);
        return new ResponseEntity<>(nutricionista, HttpStatus.OK);
    }


}
