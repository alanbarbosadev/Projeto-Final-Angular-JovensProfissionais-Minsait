import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Produto } from 'src/app/_models/produto.model';
import { ProdutoService } from 'src/app/_services/produto.service';

@Component({
  selector: 'app-cadastro-produto',
  templateUrl: './cadastro-produto.component.html',
  styleUrls: ['./cadastro-produto.component.scss'],
})
export class CadastroProdutoComponent {
  cadastroProdutoForm!: FormGroup;

  constructor(
    private router: Router,
    private produtoService: ProdutoService,
    private formBuilder: FormBuilder
  ) {
    this.cadastroProdutoForm = formBuilder.group({
      codigoBarras: [null, [Validators.required]],
      nome: [null, [Validators.required]],
      preco: [null, [Validators.required, Validators.min(0.01)]],
    });
  }

  cadastrarProduto(): void {
    const dadosProduto = this.cadastroProdutoForm.value;
    const produto: Produto = {
      codigoBarras: dadosProduto.codigoBarras,
      nome: dadosProduto.nome,
      preco: dadosProduto.preco,
    };
    this.produtoService.cadastrarProduto(produto).subscribe(() => {
      this.router.navigate(['produtos/listagem']);
    });
  }

  cancelar(): void {
    this.router.navigate(['produtos/listagem']);
  }
}
