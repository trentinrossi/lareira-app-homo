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
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class LareiraAppHomoEntityModule {}
