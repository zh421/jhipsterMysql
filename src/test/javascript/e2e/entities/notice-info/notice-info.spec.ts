import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NoticeInfoComponentsPage, NoticeInfoDeleteDialog, NoticeInfoUpdatePage } from './notice-info.page-object';

const expect = chai.expect;

describe('NoticeInfo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let noticeInfoComponentsPage: NoticeInfoComponentsPage;
  let noticeInfoUpdatePage: NoticeInfoUpdatePage;
  let noticeInfoDeleteDialog: NoticeInfoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NoticeInfos', async () => {
    await navBarPage.goToEntity('notice-info');
    noticeInfoComponentsPage = new NoticeInfoComponentsPage();
    await browser.wait(ec.visibilityOf(noticeInfoComponentsPage.title), 5000);
    expect(await noticeInfoComponentsPage.getTitle()).to.eq('aIoTapplicationApp.noticeInfo.home.title');
    await browser.wait(ec.or(ec.visibilityOf(noticeInfoComponentsPage.entities), ec.visibilityOf(noticeInfoComponentsPage.noResult)), 1000);
  });

  it('should load create NoticeInfo page', async () => {
    await noticeInfoComponentsPage.clickOnCreateButton();
    noticeInfoUpdatePage = new NoticeInfoUpdatePage();
    expect(await noticeInfoUpdatePage.getPageTitle()).to.eq('aIoTapplicationApp.noticeInfo.home.createOrEditLabel');
    await noticeInfoUpdatePage.cancel();
  });

  it('should create and save NoticeInfos', async () => {
    const nbButtonsBeforeCreate = await noticeInfoComponentsPage.countDeleteButtons();

    await noticeInfoComponentsPage.clickOnCreateButton();

    await promise.all([
      noticeInfoUpdatePage.setNotiCaseidInput('notiCaseid'),
      noticeInfoUpdatePage.setNotiTitleInput('notiTitle'),
      noticeInfoUpdatePage.setNotiContentInput('notiContent'),
      noticeInfoUpdatePage.setNotiInfotypeInput('notiInfotype'),
      noticeInfoUpdatePage.setNotiStarttimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      noticeInfoUpdatePage.setNotiEndtimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      noticeInfoUpdatePage.setNotiStatcodeInput('notiStatcode'),
      noticeInfoUpdatePage.setNotiCretimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      noticeInfoUpdatePage.setNotiCreidInput('notiCreid'),
      noticeInfoUpdatePage.setNotiModtimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      noticeInfoUpdatePage.setNotiModidInput('notiModid')
    ]);

    expect(await noticeInfoUpdatePage.getNotiCaseidInput()).to.eq('notiCaseid', 'Expected NotiCaseid value to be equals to notiCaseid');
    expect(await noticeInfoUpdatePage.getNotiTitleInput()).to.eq('notiTitle', 'Expected NotiTitle value to be equals to notiTitle');
    expect(await noticeInfoUpdatePage.getNotiContentInput()).to.eq('notiContent', 'Expected NotiContent value to be equals to notiContent');
    expect(await noticeInfoUpdatePage.getNotiInfotypeInput()).to.eq(
      'notiInfotype',
      'Expected NotiInfotype value to be equals to notiInfotype'
    );
    expect(await noticeInfoUpdatePage.getNotiStarttimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected notiStarttime value to be equals to 2000-12-31'
    );
    expect(await noticeInfoUpdatePage.getNotiEndtimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected notiEndtime value to be equals to 2000-12-31'
    );
    expect(await noticeInfoUpdatePage.getNotiStatcodeInput()).to.eq(
      'notiStatcode',
      'Expected NotiStatcode value to be equals to notiStatcode'
    );
    expect(await noticeInfoUpdatePage.getNotiCretimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected notiCretime value to be equals to 2000-12-31'
    );
    expect(await noticeInfoUpdatePage.getNotiCreidInput()).to.eq('notiCreid', 'Expected NotiCreid value to be equals to notiCreid');
    expect(await noticeInfoUpdatePage.getNotiModtimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected notiModtime value to be equals to 2000-12-31'
    );
    expect(await noticeInfoUpdatePage.getNotiModidInput()).to.eq('notiModid', 'Expected NotiModid value to be equals to notiModid');

    await noticeInfoUpdatePage.save();
    expect(await noticeInfoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await noticeInfoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last NoticeInfo', async () => {
    const nbButtonsBeforeDelete = await noticeInfoComponentsPage.countDeleteButtons();
    await noticeInfoComponentsPage.clickOnLastDeleteButton();

    noticeInfoDeleteDialog = new NoticeInfoDeleteDialog();
    expect(await noticeInfoDeleteDialog.getDialogTitle()).to.eq('aIoTapplicationApp.noticeInfo.delete.question');
    await noticeInfoDeleteDialog.clickOnConfirmButton();

    expect(await noticeInfoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
