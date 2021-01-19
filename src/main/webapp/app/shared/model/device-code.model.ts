import { Moment } from 'moment';

export interface IDeviceCode {
  id?: number;
  dviCode?: string;
  dviName?: string;
  dviCretime?: Moment;
  dviCreid?: string;
  dviModtime?: Moment;
  dviModid?: string;
}

export class DeviceCode implements IDeviceCode {
  constructor(
    public id?: number,
    public dviCode?: string,
    public dviName?: string,
    public dviCretime?: Moment,
    public dviCreid?: string,
    public dviModtime?: Moment,
    public dviModid?: string
  ) {}
}
