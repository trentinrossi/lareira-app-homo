import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITipoUniao } from 'app/shared/model/tipo-uniao.model';
import { TipoUniaoService } from './tipo-uniao.service';
import { TipoUniaoDeleteDialogComponent } from './tipo-uniao-delete-dialog.component';

@Component({
  selector: 'jhi-tipo-uniao',
  templateUrl: './tipo-uniao.component.html'
})
export class TipoUniaoComponent implements OnInit, OnDestroy {
  tipoUniaos?: ITipoUniao[];
  eventSubscriber?: Subscription;

  constructor(protected tipoUniaoService: TipoUniaoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.tipoUniaoService.query().subscribe((res: HttpResponse<ITipoUniao[]>) => (this.tipoUniaos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTipoUniaos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITipoUniao): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTipoUniaos(): void {
    this.eventSubscriber = this.eventManager.subscribe('tipoUniaoListModification', () => this.loadAll());
  }

  delete(tipoUniao: ITipoUniao): void {
    const modalRef = this.modalService.open(TipoUniaoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tipoUniao = tipoUniao;
  }
}
