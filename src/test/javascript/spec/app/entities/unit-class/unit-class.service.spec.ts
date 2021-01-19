import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { UnitClassService } from 'app/entities/unit-class/unit-class.service';
import { IUnitClass, UnitClass } from 'app/shared/model/unit-class.model';

describe('Service Tests', () => {
  describe('UnitClass Service', () => {
    let injector: TestBed;
    let service: UnitClassService;
    let httpMock: HttpTestingController;
    let elemDefault: IUnitClass;
    let expectedResult: IUnitClass | IUnitClass[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UnitClassService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new UnitClass(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ucCretime: currentDate.format(DATE_TIME_FORMAT),
            ucModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UnitClass', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ucCretime: currentDate.format(DATE_TIME_FORMAT),
            ucModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ucCretime: currentDate,
            ucModtime: currentDate
          },
          returnedFromService
        );

        service.create(new UnitClass()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UnitClass', () => {
        const returnedFromService = Object.assign(
          {
            ucCode: 'BBBBBB',
            ucName: 'BBBBBB',
            ucCretime: currentDate.format(DATE_TIME_FORMAT),
            ucCreid: 'BBBBBB',
            ucModtime: currentDate.format(DATE_TIME_FORMAT),
            ucModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ucCretime: currentDate,
            ucModtime: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UnitClass', () => {
        const returnedFromService = Object.assign(
          {
            ucCode: 'BBBBBB',
            ucName: 'BBBBBB',
            ucCretime: currentDate.format(DATE_TIME_FORMAT),
            ucCreid: 'BBBBBB',
            ucModtime: currentDate.format(DATE_TIME_FORMAT),
            ucModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ucCretime: currentDate,
            ucModtime: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a UnitClass', () => {
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
