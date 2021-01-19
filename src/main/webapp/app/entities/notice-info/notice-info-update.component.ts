import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { INoticeInfo, NoticeInfo } from 'app/shared/model/notice-info.model';
import { NoticeInfoService } from './notice-info.service';

@Component({
  selector: 'jhi-notice-info-update',
  templateUrl: './notice-info-update.component.html'
})
export class NoticeInfoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    notiCaseid: [null, [Validators.required, Validators.maxLength(255)]],
    notiTitle: [null, [Validators.required, Validators.maxLength(255)]],
    notiContent: [null, [Validators.required, Validators.maxLength(2048)]],
    notiInfotype: [null, [Validators.required, Validators.maxLength(255)]],
    notiStarttime: [null, [Validators.required]],
    notiEndtime: [null, [Validators.required]],
    notiStatcode: [null, [Validators.required, Validators.maxLength(255)]],
    notiCretime: [null, [Validators.required]],
    notiCreid: [null, [Validators.required, Validators.maxLength(255)]],
    notiModtime: [null, [Validators.required]],
    notiModid: [null, [Validators.required, Validators.maxLength(255)]]
  });

  constructor(protected noticeInfoService: NoticeInfoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ noticeInfo }) => {
      if (!noticeInfo.id) {
        const today = moment().startOf('day');
        noticeInfo.notiStarttime = today;
        noticeInfo.notiEndtime = today;
        noticeInfo.notiCretime = today;
        noticeInfo.notiModtime = today;
      }

      this.updateForm(noticeInfo);
    });
  }

  updateForm(noticeInfo: INoticeInfo): void {
    this.editForm.patchValue({
      id: noticeInfo.id,
      notiCaseid: noticeInfo.notiCaseid,
      notiTitle: noticeInfo.notiTitle,
      notiContent: noticeInfo.notiContent,
      notiInfotype: noticeInfo.notiInfotype,
      notiStarttime: noticeInfo.notiStarttime ? noticeInfo.notiStarttime.format(DATE_TIME_FORMAT) : null,
      notiEndtime: noticeInfo.notiEndtime ? noticeInfo.notiEndtime.format(DATE_TIME_FORMAT) : null,
      notiStatcode: noticeInfo.notiStatcode,
      notiCretime: noticeInfo.notiCretime ? noticeInfo.notiCretime.format(DATE_TIME_FORMAT) : null,
      notiCreid: noticeInfo.notiCreid,
      notiModtime: noticeInfo.notiModtime ? noticeInfo.notiModtime.format(DATE_TIME_FORMAT) : null,
      notiModid: noticeInfo.notiModid
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const noticeInfo = this.createFromForm();
    if (noticeInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.noticeInfoService.update(noticeInfo));
    } else {
      this.subscribeToSaveResponse(this.noticeInfoService.create(noticeInfo));
    }
  }

  private createFromForm(): INoticeInfo {
    return {
      ...new NoticeInfo(),
      id: this.editForm.get(['id'])!.value,
      notiCaseid: this.editForm.get(['notiCaseid'])!.value,
      notiTitle: this.editForm.get(['notiTitle'])!.value,
      notiContent: this.editForm.get(['notiContent'])!.value,
      notiInfotype: this.editForm.get(['notiInfotype'])!.value,
      notiStarttime: this.editForm.get(['notiStarttime'])!.value
        ? moment(this.editForm.get(['notiStarttime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      notiEndtime: this.editForm.get(['notiEndtime'])!.value
        ? moment(this.editForm.get(['notiEndtime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      notiStatcode: this.editForm.get(['notiStatcode'])!.value,
      notiCretime: this.editForm.get(['notiCretime'])!.value
        ? moment(this.editForm.get(['notiCretime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      notiCreid: this.editForm.get(['notiCreid'])!.value,
      notiModtime: this.editForm.get(['notiModtime'])!.value
        ? moment(this.editForm.get(['notiModtime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      notiModid: this.editForm.get(['notiModid'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INoticeInfo>>): void {
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
