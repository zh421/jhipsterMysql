import { element, by, ElementFinder } from 'protractor';

export class UnitClassComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-unit-class div table .btn-danger'));
  title = element.all(by.css('jhi-unit-class div h2#page-heading span')).first();
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

export class UnitClassUpdatePage {
  pageTitle = element(by.id('jhi-unit-class-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  ucCodeInput = element(by.id('field_ucCode'));
  ucNameInput = element(by.id('field_ucName'));
  ucCretimeInput = element(by.id('field_ucCretime'));
  ucCreidInput = element(by.id('field_ucCreid'));
  ucModtimeInput = element(by.id('field_ucModtime'));
  ucModidInput = element(by.id('field_ucModid'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setUcCodeInput(ucCode: string): Promise<void> {
    await this.ucCodeInput.sendKeys(ucCode);
  }

  async getUcCodeInput(): Promise<string> {
    return await this.ucCodeInput.getAttribute('value');
  }

  async setUcNameInput(ucName: string): Promise<void> {
    await this.ucNameInput.sendKeys(ucName);
  }

  async getUcNameInput(): Promise<string> {
    return await this.ucNameInput.getAttribute('value');
  }

  async setUcCretimeInput(ucCretime: string): Promise<void> {
    await this.ucCretimeInput.sendKeys(ucCretime);
  }

  async getUcCretimeInput(): Promise<string> {
    return await this.ucCretimeInput.getAttribute('value');
  }

  async setUcCreidInput(ucCreid: string): Promise<void> {
    await this.ucCreidInput.sendKeys(ucCreid);
  }

  async getUcCreidInput(): Promise<string> {
    return await this.ucCreidInput.getAttribute('value');
  }

  async setUcModtimeInput(ucModtime: string): Promise<void> {
    await this.ucModtimeInput.sendKeys(ucModtime);
  }

  async getUcModtimeInput(): Promise<string> {
    return await this.ucModtimeInput.getAttribute('value');
  }

  async setUcModidInput(ucModid: string): Promise<void> {
    await this.ucModidInput.sendKeys(ucModid);
  }

  async getUcModidInput(): Promise<string> {
    return await this.ucModidInput.getAttribute('value');
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

export class UnitClassDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-unitClass-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-unitClass'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
