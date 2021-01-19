import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { SensorCodeUpdateComponent } from 'app/entities/sensor-code/sensor-code-update.component';
import { SensorCodeService } from 'app/entities/sensor-code/sensor-code.service';
import { SensorCode } from 'app/shared/model/sensor-code.model';

describe('Component Tests', () => {
  describe('SensorCode Management Update Component', () => {
    let comp: SensorCodeUpdateComponent;
    let fixture: ComponentFixture<SensorCodeUpdateComponent>;
    let service: SensorCodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [SensorCodeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SensorCodeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SensorCodeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SensorCodeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SensorCode(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SensorCode();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
