import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISensorCode } from 'app/shared/model/sensor-code.model';

@Component({
  selector: 'jhi-sensor-code-detail',
  templateUrl: './sensor-code-detail.component.html'
})
export class SensorCodeDetailComponent implements OnInit {
  sensorCode: ISensorCode | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sensorCode }) => (this.sensorCode = sensorCode));
  }

  previousState(): void {
    window.history.back();
  }
}
