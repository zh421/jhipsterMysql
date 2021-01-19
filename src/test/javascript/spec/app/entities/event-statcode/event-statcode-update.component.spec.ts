import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { EventStatcodeUpdateComponent } from 'app/entities/event-statcode/event-statcode-update.component';
import { EventStatcodeService } from 'app/entities/event-statcode/event-statcode.service';
import { EventStatcode } from 'app/shared/model/event-statcode.model';

describe('Component Tests', () => {
  describe('EventStatcode Management Update Component', () => {
    let comp: EventStatcodeUpdateComponent;
    let fixture: ComponentFixture<EventStatcodeUpdateComponent>;
    let service: EventStatcodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [EventStatcodeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EventStatcodeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EventStatcodeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EventStatcodeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EventStatcode(123);
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
        const entity = new EventStatcode();
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
