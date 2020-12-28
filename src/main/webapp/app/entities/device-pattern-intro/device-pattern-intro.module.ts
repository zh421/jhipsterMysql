import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AIoTapplicationSharedModule } from 'app/shared/shared.module';
import { DevicePatternIntroComponent } from './device-pattern-intro.component';
import { DevicePatternIntroDetailComponent } from './device-pattern-intro-detail.component';
import { DevicePatternIntroUpdateComponent } from './device-pattern-intro-update.component';
import { DevicePatternIntroDeleteDialogComponent } from './device-pattern-intro-delete-dialog.component';
import { devicePatternIntroRoute } from './device-pattern-intro.route';

@NgModule({
  imports: [AIoTapplicationSharedModule, RouterModule.forChild(devicePatternIntroRoute)],
  declarations: [
    DevicePatternIntroComponent,
    DevicePatternIntroDetailComponent,
    DevicePatternIntroUpdateComponent,
    DevicePatternIntroDeleteDialogComponent
  ],
  entryComponents: [DevicePatternIntroDeleteDialogComponent]
})
export class AIoTapplicationDevicePatternIntroModule {}
