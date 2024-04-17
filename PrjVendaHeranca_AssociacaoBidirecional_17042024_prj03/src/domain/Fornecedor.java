package domain;

/**
 *
 * @author Professor
 */
public abstract class Fornecedor 
        extends Object
        implements IFornecedor {
    protected int id;
    protected String nome;
    protected String email;
    protected String fone;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    
    
    public Fornecedor() {
    }

    public Fornecedor(int id, String nome, String email, String fone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", fone=" + fone + '}';
    }
    
    @Override
    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome.......: ").append(nome).append("\n");
        sb.append("Email......: ").append(email).append("\n");
        sb.append("Fone.......: ").append(fone).append("\n");
        return sb.toString();
    }
    
    
    
}
