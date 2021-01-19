import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EventInterService } from 'app/entities/event-inter/event-inter.service';
import { IEventInter, EventInter } from 'app/shared/model/event-inter.model';

describe('Service Tests', () => {
  describe('EventInter Service', () => {
    let injector: TestBed;
    let service: EventInterService;
    let httpMock: HttpTestingController;
    let elemDefault: IEventInter;
    let expectedResult: IEventInter | IEventInter[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EventInterService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new EventInter(
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            evninTime: currentDate.format(DATE_TIME_FORMAT),
            evninCretime: currentDate.format(DATE_TIME_FORMAT),
            evninModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EventInter', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            evninTime: currentDate.format(DATE_TIME_FORMAT),
            evninCretime: currentDate.format(DATE_TIME_FORMAT),
            evninModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            evninTime: currentDate,
            evninCretime: currentDate,
            evninModtime: currentDate
          },
          returnedFromService
        );

        service.create(new EventInter()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EventInter', () => {
        const returnedFromService = Object.assign(
          {
            evninTime: currentDate.format(DATE_TIME_FORMAT),
            evninEsCode: 'BBBBBB',
            evninDeviceid: 'BBBBBB',
            evninDviModNum: 1,
            evninDviCode: 'BBBBBB',
            evninUnitUcCode: 'BBBBBB',
            evninUnitCode: 'BBBBBB',
            evninUnitName: 'BBBBBB',
            evninUnitAddr: 'BBBBBB',
            evninTheme: 'BBBBBB',
            evninMemo: 'BBBBBB',
            evninIsres: true,
            evninResMemo: 'BBBBBB',
            evninCretime: currentDate.format(DATE_TIME_FORMAT),
            evninCreid: 'BBBBBB',
            evninModtime: currentDate.format(DATE_TIME_FORMAT),
            evninModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            evninTime: currentDate,
            evninCretime: currentDate,
            evninModtime: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EventInter', () => {
        const returnedFromService = Object.assign(
          {
            evninTime: currentDate.format(DATE_TIME_FORMAT),
            evninEsCode: 'BBBBBB',
            evninDeviceid: 'BBBBBB',
            evninDviModNum: 1,
            evninDviCode: 'BBBBBB',
            evninUnitUcCode: 'BBBBBB',
            evninUnitCode: 'BBBBBB',
            evninUnitName: 'BBBBBB',
            evninUnitAddr: 'BBBBBB',
            evninTheme: 'BBBBBB',
            evninMemo: 'BBBBBB',
            evninIsres: true,
            evninResMemo: 'BBBBBB',
            evninCretime: currentDate.format(DATE_TIME_FORMAT),
            evninCreid: 'BBBBBB',
            evninModtime: currentDate.format(DATE_TIME_FORMAT),
            evninModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            evninTime: currentDate,
            evninCretime: currentDate,
            evninModtime: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EventInter', () => {
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
