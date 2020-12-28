import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DevicePatternIntroService } from 'app/entities/device-pattern-intro/device-pattern-intro.service';
import { IDevicePatternIntro, DevicePatternIntro } from 'app/shared/model/device-pattern-intro.model';

describe('Service Tests', () => {
  describe('DevicePatternIntro Service', () => {
    let injector: TestBed;
    let service: DevicePatternIntroService;
    let httpMock: HttpTestingController;
    let elemDefault: IDevicePatternIntro;
    let expectedResult: IDevicePatternIntro | IDevicePatternIntro[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DevicePatternIntroService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DevicePatternIntro(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            cretime: currentDate.format(DATE_TIME_FORMAT),
            modtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DevicePatternIntro', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            cretime: currentDate.format(DATE_TIME_FORMAT),
            modtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cretime: currentDate,
            modtime: currentDate
          },
          returnedFromService
        );

        service.create(new DevicePatternIntro()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DevicePatternIntro', () => {
        const returnedFromService = Object.assign(
          {
            devicepatternId: 'BBBBBB',
            devicepatternCode: 'BBBBBB',
            memo: 'BBBBBB',
            creuser: 'BBBBBB',
            cretime: currentDate.format(DATE_TIME_FORMAT),
            moduser: 'BBBBBB',
            modtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cretime: currentDate,
            modtime: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DevicePatternIntro', () => {
        const returnedFromService = Object.assign(
          {
            devicepatternId: 'BBBBBB',
            devicepatternCode: 'BBBBBB',
            memo: 'BBBBBB',
            creuser: 'BBBBBB',
            cretime: currentDate.format(DATE_TIME_FORMAT),
            moduser: 'BBBBBB',
            modtime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cretime: currentDate,
            modtime: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DevicePatternIntro', () => {
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
