import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { EventInter, IEventInter } from 'app/shared/model/event-inter.model';

type EntityResponseType = HttpResponse<IEventInter>;
type EntityArrayResponseType = HttpResponse<IEventInter[]>;

@Injectable({ providedIn: 'root' })
export class EventInterService {
  public resourceUrl = SERVER_API_URL + 'api/event-inters';

  constructor(protected http: HttpClient) {}

  create(eventInter: IEventInter): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(eventInter);
    return this.http
      .post<IEventInter>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(eventInter: IEventInter): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(eventInter);
    return this.http
      .put<IEventInter>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEventInter>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEventInter[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  search(req?: any, eventInter?: EventInter): Observable<EntityArrayResponseType> {
    return this.http
      .post<IEventInter[]>(this.resourceUrl + '/search', eventInter, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(eventInter: IEventInter): IEventInter {
    const copy: IEventInter = Object.assign({}, eventInter, {
      evninTime: eventInter.evninTime && eventInter.evninTime.isValid() ? eventInter.evninTime.toJSON() : undefined,
      evninCretime: eventInter.evninCretime && eventInter.evninCretime.isValid() ? eventInter.evninCretime.toJSON() : undefined,
      evninModtime: eventInter.evninModtime && eventInter.evninModtime.isValid() ? eventInter.evninModtime.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.evninTime = res.body.evninTime ? moment(res.body.evninTime) : undefined;
      res.body.evninCretime = res.body.evninCretime ? moment(res.body.evninCretime) : undefined;
      res.body.evninModtime = res.body.evninModtime ? moment(res.body.evninModtime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((eventInter: IEventInter) => {
        eventInter.evninTime = eventInter.evninTime ? moment(eventInter.evninTime) : undefined;
        eventInter.evninCretime = eventInter.evninCretime ? moment(eventInter.evninCretime) : undefined;
        eventInter.evninModtime = eventInter.evninModtime ? moment(eventInter.evninModtime) : undefined;
      });
    }
    return res;
  }
}
