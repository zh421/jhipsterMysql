import { element, by, ElementFinder } from 'protractor';

export class DeviceCodeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-device-code div table .btn-danger'));
  title = element.all(by.css('jhi-device-code div h2#page-heading span')).first();
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

export class DeviceCodeUpdatePage {
  pageTitle = element(by.id('jhi-device-code-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dviCodeInput = element(by.id('field_dviCode'));
  dviNameInput = element(by.id('field_dviName'));
  dviCretimeInput = element(by.id('field_dviCretime'));
  dviCreidInput = element(by.id('field_dviCreid'));
  dviModtimeInput = element(by.id('field_dviModtime'));
  dviModidInput = element(by.id('field_dviModid'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDviCodeInput(dviCode: string): Promise<void> {
    await this.dviCodeInput.sendKeys(dviCode);
  }

  async getDviCodeInput(): Promise<string> {
    return await this.dviCodeInput.getAttribute('value');
  }

  async setDviNameInput(dviName: string): Promise<void> {
    await this.dviNameInput.sendKeys(dviName);
  }

  async getDviNameInput(): Promise<string> {
    return await this.dviNameInput.getAttribute('value');
  }

  async setDviCretimeInput(dviCretime: string): Promise<void> {
    await this.dviCretimeInput.sendKeys(dviCretime);
  }

  async getDviCretimeInput(): Promise<string> {
    return await this.dviCretimeInput.getAttribute('value');
  }

  async setDviCreidInput(dviCreid: string): Promise<void> {
    await this.dviCreidInput.sendKeys(dviCreid);
  }

  async getDviCreidInput(): Promise<string> {
    return await this.dviCreidInput.getAttribute('value');
  }

  async setDviModtimeInput(dviModtime: string): Promise<void> {
    await this.dviModtimeInput.sendKeys(dviModtime);
  }

  async getDviModtimeInput(): Promise<string> {
    return await this.dviModtimeInput.getAttribute('value');
  }

  async setDviModidInput(dviModid: string): Promise<void> {
    await this.dviModidInput.sendKeys(dviModid);
  }

  async getDviModidInput(): Promise<string> {
    return await this.dviModidInput.getAttribute('value');
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

export class DeviceCodeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-deviceCode-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-deviceCode'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
