import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFilho } from 'app/shared/model/filho.model';

@Component({
  selector: 'jhi-filho-detail',
  templateUrl: './filho-detail.component.html'
})
export class FilhoDetailComponent implements OnInit {
  filho: IFilho | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ filho }) => (this.filho = filho));
  }

  previousState(): void {
    window.history.back();
  }
}
