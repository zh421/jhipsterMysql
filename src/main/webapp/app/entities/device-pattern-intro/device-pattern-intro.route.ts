import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDevicePatternIntro, DevicePatternIntro } from 'app/shared/model/device-pattern-intro.model';
import { DevicePatternIntroService } from './device-pattern-intro.service';
import { DevicePatternIntroComponent } from './device-pattern-intro.component';
import { DevicePatternIntroDetailComponent } from './device-pattern-intro-detail.component';
import { DevicePatternIntroUpdateComponent } from './device-pattern-intro-update.component';

@Injectable({ providedIn: 'root' })
export class DevicePatternIntroResolve implements Resolve<IDevicePatternIntro> {
  constructor(private service: DevicePatternIntroService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDevicePatternIntro> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((devicePatternIntro: HttpResponse<DevicePatternIntro>) => {
          if (devicePatternIntro.body) {
            return of(devicePatternIntro.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DevicePatternIntro());
  }
}

export const devicePatternIntroRoute: Routes = [
  {
    path: '',
    component: DevicePatternIntroComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aIoTapplicationApp.devicePatternIntro.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DevicePatternIntroDetailComponent,
    resolve: {
      devicePatternIntro: DevicePatternIntroResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.devicePatternIntro.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DevicePatternIntroUpdateComponent,
    resolve: {
      devicePatternIntro: DevicePatternIntroResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.devicePatternIntro.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DevicePatternIntroUpdateComponent,
    resolve: {
      devicePatternIntro: DevicePatternIntroResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.devicePatternIntro.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
