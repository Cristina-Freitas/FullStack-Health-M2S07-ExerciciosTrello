package br.senai.lab365.sistema_de_saude.services;

import br.senai.lab365.sistema_de_saude.dto.ConsultaListDTO;
import br.senai.lab365.sistema_de_saude.dto.ConsultaRequestDTO;
import br.senai.lab365.sistema_de_saude.dto.ConsultaResponseDTO;
import br.senai.lab365.sistema_de_saude.models.Consulta;
import br.senai.lab365.sistema_de_saude.models.Nutricionista;
import br.senai.lab365.sistema_de_saude.models.Paciente;
import br.senai.lab365.sistema_de_saude.repositories.ConsultaRepository;
import br.senai.lab365.sistema_de_saude.repositories.NutricionistaRepository;
import br.senai.lab365.sistema_de_saude.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public ConsultaResponseDTO criaConsulta(ConsultaRequestDTO consultaRequest) {
        Consulta consulta = new Consulta();
        consulta.setData(consultaRequest.getData());
        consulta.setObservacoes(consultaRequest.getObservacoes());

        Nutricionista nutricionista = nutricionistaRepository.findById(consultaRequest.getNutricionistaId())
                .orElseThrow(() -> new RuntimeException("Nutricionista não encontrado com ID " + consultaRequest.getNutricionistaId()));
        consulta.setNutricionista(nutricionista);

        Paciente paciente = pacienteRepository.findById(consultaRequest.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID " + consultaRequest.getPacienteId()));
        consulta.setPaciente(paciente);

        Consulta savedConsulta = consultaRepository.save(consulta);

        return mapToConsultaResponse(savedConsulta);
    }

    public ConsultaResponseDTO atualizaConsulta(Long id, ConsultaRequestDTO consultaRequest) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID " + id));

        consulta.setData(consultaRequest.getData());
        consulta.setObservacoes(consultaRequest.getObservacoes());

        Nutricionista nutricionista = nutricionistaRepository.findById(consultaRequest.getNutricionistaId())
                .orElseThrow(() -> new RuntimeException("Nutricionista não encontrado com ID " + consultaRequest.getNutricionistaId()));
        consulta.setNutricionista(nutricionista);

        Paciente paciente = pacienteRepository.findById(consultaRequest.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID " + consultaRequest.getPacienteId()));
        consulta.setPaciente(paciente);

        Consulta updatedConsulta = consultaRepository.save(consulta);

        return mapToConsultaResponse(updatedConsulta);
    }

    public void deletaConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID " + id));

        consultaRepository.delete(consulta);
    }

    public ConsultaResponseDTO getConsultaById(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID " + id));

        return mapToConsultaResponse(consulta);
    }

    public List<ConsultaListDTO> listaConsultas() {
        List<Consulta> consultas = consultaRepository.findAll();

        return consultas.stream().map(this::mapToConsultaListDTO).collect(Collectors.toList());
    }

    private ConsultaResponseDTO mapToConsultaResponse(Consulta consulta) {
        ConsultaResponseDTO response = new ConsultaResponseDTO();
        response.setId(consulta.getId());
        response.setData(consulta.getData());
        response.setObservacoes(consulta.getObservacoes());

        // Verifica se nutricionista e paciente não são nulos
        if (consulta.getNutricionista() != null) {
            response.setNutricionistaId(consulta.getNutricionista().getId());
        } else {
            response.setNutricionistaId(null);
        }

        if (consulta.getPaciente() != null) {
            response.setPacienteId(consulta.getPaciente().getId());
        } else {
            response.setPacienteId(null);
        }

        return response;
    }

    private ConsultaListDTO mapToConsultaListDTO(Consulta consulta) {
        ConsultaListDTO response = new ConsultaListDTO();
        response.setData(consulta.getData());
        response.setNutricionistaNome(consulta.getNutricionista().getNome());
        response.setPacienteNome(consulta.getPaciente().getNome());

        return response;
    }
}
