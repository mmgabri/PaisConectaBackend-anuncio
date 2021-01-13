package anuncio.domain.enums;

public enum CategoriaEnum {
    UNIFORME(1, "Uniforme"),
    LIVRO(2, "Livro"),
    MATERIAL_ESCOLAR(3, "Material escolar");

    private int cod;
    private String descricao;

    CategoriaEnum(int cod, String descricao) {
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
