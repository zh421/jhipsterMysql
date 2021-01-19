import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { NoticeInfoUpdateComponent } from 'app/entities/notice-info/notice-info-update.component';
import { NoticeInfoService } from 'app/entities/notice-info/notice-info.service';
import { NoticeInfo } from 'app/shared/model/notice-info.model';

describe('Component Tests', () => {
  describe('NoticeInfo Management Update Component', () => {
    let comp: NoticeInfoUpdateComponent;
    let fixture: ComponentFixture<NoticeInfoUpdateComponent>;
    let service: NoticeInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [NoticeInfoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NoticeInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NoticeInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NoticeInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NoticeInfo(123);
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
        const entity = new NoticeInfo();
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
