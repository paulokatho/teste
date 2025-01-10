package com.teste.pedidos.enums;

public enum EmailUtilsEnum {

    OWNERREF("Pedidos Simplificados LDA"),
    EMAILFROM("pkatho@gmail.com"),
    SUBJECT("Conclusão do seu pedido Simplificado"),
	TEXT("Olá, somos da Pedidos Simplicicados. Parabéns, seu pedido foi concluído com sucesso!");

    private final String value;

    EmailUtilsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
