import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INoticeInfo } from 'app/shared/model/notice-info.model';

type EntityResponseType = HttpResponse<INoticeInfo>;
type EntityArrayResponseType = HttpResponse<INoticeInfo[]>;

@Injectable({ providedIn: 'root' })
export class NoticeInfoService {
  public resourceUrl = SERVER_API_URL + 'api/notice-infos';

  constructor(protected http: HttpClient) {}

  create(noticeInfo: INoticeInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(noticeInfo);
    return this.http
      .post<INoticeInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(noticeInfo: INoticeInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(noticeInfo);
    return this.http
      .put<INoticeInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INoticeInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INoticeInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(noticeInfo: INoticeInfo): INoticeInfo {
    const copy: INoticeInfo = Object.assign({}, noticeInfo, {
      notiStarttime: noticeInfo.notiStarttime && noticeInfo.notiStarttime.isValid() ? noticeInfo.notiStarttime.toJSON() : undefined,
      notiEndtime: noticeInfo.notiEndtime && noticeInfo.notiEndtime.isValid() ? noticeInfo.notiEndtime.toJSON() : undefined,
      notiCretime: noticeInfo.notiCretime && noticeInfo.notiCretime.isValid() ? noticeInfo.notiCretime.toJSON() : undefined,
      notiModtime: noticeInfo.notiModtime && noticeInfo.notiModtime.isValid() ? noticeInfo.notiModtime.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.notiStarttime = res.body.notiStarttime ? moment(res.body.notiStarttime) : undefined;
      res.body.notiEndtime = res.body.notiEndtime ? moment(res.body.notiEndtime) : undefined;
      res.body.notiCretime = res.body.notiCretime ? moment(res.body.notiCretime) : undefined;
      res.body.notiModtime = res.body.notiModtime ? moment(res.body.notiModtime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((noticeInfo: INoticeInfo) => {
        noticeInfo.notiStarttime = noticeInfo.notiStarttime ? moment(noticeInfo.notiStarttime) : undefined;
        noticeInfo.notiEndtime = noticeInfo.notiEndtime ? moment(noticeInfo.notiEndtime) : undefined;
        noticeInfo.notiCretime = noticeInfo.notiCretime ? moment(noticeInfo.notiCretime) : undefined;
        noticeInfo.notiModtime = noticeInfo.notiModtime ? moment(noticeInfo.notiModtime) : undefined;
      });
    }
    return res;
  }
}
