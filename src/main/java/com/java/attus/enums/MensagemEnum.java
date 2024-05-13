package com.java.attus.enums;

public enum MensagemEnum {
    REGISTRO_INSERIDO_COM_SUCESSO("Registro inserido com sucesso."),
    REGISTRO_ALTERADO_COM_SUCESSO("Registro alterado com sucesso.");

    private final String mensagem;

    MensagemEnum(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
