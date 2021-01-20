import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { DeviceCode, IDeviceCode } from 'app/shared/model/device-code.model';

type EntityResponseType = HttpResponse<IDeviceCode>;
type EntityArrayResponseType = HttpResponse<IDeviceCode[]>;

@Injectable({ providedIn: 'root' })
export class DeviceCodeService {
  public resourceUrl = SERVER_API_URL + 'api/device-codes';

  constructor(protected http: HttpClient) {}

  create(deviceCode: IDeviceCode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deviceCode);
    return this.http
      .post<IDeviceCode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(deviceCode: IDeviceCode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deviceCode);
    return this.http
      .put<IDeviceCode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDeviceCode>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDeviceCode[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  search(req?: any, deviceCode?: DeviceCode): Observable<EntityArrayResponseType> {
    return this.http
      .post<IDeviceCode[]>(this.resourceUrl + '/search', deviceCode, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(deviceCode: IDeviceCode): IDeviceCode {
    const copy: IDeviceCode = Object.assign({}, deviceCode, {
      dviCretime: deviceCode.dviCretime && deviceCode.dviCretime.isValid() ? deviceCode.dviCretime.toJSON() : undefined,
      dviModtime: deviceCode.dviModtime && deviceCode.dviModtime.isValid() ? deviceCode.dviModtime.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dviCretime = res.body.dviCretime ? moment(res.body.dviCretime) : undefined;
      res.body.dviModtime = res.body.dviModtime ? moment(res.body.dviModtime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((deviceCode: IDeviceCode) => {
        deviceCode.dviCretime = deviceCode.dviCretime ? moment(deviceCode.dviCretime) : undefined;
        deviceCode.dviModtime = deviceCode.dviModtime ? moment(deviceCode.dviModtime) : undefined;
      });
    }
    return res;
  }
}
