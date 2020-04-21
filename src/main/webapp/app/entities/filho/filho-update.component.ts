import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFilho, Filho } from 'app/shared/model/filho.model';
import { FilhoService } from './filho.service';
import { ICasal } from 'app/shared/model/casal.model';
import { CasalService } from 'app/entities/casal/casal.service';

@Component({
  selector: 'jhi-filho-update',
  templateUrl: './filho-update.component.html'
})
export class FilhoUpdateComponent implements OnInit {
  isSaving = false;
  casals: ICasal[] = [];
  dataNascimentoDp: any;

  editForm = this.fb.group({
    id: [],
    nome: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    sexo: [],
    dataNascimento: [],
    idCasalId: [null, Validators.required]
  });

  constructor(
    protected filhoService: FilhoService,
    protected casalService: CasalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ filho }) => {
      this.updateForm(filho);

      this.casalService.query().subscribe((res: HttpResponse<ICasal[]>) => (this.casals = res.body || []));
    });
  }

  updateForm(filho: IFilho): void {
    this.editForm.patchValue({
      id: filho.id,
      nome: filho.nome,
      sexo: filho.sexo,
      dataNascimento: filho.dataNascimento,
      idCasalId: filho.idCasalId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const filho = this.createFromForm();
    if (filho.id !== undefined) {
      this.subscribeToSaveResponse(this.filhoService.update(filho));
    } else {
      this.subscribeToSaveResponse(this.filhoService.create(filho));
    }
  }

  private createFromForm(): IFilho {
    return {
      ...new Filho(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      sexo: this.editForm.get(['sexo'])!.value,
      dataNascimento: this.editForm.get(['dataNascimento'])!.value,
      idCasalId: this.editForm.get(['idCasalId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFilho>>): void {
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

  trackById(index: number, item: ICasal): any {
    return item.id;
  }
}
