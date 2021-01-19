import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { DeviceCodeUpdateComponent } from 'app/entities/device-code/device-code-update.component';
import { DeviceCodeService } from 'app/entities/device-code/device-code.service';
import { DeviceCode } from 'app/shared/model/device-code.model';

describe('Component Tests', () => {
  describe('DeviceCode Management Update Component', () => {
    let comp: DeviceCodeUpdateComponent;
    let fixture: ComponentFixture<DeviceCodeUpdateComponent>;
    let service: DeviceCodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [DeviceCodeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DeviceCodeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DeviceCodeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DeviceCodeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DeviceCode(123);
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
        const entity = new DeviceCode();
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
