import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDevicePatternIntro } from 'app/shared/model/device-pattern-intro.model';
import { DevicePatternIntroService } from './device-pattern-intro.service';

@Component({
  templateUrl: './device-pattern-intro-delete-dialog.component.html'
})
export class DevicePatternIntroDeleteDialogComponent {
  devicePatternIntro?: IDevicePatternIntro;

  constructor(
    protected devicePatternIntroService: DevicePatternIntroService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.devicePatternIntroService.delete(id).subscribe(() => {
      this.eventManager.broadcast('devicePatternIntroListModification');
      this.activeModal.close();
    });
  }
}
