import { element, by, ElementFinder } from 'protractor';

export class EventStatcodeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-event-statcode div table .btn-danger'));
  title = element.all(by.css('jhi-event-statcode div h2#page-heading span')).first();
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

export class EventStatcodeUpdatePage {
  pageTitle = element(by.id('jhi-event-statcode-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  esCodeInput = element(by.id('field_esCode'));
  esNameInput = element(by.id('field_esName'));
  esCretimeInput = element(by.id('field_esCretime'));
  esCreidInput = element(by.id('field_esCreid'));
  esModtimeInput = element(by.id('field_esModtime'));
  esModidInput = element(by.id('field_esModid'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setEsCodeInput(esCode: string): Promise<void> {
    await this.esCodeInput.sendKeys(esCode);
  }

  async getEsCodeInput(): Promise<string> {
    return await this.esCodeInput.getAttribute('value');
  }

  async setEsNameInput(esName: string): Promise<void> {
    await this.esNameInput.sendKeys(esName);
  }

  async getEsNameInput(): Promise<string> {
    return await this.esNameInput.getAttribute('value');
  }

  async setEsCretimeInput(esCretime: string): Promise<void> {
    await this.esCretimeInput.sendKeys(esCretime);
  }

  async getEsCretimeInput(): Promise<string> {
    return await this.esCretimeInput.getAttribute('value');
  }

  async setEsCreidInput(esCreid: string): Promise<void> {
    await this.esCreidInput.sendKeys(esCreid);
  }

  async getEsCreidInput(): Promise<string> {
    return await this.esCreidInput.getAttribute('value');
  }

  async setEsModtimeInput(esModtime: string): Promise<void> {
    await this.esModtimeInput.sendKeys(esModtime);
  }

  async getEsModtimeInput(): Promise<string> {
    return await this.esModtimeInput.getAttribute('value');
  }

  async setEsModidInput(esModid: string): Promise<void> {
    await this.esModidInput.sendKeys(esModid);
  }

  async getEsModidInput(): Promise<string> {
    return await this.esModidInput.getAttribute('value');
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

export class EventStatcodeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-eventStatcode-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-eventStatcode'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
