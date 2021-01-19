import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEventStatcode } from 'app/shared/model/event-statcode.model';
import { EventStatcodeService } from './event-statcode.service';

@Component({
  templateUrl: './event-statcode-delete-dialog.component.html'
})
export class EventStatcodeDeleteDialogComponent {
  eventStatcode?: IEventStatcode;

  constructor(
    protected eventStatcodeService: EventStatcodeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.eventStatcodeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('eventStatcodeListModification');
      this.activeModal.close();
    });
  }
}
