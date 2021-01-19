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
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class AIoTapplicationEntityModule {}
