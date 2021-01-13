package anuncio.domain.enums;

public enum StatusAnuncioEnum {
    ATIVO (1, "Ativo"),
    INATIVO (2, "Inativo"),
    EXPIRADO (3, "Expirado"),
    FINALIZADO (4, "Finalizado");

    private int cod;
    private String descricao;

    StatusAnuncioEnum(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod()
    {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

}
