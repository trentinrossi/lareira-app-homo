import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILareira } from 'app/shared/model/lareira.model';

@Component({
  selector: 'jhi-lareira-detail',
  templateUrl: './lareira-detail.component.html'
})
export class LareiraDetailComponent implements OnInit {
  lareira: ILareira | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lareira }) => (this.lareira = lareira));
  }

  previousState(): void {
    window.history.back();
  }
}
