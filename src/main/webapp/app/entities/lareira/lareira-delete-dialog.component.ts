import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILareira } from 'app/shared/model/lareira.model';
import { LareiraService } from './lareira.service';

@Component({
  templateUrl: './lareira-delete-dialog.component.html'
})
export class LareiraDeleteDialogComponent {
  lareira?: ILareira;

  constructor(protected lareiraService: LareiraService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.lareiraService.delete(id).subscribe(() => {
      this.eventManager.broadcast('lareiraListModification');
      this.activeModal.close();
    });
  }
}
