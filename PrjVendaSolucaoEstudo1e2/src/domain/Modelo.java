/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public class Modelo  {
    private int id;
    private String descricao;
    
    private Marca marca;//associação de classe
    
    private ECategoria categoria;
    
    private Motor motor;// = new Motor();
    
    private void createMotor() {
        motor = new Motor();
    }

    public Motor getMotor() {
        return motor;
    }

    public ECategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ECategoria categoria) {
        this.categoria = categoria;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo() {
        createMotor();
    }

    public Modelo(int id, String descricao, Marca marca) {
        this(id, descricao);
//        this.id = id;
//        this.descricao = descricao;
        this.marca = marca;
    }

    public Modelo(int id, String descricao) {
        this();
        this.id = id;
        this.descricao = descricao;
    }

    public Modelo(int id, String descricao, Marca marca, ECategoria categoria) {
        this(id, descricao, marca);
//        this.id = id;
//        this.descricao = descricao;
//        this.marca = marca;
        this.categoria = categoria;
    }

    public Modelo(int id, String descricao, Marca marca, ECategoria categoria, Motor motor) {
        this(id, descricao, marca, categoria);
//        this.id = id;
//        this.descricao = descricao;
//        this.marca = marca;
//        this.categoria = categoria;
        this.motor = motor;
    }
    
    

    @Override
    public String toString() {
        return "Modelo{" + "id=" + id + ", descricao=" + descricao + ", marca=" + marca + ", categoria=" + categoria + '}';
    }
    
       
}
