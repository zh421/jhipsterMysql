import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { EventStatcodeDetailComponent } from 'app/entities/event-statcode/event-statcode-detail.component';
import { EventStatcode } from 'app/shared/model/event-statcode.model';

describe('Component Tests', () => {
  describe('EventStatcode Management Detail Component', () => {
    let comp: EventStatcodeDetailComponent;
    let fixture: ComponentFixture<EventStatcodeDetailComponent>;
    const route = ({ data: of({ eventStatcode: new EventStatcode(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [EventStatcodeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EventStatcodeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EventStatcodeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load eventStatcode on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.eventStatcode).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
