package com.java.attus.services;
import com.java.attus.dtos.EnderecoDTO;
import com.java.attus.dtos.PessoaDTO;
import com.java.attus.entities.Endereco;
import com.java.attus.entities.Pessoa;
import com.java.attus.repositories.EnderecoRepository;
import com.java.attus.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    private PessoaDTO convertPessoaToPessoaDTO(Pessoa pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNomeCompleto(pessoa.getNomeCompleto());
        pessoaDTO.setDataNascimento(pessoa.getDataNascimento());
        pessoaDTO.setId(pessoaDTO.getId());

        if (pessoa.getEnderecos() != null && !pessoa.getEnderecos().isEmpty()) {
            List<EnderecoDTO> enderecoDTOs = pessoa.getEnderecos().stream().map(endereco -> {
                EnderecoDTO enderecoDTO = new EnderecoDTO();
                enderecoDTO.setId(endereco.getId());
                enderecoDTO.setLogradouro(endereco.getLogradouro());
                enderecoDTO.setCep(endereco.getCep());
                enderecoDTO.setNumero(endereco.getNumero());
                enderecoDTO.setCidade(endereco.getCidade());
                enderecoDTO.setEstado(endereco.getEstado());
                enderecoDTO.setPrincipal(endereco.getPrincipal());
                return enderecoDTO;
            }).collect(Collectors.toList());
            pessoaDTO.setEnderecos(enderecoDTOs);
        }

        return pessoaDTO;
    }



    public void salvar(PessoaDTO pessoaDTO) throws Exception{
        Pessoa pessoa = new Pessoa();
        pessoa.setNomeCompleto(pessoaDTO.getNomeCompleto());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        if (pessoaDTO.getEnderecos() != null && !pessoaDTO.getEnderecos().isEmpty()) {
            for (EnderecoDTO enderecoDTO : pessoaDTO.getEnderecos()) {
                Endereco endereco = new Endereco();
                endereco.setLogradouro(enderecoDTO.getLogradouro());
                endereco.setCep(enderecoDTO.getCep());
                endereco.setNumero(enderecoDTO.getNumero());
                endereco.setCidade(enderecoDTO.getCidade());
                endereco.setEstado(enderecoDTO.getEstado());
                endereco.setPrincipal(enderecoDTO.getPrincipal());
                enderecoRepository.save(endereco);
                pessoa.adicionarEndereco(endereco);
            }
        }
        pessoaRepository.save(pessoa);
    }

    public void atualizar(PessoaDTO pessoaDTO) throws Exception{
        Pessoa pessoa = pessoaRepository.getOne(pessoaDTO.getId());
        pessoa.setNomeCompleto(pessoaDTO.getNomeCompleto());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        if (pessoaDTO.getEnderecos() != null && !pessoaDTO.getEnderecos().isEmpty()) {
            for (EnderecoDTO enderecoDTO : pessoaDTO.getEnderecos()) {
                Endereco endereco = enderecoRepository.getOne(enderecoDTO.getId());
                endereco.setLogradouro(enderecoDTO.getLogradouro());
                endereco.setCep(enderecoDTO.getCep());
                endereco.setNumero(enderecoDTO.getNumero());
                endereco.setCidade(enderecoDTO.getCidade());
                endereco.setEstado(enderecoDTO.getEstado());
                endereco.setPrincipal(enderecoDTO.getPrincipal());
                enderecoRepository.save(endereco);
                pessoa.adicionarEndereco(endereco);
            }
        }
        pessoaRepository.save(pessoa);
    }

    public Optional<PessoaDTO> buscarPorId(Integer id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return Optional.of(convertPessoaToPessoaDTO(pessoa.get()));
        } else {
            return Optional.empty();
        }
    }

    public List<PessoaDTO> listarTodos() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(this::convertPessoaToPessoaDTO)
                .collect(Collectors.toList());
    }


}







