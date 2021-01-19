import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DeviceCodeService } from 'app/entities/device-code/device-code.service';
import { IDeviceCode, DeviceCode } from 'app/shared/model/device-code.model';

describe('Service Tests', () => {
  describe('DeviceCode Service', () => {
    let injector: TestBed;
    let service: DeviceCodeService;
    let httpMock: HttpTestingController;
    let elemDefault: IDeviceCode;
    let expectedResult: IDeviceCode | IDeviceCode[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DeviceCodeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DeviceCode(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dviCretime: currentDate.format(DATE_TIME_FORMAT),
            dviModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DeviceCode', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dviCretime: currentDate.format(DATE_TIME_FORMAT),
            dviModtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dviCretime: currentDate,
            dviModtime: currentDate
          },
          returnedFromService
        );

        service.create(new DeviceCode()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DeviceCode', () => {
        const returnedFromService = Object.assign(
          {
            dviCode: 'BBBBBB',
            dviName: 'BBBBBB',
            dviCretime: currentDate.format(DATE_TIME_FORMAT),
            dviCreid: 'BBBBBB',
            dviModtime: currentDate.format(DATE_TIME_FORMAT),
            dviModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dviCretime: currentDate,
            dviModtime: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DeviceCode', () => {
        const returnedFromService = Object.assign(
          {
            dviCode: 'BBBBBB',
            dviName: 'BBBBBB',
            dviCretime: currentDate.format(DATE_TIME_FORMAT),
            dviCreid: 'BBBBBB',
            dviModtime: currentDate.format(DATE_TIME_FORMAT),
            dviModid: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dviCretime: currentDate,
            dviModtime: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DeviceCode', () => {
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
