import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AIoTapplicationSharedModule } from 'app/shared/shared.module';
import { DeviceCodeComponent } from './device-code.component';
import { DeviceCodeDetailComponent } from './device-code-detail.component';
import { DeviceCodeUpdateComponent } from './device-code-update.component';
import { DeviceCodeDeleteDialogComponent } from './device-code-delete-dialog.component';
import { deviceCodeRoute } from './device-code.route';

@NgModule({
  imports: [AIoTapplicationSharedModule, RouterModule.forChild(deviceCodeRoute)],
  declarations: [DeviceCodeComponent, DeviceCodeDetailComponent, DeviceCodeUpdateComponent, DeviceCodeDeleteDialogComponent],
  entryComponents: [DeviceCodeDeleteDialogComponent]
})
export class AIoTapplicationDeviceCodeModule {}
