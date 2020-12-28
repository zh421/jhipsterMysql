import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDevicePatternIntro, DevicePatternIntro } from 'app/shared/model/device-pattern-intro.model';
import { DevicePatternIntroService } from './device-pattern-intro.service';

@Component({
  selector: 'jhi-device-pattern-intro-update',
  templateUrl: './device-pattern-intro-update.component.html'
})
export class DevicePatternIntroUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    devicepatternId: [null, [Validators.required, Validators.maxLength(100)]],
    devicepatternCode: [null, [Validators.required, Validators.maxLength(100)]],
    memo: [null, [Validators.maxLength(255)]],
    creuser: [null, [Validators.required, Validators.maxLength(140)]],
    cretime: [null, [Validators.required]],
    moduser: [null, [Validators.required, Validators.maxLength(140)]],
    modtime: [null, [Validators.required]]
  });

  constructor(
    protected devicePatternIntroService: DevicePatternIntroService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ devicePatternIntro }) => {
      if (!devicePatternIntro.id) {
        const today = moment().startOf('day');
        devicePatternIntro.cretime = today;
        devicePatternIntro.modtime = today;
      }

      this.updateForm(devicePatternIntro);
    });
  }

  updateForm(devicePatternIntro: IDevicePatternIntro): void {
    this.editForm.patchValue({
      id: devicePatternIntro.id,
      devicepatternId: devicePatternIntro.devicepatternId,
      devicepatternCode: devicePatternIntro.devicepatternCode,
      memo: devicePatternIntro.memo,
      creuser: devicePatternIntro.creuser,
      cretime: devicePatternIntro.cretime ? devicePatternIntro.cretime.format(DATE_TIME_FORMAT) : null,
      moduser: devicePatternIntro.moduser,
      modtime: devicePatternIntro.modtime ? devicePatternIntro.modtime.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const devicePatternIntro = this.createFromForm();
    if (devicePatternIntro.id !== undefined) {
      this.subscribeToSaveResponse(this.devicePatternIntroService.update(devicePatternIntro));
    } else {
      this.subscribeToSaveResponse(this.devicePatternIntroService.create(devicePatternIntro));
    }
  }

  private createFromForm(): IDevicePatternIntro {
    return {
      ...new DevicePatternIntro(),
      id: this.editForm.get(['id'])!.value,
      devicepatternId: this.editForm.get(['devicepatternId'])!.value,
      devicepatternCode: this.editForm.get(['devicepatternCode'])!.value,
      memo: this.editForm.get(['memo'])!.value,
      creuser: this.editForm.get(['creuser'])!.value,
      cretime: this.editForm.get(['cretime'])!.value ? moment(this.editForm.get(['cretime'])!.value, DATE_TIME_FORMAT) : undefined,
      moduser: this.editForm.get(['moduser'])!.value,
      modtime: this.editForm.get(['modtime'])!.value ? moment(this.editForm.get(['modtime'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDevicePatternIntro>>): void {
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
