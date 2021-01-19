import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEventStatcode, EventStatcode } from 'app/shared/model/event-statcode.model';
import { EventStatcodeService } from './event-statcode.service';

@Component({
  selector: 'jhi-event-statcode-update',
  templateUrl: './event-statcode-update.component.html'
})
export class EventStatcodeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    esCode: [null, [Validators.required, Validators.maxLength(60)]],
    esName: [null, [Validators.required, Validators.maxLength(60)]],
    esCretime: [null, [Validators.required]],
    esCreid: [null, [Validators.required, Validators.maxLength(255)]],
    esModtime: [null, [Validators.required]],
    esModid: [null, [Validators.required, Validators.maxLength(255)]]
  });

  constructor(protected eventStatcodeService: EventStatcodeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ eventStatcode }) => {
      if (!eventStatcode.id) {
        const today = moment().startOf('day');
        eventStatcode.esCretime = today;
        eventStatcode.esModtime = today;
      }

      this.updateForm(eventStatcode);
    });
  }

  updateForm(eventStatcode: IEventStatcode): void {
    this.editForm.patchValue({
      id: eventStatcode.id,
      esCode: eventStatcode.esCode,
      esName: eventStatcode.esName,
      esCretime: eventStatcode.esCretime ? eventStatcode.esCretime.format(DATE_TIME_FORMAT) : null,
      esCreid: eventStatcode.esCreid,
      esModtime: eventStatcode.esModtime ? eventStatcode.esModtime.format(DATE_TIME_FORMAT) : null,
      esModid: eventStatcode.esModid
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const eventStatcode = this.createFromForm();
    if (eventStatcode.id !== undefined) {
      this.subscribeToSaveResponse(this.eventStatcodeService.update(eventStatcode));
    } else {
      this.subscribeToSaveResponse(this.eventStatcodeService.create(eventStatcode));
    }
  }

  private createFromForm(): IEventStatcode {
    return {
      ...new EventStatcode(),
      id: this.editForm.get(['id'])!.value,
      esCode: this.editForm.get(['esCode'])!.value,
      esName: this.editForm.get(['esName'])!.value,
      esCretime: this.editForm.get(['esCretime'])!.value ? moment(this.editForm.get(['esCretime'])!.value, DATE_TIME_FORMAT) : undefined,
      esCreid: this.editForm.get(['esCreid'])!.value,
      esModtime: this.editForm.get(['esModtime'])!.value ? moment(this.editForm.get(['esModtime'])!.value, DATE_TIME_FORMAT) : undefined,
      esModid: this.editForm.get(['esModid'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEventStatcode>>): void {
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
