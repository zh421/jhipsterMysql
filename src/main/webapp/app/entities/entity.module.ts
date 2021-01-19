import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'device-code',
        loadChildren: () => import('./device-code/device-code.module').then(m => m.AIoTapplicationDeviceCodeModule)
      },
      {
        path: 'event-inter',
        loadChildren: () => import('./event-inter/event-inter.module').then(m => m.AIoTapplicationEventInterModule)
      },
      {
        path: 'event-statcode',
        loadChildren: () => import('./event-statcode/event-statcode.module').then(m => m.AIoTapplicationEventStatcodeModule)
      },
      {
        path: 'sensor-code',
        loadChildren: () => import('./sensor-code/sensor-code.module').then(m => m.AIoTapplicationSensorCodeModule)
      },
      {
        path: 'unit',
        loadChildren: () => import('./unit/unit.module').then(m => m.AIoTapplicationUnitModule)
      },
      {
        path: 'unit-class',
        loadChildren: () => import('./unit-class/unit-class.module').then(m => m.AIoTapplicationUnitClassModule)
      },
      {
        path: 'notice-info',
        loadChildren: () => import('./notice-info/notice-info.module').then(m => m.AIoTapplicationNoticeInfoModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class AIoTapplicationEntityModule {}
