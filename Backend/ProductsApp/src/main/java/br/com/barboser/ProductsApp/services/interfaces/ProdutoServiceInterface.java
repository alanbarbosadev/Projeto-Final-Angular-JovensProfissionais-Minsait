package br.com.barboser.ProductsApp.services.interfaces;

import br.com.barboser.ProductsApp.models.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoServiceInterface {
    List<Produto> getAll();
    Optional<Produto> getById(Long id);
    Produto update(Produto produto);
    void delete(Long id);
    Produto save(Produto produto);
}
