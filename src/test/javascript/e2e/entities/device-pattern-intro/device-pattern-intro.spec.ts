import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  DevicePatternIntroComponentsPage,
  DevicePatternIntroDeleteDialog,
  DevicePatternIntroUpdatePage
} from './device-pattern-intro.page-object';

const expect = chai.expect;

describe('DevicePatternIntro e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let devicePatternIntroComponentsPage: DevicePatternIntroComponentsPage;
  let devicePatternIntroUpdatePage: DevicePatternIntroUpdatePage;
  let devicePatternIntroDeleteDialog: DevicePatternIntroDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DevicePatternIntros', async () => {
    await navBarPage.goToEntity('device-pattern-intro');
    devicePatternIntroComponentsPage = new DevicePatternIntroComponentsPage();
    await browser.wait(ec.visibilityOf(devicePatternIntroComponentsPage.title), 5000);
    expect(await devicePatternIntroComponentsPage.getTitle()).to.eq('aIoTapplicationApp.devicePatternIntro.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(devicePatternIntroComponentsPage.entities), ec.visibilityOf(devicePatternIntroComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DevicePatternIntro page', async () => {
    await devicePatternIntroComponentsPage.clickOnCreateButton();
    devicePatternIntroUpdatePage = new DevicePatternIntroUpdatePage();
    expect(await devicePatternIntroUpdatePage.getPageTitle()).to.eq('aIoTapplicationApp.devicePatternIntro.home.createOrEditLabel');
    await devicePatternIntroUpdatePage.cancel();
  });

  it('should create and save DevicePatternIntros', async () => {
    const nbButtonsBeforeCreate = await devicePatternIntroComponentsPage.countDeleteButtons();

    await devicePatternIntroComponentsPage.clickOnCreateButton();

    await promise.all([
      devicePatternIntroUpdatePage.setDevicepatternIdInput('devicepatternId'),
      devicePatternIntroUpdatePage.setDevicepatternCodeInput('devicepatternCode'),
      devicePatternIntroUpdatePage.setMemoInput('memo'),
      devicePatternIntroUpdatePage.setCreuserInput('creuser'),
      devicePatternIntroUpdatePage.setCretimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      devicePatternIntroUpdatePage.setModuserInput('moduser'),
      devicePatternIntroUpdatePage.setModtimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
    ]);

    expect(await devicePatternIntroUpdatePage.getDevicepatternIdInput()).to.eq(
      'devicepatternId',
      'Expected DevicepatternId value to be equals to devicepatternId'
    );
    expect(await devicePatternIntroUpdatePage.getDevicepatternCodeInput()).to.eq(
      'devicepatternCode',
      'Expected DevicepatternCode value to be equals to devicepatternCode'
    );
    expect(await devicePatternIntroUpdatePage.getMemoInput()).to.eq('memo', 'Expected Memo value to be equals to memo');
    expect(await devicePatternIntroUpdatePage.getCreuserInput()).to.eq('creuser', 'Expected Creuser value to be equals to creuser');
    expect(await devicePatternIntroUpdatePage.getCretimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected cretime value to be equals to 2000-12-31'
    );
    expect(await devicePatternIntroUpdatePage.getModuserInput()).to.eq('moduser', 'Expected Moduser value to be equals to moduser');
    expect(await devicePatternIntroUpdatePage.getModtimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected modtime value to be equals to 2000-12-31'
    );

    await devicePatternIntroUpdatePage.save();
    expect(await devicePatternIntroUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await devicePatternIntroComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last DevicePatternIntro', async () => {
    const nbButtonsBeforeDelete = await devicePatternIntroComponentsPage.countDeleteButtons();
    await devicePatternIntroComponentsPage.clickOnLastDeleteButton();

    devicePatternIntroDeleteDialog = new DevicePatternIntroDeleteDialog();
    expect(await devicePatternIntroDeleteDialog.getDialogTitle()).to.eq('aIoTapplicationApp.devicePatternIntro.delete.question');
    await devicePatternIntroDeleteDialog.clickOnConfirmButton();

    expect(await devicePatternIntroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
