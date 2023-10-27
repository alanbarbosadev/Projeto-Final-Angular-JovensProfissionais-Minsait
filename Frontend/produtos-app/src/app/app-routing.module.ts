import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './_pages/inicio/inicio.component';
import { ListagemProdutosComponent } from './_pages/listagem-produtos/listagem-produtos.component';
import { CadastroProdutoComponent } from './_pages/cadastro-produto/cadastro-produto.component';
import { EditarProdutoComponent } from './_pages/editar-produto/editar-produto.component';

const routes: Routes = [
  {
    path: '',
    component: InicioComponent,
  },
  {
    path: 'produtos/listagem',
    component: ListagemProdutosComponent,
  },
  {
    path: 'produtos/cadastro',
    component: CadastroProdutoComponent,
  },
  {
    path: 'produtos/editar/:id',
    component: EditarProdutoComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
