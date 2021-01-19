import { Moment } from 'moment';

export interface IUnitClass {
  id?: number;
  ucCode?: string;
  ucName?: string;
  ucCretime?: Moment;
  ucCreid?: string;
  ucModtime?: Moment;
  ucModid?: string;
}

export class UnitClass implements IUnitClass {
  constructor(
    public id?: number,
    public ucCode?: string,
    public ucName?: string,
    public ucCretime?: Moment,
    public ucCreid?: string,
    public ucModtime?: Moment,
    public ucModid?: string
  ) {}
}
