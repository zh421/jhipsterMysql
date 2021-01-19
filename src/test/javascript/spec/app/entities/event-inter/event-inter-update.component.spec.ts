import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { EventInterUpdateComponent } from 'app/entities/event-inter/event-inter-update.component';
import { EventInterService } from 'app/entities/event-inter/event-inter.service';
import { EventInter } from 'app/shared/model/event-inter.model';

describe('Component Tests', () => {
  describe('EventInter Management Update Component', () => {
    let comp: EventInterUpdateComponent;
    let fixture: ComponentFixture<EventInterUpdateComponent>;
    let service: EventInterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [EventInterUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EventInterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EventInterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EventInterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EventInter(123);
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
        const entity = new EventInter();
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
