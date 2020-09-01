import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPRoul } from 'app/shared/model/p-roul.model';
import { PRoulService } from './p-roul.service';
import { PRoulDeleteDialogComponent } from './p-roul-delete-dialog.component';

@Component({
  selector: 'jhi-p-roul',
  templateUrl: './p-roul.component.html',
})
export class PRoulComponent implements OnInit, OnDestroy {
  pRouls?: IPRoul[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected pRoulService: PRoulService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected activatedRoute: ActivatedRoute
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll(): void {
    if (this.currentSearch) {
      this.pRoulService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IPRoul[]>) => (this.pRouls = res.body || []));
      return;
    }

    this.pRoulService.query().subscribe((res: HttpResponse<IPRoul[]>) => (this.pRouls = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPRouls();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPRoul): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPRouls(): void {
    this.eventSubscriber = this.eventManager.subscribe('pRoulListModification', () => this.loadAll());
  }

  delete(pRoul: IPRoul): void {
    const modalRef = this.modalService.open(PRoulDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.pRoul = pRoul;
  }
}
