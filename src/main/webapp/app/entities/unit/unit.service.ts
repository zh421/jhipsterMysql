import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUnit, Unit } from 'app/shared/model/unit.model';

type EntityResponseType = HttpResponse<IUnit>;
type EntityArrayResponseType = HttpResponse<IUnit[]>;

@Injectable({ providedIn: 'root' })
export class UnitService {
  public resourceUrl = SERVER_API_URL + 'api/units';

  constructor(protected http: HttpClient) {}

  create(unit: IUnit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(unit);
    return this.http
      .post<IUnit>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(unit: IUnit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(unit);
    return this.http
      .put<IUnit>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUnit>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUnit[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  search(req?: any, unit?: Unit): Observable<EntityArrayResponseType> {
    return this.http
      .post<IUnit[]>(this.resourceUrl + '/search', unit, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(unit: IUnit): IUnit {
    const copy: IUnit = Object.assign({}, unit, {
      unitCretime: unit.unitCretime && unit.unitCretime.isValid() ? unit.unitCretime.toJSON() : undefined,
      unitModtime: unit.unitModtime && unit.unitModtime.isValid() ? unit.unitModtime.toJSON() : undefined,
      unitSignDate: unit.unitSignDate && unit.unitSignDate.isValid() ? unit.unitSignDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.unitCretime = res.body.unitCretime ? moment(res.body.unitCretime) : undefined;
      res.body.unitModtime = res.body.unitModtime ? moment(res.body.unitModtime) : undefined;
      res.body.unitSignDate = res.body.unitSignDate ? moment(res.body.unitSignDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((unit: IUnit) => {
        unit.unitCretime = unit.unitCretime ? moment(unit.unitCretime) : undefined;
        unit.unitModtime = unit.unitModtime ? moment(unit.unitModtime) : undefined;
        unit.unitSignDate = unit.unitSignDate ? moment(unit.unitSignDate) : undefined;
      });
    }
    return res;
  }
}
