import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EventInterComponentsPage, EventInterDeleteDialog, EventInterUpdatePage } from './event-inter.page-object';

const expect = chai.expect;

describe('EventInter e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let eventInterComponentsPage: EventInterComponentsPage;
  let eventInterUpdatePage: EventInterUpdatePage;
  let eventInterDeleteDialog: EventInterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EventInters', async () => {
    await navBarPage.goToEntity('event-inter');
    eventInterComponentsPage = new EventInterComponentsPage();
    await browser.wait(ec.visibilityOf(eventInterComponentsPage.title), 5000);
    expect(await eventInterComponentsPage.getTitle()).to.eq('aIoTapplicationApp.eventInter.home.title');
    await browser.wait(ec.or(ec.visibilityOf(eventInterComponentsPage.entities), ec.visibilityOf(eventInterComponentsPage.noResult)), 1000);
  });

  it('should load create EventInter page', async () => {
    await eventInterComponentsPage.clickOnCreateButton();
    eventInterUpdatePage = new EventInterUpdatePage();
    expect(await eventInterUpdatePage.getPageTitle()).to.eq('aIoTapplicationApp.eventInter.home.createOrEditLabel');
    await eventInterUpdatePage.cancel();
  });

  it('should create and save EventInters', async () => {
    const nbButtonsBeforeCreate = await eventInterComponentsPage.countDeleteButtons();

    await eventInterComponentsPage.clickOnCreateButton();

    await promise.all([
      eventInterUpdatePage.setEvninTimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      eventInterUpdatePage.setEvninEsCodeInput('evninEsCode'),
      eventInterUpdatePage.setEvninDeviceidInput('evninDeviceid'),
      eventInterUpdatePage.setEvninDviModNumInput('5'),
      eventInterUpdatePage.setEvninDviCodeInput('evninDviCode'),
      eventInterUpdatePage.setEvninUnitUcCodeInput('evninUnitUcCode'),
      eventInterUpdatePage.setEvninUnitCodeInput('evninUnitCode'),
      eventInterUpdatePage.setEvninUnitNameInput('evninUnitName'),
      eventInterUpdatePage.setEvninUnitAddrInput('evninUnitAddr'),
      eventInterUpdatePage.setEvninThemeInput('evninTheme'),
      eventInterUpdatePage.setEvninMemoInput('evninMemo'),
      eventInterUpdatePage.setEvninResMemoInput('evninResMemo'),
      eventInterUpdatePage.setEvninCretimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      eventInterUpdatePage.setEvninCreidInput('evninCreid'),
      eventInterUpdatePage.setEvninModtimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      eventInterUpdatePage.setEvninModidInput('evninModid')
    ]);

    expect(await eventInterUpdatePage.getEvninTimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected evninTime value to be equals to 2000-12-31'
    );
    expect(await eventInterUpdatePage.getEvninEsCodeInput()).to.eq('evninEsCode', 'Expected EvninEsCode value to be equals to evninEsCode');
    expect(await eventInterUpdatePage.getEvninDeviceidInput()).to.eq(
      'evninDeviceid',
      'Expected EvninDeviceid value to be equals to evninDeviceid'
    );
    expect(await eventInterUpdatePage.getEvninDviModNumInput()).to.eq('5', 'Expected evninDviModNum value to be equals to 5');
    expect(await eventInterUpdatePage.getEvninDviCodeInput()).to.eq(
      'evninDviCode',
      'Expected EvninDviCode value to be equals to evninDviCode'
    );
    expect(await eventInterUpdatePage.getEvninUnitUcCodeInput()).to.eq(
      'evninUnitUcCode',
      'Expected EvninUnitUcCode value to be equals to evninUnitUcCode'
    );
    expect(await eventInterUpdatePage.getEvninUnitCodeInput()).to.eq(
      'evninUnitCode',
      'Expected EvninUnitCode value to be equals to evninUnitCode'
    );
    expect(await eventInterUpdatePage.getEvninUnitNameInput()).to.eq(
      'evninUnitName',
      'Expected EvninUnitName value to be equals to evninUnitName'
    );
    expect(await eventInterUpdatePage.getEvninUnitAddrInput()).to.eq(
      'evninUnitAddr',
      'Expected EvninUnitAddr value to be equals to evninUnitAddr'
    );
    expect(await eventInterUpdatePage.getEvninThemeInput()).to.eq('evninTheme', 'Expected EvninTheme value to be equals to evninTheme');
    expect(await eventInterUpdatePage.getEvninMemoInput()).to.eq('evninMemo', 'Expected EvninMemo value to be equals to evninMemo');
    const selectedEvninIsres = eventInterUpdatePage.getEvninIsresInput();
    if (await selectedEvninIsres.isSelected()) {
      await eventInterUpdatePage.getEvninIsresInput().click();
      expect(await eventInterUpdatePage.getEvninIsresInput().isSelected(), 'Expected evninIsres not to be selected').to.be.false;
    } else {
      await eventInterUpdatePage.getEvninIsresInput().click();
      expect(await eventInterUpdatePage.getEvninIsresInput().isSelected(), 'Expected evninIsres to be selected').to.be.true;
    }
    expect(await eventInterUpdatePage.getEvninResMemoInput()).to.eq(
      'evninResMemo',
      'Expected EvninResMemo value to be equals to evninResMemo'
    );
    expect(await eventInterUpdatePage.getEvninCretimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected evninCretime value to be equals to 2000-12-31'
    );
    expect(await eventInterUpdatePage.getEvninCreidInput()).to.eq('evninCreid', 'Expected EvninCreid value to be equals to evninCreid');
    expect(await eventInterUpdatePage.getEvninModtimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected evninModtime value to be equals to 2000-12-31'
    );
    expect(await eventInterUpdatePage.getEvninModidInput()).to.eq('evninModid', 'Expected EvninModid value to be equals to evninModid');

    await eventInterUpdatePage.save();
    expect(await eventInterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await eventInterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last EventInter', async () => {
    const nbButtonsBeforeDelete = await eventInterComponentsPage.countDeleteButtons();
    await eventInterComponentsPage.clickOnLastDeleteButton();

    eventInterDeleteDialog = new EventInterDeleteDialog();
    expect(await eventInterDeleteDialog.getDialogTitle()).to.eq('aIoTapplicationApp.eventInter.delete.question');
    await eventInterDeleteDialog.clickOnConfirmButton();

    expect(await eventInterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
