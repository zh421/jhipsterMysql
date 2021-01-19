import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AIoTapplicationTestModule } from '../../../test.module';
import { NoticeInfoDetailComponent } from 'app/entities/notice-info/notice-info-detail.component';
import { NoticeInfo } from 'app/shared/model/notice-info.model';

describe('Component Tests', () => {
  describe('NoticeInfo Management Detail Component', () => {
    let comp: NoticeInfoDetailComponent;
    let fixture: ComponentFixture<NoticeInfoDetailComponent>;
    const route = ({ data: of({ noticeInfo: new NoticeInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AIoTapplicationTestModule],
        declarations: [NoticeInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NoticeInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NoticeInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load noticeInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.noticeInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
