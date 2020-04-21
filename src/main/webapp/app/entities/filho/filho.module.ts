import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LareiraAppHomoSharedModule } from 'app/shared/shared.module';
import { FilhoComponent } from './filho.component';
import { FilhoDetailComponent } from './filho-detail.component';
import { FilhoUpdateComponent } from './filho-update.component';
import { FilhoDeleteDialogComponent } from './filho-delete-dialog.component';
import { filhoRoute } from './filho.route';

@NgModule({
  imports: [LareiraAppHomoSharedModule, RouterModule.forChild(filhoRoute)],
  declarations: [FilhoComponent, FilhoDetailComponent, FilhoUpdateComponent, FilhoDeleteDialogComponent],
  entryComponents: [FilhoDeleteDialogComponent]
})
export class LareiraAppHomoFilhoModule {}
