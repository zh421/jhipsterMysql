import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INoticeInfo } from 'app/shared/model/notice-info.model';

@Component({
  selector: 'jhi-notice-info-detail',
  templateUrl: './notice-info-detail.component.html'
})
export class NoticeInfoDetailComponent implements OnInit {
  noticeInfo: INoticeInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ noticeInfo }) => (this.noticeInfo = noticeInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
