import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LareiraAppHomoSharedModule } from 'app/shared/shared.module';
import { LareiraComponent } from './lareira.component';
import { LareiraDetailComponent } from './lareira-detail.component';
import { LareiraUpdateComponent } from './lareira-update.component';
import { LareiraDeleteDialogComponent } from './lareira-delete-dialog.component';
import { lareiraRoute } from './lareira.route';

@NgModule({
  imports: [LareiraAppHomoSharedModule, RouterModule.forChild(lareiraRoute)],
  declarations: [LareiraComponent, LareiraDetailComponent, LareiraUpdateComponent, LareiraDeleteDialogComponent],
  entryComponents: [LareiraDeleteDialogComponent]
})
export class LareiraAppHomoLareiraModule {}
