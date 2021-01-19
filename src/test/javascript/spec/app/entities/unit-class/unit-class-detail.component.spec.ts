import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { UnitClassDetailComponent } from 'app/entities/unit-class/unit-class-detail.component';
import { UnitClass } from 'app/shared/model/unit-class.model';

describe('Component Tests', () => {
  describe('UnitClass Management Detail Component', () => {
    let comp: UnitClassDetailComponent;
    let fixture: ComponentFixture<UnitClassDetailComponent>;
    const route = ({ data: of({ unitClass: new UnitClass(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [UnitClassDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UnitClassDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UnitClassDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load unitClass on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.unitClass).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
