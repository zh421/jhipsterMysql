import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AIoTapplicationTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { DevicePatternIntroDeleteDialogComponent } from 'app/entities/device-pattern-intro/device-pattern-intro-delete-dialog.component';
import { DevicePatternIntroService } from 'app/entities/device-pattern-intro/device-pattern-intro.service';

describe('Component Tests', () => {
  describe('DevicePatternIntro Management Delete Component', () => {
    let comp: DevicePatternIntroDeleteDialogComponent;
    let fixture: ComponentFixture<DevicePatternIntroDeleteDialogComponent>;
    let service: DevicePatternIntroService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [DevicePatternIntroDeleteDialogComponent]
      })
        .overrideTemplate(DevicePatternIntroDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DevicePatternIntroDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DevicePatternIntroService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
