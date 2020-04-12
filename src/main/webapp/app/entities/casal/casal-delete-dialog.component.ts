import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICasal } from 'app/shared/model/casal.model';
import { CasalService } from './casal.service';

@Component({
  templateUrl: './casal-delete-dialog.component.html'
})
export class CasalDeleteDialogComponent {
  casal?: ICasal;

  constructor(protected casalService: CasalService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.casalService.delete(id).subscribe(() => {
      this.eventManager.broadcast('casalListModification');
      this.activeModal.close();
    });
  }
}
