import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { NoticeInfoService } from 'app/entities/notice-info/notice-info.service';
import { INoticeInfo, NoticeInfo } from 'app/shared/model/notice-info.model';

describe('Service Tests', () => {
  describe('NoticeInfo Service', () => {
    let injector: TestBed;
    let service: NoticeInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: INoticeInfo;
    let expectedResult: INoticeInfo | INoticeInfo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(NoticeInfoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new NoticeInfo(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
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
            notiStarttime: currentDate.format(DATE_TIME_FORMAT),
            notiEndtime: currentDate.format(DATE_TIME_FORMAT),
            notiCretime: currentDate.format(DATE_TIME_FORMAT),
            notiModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a NoticeInfo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            notiStarttime: currentDate.format(DATE_TIME_FORMAT),
            notiEndtime: currentDate.format(DATE_TIME_FORMAT),
            notiCretime: currentDate.format(DATE_TIME_FORMAT),
            notiModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            notiStarttime: currentDate,
            notiEndtime: currentDate,
            notiCretime: currentDate,
            notiModtime: currentDate
          },
          returnedFromService
        );

        service.create(new NoticeInfo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a NoticeInfo', () => {
        const returnedFromService = Object.assign(
          {
            notiCaseid: 'BBBBBB',
            notiTitle: 'BBBBBB',
            notiContent: 'BBBBBB',
            notiInfotype: 'BBBBBB',
            notiStarttime: currentDate.format(DATE_TIME_FORMAT),
            notiEndtime: currentDate.format(DATE_TIME_FORMAT),
            notiStatcode: 'BBBBBB',
            notiCretime: currentDate.format(DATE_TIME_FORMAT),
            notiCreid: 'BBBBBB',
            notiModtime: currentDate.format(DATE_TIME_FORMAT),
            notiModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            notiStarttime: currentDate,
            notiEndtime: currentDate,
            notiCretime: currentDate,
            notiModtime: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of NoticeInfo', () => {
        const returnedFromService = Object.assign(
          {
            notiCaseid: 'BBBBBB',
            notiTitle: 'BBBBBB',
            notiContent: 'BBBBBB',
            notiInfotype: 'BBBBBB',
            notiStarttime: currentDate.format(DATE_TIME_FORMAT),
            notiEndtime: currentDate.format(DATE_TIME_FORMAT),
            notiStatcode: 'BBBBBB',
            notiCretime: currentDate.format(DATE_TIME_FORMAT),
            notiCreid: 'BBBBBB',
            notiModtime: currentDate.format(DATE_TIME_FORMAT),
            notiModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            notiStarttime: currentDate,
            notiEndtime: currentDate,
            notiCretime: currentDate,
            notiModtime: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a NoticeInfo', () => {
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
