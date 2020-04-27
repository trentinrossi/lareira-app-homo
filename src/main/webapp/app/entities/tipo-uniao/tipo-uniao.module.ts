import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LareiraAppHomoSharedModule } from 'app/shared/shared.module';
import { TipoUniaoComponent } from './tipo-uniao.component';
import { TipoUniaoDetailComponent } from './tipo-uniao-detail.component';
import { TipoUniaoUpdateComponent } from './tipo-uniao-update.component';
import { TipoUniaoDeleteDialogComponent } from './tipo-uniao-delete-dialog.component';
import { tipoUniaoRoute } from './tipo-uniao.route';

@NgModule({
  imports: [LareiraAppHomoSharedModule, RouterModule.forChild(tipoUniaoRoute)],
  declarations: [TipoUniaoComponent, TipoUniaoDetailComponent, TipoUniaoUpdateComponent, TipoUniaoDeleteDialogComponent],
  entryComponents: [TipoUniaoDeleteDialogComponent]
})
export class LareiraAppHomoTipoUniaoModule {}
