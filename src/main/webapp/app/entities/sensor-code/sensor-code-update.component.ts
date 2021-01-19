import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISensorCode, SensorCode } from 'app/shared/model/sensor-code.model';
import { SensorCodeService } from './sensor-code.service';

@Component({
  selector: 'jhi-sensor-code-update',
  templateUrl: './sensor-code-update.component.html'
})
export class SensorCodeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    scCode: [null, [Validators.required, Validators.maxLength(60)]],
    scName: [null, [Validators.required, Validators.maxLength(60)]],
    scCretime: [null, [Validators.required]],
    scCreid: [null, [Validators.required, Validators.maxLength(255)]],
    scModtime: [null, [Validators.required]],
    scModid: [null, [Validators.required, Validators.maxLength(255)]]
  });

  constructor(protected sensorCodeService: SensorCodeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sensorCode }) => {
      if (!sensorCode.id) {
        const today = moment().startOf('day');
        sensorCode.scCretime = today;
        sensorCode.scModtime = today;
      }

      this.updateForm(sensorCode);
    });
  }

  updateForm(sensorCode: ISensorCode): void {
    this.editForm.patchValue({
      id: sensorCode.id,
      scCode: sensorCode.scCode,
      scName: sensorCode.scName,
      scCretime: sensorCode.scCretime ? sensorCode.scCretime.format(DATE_TIME_FORMAT) : null,
      scCreid: sensorCode.scCreid,
      scModtime: sensorCode.scModtime ? sensorCode.scModtime.format(DATE_TIME_FORMAT) : null,
      scModid: sensorCode.scModid
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sensorCode = this.createFromForm();
    if (sensorCode.id !== undefined) {
      this.subscribeToSaveResponse(this.sensorCodeService.update(sensorCode));
    } else {
      this.subscribeToSaveResponse(this.sensorCodeService.create(sensorCode));
    }
  }

  private createFromForm(): ISensorCode {
    return {
      ...new SensorCode(),
      id: this.editForm.get(['id'])!.value,
      scCode: this.editForm.get(['scCode'])!.value,
      scName: this.editForm.get(['scName'])!.value,
      scCretime: this.editForm.get(['scCretime'])!.value ? moment(this.editForm.get(['scCretime'])!.value, DATE_TIME_FORMAT) : undefined,
      scCreid: this.editForm.get(['scCreid'])!.value,
      scModtime: this.editForm.get(['scModtime'])!.value ? moment(this.editForm.get(['scModtime'])!.value, DATE_TIME_FORMAT) : undefined,
      scModid: this.editForm.get(['scModid'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISensorCode>>): void {
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
