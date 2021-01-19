import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { DeviceCodeDetailComponent } from 'app/entities/device-code/device-code-detail.component';
import { DeviceCode } from 'app/shared/model/device-code.model';

describe('Component Tests', () => {
  describe('DeviceCode Management Detail Component', () => {
    let comp: DeviceCodeDetailComponent;
    let fixture: ComponentFixture<DeviceCodeDetailComponent>;
    const route = ({ data: of({ deviceCode: new DeviceCode(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [DeviceCodeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DeviceCodeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DeviceCodeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load deviceCode on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.deviceCode).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
