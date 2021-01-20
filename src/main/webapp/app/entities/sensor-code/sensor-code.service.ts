import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISensorCode, SensorCode } from 'app/shared/model/sensor-code.model';

type EntityResponseType = HttpResponse<ISensorCode>;
type EntityArrayResponseType = HttpResponse<ISensorCode[]>;

@Injectable({ providedIn: 'root' })
export class SensorCodeService {
  public resourceUrl = SERVER_API_URL + 'api/sensor-codes';

  constructor(protected http: HttpClient) {}

  create(sensorCode: ISensorCode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sensorCode);
    return this.http
      .post<ISensorCode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sensorCode: ISensorCode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sensorCode);
    return this.http
      .put<ISensorCode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISensorCode>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISensorCode[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  search(req?: any, sensorCode?: SensorCode): Observable<EntityArrayResponseType> {
    return this.http
      .post<ISensorCode[]>(this.resourceUrl + '/search', sensorCode, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(sensorCode: ISensorCode): ISensorCode {
    const copy: ISensorCode = Object.assign({}, sensorCode, {
      scCretime: sensorCode.scCretime && sensorCode.scCretime.isValid() ? sensorCode.scCretime.toJSON() : undefined,
      scModtime: sensorCode.scModtime && sensorCode.scModtime.isValid() ? sensorCode.scModtime.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.scCretime = res.body.scCretime ? moment(res.body.scCretime) : undefined;
      res.body.scModtime = res.body.scModtime ? moment(res.body.scModtime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((sensorCode: ISensorCode) => {
        sensorCode.scCretime = sensorCode.scCretime ? moment(sensorCode.scCretime) : undefined;
        sensorCode.scModtime = sensorCode.scModtime ? moment(sensorCode.scModtime) : undefined;
      });
    }
    return res;
  }
}
