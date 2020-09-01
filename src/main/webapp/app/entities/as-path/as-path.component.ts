import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IASPath } from 'app/shared/model/as-path.model';
import { ASPathService } from './as-path.service';
import { ASPathDeleteDialogComponent } from './as-path-delete-dialog.component';

@Component({
  selector: 'jhi-as-path',
  templateUrl: './as-path.component.html',
})
export class ASPathComponent implements OnInit, OnDestroy {
  aSPaths?: IASPath[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected aSPathService: ASPathService,
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
      this.aSPathService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IASPath[]>) => (this.aSPaths = res.body || []));
      return;
    }

    this.aSPathService.query().subscribe((res: HttpResponse<IASPath[]>) => (this.aSPaths = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInASPaths();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IASPath): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInASPaths(): void {
    this.eventSubscriber = this.eventManager.subscribe('aSPathListModification', () => this.loadAll());
  }

  delete(aSPath: IASPath): void {
    const modalRef = this.modalService.open(ASPathDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.aSPath = aSPath;
  }
}
