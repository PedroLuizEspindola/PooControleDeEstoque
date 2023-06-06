package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import view.EstoqueView;
import model.Produto;
public class EstoqueController {
    
    private EstoqueView view;
    private String ARQUIVO_ESTOQUE;
    
    //construtores do estoquecontroller puxa um nome para ser criado um novo arquivo 
    public EstoqueController(String ARQUIVO_ESTOQUE) {
        this.ARQUIVO_ESTOQUE = ARQUIVO_ESTOQUE;
    } 
    
    
    public EstoqueController(EstoqueView view) {        
        this.view = view;
    }
    
    public void adicionarProduto(String nomeProduto, int quantidade) {
        Produto produto = new Produto(nomeProduto, quantidade);
        addProduto(nomeProduto,quantidade);
    }
    //metodo que joga os produtos lidos dentro do arquivo em outro metodo para que escreva uma lista
    public void displayProducts() {
        List<Produto> produtos = getProdutos();
        view.mostrarProdutos(produtos);
    }
    
    //exibi como o arquivo está escrito
    public void exibirArquivoEstoque() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ESTOQUE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo do estoque: " + e.getMessage());
        }
    }
    
    // Método para obter a lista de produtos do estoque
    public List<Produto> getProdutos(){
        List<Produto> produtos = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ESTOQUE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String nome = line;
                int quantidade = Integer.parseInt(reader.readLine());	               
                Produto produto = new Produto(nome, quantidade);
                produtos.add(produto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return produtos;
    }
    //método de entrada de produtos 
    public void entradaProduto(String produtoNome, int quantidade) {
        List<Produto> produtos = getProdutos();
        boolean found = false;
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getNome().equals(produtoNome)) {
                int updatedQuantidade = produto.getQuantidade() + quantidade;
                produto = new Produto(produtoNome, updatedQuantidade);
                produtos.set(i, produto);
                found = true;
                break;
            }
       
            
        }
        if(!found) {
        	System.out.println("Produto inexistente, adicione o produto antes de fazer alteracoes");		
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ESTOQUE))) {
            for (Produto produto : produtos) {
                writer.write(produto.getNome());
                writer.newLine();
                writer.write(String.valueOf(produto.getQuantidade()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //método de saida de produtos
    public void saidaProduto(String produtoNome, int quantidade) {
        List<Produto> produtos = getProdutos();
        boolean found = false;
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getNome().equals(produtoNome)) {
                int updatedQuantidade = produto.getQuantidade() - quantidade;
                produto = new Produto(produtoNome, updatedQuantidade);
                produtos.set(i, produto);
                found = true;
                break;
            }
       
            
        }
        if(!found) {
        	System.out.println("Produto inexistente, adicione o produto antes de fazer alteracoes");		
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ESTOQUE))) {
            for (Produto produto : produtos) {
                writer.write(produto.getNome());
                writer.newLine();
                writer.write(String.valueOf(produto.getQuantidade()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //metodo que adicionam produtos dentro do arquivo/estoque
    public void addProduto(String nomeProduto, int quantidade) {
        try{ 
        	BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ESTOQUE));
            StringBuilder sb = new StringBuilder();
            boolean encontrado = false;
            String linha;

            while ((linha = br.readLine()) != null) {
                sb.append(linha);
                sb.append(System.lineSeparator());

                if (linha.equals(nomeProduto)) {
                    encontrado = true;
                    linha = br.readLine(); // Ler a próxima linha (quantidade)
                    int quantidadeExistente = Integer.parseInt(linha);
                    int novaQuantidade = quantidadeExistente + quantidade;
                    sb.append(novaQuantidade);
                    sb.append(System.lineSeparator());
                }
            }

            if (!encontrado) {
                sb.append(nomeProduto);
                sb.append(System.lineSeparator());
                sb.append(quantidade);
                sb.append(System.lineSeparator());
            }

            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_ESTOQUE));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

