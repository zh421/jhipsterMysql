import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EventStatcodeService } from 'app/entities/event-statcode/event-statcode.service';
import { IEventStatcode, EventStatcode } from 'app/shared/model/event-statcode.model';

describe('Service Tests', () => {
  describe('EventStatcode Service', () => {
    let injector: TestBed;
    let service: EventStatcodeService;
    let httpMock: HttpTestingController;
    let elemDefault: IEventStatcode;
    let expectedResult: IEventStatcode | IEventStatcode[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EventStatcodeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new EventStatcode(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            esCretime: currentDate.format(DATE_TIME_FORMAT),
            esModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EventStatcode', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            esCretime: currentDate.format(DATE_TIME_FORMAT),
            esModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            esCretime: currentDate,
            esModtime: currentDate
          },
          returnedFromService
        );

        service.create(new EventStatcode()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EventStatcode', () => {
        const returnedFromService = Object.assign(
          {
            esCode: 'BBBBBB',
            esName: 'BBBBBB',
            esCretime: currentDate.format(DATE_TIME_FORMAT),
            esCreid: 'BBBBBB',
            esModtime: currentDate.format(DATE_TIME_FORMAT),
            esModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            esCretime: currentDate,
            esModtime: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EventStatcode', () => {
        const returnedFromService = Object.assign(
          {
            esCode: 'BBBBBB',
            esName: 'BBBBBB',
            esCretime: currentDate.format(DATE_TIME_FORMAT),
            esCreid: 'BBBBBB',
            esModtime: currentDate.format(DATE_TIME_FORMAT),
            esModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            esCretime: currentDate,
            esModtime: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EventStatcode', () => {
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
