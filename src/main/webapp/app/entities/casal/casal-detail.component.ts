import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICasal } from 'app/shared/model/casal.model';

@Component({
  selector: 'jhi-casal-detail',
  templateUrl: './casal-detail.component.html'
})
export class CasalDetailComponent implements OnInit {
  casal: ICasal | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ casal }) => (this.casal = casal));
  }

  previousState(): void {
    window.history.back();
  }
}
