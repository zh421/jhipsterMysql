import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISensorCode, SensorCode } from 'app/shared/model/sensor-code.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SensorCodeService } from './sensor-code.service';
import { SensorCodeDeleteDialogComponent } from './sensor-code-delete-dialog.component';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'jhi-sensor-code',
  templateUrl: './sensor-code.component.html'
})
export class SensorCodeComponent implements OnInit, OnDestroy {
  sensorCodes?: ISensorCode[];
  sensorCodeSearch?: SensorCode;
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  searchForm = this.fb.group({
    scCode: [],
    scName: []
  });

  constructor(
    protected sensorCodeService: SensorCodeService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private fb: FormBuilder
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.sensorCodeService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ISensorCode[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInSensorCodes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISensorCode): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSensorCodes(): void {
    this.eventSubscriber = this.eventManager.subscribe('sensorCodeListModification', () => this.loadPage());
  }

  // 查詢
  searchButton(page?: number): void {
    this.sensorCodeSearch = new SensorCode();
    this.sensorCodeSearch.scCode = this.searchForm.get('scCode')!.value;
    this.sensorCodeSearch.scName = this.searchForm.get('scName')!.value;
    this.search(page);
  }

  search(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    if (page === 1) {
      this.ngbPaginationPage = 1;
    }
    this.sensorCodeService
      .search(
        {
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort()
        },
        this.sensorCodeSearch
      )
      .subscribe(
        (res: HttpResponse<ISensorCode[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  delete(sensorCode: ISensorCode): void {
    const modalRef = this.modalService.open(SensorCodeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sensorCode = sensorCode;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ISensorCode[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/sensor-code'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.sensorCodes = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
