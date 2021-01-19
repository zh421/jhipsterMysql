import { Moment } from 'moment';

export interface ISensorCode {
  id?: number;
  scCode?: string;
  scName?: string;
  scCretime?: Moment;
  scCreid?: string;
  scModtime?: Moment;
  scModid?: string;
}

export class SensorCode implements ISensorCode {
  constructor(
    public id?: number,
    public scCode?: string,
    public scName?: string,
    public scCretime?: Moment,
    public scCreid?: string,
    public scModtime?: Moment,
    public scModid?: string
  ) {}
}
