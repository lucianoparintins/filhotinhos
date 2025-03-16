import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { FluidModule } from 'primeng/fluid';
import { MessageService } from 'primeng/api';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';

@Component({
    selector: 'app-produto-cadastro',
    imports: [CommonModule, InputTextModule, FormsModule, ButtonModule, FluidModule, MessageModule, ToastModule],
    providers: [MessageService],
    template: `
    <p-toast></p-toast>
    <p-fluid>
        <div class="card flex flex-col gap-4">
            <div class="font-semibold text-xl">Produto</div>
            <div class="flex flex-col gap-2">
                <label for="nome">Nome</label>
                <input pInputText id="nome" type="text" [(ngModel)]="nome"/>
            </div>
            <div class="flex flex-col gap-2">
                <label for="descricao">Descrição</label>
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

    constructor(private messageService: MessageService) { }
    
    salvarProduto(): void {

            console.log('Nome:', this.nome);
            console.log('Descrição:', this.descricao);
            console.log('Quantidade:', this.quantidade);
            console.log('Preço:', this.preco);

            if (this.validarFormulario()) {
                this.exibirMensagemSucesso();
                this.limparFormulario();
            }
    }

    exibirMensagemSucesso(): void {
        this.messageService.add({
            severity: 'success',
            summary: 'Produto cadastrado',
            detail: `Produto ${this.nome} cadastrado com sucesso!`
        });
    }

    validarFormulario(): boolean {
        const camposInvalidos = this.validarCamposVazios();
        if (camposInvalidos.length > 0) {
            this.messageService.add({
                severity: 'error',
                summary: 'Erro ao cadastrar',
                detail: `Preencha os campos: ${camposInvalidos.join(', ')}`
            });
            return false;
        }

        if (!this.isPrecoValido(this.preco)) {
            this.messageService.add({
                severity: 'error',
                summary: 'Erro ao cadastrar',
                detail: 'Preço inválido. Deve ter até 2 casas decimais.'
            });
            return false;
        }
        return true;
    }

    validarCamposVazios(): string[] {
        const camposInvalidos: string[] = [];
        if (!this.nome) camposInvalidos.push('Nome');
        if (!this.descricao) camposInvalidos.push('Descrição');
        if (!this.quantidade) camposInvalidos.push('Quantidade');
        if (!this.preco) camposInvalidos.push('Preço');
        return camposInvalidos;
    }

    isPrecoValido(preco: string): boolean {
        const regex = /^\d+(\,\d{1,2})?$/;
        return regex.test(preco);
    }

    limparFormulario(): void {
        this.nome = '';
        this.descricao = '';
        this.quantidade = '';
        this.preco = '';
        // Forçar a atualização do modelo
        setTimeout(() => {
            this.nome = '';
            this.descricao = '';
            this.quantidade = '';
            this.preco = '';
        }, 0);
    }

}