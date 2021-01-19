import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EventStatcodeComponentsPage, EventStatcodeDeleteDialog, EventStatcodeUpdatePage } from './event-statcode.page-object';

const expect = chai.expect;

describe('EventStatcode e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let eventStatcodeComponentsPage: EventStatcodeComponentsPage;
  let eventStatcodeUpdatePage: EventStatcodeUpdatePage;
  let eventStatcodeDeleteDialog: EventStatcodeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EventStatcodes', async () => {
    await navBarPage.goToEntity('event-statcode');
    eventStatcodeComponentsPage = new EventStatcodeComponentsPage();
    await browser.wait(ec.visibilityOf(eventStatcodeComponentsPage.title), 5000);
    expect(await eventStatcodeComponentsPage.getTitle()).to.eq('aIoTapplicationApp.eventStatcode.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(eventStatcodeComponentsPage.entities), ec.visibilityOf(eventStatcodeComponentsPage.noResult)),
      1000
    );
  });

  it('should load create EventStatcode page', async () => {
    await eventStatcodeComponentsPage.clickOnCreateButton();
    eventStatcodeUpdatePage = new EventStatcodeUpdatePage();
    expect(await eventStatcodeUpdatePage.getPageTitle()).to.eq('aIoTapplicationApp.eventStatcode.home.createOrEditLabel');
    await eventStatcodeUpdatePage.cancel();
  });

  it('should create and save EventStatcodes', async () => {
    const nbButtonsBeforeCreate = await eventStatcodeComponentsPage.countDeleteButtons();

    await eventStatcodeComponentsPage.clickOnCreateButton();

    await promise.all([
      eventStatcodeUpdatePage.setEsCodeInput('esCode'),
      eventStatcodeUpdatePage.setEsNameInput('esName'),
      eventStatcodeUpdatePage.setEsCretimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      eventStatcodeUpdatePage.setEsCreidInput('esCreid'),
      eventStatcodeUpdatePage.setEsModtimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      eventStatcodeUpdatePage.setEsModidInput('esModid')
    ]);

    expect(await eventStatcodeUpdatePage.getEsCodeInput()).to.eq('esCode', 'Expected EsCode value to be equals to esCode');
    expect(await eventStatcodeUpdatePage.getEsNameInput()).to.eq('esName', 'Expected EsName value to be equals to esName');
    expect(await eventStatcodeUpdatePage.getEsCretimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected esCretime value to be equals to 2000-12-31'
    );
    expect(await eventStatcodeUpdatePage.getEsCreidInput()).to.eq('esCreid', 'Expected EsCreid value to be equals to esCreid');
    expect(await eventStatcodeUpdatePage.getEsModtimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected esModtime value to be equals to 2000-12-31'
    );
    expect(await eventStatcodeUpdatePage.getEsModidInput()).to.eq('esModid', 'Expected EsModid value to be equals to esModid');

    await eventStatcodeUpdatePage.save();
    expect(await eventStatcodeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await eventStatcodeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last EventStatcode', async () => {
    const nbButtonsBeforeDelete = await eventStatcodeComponentsPage.countDeleteButtons();
    await eventStatcodeComponentsPage.clickOnLastDeleteButton();

    eventStatcodeDeleteDialog = new EventStatcodeDeleteDialog();
    expect(await eventStatcodeDeleteDialog.getDialogTitle()).to.eq('aIoTapplicationApp.eventStatcode.delete.question');
    await eventStatcodeDeleteDialog.clickOnConfirmButton();

    expect(await eventStatcodeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
