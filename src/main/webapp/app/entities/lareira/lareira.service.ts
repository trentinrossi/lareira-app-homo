import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILareira } from 'app/shared/model/lareira.model';

type EntityResponseType = HttpResponse<ILareira>;
type EntityArrayResponseType = HttpResponse<ILareira[]>;

@Injectable({ providedIn: 'root' })
export class LareiraService {
  public resourceUrl = SERVER_API_URL + 'api/lareiras';

  constructor(protected http: HttpClient) {}

  create(lareira: ILareira): Observable<EntityResponseType> {
    return this.http.post<ILareira>(this.resourceUrl, lareira, { observe: 'response' });
  }

  update(lareira: ILareira): Observable<EntityResponseType> {
    return this.http.put<ILareira>(this.resourceUrl, lareira, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILareira>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILareira[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
