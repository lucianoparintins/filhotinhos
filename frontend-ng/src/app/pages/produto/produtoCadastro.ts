import { Component } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { FluidModule } from 'primeng/fluid';


@Component({
    selector: 'app-produto',
    standalone: true,
    imports: [InputTextModule,  FormsModule, ButtonModule, FluidModule],
    template: `
    <p-fluid>
        <div class="card flex flex-col gap-4">
            <div class="font-semibold text-xl">Produto</div>
            <div class="flex flex-col gap-2">
                <label for="nome">Nome</label>
                <input pInputText id="nome" type="text" [(ngModel)]="nome"/>
            </div>
            <div class="flex flex-col gap-2">
                <label for="descricao">Descricao</label>
                <input pInputText id="descricao" type="text" [(ngModel)]="descricao"/>
            </div>
            <div class="flex flex-col gap-2">
                <label for="quantidade">Quantidade</label>
                <input pInputText id="quantidade" type="number" pattern="\d*" [(ngModel)]="quantidade"/>
            </div>
            <div class="flex flex-col gap-2">
                <label for="preco">Preço</label>
                <input pInputText id="preco" type="text" [(ngModel)]="preco"/>
            </div>
            <p-button label="Cadastrar" [fluid]="false" (click)="salvarProduto()"></p-button>
        </div>
    </p-fluid>
    `
})
export class ProdutoCadastro  {
    nome: string = '';
    descricao: string = '';
    quantidade: string = '';
    preco: string = '';

    constructor() { }
    
    salvarProduto(): void {
        if (this.isPrecoValido(this.preco)) {
            console.log('Nome:', this.nome);
            console.log('Descrição:', this.descricao);
            console.log('Quantidade:', this.quantidade);
            console.log('Preço:', this.preco);
        } else {
            console.error('Preço inválido. Deve ter até 2 casas decimais.');
        }
    }

    isPrecoValido(preco: string): boolean {
        const regex = /^\d+(\,\d{1,2})?$/;
        return regex.test(preco);
    }

}