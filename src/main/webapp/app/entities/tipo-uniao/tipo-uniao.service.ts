import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITipoUniao } from 'app/shared/model/tipo-uniao.model';

type EntityResponseType = HttpResponse<ITipoUniao>;
type EntityArrayResponseType = HttpResponse<ITipoUniao[]>;

@Injectable({ providedIn: 'root' })
export class TipoUniaoService {
  public resourceUrl = SERVER_API_URL + 'api/tipo-uniaos';

  constructor(protected http: HttpClient) {}

  create(tipoUniao: ITipoUniao): Observable<EntityResponseType> {
    return this.http.post<ITipoUniao>(this.resourceUrl, tipoUniao, { observe: 'response' });
  }

  update(tipoUniao: ITipoUniao): Observable<EntityResponseType> {
    return this.http.put<ITipoUniao>(this.resourceUrl, tipoUniao, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITipoUniao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoUniao[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
