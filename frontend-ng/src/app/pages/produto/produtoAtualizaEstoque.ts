import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastModule } from "primeng/toast";
import { MessageService } from "primeng/api";
import { DataViewModule } from 'primeng/dataview';
import { Button, ButtonModule } from 'primeng/button';

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
                                                <div
                                                class="flex flex-col sm:flex-row sm:items-center p-6 gap-4"
                                                [ngClass]="{ 'border-t border-surface-200 dark:border-surface-700': !first }"
                                                >
                                                
                                                        <div class="flex flex-col md:flex-row justify-between md:items-center flex-1 gap-6">

                                                                <div class="flex flex-row md:flex-col justify-between items-start gap-2">
                                                                                <div>
                                                                                        <div class="text-lg font-medium text-surface-900 dark:text-surface-0 mt-2">{{ item.nome }}</div>
                                                                                </div>
                                                                        <div class="bg-surface-100 dark:bg-surface-700 p-1" style="border-radius: 30px">
                                                                                <div
                                                                                class="bg-surface-0 dark:bg-surface-900 flex items-center gap-2 justify-center py-1 px-2"
                                                                                style="border-radius: 30px; box-shadow: 0px 1px 2px 0px rgba(0, 0, 0, 0.04), 0px 1px 2px 0px rgba(0, 0, 0, 0.06)"
                                                                                >
                                                                                        <span class="text-surface-900 dark:text-surface-0 font-medium text-sm">{{ item.quantidade}}</span>
                                                                                        <i class="pi pi-star-fill text-yellow-500"></i>
                                                                                </div>
                                                                        </div>
                                                                </div>

                                                                <div class="flex flex-col md:items-end gap-8">
                                                                        <span class="text-xl font-semibold text-surface-900 dark:text-surface-0">{{ '$' + item.quantidade }}</span>
                                                                        <div class="flex flex-row-reverse md:flex-row gap-2">
                                                                                <p-button icon="pi pi-heart" [outlined]="true" />
                                                                        </div>
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
    public produtos: Produto[] = [];
    public titulo: string = 'Atualizar Estoque';

    constructor(private messageService: MessageService) {}

    ngOnInit() {  
        setTimeout(() => {
            this.produtos = [
                { id: 1, nome: 'Produto 1', quantidade: 10 },
                { id: 2, nome: 'Produto 2', quantidade: 20 },
                { id: 3, nome: 'Produto 3', quantidade: 30 },
                { id: 4, nome: 'Produto 4', quantidade: 40 },
                { id: 5, nome: 'Produto 5', quantidade: 50 },
            ];
            console.log('Produtos carregados:', this.produtos);
        }, 1000); // Simula carregamento assíncrono
    }
}

export interface Produto {
    id: number;
    nome: string;
    quantidade: number;
}
