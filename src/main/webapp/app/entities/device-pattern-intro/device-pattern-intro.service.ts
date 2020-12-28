import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDevicePatternIntro } from 'app/shared/model/device-pattern-intro.model';

type EntityResponseType = HttpResponse<IDevicePatternIntro>;
type EntityArrayResponseType = HttpResponse<IDevicePatternIntro[]>;

@Injectable({ providedIn: 'root' })
export class DevicePatternIntroService {
  public resourceUrl = SERVER_API_URL + 'api/device-pattern-intros';

  constructor(protected http: HttpClient) {}

  create(devicePatternIntro: IDevicePatternIntro): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(devicePatternIntro);
    return this.http
      .post<IDevicePatternIntro>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(devicePatternIntro: IDevicePatternIntro): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(devicePatternIntro);
    return this.http
      .put<IDevicePatternIntro>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDevicePatternIntro>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDevicePatternIntro[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(devicePatternIntro: IDevicePatternIntro): IDevicePatternIntro {
    const copy: IDevicePatternIntro = Object.assign({}, devicePatternIntro, {
      cretime: devicePatternIntro.cretime && devicePatternIntro.cretime.isValid() ? devicePatternIntro.cretime.toJSON() : undefined,
      modtime: devicePatternIntro.modtime && devicePatternIntro.modtime.isValid() ? devicePatternIntro.modtime.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.cretime = res.body.cretime ? moment(res.body.cretime) : undefined;
      res.body.modtime = res.body.modtime ? moment(res.body.modtime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((devicePatternIntro: IDevicePatternIntro) => {
        devicePatternIntro.cretime = devicePatternIntro.cretime ? moment(devicePatternIntro.cretime) : undefined;
        devicePatternIntro.modtime = devicePatternIntro.modtime ? moment(devicePatternIntro.modtime) : undefined;
      });
    }
    return res;
  }
}
