import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AIoTapplicationSharedModule } from 'app/shared/shared.module';
import { EventInterComponent } from './event-inter.component';
import { EventInterDetailComponent } from './event-inter-detail.component';
import { EventInterUpdateComponent } from './event-inter-update.component';
import { EventInterDeleteDialogComponent } from './event-inter-delete-dialog.component';
import { eventInterRoute } from './event-inter.route';

@NgModule({
  imports: [AIoTapplicationSharedModule, RouterModule.forChild(eventInterRoute)],
  declarations: [EventInterComponent, EventInterDetailComponent, EventInterUpdateComponent, EventInterDeleteDialogComponent],
  entryComponents: [EventInterDeleteDialogComponent]
})
export class AIoTapplicationEventInterModule {}
