import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { SensorCodeDetailComponent } from 'app/entities/sensor-code/sensor-code-detail.component';
import { SensorCode } from 'app/shared/model/sensor-code.model';

describe('Component Tests', () => {
  describe('SensorCode Management Detail Component', () => {
    let comp: SensorCodeDetailComponent;
    let fixture: ComponentFixture<SensorCodeDetailComponent>;
    const route = ({ data: of({ sensorCode: new SensorCode(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [SensorCodeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SensorCodeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SensorCodeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sensorCode on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sensorCode).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
