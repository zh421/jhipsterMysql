import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { DeviceCode, IDeviceCode } from 'app/shared/model/device-code.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DeviceCodeService } from './device-code.service';
import { DeviceCodeDeleteDialogComponent } from './device-code-delete-dialog.component';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'jhi-device-code',
  templateUrl: './device-code.component.html'
})
export class DeviceCodeComponent implements OnInit, OnDestroy {
  deviceCodes?: IDeviceCode[];
  deviceCodeSearch?: DeviceCode;
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  searchForm = this.fb.group({
    dviCode: [],
    dviName: []
  });

  constructor(
    protected deviceCodeService: DeviceCodeService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private fb: FormBuilder
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.deviceCodeService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IDeviceCode[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInDeviceCodes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDeviceCode): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDeviceCodes(): void {
    this.eventSubscriber = this.eventManager.subscribe('deviceCodeListModification', () => this.loadPage());
  }
  // 查詢
  searchButton(page?: number): void {
    this.deviceCodeSearch = new DeviceCode();
    this.deviceCodeSearch.dviCode = this.searchForm.get('dviCode')!.value;
    this.deviceCodeSearch.dviName = this.searchForm.get('dviName')!.value;
    this.search(page);
  }

  search(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    if (page === 1) {
      this.ngbPaginationPage = 1;
    }
    this.deviceCodeService
      .search(
        {
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort()
        },
        this.deviceCodeSearch
      )
      .subscribe(
        (res: HttpResponse<IDeviceCode[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  delete(deviceCode: IDeviceCode): void {
    const modalRef = this.modalService.open(DeviceCodeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.deviceCode = deviceCode;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IDeviceCode[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/device-code'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.deviceCodes = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
