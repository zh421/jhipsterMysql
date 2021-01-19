import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'device-code',
        loadChildren: () => import('./device-code/device-code.module').then(m => m.AIoTapplicationDeviceCodeModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class AIoTapplicationEntityModule {}
