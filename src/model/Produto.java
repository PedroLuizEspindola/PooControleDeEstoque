package model;

//Classe Product para representar um produto
public class Produto {
 private String nome;
 private int quantidade;
 
 public Produto(String nome, int quantidade) {
     this.nome = nome;
     this.quantidade = quantidade;
 }
 
 public String getNome() {
     return nome;
 }
 
 public int getQuantidade() {
     return quantidade;
 }
}
