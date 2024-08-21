package ifsc.poo.lavacao.model.domain;

import java.io.Serializable;

public class Categoria implements Serializable {

    private int cdCategoria;
    private String descricao;
    
    public Categoria(){
    }

    public Categoria(int cdCategoria, String descricao) {
        this.cdCategoria = cdCategoria;
        this.descricao = descricao;
    }

    public int getCdCategoria() {
        return cdCategoria;
    }

    public void setCdCategoria(int cdCategoria) {
        this.cdCategoria = cdCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return this.descricao;
    }
    
}
