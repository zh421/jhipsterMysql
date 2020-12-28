import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { DevicePatternIntroDetailComponent } from 'app/entities/device-pattern-intro/device-pattern-intro-detail.component';
import { DevicePatternIntro } from 'app/shared/model/device-pattern-intro.model';

describe('Component Tests', () => {
  describe('DevicePatternIntro Management Detail Component', () => {
    let comp: DevicePatternIntroDetailComponent;
    let fixture: ComponentFixture<DevicePatternIntroDetailComponent>;
    const route = ({ data: of({ devicePatternIntro: new DevicePatternIntro(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [DevicePatternIntroDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DevicePatternIntroDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DevicePatternIntroDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load devicePatternIntro on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.devicePatternIntro).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
