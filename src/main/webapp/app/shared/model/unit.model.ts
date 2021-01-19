import { Moment } from 'moment';

export interface IUnit {
  id?: number;
  unitUcCode?: string;
  unitCode?: string;
  unitName?: string;
  unitAddr?: string;
  unitLongitude?: string;
  unitLatitude?: string;
  unitPic?: string;
  unitPhone?: string;
  unitEmail?: string;
  unitCretime?: Moment;
  unitCreid?: string;
  unitModtime?: Moment;
  unitModid?: string;
  unitLogip?: string;
  unitSignDate?: Moment;
}

export class Unit implements IUnit {
  constructor(
    public id?: number,
    public unitUcCode?: string,
    public unitCode?: string,
    public unitName?: string,
    public unitAddr?: string,
    public unitLongitude?: string,
    public unitLatitude?: string,
    public unitPic?: string,
    public unitPhone?: string,
    public unitEmail?: string,
    public unitCretime?: Moment,
    public unitCreid?: string,
    public unitModtime?: Moment,
    public unitModid?: string,
    public unitLogip?: string,
    public unitSignDate?: Moment
  ) {}
}
