package com.example.demo.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteRequestDTO;
import com.example.demo.dto.ClienteResponseDTO;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.exception.CredenciaisInvalidasException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponseDTO> listar(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto){
        if (repository.existsByEmail(dto.email())) {
            throw new BusinessRuleException("Já existe um cliente cadastrado com este e-mail");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEndereco(dto.endereco());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        cliente.setSenha(passwordEncoder.encode(dto.senha()));

        return toResponse(repository.save(cliente));
    }

    public ClienteResponseDTO autenticar(LoginRequestDTO dto) {
        Cliente cliente = repository.findByEmail(dto.email())
            .orElseThrow(() -> new CredenciaisInvalidasException("E-mail ou senha inválidos"));

        if (!passwordEncoder.matches(dto.senha(), cliente.getSenha())) {
            throw new CredenciaisInvalidasException("E-mail ou senha inválidos");
        }

        return toResponse(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
    }

    private ClienteResponseDTO toResponse(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getEndereco(),
            cliente.getTelefone(),
            cliente.getEmail()
        );
    }
}
