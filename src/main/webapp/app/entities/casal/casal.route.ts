import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICasal, Casal } from 'app/shared/model/casal.model';
import { CasalService } from './casal.service';
import { CasalComponent } from './casal.component';
import { CasalDetailComponent } from './casal-detail.component';
import { CasalUpdateComponent } from './casal-update.component';

@Injectable({ providedIn: 'root' })
export class CasalResolve implements Resolve<ICasal> {
  constructor(private service: CasalService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICasal> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((casal: HttpResponse<Casal>) => {
          if (casal.body) {
            return of(casal.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Casal());
  }
}

export const casalRoute: Routes = [
  {
    path: '',
    component: CasalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'lareiraAppHomoApp.casal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CasalDetailComponent,
    resolve: {
      casal: CasalResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.casal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CasalUpdateComponent,
    resolve: {
      casal: CasalResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.casal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CasalUpdateComponent,
    resolve: {
      casal: CasalResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.casal.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
