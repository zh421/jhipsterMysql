import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEventInter, EventInter } from 'app/shared/model/event-inter.model';
import { EventInterService } from './event-inter.service';
import { EventInterComponent } from './event-inter.component';
import { EventInterDetailComponent } from './event-inter-detail.component';
import { EventInterUpdateComponent } from './event-inter-update.component';

@Injectable({ providedIn: 'root' })
export class EventInterResolve implements Resolve<IEventInter> {
  constructor(private service: EventInterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEventInter> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((eventInter: HttpResponse<EventInter>) => {
          if (eventInter.body) {
            return of(eventInter.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EventInter());
  }
}

export const eventInterRoute: Routes = [
  {
    path: '',
    component: EventInterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aIoTapplicationApp.eventInter.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EventInterDetailComponent,
    resolve: {
      eventInter: EventInterResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.eventInter.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EventInterUpdateComponent,
    resolve: {
      eventInter: EventInterResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.eventInter.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EventInterUpdateComponent,
    resolve: {
      eventInter: EventInterResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.eventInter.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
