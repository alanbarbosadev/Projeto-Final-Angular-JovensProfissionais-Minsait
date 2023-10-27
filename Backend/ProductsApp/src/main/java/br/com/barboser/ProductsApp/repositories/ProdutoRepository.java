package br.com.barboser.ProductsApp.repositories;

import br.com.barboser.ProductsApp.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
