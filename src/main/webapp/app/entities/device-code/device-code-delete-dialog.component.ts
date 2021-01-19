import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeviceCode } from 'app/shared/model/device-code.model';
import { DeviceCodeService } from './device-code.service';

@Component({
  templateUrl: './device-code-delete-dialog.component.html'
})
export class DeviceCodeDeleteDialogComponent {
  deviceCode?: IDeviceCode;

  constructor(
    protected deviceCodeService: DeviceCodeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.deviceCodeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('deviceCodeListModification');
      this.activeModal.close();
    });
  }
}
