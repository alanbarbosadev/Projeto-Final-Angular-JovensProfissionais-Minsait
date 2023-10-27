import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Produto } from 'src/app/_models/produto.model';
import { ProdutoService } from 'src/app/_services/produto.service';

@Component({
  selector: 'app-listagem-produtos',
  templateUrl: './listagem-produtos.component.html',
  styleUrls: ['./listagem-produtos.component.scss'],
})
export class ListagemProdutosComponent implements OnInit {
  produtos: Produto[] = [];
  produtoSelecionado!: Produto;
  confirmaDelecaoRef!: BsModalRef;

  constructor(
    private produtoService: ProdutoService,
    private modalService: BsModalService
  ) {}

  ngOnInit(): void {
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.produtoService.lerTodos().subscribe((response: Produto[]) => {
      this.produtos = response;
    });
  }

  deletarProduto(produto: Produto, modalDelecao: TemplateRef<any>): void {
    this.produtoSelecionado = produto;
    this.confirmaDelecaoRef = this.modalService.show(modalDelecao);
  }

  confirmarDelecao(): void {
    this.produtoService
      .deletarProduto(this.produtoSelecionado.id?.toString()!)
      .subscribe(() => {
        this.confirmaDelecaoRef.hide();
        this.ngOnInit();
      });
  }
}
