import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUnitClass, UnitClass } from 'app/shared/model/unit-class.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { UnitClassService } from './unit-class.service';
import { UnitClassDeleteDialogComponent } from './unit-class-delete-dialog.component';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'jhi-unit-class',
  templateUrl: './unit-class.component.html'
})
export class UnitClassComponent implements OnInit, OnDestroy {
  unitClasses?: IUnitClass[];
  unitClasseSearch?: UnitClass;
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  searchForm = this.fb.group({
    ucCode: [],
    ucName: []
  });

  constructor(
    protected unitClassService: UnitClassService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private fb: FormBuilder
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.unitClassService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IUnitClass[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInUnitClasses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUnitClass): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUnitClasses(): void {
    this.eventSubscriber = this.eventManager.subscribe('unitClassListModification', () => this.loadPage());
  }

  // 查詢
  searchButton(page?: number): void {
    this.unitClasseSearch = new UnitClass();
    this.unitClasseSearch.ucCode = this.searchForm.get('ucCode')!.value;
    this.unitClasseSearch.ucName = this.searchForm.get('ucName')!.value;
    this.search(page);
  }

  search(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    if (page === 1) {
      this.ngbPaginationPage = 1;
    }
    this.unitClassService
      .search(
        {
          page: pageToLoad - 1,
          size: this.itemsPerPage,
          sort: this.sort()
        },
        this.unitClasseSearch
      )
      .subscribe(
        (res: HttpResponse<IUnitClass[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  delete(unitClass: IUnitClass): void {
    const modalRef = this.modalService.open(UnitClassDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.unitClass = unitClass;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IUnitClass[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/unit-class'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.unitClasses = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
