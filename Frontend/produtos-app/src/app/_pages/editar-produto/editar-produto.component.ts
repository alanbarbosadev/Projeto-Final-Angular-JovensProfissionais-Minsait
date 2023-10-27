import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Produto } from 'src/app/_models/produto.model';
import { ProdutoService } from 'src/app/_services/produto.service';

@Component({
  selector: 'app-editar-produto',
  templateUrl: './editar-produto.component.html',
  styleUrls: ['./editar-produto.component.scss'],
})
export class EditarProdutoComponent implements OnInit {
  editarProdutoForm!: FormGroup;
  id: string = this.route.snapshot.paramMap.get('id')!;

  constructor(
    private produtoService: ProdutoService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
    this.editarProdutoForm = this.formBuilder.group({
      codigoBarras: [null, [Validators.required]],
      nome: [null, [Validators.required]],
      preco: [null, [Validators.required, Validators.min(0.01)]],
    });
  }

  ngOnInit(): void {
    this.carregarProduto();
  }

  carregarProduto(): void {
    this.produtoService.lerPorId(this.id).subscribe((response: Produto) => {
      this.editarProdutoForm.patchValue(response);
    });
  }

  editarProduto(): void {
    const dadosProduto = this.editarProdutoForm.value;
    const produto: Produto = {
      id: Number(this.id),
      codigoBarras: dadosProduto.codigoBarras,
      nome: dadosProduto.nome,
      preco: dadosProduto.preco,
    };
    this.produtoService.editarProduto(produto).subscribe(() => {
      this.router.navigate(['produtos/listagem']);
    });
  }

  cancelar(): void {
    this.router.navigate(['produtos/listagem']);
  }
}
