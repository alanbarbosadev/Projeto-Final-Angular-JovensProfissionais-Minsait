package br.com.barboser.ProductsApp.resources;

import br.com.barboser.ProductsApp.models.Produto;
import br.com.barboser.ProductsApp.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {
    private ProdutoService produtoService;

    @Autowired
    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos(){
        List<Produto> produtos = produtoService.getAll();
        if(produtos == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> getById(@PathVariable Long id){
        Optional<Produto> produto = produtoService.getById(id);
        if(produto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        Produto newProduto = produtoService.save(produto);
        if(newProduto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(newProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@RequestBody Produto produto){
        Produto newProduto = produtoService.update(produto);
        if(newProduto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(newProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
