import { element, by, ElementFinder } from 'protractor';

export class SensorCodeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-sensor-code div table .btn-danger'));
  title = element.all(by.css('jhi-sensor-code div h2#page-heading span')).first();
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

export class SensorCodeUpdatePage {
  pageTitle = element(by.id('jhi-sensor-code-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  scCodeInput = element(by.id('field_scCode'));
  scNameInput = element(by.id('field_scName'));
  scCretimeInput = element(by.id('field_scCretime'));
  scCreidInput = element(by.id('field_scCreid'));
  scModtimeInput = element(by.id('field_scModtime'));
  scModidInput = element(by.id('field_scModid'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setScCodeInput(scCode: string): Promise<void> {
    await this.scCodeInput.sendKeys(scCode);
  }

  async getScCodeInput(): Promise<string> {
    return await this.scCodeInput.getAttribute('value');
  }

  async setScNameInput(scName: string): Promise<void> {
    await this.scNameInput.sendKeys(scName);
  }

  async getScNameInput(): Promise<string> {
    return await this.scNameInput.getAttribute('value');
  }

  async setScCretimeInput(scCretime: string): Promise<void> {
    await this.scCretimeInput.sendKeys(scCretime);
  }

  async getScCretimeInput(): Promise<string> {
    return await this.scCretimeInput.getAttribute('value');
  }

  async setScCreidInput(scCreid: string): Promise<void> {
    await this.scCreidInput.sendKeys(scCreid);
  }

  async getScCreidInput(): Promise<string> {
    return await this.scCreidInput.getAttribute('value');
  }

  async setScModtimeInput(scModtime: string): Promise<void> {
    await this.scModtimeInput.sendKeys(scModtime);
  }

  async getScModtimeInput(): Promise<string> {
    return await this.scModtimeInput.getAttribute('value');
  }

  async setScModidInput(scModid: string): Promise<void> {
    await this.scModidInput.sendKeys(scModid);
  }

  async getScModidInput(): Promise<string> {
    return await this.scModidInput.getAttribute('value');
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

export class SensorCodeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-sensorCode-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-sensorCode'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
