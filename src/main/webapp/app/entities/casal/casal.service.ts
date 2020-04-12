import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICasal } from 'app/shared/model/casal.model';

type EntityResponseType = HttpResponse<ICasal>;
type EntityArrayResponseType = HttpResponse<ICasal[]>;

@Injectable({ providedIn: 'root' })
export class CasalService {
  public resourceUrl = SERVER_API_URL + 'api/casals';

  constructor(protected http: HttpClient) {}

  create(casal: ICasal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(casal);
    return this.http
      .post<ICasal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(casal: ICasal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(casal);
    return this.http
      .put<ICasal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICasal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICasal[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(casal: ICasal): ICasal {
    const copy: ICasal = Object.assign({}, casal, {
      maridoDataNascimento:
        casal.maridoDataNascimento && casal.maridoDataNascimento.isValid() ? casal.maridoDataNascimento.format(DATE_FORMAT) : undefined,
      esposaDataNascimento:
        casal.esposaDataNascimento && casal.esposaDataNascimento.isValid() ? casal.esposaDataNascimento.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.maridoDataNascimento = res.body.maridoDataNascimento ? moment(res.body.maridoDataNascimento) : undefined;
      res.body.esposaDataNascimento = res.body.esposaDataNascimento ? moment(res.body.esposaDataNascimento) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((casal: ICasal) => {
        casal.maridoDataNascimento = casal.maridoDataNascimento ? moment(casal.maridoDataNascimento) : undefined;
        casal.esposaDataNascimento = casal.esposaDataNascimento ? moment(casal.esposaDataNascimento) : undefined;
      });
    }
    return res;
  }
}
