package br.com.barboser.ProductsApp.services;

import br.com.barboser.ProductsApp.models.Produto;
import br.com.barboser.ProductsApp.repositories.ProdutoRepository;
import br.com.barboser.ProductsApp.services.interfaces.ProdutoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements ProdutoServiceInterface {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    @Override
    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Optional<Produto> getById(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public Produto update(Produto produto) {
        Optional<Produto> upProduto = produtoRepository.findById(produto.getId());

        if(upProduto.isPresent()) {
            Produto newProduto = upProduto.get();
            newProduto.setCodigoBarras(produto.getCodigoBarras());
            newProduto.setNome(produto.getNome());
            newProduto.setPreco(produto.getPreco());
            return produtoRepository.save(newProduto);
        }
        return produto;
    }

    @Override
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }
}
