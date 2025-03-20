import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  private apiUrl =  `${environment.apiUrl}/api/produtos`;

  constructor(private http: HttpClient) { }

  cadastrarProduto(produto: any): Observable<any> {
    return this.http.post(this.apiUrl, produto);
  }

  listarProduto(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

}