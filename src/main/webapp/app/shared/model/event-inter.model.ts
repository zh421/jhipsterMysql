import { Moment } from 'moment';

export interface IEventInter {
  id?: number;
  evninTime?: Moment;
  evninEsCode?: string;
  evninDeviceid?: string;
  evninDviModNum?: number;
  evninDviCode?: string;
  evninUnitUcCode?: string;
  evninUnitCode?: string;
  evninUnitName?: string;
  evninUnitAddr?: string;
  evninTheme?: string;
  evninMemo?: string;
  evninIsres?: boolean;
  evninResMemo?: string;
  evninCretime?: Moment;
  evninCreid?: string;
  evninModtime?: Moment;
  evninModid?: string;
}

export class EventInter implements IEventInter {
  constructor(
    public id?: number,
    public evninTime?: Moment,
    public evninEsCode?: string,
    public evninDeviceid?: string,
    public evninDviModNum?: number,
    public evninDviCode?: string,
    public evninUnitUcCode?: string,
    public evninUnitCode?: string,
    public evninUnitName?: string,
    public evninUnitAddr?: string,
    public evninTheme?: string,
    public evninMemo?: string,
    public evninIsres?: boolean,
    public evninResMemo?: string,
    public evninCretime?: Moment,
    public evninCreid?: string,
    public evninModtime?: Moment,
    public evninModid?: string
  ) {
    this.evninIsres = this.evninIsres || false;
  }
}
