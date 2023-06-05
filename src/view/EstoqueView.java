package view;
import model.Produto;
import controller.EstoqueController;
import java.util.ArrayList;
import java.util.List;
public class EstoqueView {
	public void mostrarProdutos(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto no estoque.");
        } else {
            System.out.println("Produtos no estoque:");
            for (Produto produto : produtos) {
                System.out.println("Nome: " + produto.getNome() + ", Quantidade: " + produto.getQuantidade());
            }
        }
    }
}

