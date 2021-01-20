import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { EventStatcode, IEventStatcode } from 'app/shared/model/event-statcode.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { EventStatcodeService } from './event-statcode.service';
import { EventStatcodeDeleteDialogComponent } from './event-statcode-delete-dialog.component';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'jhi-event-statcode',
  templateUrl: './event-statcode.component.html'
})
export class EventStatcodeComponent implements OnInit, OnDestroy {
  eventStatcodes?: IEventStatcode[];
  eventStatcodeSearch?: EventStatcode;
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  searchForm = this.fb.group({
    esCode: [],
    esName: []
  });

  constructor(
    protected eventStatcodeService: EventStatcodeService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private fb: FormBuilder
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.eventStatcodeService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IEventStatcode[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInEventStatcodes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEventStatcode): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEventStatcodes(): void {
    this.eventSubscriber = this.eventManager.subscribe('eventStatcodeListModification', () => this.loadPage());
  }

  // 查詢
  searchButton(page?: number): void {
    this.eventStatcodeSearch = new EventStatcode();
    this.eventStatcodeSearch.esCode = this.searchForm.get('esCode')!.value;
    this.eventStatcodeSearch.esName = this.searchForm.get('esName')!.value;
    this.search(page);
  }

  search(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    if (page === 1) {
      this.ngbPaginationPage = 1;
    }
    this.eventStatcodeService
      .search(
        {
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort()
        },
        this.eventStatcodeSearch
      )
      .subscribe(
        (res: HttpResponse<IEventStatcode[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  delete(eventStatcode: IEventStatcode): void {
    const modalRef = this.modalService.open(EventStatcodeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.eventStatcode = eventStatcode;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IEventStatcode[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/event-statcode'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.eventStatcodes = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
