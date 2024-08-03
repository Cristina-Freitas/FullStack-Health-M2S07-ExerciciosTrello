package br.senai.lab365.sistema_de_saude.services;

import br.senai.lab365.sistema_de_saude.dto.PacienteRequestDTO;
import br.senai.lab365.sistema_de_saude.dto.PacienteResponseDTO;
import br.senai.lab365.sistema_de_saude.models.Paciente;
import br.senai.lab365.sistema_de_saude.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteResponseDTO criaPaciente(PacienteRequestDTO pacienteRequest) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteRequest.getNome());
        paciente.setDataNascimento(pacienteRequest.getDataNascimento());
        paciente.setCpf(pacienteRequest.getCpf());
        paciente.setTelefone(pacienteRequest.getTelefone());
        paciente.setEmail(pacienteRequest.getEmail());

        Paciente savedPaciente = pacienteRepository.save(paciente);

        return mapToPacienteResponse(savedPaciente);
    }

    public PacienteResponseDTO atualizaPaciente(Long id, PacienteRequestDTO pacienteRequest) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado " + id));

        paciente.setNome(pacienteRequest.getNome());
        paciente.setDataNascimento(pacienteRequest.getDataNascimento());
        paciente.setCpf(pacienteRequest.getCpf());
        paciente.setTelefone(pacienteRequest.getTelefone());
        paciente.setEmail(pacienteRequest.getEmail());

        Paciente updatedPaciente = pacienteRepository.save(paciente);

        return mapToPacienteResponse(updatedPaciente);
    }

    public void deletaPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado " + id));

        pacienteRepository.delete(paciente);
    }

    public PacienteResponseDTO getPacienteById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado " + id));

        return mapToPacienteResponse(paciente);
    }

    private PacienteResponseDTO mapToPacienteResponse(Paciente paciente) {
        PacienteResponseDTO response = new PacienteResponseDTO();
        response.setId(paciente.getId());
        response.setNome(paciente.getNome());
        response.setDataNascimento(paciente.getDataNascimento());
        response.setCpf(paciente.getCpf());
        response.setTelefone(paciente.getTelefone());
        response.setEmail(paciente.getEmail());

        return response;
    }

    public List<PacienteResponseDTO> getAllPacientes() {
        return pacienteRepository.findAll().stream()
                .map(this::mapToPacienteResponse)
                .toList();
    }
}
