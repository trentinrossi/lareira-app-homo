import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICasal, Casal } from 'app/shared/model/casal.model';
import { CasalService } from './casal.service';
import { ILareira } from 'app/shared/model/lareira.model';
import { LareiraService } from 'app/entities/lareira/lareira.service';

@Component({
  selector: 'jhi-casal-update',
  templateUrl: './casal-update.component.html'
})
export class CasalUpdateComponent implements OnInit {
  isSaving = false;
  lareiras: ILareira[] = [];
  maridoDataNascimentoDp: any;
  esposaDataNascimentoDp: any;

  editForm = this.fb.group({
    id: [],
    maridoNome: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    maridoSobrenome: [],
    maridoDataNascimento: [],
    maridoProfissao: [],
    maridoTelCelular: [],
    maridoEmail: [],
    maridoProblemaSaude: [],
    esposaNome: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    esposaSobrenome: [],
    esposaDataNascimento: [],
    esposaProfissao: [],
    esposaTelCelular: [],
    esposaEmail: [],
    esposaProblemaSaude: [],
    idLareiraId: [null, Validators.required]
  });

  constructor(
    protected casalService: CasalService,
    protected lareiraService: LareiraService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ casal }) => {
      this.updateForm(casal);

      this.lareiraService.query().subscribe((res: HttpResponse<ILareira[]>) => (this.lareiras = res.body || []));
    });
  }

  updateForm(casal: ICasal): void {
    this.editForm.patchValue({
      id: casal.id,
      maridoNome: casal.maridoNome,
      maridoSobrenome: casal.maridoSobrenome,
      maridoDataNascimento: casal.maridoDataNascimento,
      maridoProfissao: casal.maridoProfissao,
      maridoTelCelular: casal.maridoTelCelular,
      maridoEmail: casal.maridoEmail,
      maridoProblemaSaude: casal.maridoProblemaSaude,
      esposaNome: casal.esposaNome,
      esposaSobrenome: casal.esposaSobrenome,
      esposaDataNascimento: casal.esposaDataNascimento,
      esposaProfissao: casal.esposaProfissao,
      esposaTelCelular: casal.esposaTelCelular,
      esposaEmail: casal.esposaEmail,
      esposaProblemaSaude: casal.esposaProblemaSaude,
      idLareiraId: casal.idLareiraId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const casal = this.createFromForm();
    if (casal.id !== undefined) {
      this.subscribeToSaveResponse(this.casalService.update(casal));
    } else {
      this.subscribeToSaveResponse(this.casalService.create(casal));
    }
  }

  private createFromForm(): ICasal {
    return {
      ...new Casal(),
      id: this.editForm.get(['id'])!.value,
      maridoNome: this.editForm.get(['maridoNome'])!.value,
      maridoSobrenome: this.editForm.get(['maridoSobrenome'])!.value,
      maridoDataNascimento: this.editForm.get(['maridoDataNascimento'])!.value,
      maridoProfissao: this.editForm.get(['maridoProfissao'])!.value,
      maridoTelCelular: this.editForm.get(['maridoTelCelular'])!.value,
      maridoEmail: this.editForm.get(['maridoEmail'])!.value,
      maridoProblemaSaude: this.editForm.get(['maridoProblemaSaude'])!.value,
      esposaNome: this.editForm.get(['esposaNome'])!.value,
      esposaSobrenome: this.editForm.get(['esposaSobrenome'])!.value,
      esposaDataNascimento: this.editForm.get(['esposaDataNascimento'])!.value,
      esposaProfissao: this.editForm.get(['esposaProfissao'])!.value,
      esposaTelCelular: this.editForm.get(['esposaTelCelular'])!.value,
      esposaEmail: this.editForm.get(['esposaEmail'])!.value,
      esposaProblemaSaude: this.editForm.get(['esposaProblemaSaude'])!.value,
      idLareiraId: this.editForm.get(['idLareiraId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICasal>>): void {
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

  trackById(index: number, item: ILareira): any {
    return item.id;
  }
}
