import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEventInter, EventInter } from 'app/shared/model/event-inter.model';
import { EventInterService } from './event-inter.service';

@Component({
  selector: 'jhi-event-inter-update',
  templateUrl: './event-inter-update.component.html'
})
export class EventInterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    evninTime: [null, [Validators.required]],
    evninEsCode: [null, [Validators.required, Validators.maxLength(60)]],
    evninDeviceid: [null, [Validators.required, Validators.maxLength(30)]],
    evninDviModNum: [null, [Validators.required]],
    evninDviCode: [null, [Validators.required, Validators.maxLength(50)]],
    evninUnitUcCode: [null, [Validators.required, Validators.maxLength(60)]],
    evninUnitCode: [null, [Validators.required, Validators.maxLength(50)]],
    evninUnitName: [null, [Validators.required, Validators.maxLength(60)]],
    evninUnitAddr: [null, [Validators.required, Validators.maxLength(255)]],
    evninTheme: [null, [Validators.required, Validators.maxLength(20)]],
    evninMemo: [null, [Validators.maxLength(1023)]],
    evninIsres: [],
    evninResMemo: [null, [Validators.maxLength(1023)]],
    evninCretime: [null, [Validators.required]],
    evninCreid: [null, [Validators.required, Validators.maxLength(255)]],
    evninModtime: [null, [Validators.required]],
    evninModid: [null, [Validators.required, Validators.maxLength(255)]]
  });

  constructor(protected eventInterService: EventInterService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ eventInter }) => {
      if (!eventInter.id) {
        const today = moment().startOf('day');
        eventInter.evninTime = today;
        eventInter.evninCretime = today;
        eventInter.evninModtime = today;
      }

      this.updateForm(eventInter);
    });
  }

  updateForm(eventInter: IEventInter): void {
    this.editForm.patchValue({
      id: eventInter.id,
      evninTime: eventInter.evninTime ? eventInter.evninTime.format(DATE_TIME_FORMAT) : null,
      evninEsCode: eventInter.evninEsCode,
      evninDeviceid: eventInter.evninDeviceid,
      evninDviModNum: eventInter.evninDviModNum,
      evninDviCode: eventInter.evninDviCode,
      evninUnitUcCode: eventInter.evninUnitUcCode,
      evninUnitCode: eventInter.evninUnitCode,
      evninUnitName: eventInter.evninUnitName,
      evninUnitAddr: eventInter.evninUnitAddr,
      evninTheme: eventInter.evninTheme,
      evninMemo: eventInter.evninMemo,
      evninIsres: eventInter.evninIsres,
      evninResMemo: eventInter.evninResMemo,
      evninCretime: eventInter.evninCretime ? eventInter.evninCretime.format(DATE_TIME_FORMAT) : null,
      evninCreid: eventInter.evninCreid,
      evninModtime: eventInter.evninModtime ? eventInter.evninModtime.format(DATE_TIME_FORMAT) : null,
      evninModid: eventInter.evninModid
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const eventInter = this.createFromForm();
    if (eventInter.id !== undefined) {
      this.subscribeToSaveResponse(this.eventInterService.update(eventInter));
    } else {
      this.subscribeToSaveResponse(this.eventInterService.create(eventInter));
    }
  }

  private createFromForm(): IEventInter {
    return {
      ...new EventInter(),
      id: this.editForm.get(['id'])!.value,
      evninTime: this.editForm.get(['evninTime'])!.value ? moment(this.editForm.get(['evninTime'])!.value, DATE_TIME_FORMAT) : undefined,
      evninEsCode: this.editForm.get(['evninEsCode'])!.value,
      evninDeviceid: this.editForm.get(['evninDeviceid'])!.value,
      evninDviModNum: this.editForm.get(['evninDviModNum'])!.value,
      evninDviCode: this.editForm.get(['evninDviCode'])!.value,
      evninUnitUcCode: this.editForm.get(['evninUnitUcCode'])!.value,
      evninUnitCode: this.editForm.get(['evninUnitCode'])!.value,
      evninUnitName: this.editForm.get(['evninUnitName'])!.value,
      evninUnitAddr: this.editForm.get(['evninUnitAddr'])!.value,
      evninTheme: this.editForm.get(['evninTheme'])!.value,
      evninMemo: this.editForm.get(['evninMemo'])!.value,
      evninIsres: this.editForm.get(['evninIsres'])!.value,
      evninResMemo: this.editForm.get(['evninResMemo'])!.value,
      evninCretime: this.editForm.get(['evninCretime'])!.value
        ? moment(this.editForm.get(['evninCretime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      evninCreid: this.editForm.get(['evninCreid'])!.value,
      evninModtime: this.editForm.get(['evninModtime'])!.value
        ? moment(this.editForm.get(['evninModtime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      evninModid: this.editForm.get(['evninModid'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEventInter>>): void {
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
