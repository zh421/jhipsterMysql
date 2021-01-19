import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INoticeInfo } from 'app/shared/model/notice-info.model';
import { NoticeInfoService } from './notice-info.service';

@Component({
  templateUrl: './notice-info-delete-dialog.component.html'
})
export class NoticeInfoDeleteDialogComponent {
  noticeInfo?: INoticeInfo;

  constructor(
    protected noticeInfoService: NoticeInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.noticeInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('noticeInfoListModification');
      this.activeModal.close();
    });
  }
}
