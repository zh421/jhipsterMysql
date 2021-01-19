import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEventStatcode } from 'app/shared/model/event-statcode.model';

@Component({
  selector: 'jhi-event-statcode-detail',
  templateUrl: './event-statcode-detail.component.html'
})
export class EventStatcodeDetailComponent implements OnInit {
  eventStatcode: IEventStatcode | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ eventStatcode }) => (this.eventStatcode = eventStatcode));
  }

  previousState(): void {
    window.history.back();
  }
}
