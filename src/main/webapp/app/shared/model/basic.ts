export interface IBasic {
  fieldCode?: string;
  typeCode?: string;
  classCode?: string;
  idCode?: string;
  nameCode?: string;
  formControlCode?: string;
  regexType?: string;
  required?: string[];
}

export class Basic implements IBasic {
  constructor(
    public fieldCode?: string,
    public typeCode?: string,
    public classCode?: string,
    public idCode?: string,
    public nameCode?: string,
    public formControlCode?: string,
    public regexType?: string,
    public required?: string[]
  ) {}
}
