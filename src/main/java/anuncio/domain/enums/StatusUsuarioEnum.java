package anuncio.domain.enums;

public enum StatusUsuarioEnum {
    ATIVO(1, "Ativo"),
    INATIVO(0, "Inativo");


    private int cod;
    private String descricao;

    StatusUsuarioEnum(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }
}
