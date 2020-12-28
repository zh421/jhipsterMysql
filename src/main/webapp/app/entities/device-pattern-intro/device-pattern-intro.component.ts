import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDevicePatternIntro } from 'app/shared/model/device-pattern-intro.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DevicePatternIntroService } from './device-pattern-intro.service';
import { DevicePatternIntroDeleteDialogComponent } from './device-pattern-intro-delete-dialog.component';

@Component({
  selector: 'jhi-device-pattern-intro',
  templateUrl: './device-pattern-intro.component.html'
})
export class DevicePatternIntroComponent implements OnInit, OnDestroy {
  devicePatternIntros?: IDevicePatternIntro[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected devicePatternIntroService: DevicePatternIntroService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.devicePatternIntroService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IDevicePatternIntro[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInDevicePatternIntros();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDevicePatternIntro): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDevicePatternIntros(): void {
    this.eventSubscriber = this.eventManager.subscribe('devicePatternIntroListModification', () => this.loadPage());
  }

  delete(devicePatternIntro: IDevicePatternIntro): void {
    const modalRef = this.modalService.open(DevicePatternIntroDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.devicePatternIntro = devicePatternIntro;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IDevicePatternIntro[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/device-pattern-intro'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.devicePatternIntros = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
