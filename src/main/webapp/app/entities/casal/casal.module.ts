import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LareiraAppHomoSharedModule } from 'app/shared/shared.module';
import { CasalComponent } from './casal.component';
import { CasalDetailComponent } from './casal-detail.component';
import { CasalUpdateComponent } from './casal-update.component';
import { CasalDeleteDialogComponent } from './casal-delete-dialog.component';
import { casalRoute } from './casal.route';

@NgModule({
  imports: [LareiraAppHomoSharedModule, RouterModule.forChild(casalRoute)],
  declarations: [CasalComponent, CasalDetailComponent, CasalUpdateComponent, CasalDeleteDialogComponent],
  entryComponents: [CasalDeleteDialogComponent]
})
export class LareiraAppHomoCasalModule {}
