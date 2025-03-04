import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILareira, Lareira } from 'app/shared/model/lareira.model';
import { LareiraService } from './lareira.service';
import { LareiraComponent } from './lareira.component';
import { LareiraDetailComponent } from './lareira-detail.component';
import { LareiraUpdateComponent } from './lareira-update.component';

@Injectable({ providedIn: 'root' })
export class LareiraResolve implements Resolve<ILareira> {
  constructor(private service: LareiraService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILareira> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((lareira: HttpResponse<Lareira>) => {
          if (lareira.body) {
            return of(lareira.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Lareira());
  }
}

export const lareiraRoute: Routes = [
  {
    path: '',
    component: LareiraComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'lareiraAppHomoApp.lareira.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LareiraDetailComponent,
    resolve: {
      lareira: LareiraResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.lareira.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LareiraUpdateComponent,
    resolve: {
      lareira: LareiraResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.lareira.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LareiraUpdateComponent,
    resolve: {
      lareira: LareiraResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.lareira.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
