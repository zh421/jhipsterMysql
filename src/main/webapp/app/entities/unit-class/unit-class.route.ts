import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUnitClass, UnitClass } from 'app/shared/model/unit-class.model';
import { UnitClassService } from './unit-class.service';
import { UnitClassComponent } from './unit-class.component';
import { UnitClassDetailComponent } from './unit-class-detail.component';
import { UnitClassUpdateComponent } from './unit-class-update.component';

@Injectable({ providedIn: 'root' })
export class UnitClassResolve implements Resolve<IUnitClass> {
  constructor(private service: UnitClassService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUnitClass> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((unitClass: HttpResponse<UnitClass>) => {
          if (unitClass.body) {
            return of(unitClass.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UnitClass());
  }
}

export const unitClassRoute: Routes = [
  {
    path: '',
    component: UnitClassComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aIoTapplicationApp.unitClass.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UnitClassDetailComponent,
    resolve: {
      unitClass: UnitClassResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.unitClass.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UnitClassUpdateComponent,
    resolve: {
      unitClass: UnitClassResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.unitClass.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UnitClassUpdateComponent,
    resolve: {
      unitClass: UnitClassResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.unitClass.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
