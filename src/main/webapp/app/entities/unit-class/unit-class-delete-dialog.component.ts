import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUnitClass } from 'app/shared/model/unit-class.model';
import { UnitClassService } from './unit-class.service';

@Component({
  templateUrl: './unit-class-delete-dialog.component.html'
})
export class UnitClassDeleteDialogComponent {
  unitClass?: IUnitClass;

  constructor(protected unitClassService: UnitClassService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.unitClassService.delete(id).subscribe(() => {
      this.eventManager.broadcast('unitClassListModification');
      this.activeModal.close();
    });
  }
}
