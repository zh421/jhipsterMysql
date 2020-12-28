import { element, by, ElementFinder } from 'protractor';

export class DevicePatternIntroComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-device-pattern-intro div table .btn-danger'));
  title = element.all(by.css('jhi-device-pattern-intro div h2#page-heading span')).first();
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

export class DevicePatternIntroUpdatePage {
  pageTitle = element(by.id('jhi-device-pattern-intro-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  devicepatternIdInput = element(by.id('field_devicepatternId'));
  devicepatternCodeInput = element(by.id('field_devicepatternCode'));
  memoInput = element(by.id('field_memo'));
  creuserInput = element(by.id('field_creuser'));
  cretimeInput = element(by.id('field_cretime'));
  moduserInput = element(by.id('field_moduser'));
  modtimeInput = element(by.id('field_modtime'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDevicepatternIdInput(devicepatternId: string): Promise<void> {
    await this.devicepatternIdInput.sendKeys(devicepatternId);
  }

  async getDevicepatternIdInput(): Promise<string> {
    return await this.devicepatternIdInput.getAttribute('value');
  }

  async setDevicepatternCodeInput(devicepatternCode: string): Promise<void> {
    await this.devicepatternCodeInput.sendKeys(devicepatternCode);
  }

  async getDevicepatternCodeInput(): Promise<string> {
    return await this.devicepatternCodeInput.getAttribute('value');
  }

  async setMemoInput(memo: string): Promise<void> {
    await this.memoInput.sendKeys(memo);
  }

  async getMemoInput(): Promise<string> {
    return await this.memoInput.getAttribute('value');
  }

  async setCreuserInput(creuser: string): Promise<void> {
    await this.creuserInput.sendKeys(creuser);
  }

  async getCreuserInput(): Promise<string> {
    return await this.creuserInput.getAttribute('value');
  }

  async setCretimeInput(cretime: string): Promise<void> {
    await this.cretimeInput.sendKeys(cretime);
  }

  async getCretimeInput(): Promise<string> {
    return await this.cretimeInput.getAttribute('value');
  }

  async setModuserInput(moduser: string): Promise<void> {
    await this.moduserInput.sendKeys(moduser);
  }

  async getModuserInput(): Promise<string> {
    return await this.moduserInput.getAttribute('value');
  }

  async setModtimeInput(modtime: string): Promise<void> {
    await this.modtimeInput.sendKeys(modtime);
  }

  async getModtimeInput(): Promise<string> {
    return await this.modtimeInput.getAttribute('value');
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

export class DevicePatternIntroDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-devicePatternIntro-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-devicePatternIntro'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
