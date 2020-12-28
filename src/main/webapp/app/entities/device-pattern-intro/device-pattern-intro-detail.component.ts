import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDevicePatternIntro } from 'app/shared/model/device-pattern-intro.model';

@Component({
  selector: 'jhi-device-pattern-intro-detail',
  templateUrl: './device-pattern-intro-detail.component.html'
})
export class DevicePatternIntroDetailComponent implements OnInit {
  devicePatternIntro: IDevicePatternIntro | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ devicePatternIntro }) => (this.devicePatternIntro = devicePatternIntro));
  }

  previousState(): void {
    window.history.back();
  }
}
