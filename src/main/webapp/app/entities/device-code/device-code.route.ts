import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDeviceCode, DeviceCode } from 'app/shared/model/device-code.model';
import { DeviceCodeService } from './device-code.service';
import { DeviceCodeComponent } from './device-code.component';
import { DeviceCodeDetailComponent } from './device-code-detail.component';
import { DeviceCodeUpdateComponent } from './device-code-update.component';

@Injectable({ providedIn: 'root' })
export class DeviceCodeResolve implements Resolve<IDeviceCode> {
  constructor(private service: DeviceCodeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDeviceCode> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((deviceCode: HttpResponse<DeviceCode>) => {
          if (deviceCode.body) {
            return of(deviceCode.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DeviceCode());
  }
}

export const deviceCodeRoute: Routes = [
  {
    path: '',
    component: DeviceCodeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aIoTapplicationApp.deviceCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DeviceCodeDetailComponent,
    resolve: {
      deviceCode: DeviceCodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.deviceCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DeviceCodeUpdateComponent,
    resolve: {
      deviceCode: DeviceCodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.deviceCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DeviceCodeUpdateComponent,
    resolve: {
      deviceCode: DeviceCodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.deviceCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
