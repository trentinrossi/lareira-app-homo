import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITipoUniao, TipoUniao } from 'app/shared/model/tipo-uniao.model';
import { TipoUniaoService } from './tipo-uniao.service';
import { TipoUniaoComponent } from './tipo-uniao.component';
import { TipoUniaoDetailComponent } from './tipo-uniao-detail.component';
import { TipoUniaoUpdateComponent } from './tipo-uniao-update.component';

@Injectable({ providedIn: 'root' })
export class TipoUniaoResolve implements Resolve<ITipoUniao> {
  constructor(private service: TipoUniaoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITipoUniao> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tipoUniao: HttpResponse<TipoUniao>) => {
          if (tipoUniao.body) {
            return of(tipoUniao.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TipoUniao());
  }
}

export const tipoUniaoRoute: Routes = [
  {
    path: '',
    component: TipoUniaoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.tipoUniao.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TipoUniaoDetailComponent,
    resolve: {
      tipoUniao: TipoUniaoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.tipoUniao.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TipoUniaoUpdateComponent,
    resolve: {
      tipoUniao: TipoUniaoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.tipoUniao.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TipoUniaoUpdateComponent,
    resolve: {
      tipoUniao: TipoUniaoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.tipoUniao.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
