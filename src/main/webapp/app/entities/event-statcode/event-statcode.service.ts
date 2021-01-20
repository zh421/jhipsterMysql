import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { EventStatcode, IEventStatcode } from 'app/shared/model/event-statcode.model';

type EntityResponseType = HttpResponse<IEventStatcode>;
type EntityArrayResponseType = HttpResponse<IEventStatcode[]>;

@Injectable({ providedIn: 'root' })
export class EventStatcodeService {
  public resourceUrl = SERVER_API_URL + 'api/event-statcodes';

  constructor(protected http: HttpClient) {}

  create(eventStatcode: IEventStatcode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(eventStatcode);
    return this.http
      .post<IEventStatcode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(eventStatcode: IEventStatcode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(eventStatcode);
    return this.http
      .put<IEventStatcode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEventStatcode>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEventStatcode[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  search(req?: any, eventInter?: EventStatcode): Observable<EntityArrayResponseType> {
    return this.http
      .post<IEventStatcode[]>(this.resourceUrl + '/search', eventInter, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(eventStatcode: IEventStatcode): IEventStatcode {
    const copy: IEventStatcode = Object.assign({}, eventStatcode, {
      esCretime: eventStatcode.esCretime && eventStatcode.esCretime.isValid() ? eventStatcode.esCretime.toJSON() : undefined,
      esModtime: eventStatcode.esModtime && eventStatcode.esModtime.isValid() ? eventStatcode.esModtime.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.esCretime = res.body.esCretime ? moment(res.body.esCretime) : undefined;
      res.body.esModtime = res.body.esModtime ? moment(res.body.esModtime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((eventStatcode: IEventStatcode) => {
        eventStatcode.esCretime = eventStatcode.esCretime ? moment(eventStatcode.esCretime) : undefined;
        eventStatcode.esModtime = eventStatcode.esModtime ? moment(eventStatcode.esModtime) : undefined;
      });
    }
    return res;
  }
}
