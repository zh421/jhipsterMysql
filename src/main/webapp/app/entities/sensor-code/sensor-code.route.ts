import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISensorCode, SensorCode } from 'app/shared/model/sensor-code.model';
import { SensorCodeService } from './sensor-code.service';
import { SensorCodeComponent } from './sensor-code.component';
import { SensorCodeDetailComponent } from './sensor-code-detail.component';
import { SensorCodeUpdateComponent } from './sensor-code-update.component';

@Injectable({ providedIn: 'root' })
export class SensorCodeResolve implements Resolve<ISensorCode> {
  constructor(private service: SensorCodeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISensorCode> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sensorCode: HttpResponse<SensorCode>) => {
          if (sensorCode.body) {
            return of(sensorCode.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SensorCode());
  }
}

export const sensorCodeRoute: Routes = [
  {
    path: '',
    component: SensorCodeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aIoTapplicationApp.sensorCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SensorCodeDetailComponent,
    resolve: {
      sensorCode: SensorCodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.sensorCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SensorCodeUpdateComponent,
    resolve: {
      sensorCode: SensorCodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.sensorCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SensorCodeUpdateComponent,
    resolve: {
      sensorCode: SensorCodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.sensorCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
