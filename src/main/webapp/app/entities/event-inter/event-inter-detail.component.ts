import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEventInter } from 'app/shared/model/event-inter.model';

@Component({
  selector: 'jhi-event-inter-detail',
  templateUrl: './event-inter-detail.component.html'
})
export class EventInterDetailComponent implements OnInit {
  eventInter: IEventInter | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ eventInter }) => (this.eventInter = eventInter));
  }

  previousState(): void {
    window.history.back();
  }
}
