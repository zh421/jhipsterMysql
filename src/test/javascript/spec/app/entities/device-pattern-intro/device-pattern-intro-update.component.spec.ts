import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { DevicePatternIntroUpdateComponent } from 'app/entities/device-pattern-intro/device-pattern-intro-update.component';
import { DevicePatternIntroService } from 'app/entities/device-pattern-intro/device-pattern-intro.service';
import { DevicePatternIntro } from 'app/shared/model/device-pattern-intro.model';

describe('Component Tests', () => {
  describe('DevicePatternIntro Management Update Component', () => {
    let comp: DevicePatternIntroUpdateComponent;
    let fixture: ComponentFixture<DevicePatternIntroUpdateComponent>;
    let service: DevicePatternIntroService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [DevicePatternIntroUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DevicePatternIntroUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DevicePatternIntroUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DevicePatternIntroService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DevicePatternIntro(123);
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
        const entity = new DevicePatternIntro();
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
