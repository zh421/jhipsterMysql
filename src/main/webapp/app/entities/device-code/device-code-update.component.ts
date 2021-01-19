import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDeviceCode, DeviceCode } from 'app/shared/model/device-code.model';
import { DeviceCodeService } from './device-code.service';

@Component({
  selector: 'jhi-device-code-update',
  templateUrl: './device-code-update.component.html'
})
export class DeviceCodeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dviCode: [null, [Validators.required, Validators.maxLength(60)]],
    dviName: [null, [Validators.required, Validators.maxLength(60)]],
    dviCretime: [null, [Validators.required]],
    dviCreid: [null, [Validators.required, Validators.maxLength(255)]],
    dviModtime: [null, [Validators.required]],
    dviModid: [null, [Validators.required, Validators.maxLength(255)]]
  });

  constructor(protected deviceCodeService: DeviceCodeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deviceCode }) => {
      if (!deviceCode.id) {
        const today = moment().startOf('day');
        deviceCode.dviCretime = today;
        deviceCode.dviModtime = today;
      }

      this.updateForm(deviceCode);
    });
  }

  updateForm(deviceCode: IDeviceCode): void {
    this.editForm.patchValue({
      id: deviceCode.id,
      dviCode: deviceCode.dviCode,
      dviName: deviceCode.dviName,
      dviCretime: deviceCode.dviCretime ? deviceCode.dviCretime.format(DATE_TIME_FORMAT) : null,
      dviCreid: deviceCode.dviCreid,
      dviModtime: deviceCode.dviModtime ? deviceCode.dviModtime.format(DATE_TIME_FORMAT) : null,
      dviModid: deviceCode.dviModid
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deviceCode = this.createFromForm();
    if (deviceCode.id !== undefined) {
      this.subscribeToSaveResponse(this.deviceCodeService.update(deviceCode));
    } else {
      this.subscribeToSaveResponse(this.deviceCodeService.create(deviceCode));
    }
  }

  private createFromForm(): IDeviceCode {
    return {
      ...new DeviceCode(),
      id: this.editForm.get(['id'])!.value,
      dviCode: this.editForm.get(['dviCode'])!.value,
      dviName: this.editForm.get(['dviName'])!.value,
      dviCretime: this.editForm.get(['dviCretime'])!.value ? moment(this.editForm.get(['dviCretime'])!.value, DATE_TIME_FORMAT) : undefined,
      dviCreid: this.editForm.get(['dviCreid'])!.value,
      dviModtime: this.editForm.get(['dviModtime'])!.value ? moment(this.editForm.get(['dviModtime'])!.value, DATE_TIME_FORMAT) : undefined,
      dviModid: this.editForm.get(['dviModid'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeviceCode>>): void {
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
