import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SensorCodeComponentsPage, SensorCodeDeleteDialog, SensorCodeUpdatePage } from './sensor-code.page-object';

const expect = chai.expect;

describe('SensorCode e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sensorCodeComponentsPage: SensorCodeComponentsPage;
  let sensorCodeUpdatePage: SensorCodeUpdatePage;
  let sensorCodeDeleteDialog: SensorCodeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SensorCodes', async () => {
    await navBarPage.goToEntity('sensor-code');
    sensorCodeComponentsPage = new SensorCodeComponentsPage();
    await browser.wait(ec.visibilityOf(sensorCodeComponentsPage.title), 5000);
    expect(await sensorCodeComponentsPage.getTitle()).to.eq('aIoTapplicationApp.sensorCode.home.title');
    await browser.wait(ec.or(ec.visibilityOf(sensorCodeComponentsPage.entities), ec.visibilityOf(sensorCodeComponentsPage.noResult)), 1000);
  });

  it('should load create SensorCode page', async () => {
    await sensorCodeComponentsPage.clickOnCreateButton();
    sensorCodeUpdatePage = new SensorCodeUpdatePage();
    expect(await sensorCodeUpdatePage.getPageTitle()).to.eq('aIoTapplicationApp.sensorCode.home.createOrEditLabel');
    await sensorCodeUpdatePage.cancel();
  });

  it('should create and save SensorCodes', async () => {
    const nbButtonsBeforeCreate = await sensorCodeComponentsPage.countDeleteButtons();

    await sensorCodeComponentsPage.clickOnCreateButton();

    await promise.all([
      sensorCodeUpdatePage.setScCodeInput('scCode'),
      sensorCodeUpdatePage.setScNameInput('scName'),
      sensorCodeUpdatePage.setScCretimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      sensorCodeUpdatePage.setScCreidInput('scCreid'),
      sensorCodeUpdatePage.setScModtimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      sensorCodeUpdatePage.setScModidInput('scModid')
    ]);

    expect(await sensorCodeUpdatePage.getScCodeInput()).to.eq('scCode', 'Expected ScCode value to be equals to scCode');
    expect(await sensorCodeUpdatePage.getScNameInput()).to.eq('scName', 'Expected ScName value to be equals to scName');
    expect(await sensorCodeUpdatePage.getScCretimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected scCretime value to be equals to 2000-12-31'
    );
    expect(await sensorCodeUpdatePage.getScCreidInput()).to.eq('scCreid', 'Expected ScCreid value to be equals to scCreid');
    expect(await sensorCodeUpdatePage.getScModtimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected scModtime value to be equals to 2000-12-31'
    );
    expect(await sensorCodeUpdatePage.getScModidInput()).to.eq('scModid', 'Expected ScModid value to be equals to scModid');

    await sensorCodeUpdatePage.save();
    expect(await sensorCodeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await sensorCodeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SensorCode', async () => {
    const nbButtonsBeforeDelete = await sensorCodeComponentsPage.countDeleteButtons();
    await sensorCodeComponentsPage.clickOnLastDeleteButton();

    sensorCodeDeleteDialog = new SensorCodeDeleteDialog();
    expect(await sensorCodeDeleteDialog.getDialogTitle()).to.eq('aIoTapplicationApp.sensorCode.delete.question');
    await sensorCodeDeleteDialog.clickOnConfirmButton();

    expect(await sensorCodeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
