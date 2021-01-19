import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DeviceCodeComponentsPage, DeviceCodeDeleteDialog, DeviceCodeUpdatePage } from './device-code.page-object';

const expect = chai.expect;

describe('DeviceCode e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let deviceCodeComponentsPage: DeviceCodeComponentsPage;
  let deviceCodeUpdatePage: DeviceCodeUpdatePage;
  let deviceCodeDeleteDialog: DeviceCodeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DeviceCodes', async () => {
    await navBarPage.goToEntity('device-code');
    deviceCodeComponentsPage = new DeviceCodeComponentsPage();
    await browser.wait(ec.visibilityOf(deviceCodeComponentsPage.title), 5000);
    expect(await deviceCodeComponentsPage.getTitle()).to.eq('aIoTapplicationApp.deviceCode.home.title');
    await browser.wait(ec.or(ec.visibilityOf(deviceCodeComponentsPage.entities), ec.visibilityOf(deviceCodeComponentsPage.noResult)), 1000);
  });

  it('should load create DeviceCode page', async () => {
    await deviceCodeComponentsPage.clickOnCreateButton();
    deviceCodeUpdatePage = new DeviceCodeUpdatePage();
    expect(await deviceCodeUpdatePage.getPageTitle()).to.eq('aIoTapplicationApp.deviceCode.home.createOrEditLabel');
    await deviceCodeUpdatePage.cancel();
  });

  it('should create and save DeviceCodes', async () => {
    const nbButtonsBeforeCreate = await deviceCodeComponentsPage.countDeleteButtons();

    await deviceCodeComponentsPage.clickOnCreateButton();

    await promise.all([
      deviceCodeUpdatePage.setDviCodeInput('dviCode'),
      deviceCodeUpdatePage.setDviNameInput('dviName'),
      deviceCodeUpdatePage.setDviCretimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deviceCodeUpdatePage.setDviCreidInput('dviCreid'),
      deviceCodeUpdatePage.setDviModtimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deviceCodeUpdatePage.setDviModidInput('dviModid')
    ]);

    expect(await deviceCodeUpdatePage.getDviCodeInput()).to.eq('dviCode', 'Expected DviCode value to be equals to dviCode');
    expect(await deviceCodeUpdatePage.getDviNameInput()).to.eq('dviName', 'Expected DviName value to be equals to dviName');
    expect(await deviceCodeUpdatePage.getDviCretimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dviCretime value to be equals to 2000-12-31'
    );
    expect(await deviceCodeUpdatePage.getDviCreidInput()).to.eq('dviCreid', 'Expected DviCreid value to be equals to dviCreid');
    expect(await deviceCodeUpdatePage.getDviModtimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dviModtime value to be equals to 2000-12-31'
    );
    expect(await deviceCodeUpdatePage.getDviModidInput()).to.eq('dviModid', 'Expected DviModid value to be equals to dviModid');

    await deviceCodeUpdatePage.save();
    expect(await deviceCodeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await deviceCodeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DeviceCode', async () => {
    const nbButtonsBeforeDelete = await deviceCodeComponentsPage.countDeleteButtons();
    await deviceCodeComponentsPage.clickOnLastDeleteButton();

    deviceCodeDeleteDialog = new DeviceCodeDeleteDialog();
    expect(await deviceCodeDeleteDialog.getDialogTitle()).to.eq('aIoTapplicationApp.deviceCode.delete.question');
    await deviceCodeDeleteDialog.clickOnConfirmButton();

    expect(await deviceCodeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
