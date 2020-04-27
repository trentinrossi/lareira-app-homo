import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'lareira',
        loadChildren: () => import('./lareira/lareira.module').then(m => m.LareiraAppHomoLareiraModule)
      },
      {
        path: 'casal',
        loadChildren: () => import('./casal/casal.module').then(m => m.LareiraAppHomoCasalModule)
      },
      {
        path: 'filho',
        loadChildren: () => import('./filho/filho.module').then(m => m.LareiraAppHomoFilhoModule)
      },
      {
        path: 'tipo-uniao',
        loadChildren: () => import('./tipo-uniao/tipo-uniao.module').then(m => m.LareiraAppHomoTipoUniaoModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class LareiraAppHomoEntityModule {}
