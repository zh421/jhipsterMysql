import { element, by, ElementFinder } from 'protractor';

export class EventInterComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-event-inter div table .btn-danger'));
  title = element.all(by.css('jhi-event-inter div h2#page-heading span')).first();
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

export class EventInterUpdatePage {
  pageTitle = element(by.id('jhi-event-inter-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  evninTimeInput = element(by.id('field_evninTime'));
  evninEsCodeInput = element(by.id('field_evninEsCode'));
  evninDeviceidInput = element(by.id('field_evninDeviceid'));
  evninDviModNumInput = element(by.id('field_evninDviModNum'));
  evninDviCodeInput = element(by.id('field_evninDviCode'));
  evninUnitUcCodeInput = element(by.id('field_evninUnitUcCode'));
  evninUnitCodeInput = element(by.id('field_evninUnitCode'));
  evninUnitNameInput = element(by.id('field_evninUnitName'));
  evninUnitAddrInput = element(by.id('field_evninUnitAddr'));
  evninThemeInput = element(by.id('field_evninTheme'));
  evninMemoInput = element(by.id('field_evninMemo'));
  evninIsresInput = element(by.id('field_evninIsres'));
  evninResMemoInput = element(by.id('field_evninResMemo'));
  evninCretimeInput = element(by.id('field_evninCretime'));
  evninCreidInput = element(by.id('field_evninCreid'));
  evninModtimeInput = element(by.id('field_evninModtime'));
  evninModidInput = element(by.id('field_evninModid'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setEvninTimeInput(evninTime: string): Promise<void> {
    await this.evninTimeInput.sendKeys(evninTime);
  }

  async getEvninTimeInput(): Promise<string> {
    return await this.evninTimeInput.getAttribute('value');
  }

  async setEvninEsCodeInput(evninEsCode: string): Promise<void> {
    await this.evninEsCodeInput.sendKeys(evninEsCode);
  }

  async getEvninEsCodeInput(): Promise<string> {
    return await this.evninEsCodeInput.getAttribute('value');
  }

  async setEvninDeviceidInput(evninDeviceid: string): Promise<void> {
    await this.evninDeviceidInput.sendKeys(evninDeviceid);
  }

  async getEvninDeviceidInput(): Promise<string> {
    return await this.evninDeviceidInput.getAttribute('value');
  }

  async setEvninDviModNumInput(evninDviModNum: string): Promise<void> {
    await this.evninDviModNumInput.sendKeys(evninDviModNum);
  }

  async getEvninDviModNumInput(): Promise<string> {
    return await this.evninDviModNumInput.getAttribute('value');
  }

  async setEvninDviCodeInput(evninDviCode: string): Promise<void> {
    await this.evninDviCodeInput.sendKeys(evninDviCode);
  }

  async getEvninDviCodeInput(): Promise<string> {
    return await this.evninDviCodeInput.getAttribute('value');
  }

  async setEvninUnitUcCodeInput(evninUnitUcCode: string): Promise<void> {
    await this.evninUnitUcCodeInput.sendKeys(evninUnitUcCode);
  }

  async getEvninUnitUcCodeInput(): Promise<string> {
    return await this.evninUnitUcCodeInput.getAttribute('value');
  }

  async setEvninUnitCodeInput(evninUnitCode: string): Promise<void> {
    await this.evninUnitCodeInput.sendKeys(evninUnitCode);
  }

  async getEvninUnitCodeInput(): Promise<string> {
    return await this.evninUnitCodeInput.getAttribute('value');
  }

  async setEvninUnitNameInput(evninUnitName: string): Promise<void> {
    await this.evninUnitNameInput.sendKeys(evninUnitName);
  }

  async getEvninUnitNameInput(): Promise<string> {
    return await this.evninUnitNameInput.getAttribute('value');
  }

  async setEvninUnitAddrInput(evninUnitAddr: string): Promise<void> {
    await this.evninUnitAddrInput.sendKeys(evninUnitAddr);
  }

  async getEvninUnitAddrInput(): Promise<string> {
    return await this.evninUnitAddrInput.getAttribute('value');
  }

  async setEvninThemeInput(evninTheme: string): Promise<void> {
    await this.evninThemeInput.sendKeys(evninTheme);
  }

  async getEvninThemeInput(): Promise<string> {
    return await this.evninThemeInput.getAttribute('value');
  }

  async setEvninMemoInput(evninMemo: string): Promise<void> {
    await this.evninMemoInput.sendKeys(evninMemo);
  }

  async getEvninMemoInput(): Promise<string> {
    return await this.evninMemoInput.getAttribute('value');
  }

  getEvninIsresInput(): ElementFinder {
    return this.evninIsresInput;
  }

  async setEvninResMemoInput(evninResMemo: string): Promise<void> {
    await this.evninResMemoInput.sendKeys(evninResMemo);
  }

  async getEvninResMemoInput(): Promise<string> {
    return await this.evninResMemoInput.getAttribute('value');
  }

  async setEvninCretimeInput(evninCretime: string): Promise<void> {
    await this.evninCretimeInput.sendKeys(evninCretime);
  }

  async getEvninCretimeInput(): Promise<string> {
    return await this.evninCretimeInput.getAttribute('value');
  }

  async setEvninCreidInput(evninCreid: string): Promise<void> {
    await this.evninCreidInput.sendKeys(evninCreid);
  }

  async getEvninCreidInput(): Promise<string> {
    return await this.evninCreidInput.getAttribute('value');
  }

  async setEvninModtimeInput(evninModtime: string): Promise<void> {
    await this.evninModtimeInput.sendKeys(evninModtime);
  }

  async getEvninModtimeInput(): Promise<string> {
    return await this.evninModtimeInput.getAttribute('value');
  }

  async setEvninModidInput(evninModid: string): Promise<void> {
    await this.evninModidInput.sendKeys(evninModid);
  }

  async getEvninModidInput(): Promise<string> {
    return await this.evninModidInput.getAttribute('value');
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

export class EventInterDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-eventInter-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-eventInter'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
