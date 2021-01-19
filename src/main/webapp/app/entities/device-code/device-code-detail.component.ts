import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeviceCode } from 'app/shared/model/device-code.model';

@Component({
  selector: 'jhi-device-code-detail',
  templateUrl: './device-code-detail.component.html'
})
export class DeviceCodeDetailComponent implements OnInit {
  deviceCode: IDeviceCode | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deviceCode }) => (this.deviceCode = deviceCode));
  }

  previousState(): void {
    window.history.back();
  }
}
