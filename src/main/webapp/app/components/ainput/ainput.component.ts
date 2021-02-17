import { Component, OnInit, Input } from '@angular/core';
import { Basic } from 'app/shared/model/basic';

@Component({
  selector: 'jhi-ainput',
  templateUrl: './ainput.component.html',
  styleUrls: ['./ainput.component.scss']
})
export class AinputComponent implements OnInit {
  @Input() basicData?: Basic;
  fieldName?: string;
  typeCode?: string;
  classCode?: string;
  idCode?: string;
  nameCode?: string;
  formControlCode?: string;

  constructor() {
    this.fieldName = this.basicData!.fieldCode;
    this.typeCode = this.basicData!.typeCode;
    this.classCode = this.basicData!.classCode;
    this.idCode = this.basicData!.idCode;
    this.nameCode = this.basicData!.nameCode;
    this.formControlCode = this.basicData!.formControlCode;
  }

  ngOnInit(): void {}
}
