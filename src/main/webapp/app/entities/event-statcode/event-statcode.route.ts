import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEventStatcode, EventStatcode } from 'app/shared/model/event-statcode.model';
import { EventStatcodeService } from './event-statcode.service';
import { EventStatcodeComponent } from './event-statcode.component';
import { EventStatcodeDetailComponent } from './event-statcode-detail.component';
import { EventStatcodeUpdateComponent } from './event-statcode-update.component';

@Injectable({ providedIn: 'root' })
export class EventStatcodeResolve implements Resolve<IEventStatcode> {
  constructor(private service: EventStatcodeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEventStatcode> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((eventStatcode: HttpResponse<EventStatcode>) => {
          if (eventStatcode.body) {
            return of(eventStatcode.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EventStatcode());
  }
}

export const eventStatcodeRoute: Routes = [
  {
    path: '',
    component: EventStatcodeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aIoTapplicationApp.eventStatcode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EventStatcodeDetailComponent,
    resolve: {
      eventStatcode: EventStatcodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.eventStatcode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EventStatcodeUpdateComponent,
    resolve: {
      eventStatcode: EventStatcodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.eventStatcode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EventStatcodeUpdateComponent,
    resolve: {
      eventStatcode: EventStatcodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.eventStatcode.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
