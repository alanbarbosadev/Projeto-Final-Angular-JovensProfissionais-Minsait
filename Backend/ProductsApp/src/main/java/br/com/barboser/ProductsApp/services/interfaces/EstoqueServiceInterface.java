package br.com.barboser.ProductsApp.services.interfaces;

import br.com.barboser.ProductsApp.models.Estoque;

import java.util.List;
import java.util.Optional;

public interface EstoqueServiceInterface {
    List<Estoque> getAll();
    Optional<Estoque> getById(Long id);
    Estoque update(Estoque estoque);
    void delete(Long id);
    Estoque save(Estoque estoque);
    Estoque addQuantidade(Long id, int quantidade);
    Estoque deleteQuantidade(Long id, int quantidade);

}
