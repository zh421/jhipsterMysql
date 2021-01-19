import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UnitClassComponentsPage, UnitClassDeleteDialog, UnitClassUpdatePage } from './unit-class.page-object';

const expect = chai.expect;

describe('UnitClass e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let unitClassComponentsPage: UnitClassComponentsPage;
  let unitClassUpdatePage: UnitClassUpdatePage;
  let unitClassDeleteDialog: UnitClassDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load UnitClasses', async () => {
    await navBarPage.goToEntity('unit-class');
    unitClassComponentsPage = new UnitClassComponentsPage();
    await browser.wait(ec.visibilityOf(unitClassComponentsPage.title), 5000);
    expect(await unitClassComponentsPage.getTitle()).to.eq('aIoTapplicationApp.unitClass.home.title');
    await browser.wait(ec.or(ec.visibilityOf(unitClassComponentsPage.entities), ec.visibilityOf(unitClassComponentsPage.noResult)), 1000);
  });

  it('should load create UnitClass page', async () => {
    await unitClassComponentsPage.clickOnCreateButton();
    unitClassUpdatePage = new UnitClassUpdatePage();
    expect(await unitClassUpdatePage.getPageTitle()).to.eq('aIoTapplicationApp.unitClass.home.createOrEditLabel');
    await unitClassUpdatePage.cancel();
  });

  it('should create and save UnitClasses', async () => {
    const nbButtonsBeforeCreate = await unitClassComponentsPage.countDeleteButtons();

    await unitClassComponentsPage.clickOnCreateButton();

    await promise.all([
      unitClassUpdatePage.setUcCodeInput('ucCode'),
      unitClassUpdatePage.setUcNameInput('ucName'),
      unitClassUpdatePage.setUcCretimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      unitClassUpdatePage.setUcCreidInput('ucCreid'),
      unitClassUpdatePage.setUcModtimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      unitClassUpdatePage.setUcModidInput('ucModid')
    ]);

    expect(await unitClassUpdatePage.getUcCodeInput()).to.eq('ucCode', 'Expected UcCode value to be equals to ucCode');
    expect(await unitClassUpdatePage.getUcNameInput()).to.eq('ucName', 'Expected UcName value to be equals to ucName');
    expect(await unitClassUpdatePage.getUcCretimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ucCretime value to be equals to 2000-12-31'
    );
    expect(await unitClassUpdatePage.getUcCreidInput()).to.eq('ucCreid', 'Expected UcCreid value to be equals to ucCreid');
    expect(await unitClassUpdatePage.getUcModtimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ucModtime value to be equals to 2000-12-31'
    );
    expect(await unitClassUpdatePage.getUcModidInput()).to.eq('ucModid', 'Expected UcModid value to be equals to ucModid');

    await unitClassUpdatePage.save();
    expect(await unitClassUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await unitClassComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last UnitClass', async () => {
    const nbButtonsBeforeDelete = await unitClassComponentsPage.countDeleteButtons();
    await unitClassComponentsPage.clickOnLastDeleteButton();

    unitClassDeleteDialog = new UnitClassDeleteDialog();
    expect(await unitClassDeleteDialog.getDialogTitle()).to.eq('aIoTapplicationApp.unitClass.delete.question');
    await unitClassDeleteDialog.clickOnConfirmButton();

    expect(await unitClassComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
