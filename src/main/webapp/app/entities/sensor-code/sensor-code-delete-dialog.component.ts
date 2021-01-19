import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISensorCode } from 'app/shared/model/sensor-code.model';
import { SensorCodeService } from './sensor-code.service';

@Component({
  templateUrl: './sensor-code-delete-dialog.component.html'
})
export class SensorCodeDeleteDialogComponent {
  sensorCode?: ISensorCode;

  constructor(
    protected sensorCodeService: SensorCodeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sensorCodeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sensorCodeListModification');
      this.activeModal.close();
    });
  }
}
