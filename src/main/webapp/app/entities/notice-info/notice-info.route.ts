import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INoticeInfo, NoticeInfo } from 'app/shared/model/notice-info.model';
import { NoticeInfoService } from './notice-info.service';
import { NoticeInfoComponent } from './notice-info.component';
import { NoticeInfoDetailComponent } from './notice-info-detail.component';
import { NoticeInfoUpdateComponent } from './notice-info-update.component';

@Injectable({ providedIn: 'root' })
export class NoticeInfoResolve implements Resolve<INoticeInfo> {
  constructor(private service: NoticeInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INoticeInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((noticeInfo: HttpResponse<NoticeInfo>) => {
          if (noticeInfo.body) {
            return of(noticeInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NoticeInfo());
  }
}

export const noticeInfoRoute: Routes = [
  {
    path: '',
    component: NoticeInfoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aIoTapplicationApp.noticeInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NoticeInfoDetailComponent,
    resolve: {
      noticeInfo: NoticeInfoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.noticeInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NoticeInfoUpdateComponent,
    resolve: {
      noticeInfo: NoticeInfoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.noticeInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NoticeInfoUpdateComponent,
    resolve: {
      noticeInfo: NoticeInfoResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aIoTapplicationApp.noticeInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
