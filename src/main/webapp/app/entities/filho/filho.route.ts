import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFilho, Filho } from 'app/shared/model/filho.model';
import { FilhoService } from './filho.service';
import { FilhoComponent } from './filho.component';
import { FilhoDetailComponent } from './filho-detail.component';
import { FilhoUpdateComponent } from './filho-update.component';

@Injectable({ providedIn: 'root' })
export class FilhoResolve implements Resolve<IFilho> {
  constructor(private service: FilhoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFilho> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((filho: HttpResponse<Filho>) => {
          if (filho.body) {
            return of(filho.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Filho());
  }
}

export const filhoRoute: Routes = [
  {
    path: '',
    component: FilhoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.filho.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FilhoDetailComponent,
    resolve: {
      filho: FilhoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.filho.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FilhoUpdateComponent,
    resolve: {
      filho: FilhoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.filho.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FilhoUpdateComponent,
    resolve: {
      filho: FilhoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lareiraAppHomoApp.filho.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
