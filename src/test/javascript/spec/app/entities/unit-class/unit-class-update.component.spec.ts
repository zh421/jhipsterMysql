import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { UnitClassUpdateComponent } from 'app/entities/unit-class/unit-class-update.component';
import { UnitClassService } from 'app/entities/unit-class/unit-class.service';
import { UnitClass } from 'app/shared/model/unit-class.model';

describe('Component Tests', () => {
  describe('UnitClass Management Update Component', () => {
    let comp: UnitClassUpdateComponent;
    let fixture: ComponentFixture<UnitClassUpdateComponent>;
    let service: UnitClassService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [UnitClassUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UnitClassUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UnitClassUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UnitClassService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UnitClass(123);
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
        const entity = new UnitClass();
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
