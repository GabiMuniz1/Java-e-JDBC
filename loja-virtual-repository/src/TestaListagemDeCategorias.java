import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;
import dao.CategoriaDAO;
import dao.ProdutoDAO;


public class TestaListagemDeCategorias {

	public static void main(String[] args) throws SQLException {

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {

			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listaDeCategorias = categoriaDAO.listarComProdutos();
			listaDeCategorias.stream().forEach(ct -> {
				System.out.println(ct.getNome());
				try {
					for(Produto produto: ct.getProdutos()) {
						System.out.println(ct.getNome()+ " - " + produto.getNome());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}

	}

}
