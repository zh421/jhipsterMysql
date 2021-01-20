import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUnit, Unit } from 'app/shared/model/unit.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { UnitService } from './unit.service';
import { UnitDeleteDialogComponent } from './unit-delete-dialog.component';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'jhi-unit',
  templateUrl: './unit.component.html'
})
export class UnitComponent implements OnInit, OnDestroy {
  units?: IUnit[];
  unitSearch?: Unit;
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  searchForm = this.fb.group({
    unitUcCode: [],
    unitName: [],
    unitPic: []
  });

  constructor(
    protected unitService: UnitService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private fb: FormBuilder
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.unitService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IUnit[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInUnits();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUnit): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUnits(): void {
    this.eventSubscriber = this.eventManager.subscribe('unitListModification', () => this.loadPage());
  }

  // 查詢
  searchButton(page?: number): void {
    this.unitSearch = new Unit();
    this.unitSearch.unitUcCode = this.searchForm.get('unitUcCode')!.value;
    this.unitSearch.unitName = this.searchForm.get('unitName')!.value;
    this.unitSearch.unitPic = this.searchForm.get('unitPic')!.value;
    this.search(page);
  }

  search(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    if (page === 1) {
      this.ngbPaginationPage = 1;
    }
    this.unitService
      .search(
        {
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort()
        },
        this.unitSearch
      )
      .subscribe(
        (res: HttpResponse<IUnit[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  delete(unit: IUnit): void {
    const modalRef = this.modalService.open(UnitDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.unit = unit;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IUnit[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/unit'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.units = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
