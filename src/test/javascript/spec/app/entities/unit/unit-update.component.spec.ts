import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { UnitUpdateComponent } from 'app/entities/unit/unit-update.component';
import { UnitService } from 'app/entities/unit/unit.service';
import { Unit } from 'app/shared/model/unit.model';

describe('Component Tests', () => {
  describe('Unit Management Update Component', () => {
    let comp: UnitUpdateComponent;
    let fixture: ComponentFixture<UnitUpdateComponent>;
    let service: UnitService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [UnitUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UnitUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UnitUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UnitService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Unit(123);
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
        const entity = new Unit();
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
