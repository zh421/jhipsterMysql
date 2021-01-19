import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUnit, Unit } from 'app/shared/model/unit.model';
import { UnitService } from './unit.service';
import { UnitComponent } from './unit.component';
import { UnitDetailComponent } from './unit-detail.component';
import { UnitUpdateComponent } from './unit-update.component';

@Injectable({ providedIn: 'root' })
export class UnitResolve implements Resolve<IUnit> {
  constructor(private service: UnitService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUnit> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((unit: HttpResponse<Unit>) => {
          if (unit.body) {
            return of(unit.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Unit());
  }
}

export const unitRoute: Routes = [
  {
    path: '',
    component: UnitComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aIoTapplicationApp.unit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UnitDetailComponent,
    resolve: {
      unit: UnitResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.unit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UnitUpdateComponent,
    resolve: {
      unit: UnitResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.unit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UnitUpdateComponent,
    resolve: {
      unit: UnitResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.unit.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
