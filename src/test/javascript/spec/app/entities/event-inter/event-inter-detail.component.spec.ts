import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { EventInterDetailComponent } from 'app/entities/event-inter/event-inter-detail.component';
import { EventInter } from 'app/shared/model/event-inter.model';

describe('Component Tests', () => {
  describe('EventInter Management Detail Component', () => {
    let comp: EventInterDetailComponent;
    let fixture: ComponentFixture<EventInterDetailComponent>;
    const route = ({ data: of({ eventInter: new EventInter(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [EventInterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EventInterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EventInterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load eventInter on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.eventInter).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
