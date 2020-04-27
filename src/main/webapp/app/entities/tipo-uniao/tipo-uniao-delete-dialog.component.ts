import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoUniao } from 'app/shared/model/tipo-uniao.model';
import { TipoUniaoService } from './tipo-uniao.service';

@Component({
  templateUrl: './tipo-uniao-delete-dialog.component.html'
})
export class TipoUniaoDeleteDialogComponent {
  tipoUniao?: ITipoUniao;

  constructor(protected tipoUniaoService: TipoUniaoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tipoUniaoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tipoUniaoListModification');
      this.activeModal.close();
    });
  }
}
