import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastModule } from "primeng/toast";
import { MessageService } from "primeng/api";
import { DataViewModule } from 'primeng/dataview';
import { ButtonModule } from 'primeng/button';
import { ProdutoService } from './produto.service';

@Component({
    selector: "app-produto-atualiza-estoque",
    standalone: true,
    imports: [CommonModule, DataViewModule, ToastModule, ButtonModule],
    providers: [MessageService],
    template: ` 
        <p-toast></p-toast>
        <div class="card p-4">
            <h2 class="font-semibold text-xl mb-4">{{ titulo }}</h2>

                <p-dataview #dv [value]="produtos">
                        <ng-template #list let-items>
                                <div class="grid grid-cols-12 gap-4 grid-nogutter">
                                        <div class="col-span-12" *ngFor="let item of items; let first = first" class="col-span-12">
                                                <div class="flex flex-col sm:flex-row sm:items-center p-6 gap-4" 
                                                        [ngClass]="{ 'border-t border-surface-200 dark:border-surface-700': !first }" >
                                                
                                                        <div class="flex flex-col md:flex-row justify-between md:items-center flex-1 gap-6">

                                                                <div class="flex flex-row md:flex-col justify-between items-start gap-2">
                                                                        <div class="text-lg font-medium text-surface-900 dark:text-surface-0 mt-2">{{ item.nome }}</div>
                                                                </div>

                                                                <div class="flex flex-col md:items-end gap-8">
                                                                     <span class="text-xl font-semibold text-surface-900 dark:text-surface-0">{{ item.quantidade }}</span>                                                                                                                                          
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                        </ng-template>
                </p-dataview>

            <div *ngIf="produtos.length === 0" class="text-center text-gray-500 mt-4">
                Nenhum produto encontrado.
            </div>
        </div>
    `,
})
export class ProdutoAtualizaEstoque { 
    public produtos: any[] = [];
    public titulo: string = 'Atualizar Estoque';

    constructor(private messageService: MessageService, private produtodService: ProdutoService) {}

    ngOnInit() {  
        setTimeout(() => {
            this.produtodService.listarProduto().subscribe((produtos: any[]) => {
                this.produtos = produtos;
                console.log('Produtos carregados:', this.produtos);
            });
            console.log('Produtos carregados:', this.produtos);
        }, 1000); // Simula carregamento assíncrono
    }
}

