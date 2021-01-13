package anuncio.domain.enums;

public enum TipoAnuncioEnum {
    DOACAO (1 ,"Doação"),
    VENDA (2, "Venda");

    private int cod;
    private String descricao;

    TipoAnuncioEnum(int cod, String descricao) {
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
