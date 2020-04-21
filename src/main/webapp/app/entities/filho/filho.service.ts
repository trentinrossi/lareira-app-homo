import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFilho } from 'app/shared/model/filho.model';

type EntityResponseType = HttpResponse<IFilho>;
type EntityArrayResponseType = HttpResponse<IFilho[]>;

@Injectable({ providedIn: 'root' })
export class FilhoService {
  public resourceUrl = SERVER_API_URL + 'api/filhos';

  constructor(protected http: HttpClient) {}

  create(filho: IFilho): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(filho);
    return this.http
      .post<IFilho>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(filho: IFilho): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(filho);
    return this.http
      .put<IFilho>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFilho>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFilho[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(filho: IFilho): IFilho {
    const copy: IFilho = Object.assign({}, filho, {
      dataNascimento: filho.dataNascimento && filho.dataNascimento.isValid() ? filho.dataNascimento.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataNascimento = res.body.dataNascimento ? moment(res.body.dataNascimento) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((filho: IFilho) => {
        filho.dataNascimento = filho.dataNascimento ? moment(filho.dataNascimento) : undefined;
      });
    }
    return res;
  }
}
