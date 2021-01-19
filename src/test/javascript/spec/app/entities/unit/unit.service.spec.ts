import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { UnitService } from 'app/entities/unit/unit.service';
import { IUnit, Unit } from 'app/shared/model/unit.model';

describe('Service Tests', () => {
  describe('Unit Service', () => {
    let injector: TestBed;
    let service: UnitService;
    let httpMock: HttpTestingController;
    let elemDefault: IUnit;
    let expectedResult: IUnit | IUnit[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UnitService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Unit(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            unitCretime: currentDate.format(DATE_TIME_FORMAT),
            unitModtime: currentDate.format(DATE_TIME_FORMAT),
            unitSignDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Unit', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            unitCretime: currentDate.format(DATE_TIME_FORMAT),
            unitModtime: currentDate.format(DATE_TIME_FORMAT),
            unitSignDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            unitCretime: currentDate,
            unitModtime: currentDate,
            unitSignDate: currentDate
          },
          returnedFromService
        );

        service.create(new Unit()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Unit', () => {
        const returnedFromService = Object.assign(
          {
            unitUcCode: 'BBBBBB',
            unitCode: 'BBBBBB',
            unitName: 'BBBBBB',
            unitAddr: 'BBBBBB',
            unitLongitude: 'BBBBBB',
            unitLatitude: 'BBBBBB',
            unitPic: 'BBBBBB',
            unitPhone: 'BBBBBB',
            unitEmail: 'BBBBBB',
            unitCretime: currentDate.format(DATE_TIME_FORMAT),
            unitCreid: 'BBBBBB',
            unitModtime: currentDate.format(DATE_TIME_FORMAT),
            unitModid: 'BBBBBB',
            unitLogip: 'BBBBBB',
            unitSignDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            unitCretime: currentDate,
            unitModtime: currentDate,
            unitSignDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Unit', () => {
        const returnedFromService = Object.assign(
          {
            unitUcCode: 'BBBBBB',
            unitCode: 'BBBBBB',
            unitName: 'BBBBBB',
            unitAddr: 'BBBBBB',
            unitLongitude: 'BBBBBB',
            unitLatitude: 'BBBBBB',
            unitPic: 'BBBBBB',
            unitPhone: 'BBBBBB',
            unitEmail: 'BBBBBB',
            unitCretime: currentDate.format(DATE_TIME_FORMAT),
            unitCreid: 'BBBBBB',
            unitModtime: currentDate.format(DATE_TIME_FORMAT),
            unitModid: 'BBBBBB',
            unitLogip: 'BBBBBB',
            unitSignDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            unitCretime: currentDate,
            unitModtime: currentDate,
            unitSignDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Unit', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
