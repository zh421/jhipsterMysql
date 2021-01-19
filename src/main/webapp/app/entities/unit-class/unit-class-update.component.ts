import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUnitClass, UnitClass } from 'app/shared/model/unit-class.model';
import { UnitClassService } from './unit-class.service';

@Component({
  selector: 'jhi-unit-class-update',
  templateUrl: './unit-class-update.component.html'
})
export class UnitClassUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    ucCode: [null, [Validators.required, Validators.maxLength(60)]],
    ucName: [null, [Validators.required, Validators.maxLength(60)]],
    ucCretime: [null, [Validators.required]],
    ucCreid: [null, [Validators.required, Validators.maxLength(255)]],
    ucModtime: [null, [Validators.required]],
    ucModid: [null, [Validators.required, Validators.maxLength(255)]]
  });

  constructor(protected unitClassService: UnitClassService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ unitClass }) => {
      if (!unitClass.id) {
        const today = moment().startOf('day');
        unitClass.ucCretime = today;
        unitClass.ucModtime = today;
      }

      this.updateForm(unitClass);
    });
  }

  updateForm(unitClass: IUnitClass): void {
    this.editForm.patchValue({
      id: unitClass.id,
      ucCode: unitClass.ucCode,
      ucName: unitClass.ucName,
      ucCretime: unitClass.ucCretime ? unitClass.ucCretime.format(DATE_TIME_FORMAT) : null,
      ucCreid: unitClass.ucCreid,
      ucModtime: unitClass.ucModtime ? unitClass.ucModtime.format(DATE_TIME_FORMAT) : null,
      ucModid: unitClass.ucModid
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const unitClass = this.createFromForm();
    if (unitClass.id !== undefined) {
      this.subscribeToSaveResponse(this.unitClassService.update(unitClass));
    } else {
      this.subscribeToSaveResponse(this.unitClassService.create(unitClass));
    }
  }

  private createFromForm(): IUnitClass {
    return {
      ...new UnitClass(),
      id: this.editForm.get(['id'])!.value,
      ucCode: this.editForm.get(['ucCode'])!.value,
      ucName: this.editForm.get(['ucName'])!.value,
      ucCretime: this.editForm.get(['ucCretime'])!.value ? moment(this.editForm.get(['ucCretime'])!.value, DATE_TIME_FORMAT) : undefined,
      ucCreid: this.editForm.get(['ucCreid'])!.value,
      ucModtime: this.editForm.get(['ucModtime'])!.value ? moment(this.editForm.get(['ucModtime'])!.value, DATE_TIME_FORMAT) : undefined,
      ucModid: this.editForm.get(['ucModid'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUnitClass>>): void {
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
