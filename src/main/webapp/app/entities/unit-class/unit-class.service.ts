import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUnitClass } from 'app/shared/model/unit-class.model';

type EntityResponseType = HttpResponse<IUnitClass>;
type EntityArrayResponseType = HttpResponse<IUnitClass[]>;

@Injectable({ providedIn: 'root' })
export class UnitClassService {
  public resourceUrl = SERVER_API_URL + 'api/unit-classes';

  constructor(protected http: HttpClient) {}

  create(unitClass: IUnitClass): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(unitClass);
    return this.http
      .post<IUnitClass>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(unitClass: IUnitClass): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(unitClass);
    return this.http
      .put<IUnitClass>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUnitClass>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUnitClass[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(unitClass: IUnitClass): IUnitClass {
    const copy: IUnitClass = Object.assign({}, unitClass, {
      ucCretime: unitClass.ucCretime && unitClass.ucCretime.isValid() ? unitClass.ucCretime.toJSON() : undefined,
      ucModtime: unitClass.ucModtime && unitClass.ucModtime.isValid() ? unitClass.ucModtime.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ucCretime = res.body.ucCretime ? moment(res.body.ucCretime) : undefined;
      res.body.ucModtime = res.body.ucModtime ? moment(res.body.ucModtime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((unitClass: IUnitClass) => {
        unitClass.ucCretime = unitClass.ucCretime ? moment(unitClass.ucCretime) : undefined;
        unitClass.ucModtime = unitClass.ucModtime ? moment(unitClass.ucModtime) : undefined;
      });
    }
    return res;
  }
}
