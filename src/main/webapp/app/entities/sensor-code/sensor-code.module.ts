import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AIoTapplicationSharedModule } from 'app/shared/shared.module';
import { SensorCodeComponent } from './sensor-code.component';
import { SensorCodeDetailComponent } from './sensor-code-detail.component';
import { SensorCodeUpdateComponent } from './sensor-code-update.component';
import { SensorCodeDeleteDialogComponent } from './sensor-code-delete-dialog.component';
import { sensorCodeRoute } from './sensor-code.route';

@NgModule({
  imports: [AIoTapplicationSharedModule, RouterModule.forChild(sensorCodeRoute)],
  declarations: [SensorCodeComponent, SensorCodeDetailComponent, SensorCodeUpdateComponent, SensorCodeDeleteDialogComponent],
  entryComponents: [SensorCodeDeleteDialogComponent]
})
export class AIoTapplicationSensorCodeModule {}
