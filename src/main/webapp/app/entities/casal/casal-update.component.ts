import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ICasal, Casal } from 'app/shared/model/casal.model';
import { CasalService } from './casal.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { ITipoUniao } from 'app/shared/model/tipo-uniao.model';
import { TipoUniaoService } from 'app/entities/tipo-uniao/tipo-uniao.service';
import { ILareira } from 'app/shared/model/lareira.model';
import { LareiraService } from 'app/entities/lareira/lareira.service';

type SelectableEntity = ITipoUniao | ICasal | ILareira;

@Component({
  selector: 'jhi-casal-update',
  templateUrl: './casal-update.component.html'
})
export class CasalUpdateComponent implements OnInit {
  isSaving = false;
  tipouniaos: ITipoUniao[] = [];
  casalpadrinhos: ICasal[] = [];
  lareiras: ILareira[] = [];
  maridoDataNascimentoDp: any;
  esposaDataNascimentoDp: any;
  dataUniaoDp: any;

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
    casalFoneFixo: [],
    casalFoneContato: [],
    casalCep: [],
    casalNomeRua: [],
    casalNumeroRua: [],
    casalBairro: [],
    casalCidade: [],
    casalEstado: [],
    fotoCasal: [],
    fotoCasalContentType: [],
    dataUniao: [],
    numeroFicha: [],
    informacoesCasal: [],
    tipoUniaoId: [],
    casalPadrinhoId: [],
    idLareiraId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected casalService: CasalService,
    protected tipoUniaoService: TipoUniaoService,
    protected lareiraService: LareiraService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ casal }) => {
      this.updateForm(casal);

      this.tipoUniaoService
        .query({ filter: 'casal-is-null' })
        .pipe(
          map((res: HttpResponse<ITipoUniao[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITipoUniao[]) => {
          if (!casal.tipoUniaoId) {
            this.tipouniaos = resBody;
          } else {
            this.tipoUniaoService
              .find(casal.tipoUniaoId)
              .pipe(
                map((subRes: HttpResponse<ITipoUniao>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITipoUniao[]) => (this.tipouniaos = concatRes));
          }
        });

      this.casalService
        .query({ 'casalId.specified': 'false' })
        .pipe(
          map((res: HttpResponse<ICasal[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICasal[]) => {
          if (!casal.casalPadrinhoId) {
            this.casalpadrinhos = resBody;
          } else {
            this.casalService
              .find(casal.casalPadrinhoId)
              .pipe(
                map((subRes: HttpResponse<ICasal>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICasal[]) => (this.casalpadrinhos = concatRes));
          }
        });

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
      casalFoneFixo: casal.casalFoneFixo,
      casalFoneContato: casal.casalFoneContato,
      casalCep: casal.casalCep,
      casalNomeRua: casal.casalNomeRua,
      casalNumeroRua: casal.casalNumeroRua,
      casalBairro: casal.casalBairro,
      casalCidade: casal.casalCidade,
      casalEstado: casal.casalEstado,
      fotoCasal: casal.fotoCasal,
      fotoCasalContentType: casal.fotoCasalContentType,
      dataUniao: casal.dataUniao,
      numeroFicha: casal.numeroFicha,
      informacoesCasal: casal.informacoesCasal,
      tipoUniaoId: casal.tipoUniaoId,
      casalPadrinhoId: casal.casalPadrinhoId,
      idLareiraId: casal.idLareiraId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('lareiraAppHomoApp.error', { ...err, key: 'error.file.' + err.key })
      );
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
      casalFoneFixo: this.editForm.get(['casalFoneFixo'])!.value,
      casalFoneContato: this.editForm.get(['casalFoneContato'])!.value,
      casalCep: this.editForm.get(['casalCep'])!.value,
      casalNomeRua: this.editForm.get(['casalNomeRua'])!.value,
      casalNumeroRua: this.editForm.get(['casalNumeroRua'])!.value,
      casalBairro: this.editForm.get(['casalBairro'])!.value,
      casalCidade: this.editForm.get(['casalCidade'])!.value,
      casalEstado: this.editForm.get(['casalEstado'])!.value,
      fotoCasalContentType: this.editForm.get(['fotoCasalContentType'])!.value,
      fotoCasal: this.editForm.get(['fotoCasal'])!.value,
      dataUniao: this.editForm.get(['dataUniao'])!.value,
      numeroFicha: this.editForm.get(['numeroFicha'])!.value,
      informacoesCasal: this.editForm.get(['informacoesCasal'])!.value,
      tipoUniaoId: this.editForm.get(['tipoUniaoId'])!.value,
      casalPadrinhoId: this.editForm.get(['casalPadrinhoId'])!.value,
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
