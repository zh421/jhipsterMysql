import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUnitClass } from 'app/shared/model/unit-class.model';

@Component({
  selector: 'jhi-unit-class-detail',
  templateUrl: './unit-class-detail.component.html'
})
export class UnitClassDetailComponent implements OnInit {
  unitClass: IUnitClass | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ unitClass }) => (this.unitClass = unitClass));
  }

  previousState(): void {
    window.history.back();
  }
}
