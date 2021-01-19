import { element, by, ElementFinder } from 'protractor';

export class NoticeInfoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-notice-info div table .btn-danger'));
  title = element.all(by.css('jhi-notice-info div h2#page-heading span')).first();
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

export class NoticeInfoUpdatePage {
  pageTitle = element(by.id('jhi-notice-info-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  notiCaseidInput = element(by.id('field_notiCaseid'));
  notiTitleInput = element(by.id('field_notiTitle'));
  notiContentInput = element(by.id('field_notiContent'));
  notiInfotypeInput = element(by.id('field_notiInfotype'));
  notiStarttimeInput = element(by.id('field_notiStarttime'));
  notiEndtimeInput = element(by.id('field_notiEndtime'));
  notiStatcodeInput = element(by.id('field_notiStatcode'));
  notiCretimeInput = element(by.id('field_notiCretime'));
  notiCreidInput = element(by.id('field_notiCreid'));
  notiModtimeInput = element(by.id('field_notiModtime'));
  notiModidInput = element(by.id('field_notiModid'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNotiCaseidInput(notiCaseid: string): Promise<void> {
    await this.notiCaseidInput.sendKeys(notiCaseid);
  }

  async getNotiCaseidInput(): Promise<string> {
    return await this.notiCaseidInput.getAttribute('value');
  }

  async setNotiTitleInput(notiTitle: string): Promise<void> {
    await this.notiTitleInput.sendKeys(notiTitle);
  }

  async getNotiTitleInput(): Promise<string> {
    return await this.notiTitleInput.getAttribute('value');
  }

  async setNotiContentInput(notiContent: string): Promise<void> {
    await this.notiContentInput.sendKeys(notiContent);
  }

  async getNotiContentInput(): Promise<string> {
    return await this.notiContentInput.getAttribute('value');
  }

  async setNotiInfotypeInput(notiInfotype: string): Promise<void> {
    await this.notiInfotypeInput.sendKeys(notiInfotype);
  }

  async getNotiInfotypeInput(): Promise<string> {
    return await this.notiInfotypeInput.getAttribute('value');
  }

  async setNotiStarttimeInput(notiStarttime: string): Promise<void> {
    await this.notiStarttimeInput.sendKeys(notiStarttime);
  }

  async getNotiStarttimeInput(): Promise<string> {
    return await this.notiStarttimeInput.getAttribute('value');
  }

  async setNotiEndtimeInput(notiEndtime: string): Promise<void> {
    await this.notiEndtimeInput.sendKeys(notiEndtime);
  }

  async getNotiEndtimeInput(): Promise<string> {
    return await this.notiEndtimeInput.getAttribute('value');
  }

  async setNotiStatcodeInput(notiStatcode: string): Promise<void> {
    await this.notiStatcodeInput.sendKeys(notiStatcode);
  }

  async getNotiStatcodeInput(): Promise<string> {
    return await this.notiStatcodeInput.getAttribute('value');
  }

  async setNotiCretimeInput(notiCretime: string): Promise<void> {
    await this.notiCretimeInput.sendKeys(notiCretime);
  }

  async getNotiCretimeInput(): Promise<string> {
    return await this.notiCretimeInput.getAttribute('value');
  }

  async setNotiCreidInput(notiCreid: string): Promise<void> {
    await this.notiCreidInput.sendKeys(notiCreid);
  }

  async getNotiCreidInput(): Promise<string> {
    return await this.notiCreidInput.getAttribute('value');
  }

  async setNotiModtimeInput(notiModtime: string): Promise<void> {
    await this.notiModtimeInput.sendKeys(notiModtime);
  }

  async getNotiModtimeInput(): Promise<string> {
    return await this.notiModtimeInput.getAttribute('value');
  }

  async setNotiModidInput(notiModid: string): Promise<void> {
    await this.notiModidInput.sendKeys(notiModid);
  }

  async getNotiModidInput(): Promise<string> {
    return await this.notiModidInput.getAttribute('value');
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

export class NoticeInfoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-noticeInfo-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-noticeInfo'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
