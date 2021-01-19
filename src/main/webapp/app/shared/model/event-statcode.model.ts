import { Moment } from 'moment';

export interface IEventStatcode {
  id?: number;
  esCode?: string;
  esName?: string;
  esCretime?: Moment;
  esCreid?: string;
  esModtime?: Moment;
  esModid?: string;
}

export class EventStatcode implements IEventStatcode {
  constructor(
    public id?: number,
    public esCode?: string,
    public esName?: string,
    public esCretime?: Moment,
    public esCreid?: string,
    public esModtime?: Moment,
    public esModid?: string
  ) {}
}
