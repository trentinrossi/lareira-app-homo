import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILareira, Lareira } from 'app/shared/model/lareira.model';
import { LareiraService } from './lareira.service';

@Component({
  selector: 'jhi-lareira-update',
  templateUrl: './lareira-update.component.html'
})
export class LareiraUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nome: [null, [Validators.required]],
    endereco: [],
    bairro: [],
    cep: [],
    cidade: [],
    estado: [],
    telefone: []
  });

  constructor(protected lareiraService: LareiraService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lareira }) => {
      this.updateForm(lareira);
    });
  }

  updateForm(lareira: ILareira): void {
    this.editForm.patchValue({
      id: lareira.id,
      nome: lareira.nome,
      endereco: lareira.endereco,
      bairro: lareira.bairro,
      cep: lareira.cep,
      cidade: lareira.cidade,
      estado: lareira.estado,
      telefone: lareira.telefone
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lareira = this.createFromForm();
    if (lareira.id !== undefined) {
      this.subscribeToSaveResponse(this.lareiraService.update(lareira));
    } else {
      this.subscribeToSaveResponse(this.lareiraService.create(lareira));
    }
  }

  private createFromForm(): ILareira {
    return {
      ...new Lareira(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      endereco: this.editForm.get(['endereco'])!.value,
      bairro: this.editForm.get(['bairro'])!.value,
      cep: this.editForm.get(['cep'])!.value,
      cidade: this.editForm.get(['cidade'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      telefone: this.editForm.get(['telefone'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILareira>>): void {
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
