import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AIoTapplicationSharedModule } from 'app/shared/shared.module';
import { NoticeInfoComponent } from './notice-info.component';
import { NoticeInfoDetailComponent } from './notice-info-detail.component';
import { NoticeInfoUpdateComponent } from './notice-info-update.component';
import { NoticeInfoDeleteDialogComponent } from './notice-info-delete-dialog.component';
import { noticeInfoRoute } from './notice-info.route';

@NgModule({
  imports: [AIoTapplicationSharedModule, RouterModule.forChild(noticeInfoRoute)],
  declarations: [NoticeInfoComponent, NoticeInfoDetailComponent, NoticeInfoUpdateComponent, NoticeInfoDeleteDialogComponent],
  entryComponents: [NoticeInfoDeleteDialogComponent]
})
export class AIoTapplicationNoticeInfoModule {}
