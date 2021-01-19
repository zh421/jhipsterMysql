import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AIoTapplicationSharedModule } from 'app/shared/shared.module';
import { UnitClassComponent } from './unit-class.component';
import { UnitClassDetailComponent } from './unit-class-detail.component';
import { UnitClassUpdateComponent } from './unit-class-update.component';
import { UnitClassDeleteDialogComponent } from './unit-class-delete-dialog.component';
import { unitClassRoute } from './unit-class.route';

@NgModule({
  imports: [AIoTapplicationSharedModule, RouterModule.forChild(unitClassRoute)],
  declarations: [UnitClassComponent, UnitClassDetailComponent, UnitClassUpdateComponent, UnitClassDeleteDialogComponent],
  entryComponents: [UnitClassDeleteDialogComponent]
})
export class AIoTapplicationUnitClassModule {}
