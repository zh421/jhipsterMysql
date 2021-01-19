import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UnitComponentsPage, UnitDeleteDialog, UnitUpdatePage } from './unit.page-object';

const expect = chai.expect;

describe('Unit e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let unitComponentsPage: UnitComponentsPage;
  let unitUpdatePage: UnitUpdatePage;
  let unitDeleteDialog: UnitDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Units', async () => {
    await navBarPage.goToEntity('unit');
    unitComponentsPage = new UnitComponentsPage();
    await browser.wait(ec.visibilityOf(unitComponentsPage.title), 5000);
    expect(await unitComponentsPage.getTitle()).to.eq('aIoTapplicationApp.unit.home.title');
    await browser.wait(ec.or(ec.visibilityOf(unitComponentsPage.entities), ec.visibilityOf(unitComponentsPage.noResult)), 1000);
  });

  it('should load create Unit page', async () => {
    await unitComponentsPage.clickOnCreateButton();
    unitUpdatePage = new UnitUpdatePage();
    expect(await unitUpdatePage.getPageTitle()).to.eq('aIoTapplicationApp.unit.home.createOrEditLabel');
    await unitUpdatePage.cancel();
  });

  it('should create and save Units', async () => {
    const nbButtonsBeforeCreate = await unitComponentsPage.countDeleteButtons();

    await unitComponentsPage.clickOnCreateButton();

    await promise.all([
      unitUpdatePage.setUnitUcCodeInput('unitUcCode'),
      unitUpdatePage.setUnitCodeInput('unitCode'),
      unitUpdatePage.setUnitNameInput('unitName'),
      unitUpdatePage.setUnitAddrInput('unitAddr'),
      unitUpdatePage.setUnitLongitudeInput('unitLongitude'),
      unitUpdatePage.setUnitLatitudeInput('unitLatitude'),
      unitUpdatePage.setUnitPicInput('unitPic'),
      unitUpdatePage.setUnitPhoneInput('unitPhone'),
      unitUpdatePage.setUnitEmailInput('unitEmail'),
      unitUpdatePage.setUnitCretimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      unitUpdatePage.setUnitCreidInput('unitCreid'),
      unitUpdatePage.setUnitModtimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      unitUpdatePage.setUnitModidInput('unitModid'),
      unitUpdatePage.setUnitLogipInput('unitLogip'),
      unitUpdatePage.setUnitSignDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
    ]);

    expect(await unitUpdatePage.getUnitUcCodeInput()).to.eq('unitUcCode', 'Expected UnitUcCode value to be equals to unitUcCode');
    expect(await unitUpdatePage.getUnitCodeInput()).to.eq('unitCode', 'Expected UnitCode value to be equals to unitCode');
    expect(await unitUpdatePage.getUnitNameInput()).to.eq('unitName', 'Expected UnitName value to be equals to unitName');
    expect(await unitUpdatePage.getUnitAddrInput()).to.eq('unitAddr', 'Expected UnitAddr value to be equals to unitAddr');
    expect(await unitUpdatePage.getUnitLongitudeInput()).to.eq(
      'unitLongitude',
      'Expected UnitLongitude value to be equals to unitLongitude'
    );
    expect(await unitUpdatePage.getUnitLatitudeInput()).to.eq('unitLatitude', 'Expected UnitLatitude value to be equals to unitLatitude');
    expect(await unitUpdatePage.getUnitPicInput()).to.eq('unitPic', 'Expected UnitPic value to be equals to unitPic');
    expect(await unitUpdatePage.getUnitPhoneInput()).to.eq('unitPhone', 'Expected UnitPhone value to be equals to unitPhone');
    expect(await unitUpdatePage.getUnitEmailInput()).to.eq('unitEmail', 'Expected UnitEmail value to be equals to unitEmail');
    expect(await unitUpdatePage.getUnitCretimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected unitCretime value to be equals to 2000-12-31'
    );
    expect(await unitUpdatePage.getUnitCreidInput()).to.eq('unitCreid', 'Expected UnitCreid value to be equals to unitCreid');
    expect(await unitUpdatePage.getUnitModtimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected unitModtime value to be equals to 2000-12-31'
    );
    expect(await unitUpdatePage.getUnitModidInput()).to.eq('unitModid', 'Expected UnitModid value to be equals to unitModid');
    expect(await unitUpdatePage.getUnitLogipInput()).to.eq('unitLogip', 'Expected UnitLogip value to be equals to unitLogip');
    expect(await unitUpdatePage.getUnitSignDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected unitSignDate value to be equals to 2000-12-31'
    );

    await unitUpdatePage.save();
    expect(await unitUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await unitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Unit', async () => {
    const nbButtonsBeforeDelete = await unitComponentsPage.countDeleteButtons();
    await unitComponentsPage.clickOnLastDeleteButton();

    unitDeleteDialog = new UnitDeleteDialog();
    expect(await unitDeleteDialog.getDialogTitle()).to.eq('aIoTapplicationApp.unit.delete.question');
    await unitDeleteDialog.clickOnConfirmButton();

    expect(await unitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
