import { Moment } from 'moment';

export interface IDevicePatternIntro {
  id?: number;
  devicepatternId?: string;
  devicepatternCode?: string;
  memo?: string;
  creuser?: string;
  cretime?: Moment;
  moduser?: string;
  modtime?: Moment;
}

export class DevicePatternIntro implements IDevicePatternIntro {
  constructor(
    public id?: number,
    public devicepatternId?: string,
    public devicepatternCode?: string,
    public memo?: string,
    public creuser?: string,
    public cretime?: Moment,
    public moduser?: string,
    public modtime?: Moment
  ) {}
}
