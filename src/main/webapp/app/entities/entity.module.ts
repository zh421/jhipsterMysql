import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'device-pattern-intro',
        loadChildren: () =>
          import('./device-pattern-intro/device-pattern-intro.module').then(m => m.AIoTapplicationDevicePatternIntroModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class AIoTapplicationEntityModule {}
