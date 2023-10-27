import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produto } from '../_models/produto.model';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  apiUrl: string = 'http://localhost:8080/api/produtos';

  constructor(private http: HttpClient) {}

  lerTodos(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.apiUrl);
  }

  lerPorId(id: string): Observable<Produto> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Produto>(url);
  }

  cadastrarProduto(produto: Produto): Observable<Produto> {
    return this.http.post<Produto>(this.apiUrl, produto);
  }

  editarProduto(produto: Produto): Observable<Produto> {
    const url = `${this.apiUrl}/${produto.id}`;
    return this.http.put<Produto>(url, produto);
  }

  deletarProduto(id: string): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
