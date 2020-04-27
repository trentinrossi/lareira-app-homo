import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITipoUniao, TipoUniao } from 'app/shared/model/tipo-uniao.model';
import { TipoUniaoService } from './tipo-uniao.service';

@Component({
  selector: 'jhi-tipo-uniao-update',
  templateUrl: './tipo-uniao-update.component.html'
})
export class TipoUniaoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nome: [null, [Validators.required]],
    descricao: []
  });

  constructor(protected tipoUniaoService: TipoUniaoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoUniao }) => {
      this.updateForm(tipoUniao);
    });
  }

  updateForm(tipoUniao: ITipoUniao): void {
    this.editForm.patchValue({
      id: tipoUniao.id,
      nome: tipoUniao.nome,
      descricao: tipoUniao.descricao
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tipoUniao = this.createFromForm();
    if (tipoUniao.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoUniaoService.update(tipoUniao));
    } else {
      this.subscribeToSaveResponse(this.tipoUniaoService.create(tipoUniao));
    }
  }

  private createFromForm(): ITipoUniao {
    return {
      ...new TipoUniao(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      descricao: this.editForm.get(['descricao'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoUniao>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
