import { Moment } from 'moment';

export interface INoticeInfo {
  id?: number;
  notiCaseid?: string;
  notiTitle?: string;
  notiContent?: string;
  notiInfotype?: string;
  notiStarttime?: Moment;
  notiEndtime?: Moment;
  notiStatcode?: string;
  notiCretime?: Moment;
  notiCreid?: string;
  notiModtime?: Moment;
  notiModid?: string;
}

export class NoticeInfo implements INoticeInfo {
  constructor(
    public id?: number,
    public notiCaseid?: string,
    public notiTitle?: string,
    public notiContent?: string,
    public notiInfotype?: string,
    public notiStarttime?: Moment,
    public notiEndtime?: Moment,
    public notiStatcode?: string,
    public notiCretime?: Moment,
    public notiCreid?: string,
    public notiModtime?: Moment,
    public notiModid?: string
  ) {}
}
