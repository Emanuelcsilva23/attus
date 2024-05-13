package com.java.attus.controllers;

import com.java.attus.dtos.PessoaDTO;
import com.java.attus.enums.MensagemEnum;
import com.java.attus.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody PessoaDTO pessoaDTO) {
        try {
            pessoaService.salvar(pessoaDTO);
            return ResponseEntity.ok(MensagemEnum.REGISTRO_INSERIDO_COM_SUCESSO.getMensagem());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/atualizar")
    public ResponseEntity atualizar(@RequestBody PessoaDTO pessoaDTO) {
        try {
            pessoaService.atualizar(pessoaDTO);
            return ResponseEntity.ok(MensagemEnum.REGISTRO_ALTERADO_COM_SUCESSO.getMensagem());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity listar() {
        try {
            return ResponseEntity.ok(pessoaService.listarTodos());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/buscar/id/{id}")
    public ResponseEntity buscar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(pessoaService.buscarPorId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}







