import { Routes } from '@angular/router';
import { ProdutoCadastro } from './produtoCadastro';
import { ProdutoAtualizaEstoque } from './produtoAtualizaEstoque';

export default [
    { path: 'cadastro', component: ProdutoCadastro },
    { path: 'atualizar-estoque', component: ProdutoAtualizaEstoque },
] as Routes;