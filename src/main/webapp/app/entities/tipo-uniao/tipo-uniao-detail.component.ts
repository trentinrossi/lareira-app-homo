import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoUniao } from 'app/shared/model/tipo-uniao.model';

@Component({
  selector: 'jhi-tipo-uniao-detail',
  templateUrl: './tipo-uniao-detail.component.html'
})
export class TipoUniaoDetailComponent implements OnInit {
  tipoUniao: ITipoUniao | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoUniao }) => (this.tipoUniao = tipoUniao));
  }

  previousState(): void {
    window.history.back();
  }
}
