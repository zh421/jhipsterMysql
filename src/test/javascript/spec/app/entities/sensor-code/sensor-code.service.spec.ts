import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SensorCodeService } from 'app/entities/sensor-code/sensor-code.service';
import { ISensorCode, SensorCode } from 'app/shared/model/sensor-code.model';

describe('Service Tests', () => {
  describe('SensorCode Service', () => {
    let injector: TestBed;
    let service: SensorCodeService;
    let httpMock: HttpTestingController;
    let elemDefault: ISensorCode;
    let expectedResult: ISensorCode | ISensorCode[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SensorCodeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SensorCode(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            scCretime: currentDate.format(DATE_TIME_FORMAT),
            scModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SensorCode', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            scCretime: currentDate.format(DATE_TIME_FORMAT),
            scModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            scCretime: currentDate,
            scModtime: currentDate
          },
          returnedFromService
        );

        service.create(new SensorCode()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SensorCode', () => {
        const returnedFromService = Object.assign(
          {
            scCode: 'BBBBBB',
            scName: 'BBBBBB',
            scCretime: currentDate.format(DATE_TIME_FORMAT),
            scCreid: 'BBBBBB',
            scModtime: currentDate.format(DATE_TIME_FORMAT),
            scModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            scCretime: currentDate,
            scModtime: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SensorCode', () => {
        const returnedFromService = Object.assign(
          {
            scCode: 'BBBBBB',
            scName: 'BBBBBB',
            scCretime: currentDate.format(DATE_TIME_FORMAT),
            scCreid: 'BBBBBB',
            scModtime: currentDate.format(DATE_TIME_FORMAT),
            scModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            scCretime: currentDate,
            scModtime: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SensorCode', () => {
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
