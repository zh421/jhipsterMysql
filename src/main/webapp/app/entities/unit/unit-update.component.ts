import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUnit, Unit } from 'app/shared/model/unit.model';
import { UnitService } from './unit.service';

@Component({
  selector: 'jhi-unit-update',
  templateUrl: './unit-update.component.html'
})
export class UnitUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    unitUcCode: [null, [Validators.required, Validators.maxLength(60)]],
    unitCode: [null, [Validators.required, Validators.maxLength(50)]],
    unitName: [null, [Validators.required, Validators.maxLength(60)]],
    unitAddr: [null, [Validators.required, Validators.maxLength(255)]],
    unitLongitude: [null, [Validators.required, Validators.maxLength(10)]],
    unitLatitude: [null, [Validators.required, Validators.maxLength(10)]],
    unitPic: [null, [Validators.required, Validators.maxLength(50)]],
    unitPhone: [null, [Validators.required, Validators.maxLength(20)]],
    unitEmail: [null, [Validators.required, Validators.maxLength(255)]],
    unitCretime: [null, [Validators.required]],
    unitCreid: [null, [Validators.required, Validators.maxLength(255)]],
    unitModtime: [null, [Validators.required]],
    unitModid: [null, [Validators.required, Validators.maxLength(255)]],
    unitLogip: [null, [Validators.required, Validators.maxLength(30)]],
    unitSignDate: [null, [Validators.required]]
  });

  constructor(protected unitService: UnitService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ unit }) => {
      if (!unit.id) {
        const today = moment().startOf('day');
        unit.unitCretime = today;
        unit.unitModtime = today;
        unit.unitSignDate = today;
      }

      this.updateForm(unit);
    });
  }

  updateForm(unit: IUnit): void {
    this.editForm.patchValue({
      id: unit.id,
      unitUcCode: unit.unitUcCode,
      unitCode: unit.unitCode,
      unitName: unit.unitName,
      unitAddr: unit.unitAddr,
      unitLongitude: unit.unitLongitude,
      unitLatitude: unit.unitLatitude,
      unitPic: unit.unitPic,
      unitPhone: unit.unitPhone,
      unitEmail: unit.unitEmail,
      unitCretime: unit.unitCretime ? unit.unitCretime.format(DATE_TIME_FORMAT) : null,
      unitCreid: unit.unitCreid,
      unitModtime: unit.unitModtime ? unit.unitModtime.format(DATE_TIME_FORMAT) : null,
      unitModid: unit.unitModid,
      unitLogip: unit.unitLogip,
      unitSignDate: unit.unitSignDate ? unit.unitSignDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const unit = this.createFromForm();
    if (unit.id !== undefined) {
      this.subscribeToSaveResponse(this.unitService.update(unit));
    } else {
      this.subscribeToSaveResponse(this.unitService.create(unit));
    }
  }

  private createFromForm(): IUnit {
    return {
      ...new Unit(),
      id: this.editForm.get(['id'])!.value,
      unitUcCode: this.editForm.get(['unitUcCode'])!.value,
      unitCode: this.editForm.get(['unitCode'])!.value,
      unitName: this.editForm.get(['unitName'])!.value,
      unitAddr: this.editForm.get(['unitAddr'])!.value,
      unitLongitude: this.editForm.get(['unitLongitude'])!.value,
      unitLatitude: this.editForm.get(['unitLatitude'])!.value,
      unitPic: this.editForm.get(['unitPic'])!.value,
      unitPhone: this.editForm.get(['unitPhone'])!.value,
      unitEmail: this.editForm.get(['unitEmail'])!.value,
      unitCretime: this.editForm.get(['unitCretime'])!.value
        ? moment(this.editForm.get(['unitCretime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      unitCreid: this.editForm.get(['unitCreid'])!.value,
      unitModtime: this.editForm.get(['unitModtime'])!.value
        ? moment(this.editForm.get(['unitModtime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      unitModid: this.editForm.get(['unitModid'])!.value,
      unitLogip: this.editForm.get(['unitLogip'])!.value,
      unitSignDate: this.editForm.get(['unitSignDate'])!.value
        ? moment(this.editForm.get(['unitSignDate'])!.value, DATE_TIME_FORMAT)
        : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUnit>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
