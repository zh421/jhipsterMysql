import { element, by, ElementFinder } from 'protractor';

export class UnitComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-unit div table .btn-danger'));
  title = element.all(by.css('jhi-unit div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class UnitUpdatePage {
  pageTitle = element(by.id('jhi-unit-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  unitUcCodeInput = element(by.id('field_unitUcCode'));
  unitCodeInput = element(by.id('field_unitCode'));
  unitNameInput = element(by.id('field_unitName'));
  unitAddrInput = element(by.id('field_unitAddr'));
  unitLongitudeInput = element(by.id('field_unitLongitude'));
  unitLatitudeInput = element(by.id('field_unitLatitude'));
  unitPicInput = element(by.id('field_unitPic'));
  unitPhoneInput = element(by.id('field_unitPhone'));
  unitEmailInput = element(by.id('field_unitEmail'));
  unitCretimeInput = element(by.id('field_unitCretime'));
  unitCreidInput = element(by.id('field_unitCreid'));
  unitModtimeInput = element(by.id('field_unitModtime'));
  unitModidInput = element(by.id('field_unitModid'));
  unitLogipInput = element(by.id('field_unitLogip'));
  unitSignDateInput = element(by.id('field_unitSignDate'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setUnitUcCodeInput(unitUcCode: string): Promise<void> {
    await this.unitUcCodeInput.sendKeys(unitUcCode);
  }

  async getUnitUcCodeInput(): Promise<string> {
    return await this.unitUcCodeInput.getAttribute('value');
  }

  async setUnitCodeInput(unitCode: string): Promise<void> {
    await this.unitCodeInput.sendKeys(unitCode);
  }

  async getUnitCodeInput(): Promise<string> {
    return await this.unitCodeInput.getAttribute('value');
  }

  async setUnitNameInput(unitName: string): Promise<void> {
    await this.unitNameInput.sendKeys(unitName);
  }

  async getUnitNameInput(): Promise<string> {
    return await this.unitNameInput.getAttribute('value');
  }

  async setUnitAddrInput(unitAddr: string): Promise<void> {
    await this.unitAddrInput.sendKeys(unitAddr);
  }

  async getUnitAddrInput(): Promise<string> {
    return await this.unitAddrInput.getAttribute('value');
  }

  async setUnitLongitudeInput(unitLongitude: string): Promise<void> {
    await this.unitLongitudeInput.sendKeys(unitLongitude);
  }

  async getUnitLongitudeInput(): Promise<string> {
    return await this.unitLongitudeInput.getAttribute('value');
  }

  async setUnitLatitudeInput(unitLatitude: string): Promise<void> {
    await this.unitLatitudeInput.sendKeys(unitLatitude);
  }

  async getUnitLatitudeInput(): Promise<string> {
    return await this.unitLatitudeInput.getAttribute('value');
  }

  async setUnitPicInput(unitPic: string): Promise<void> {
    await this.unitPicInput.sendKeys(unitPic);
  }

  async getUnitPicInput(): Promise<string> {
    return await this.unitPicInput.getAttribute('value');
  }

  async setUnitPhoneInput(unitPhone: string): Promise<void> {
    await this.unitPhoneInput.sendKeys(unitPhone);
  }

  async getUnitPhoneInput(): Promise<string> {
    return await this.unitPhoneInput.getAttribute('value');
  }

  async setUnitEmailInput(unitEmail: string): Promise<void> {
    await this.unitEmailInput.sendKeys(unitEmail);
  }

  async getUnitEmailInput(): Promise<string> {
    return await this.unitEmailInput.getAttribute('value');
  }

  async setUnitCretimeInput(unitCretime: string): Promise<void> {
    await this.unitCretimeInput.sendKeys(unitCretime);
  }

  async getUnitCretimeInput(): Promise<string> {
    return await this.unitCretimeInput.getAttribute('value');
  }

  async setUnitCreidInput(unitCreid: string): Promise<void> {
    await this.unitCreidInput.sendKeys(unitCreid);
  }

  async getUnitCreidInput(): Promise<string> {
    return await this.unitCreidInput.getAttribute('value');
  }

  async setUnitModtimeInput(unitModtime: string): Promise<void> {
    await this.unitModtimeInput.sendKeys(unitModtime);
  }

  async getUnitModtimeInput(): Promise<string> {
    return await this.unitModtimeInput.getAttribute('value');
  }

  async setUnitModidInput(unitModid: string): Promise<void> {
    await this.unitModidInput.sendKeys(unitModid);
  }

  async getUnitModidInput(): Promise<string> {
    return await this.unitModidInput.getAttribute('value');
  }

  async setUnitLogipInput(unitLogip: string): Promise<void> {
    await this.unitLogipInput.sendKeys(unitLogip);
  }

  async getUnitLogipInput(): Promise<string> {
    return await this.unitLogipInput.getAttribute('value');
  }

  async setUnitSignDateInput(unitSignDate: string): Promise<void> {
    await this.unitSignDateInput.sendKeys(unitSignDate);
  }

  async getUnitSignDateInput(): Promise<string> {
    return await this.unitSignDateInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class UnitDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-unit-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-unit'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
