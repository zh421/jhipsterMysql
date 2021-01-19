import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEventInter } from 'app/shared/model/event-inter.model';
import { EventInterService } from './event-inter.service';

@Component({
  templateUrl: './event-inter-delete-dialog.component.html'
})
export class EventInterDeleteDialogComponent {
  eventInter?: IEventInter;

  constructor(
    protected eventInterService: EventInterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.eventInterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('eventInterListModification');
      this.activeModal.close();
    });
  }
}
