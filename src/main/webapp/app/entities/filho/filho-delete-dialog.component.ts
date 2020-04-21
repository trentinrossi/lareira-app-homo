import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFilho } from 'app/shared/model/filho.model';
import { FilhoService } from './filho.service';

@Component({
  templateUrl: './filho-delete-dialog.component.html'
})
export class FilhoDeleteDialogComponent {
  filho?: IFilho;

  constructor(protected filhoService: FilhoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.filhoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('filhoListModification');
      this.activeModal.close();
    });
  }
}
