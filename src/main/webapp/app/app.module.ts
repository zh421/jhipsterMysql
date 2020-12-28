import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { AIoTapplicationSharedModule } from 'app/shared/shared.module';
import { AIoTapplicationCoreModule } from 'app/core/core.module';
import { AIoTapplicationAppRoutingModule } from './app-routing.module';
import { AIoTapplicationHomeModule } from './home/home.module';
import { AIoTapplicationEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    AIoTapplicationSharedModule,
    AIoTapplicationCoreModule,
    AIoTapplicationHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    AIoTapplicationEntityModule,
    AIoTapplicationAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class AIoTapplicationAppModule {}
