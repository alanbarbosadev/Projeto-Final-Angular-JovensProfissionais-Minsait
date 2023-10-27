package br.com.barboser.ProductsApp.services;

import br.com.barboser.ProductsApp.exceptions.ResourceNotFoundException;
import br.com.barboser.ProductsApp.models.Estoque;
import br.com.barboser.ProductsApp.repositories.EstoqueRepository;
import br.com.barboser.ProductsApp.services.interfaces.EstoqueServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService implements EstoqueServiceInterface {
    private EstoqueRepository estoqueRepository;

    @Autowired
    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }
    @Override
    public List<Estoque> getAll() {
        return estoqueRepository.findAll();
    }

    @Override
    public Optional<Estoque> getById(Long id) {
        return estoqueRepository.findById(id);
    }

    @Override
    public Estoque update(Estoque estoque) {
        Optional<Estoque> upEstoque = estoqueRepository.findById(estoque.getId());

        if(upEstoque.isPresent()) {
            Estoque newEstoque = upEstoque.get();
            newEstoque.setProduto(estoque.getProduto());
            newEstoque.setQuantidade(estoque.getQuantidade());
            return estoqueRepository.save(newEstoque);
        }
        return estoque;
    }

    @Override
    public void delete(Long id) {
        estoqueRepository.deleteById(id);
    }

    @Override
    public Estoque save(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    @Override
    public Estoque addQuantidade(Long id, int quantidade) {
        return estoqueRepository.findById(id)
                .map(estoque -> {
                    estoque.setQuantidade(estoque.getQuantidade() + quantidade);
                    return estoqueRepository.save(estoque);
                }).orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado no ID: " + id));
    }

    @Override
    public Estoque deleteQuantidade(Long id, int quantidade) {
        return estoqueRepository.findById(id)
                .map(estoque -> {
                    estoque.setQuantidade(estoque.getQuantidade() - quantidade);
                    return estoqueRepository.save(estoque);
                }).orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado no ID: " + id));
    }
}
