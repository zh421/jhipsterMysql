import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AIoTapplicationSharedModule } from 'app/shared/shared.module';
import { EventStatcodeComponent } from './event-statcode.component';
import { EventStatcodeDetailComponent } from './event-statcode-detail.component';
import { EventStatcodeUpdateComponent } from './event-statcode-update.component';
import { EventStatcodeDeleteDialogComponent } from './event-statcode-delete-dialog.component';
import { eventStatcodeRoute } from './event-statcode.route';

@NgModule({
  imports: [AIoTapplicationSharedModule, RouterModule.forChild(eventStatcodeRoute)],
  declarations: [EventStatcodeComponent, EventStatcodeDetailComponent, EventStatcodeUpdateComponent, EventStatcodeDeleteDialogComponent],
  entryComponents: [EventStatcodeDeleteDialogComponent]
})
export class AIoTapplicationEventStatcodeModule {}
