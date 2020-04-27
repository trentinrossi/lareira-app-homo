import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFilho } from 'app/shared/model/filho.model';
import { FilhoService } from './filho.service';
import { FilhoDeleteDialogComponent } from './filho-delete-dialog.component';

@Component({
  selector: 'jhi-filho',
  templateUrl: './filho.component.html'
})
export class FilhoComponent implements OnInit, OnDestroy {
  filhos?: IFilho[];
  eventSubscriber?: Subscription;

  constructor(protected filhoService: FilhoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.filhoService.query().subscribe((res: HttpResponse<IFilho[]>) => (this.filhos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFilhos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFilho): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFilhos(): void {
    this.eventSubscriber = this.eventManager.subscribe('filhoListModification', () => this.loadAll());
  }

  delete(filho: IFilho): void {
    const modalRef = this.modalService.open(FilhoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.filho = filho;
  }
}
