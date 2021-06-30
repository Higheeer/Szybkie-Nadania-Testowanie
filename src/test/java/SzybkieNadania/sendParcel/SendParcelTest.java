package SzybkieNadania.sendParcel;

import SzybkieNadania.Pages.MainPage.Components.DeliveryMethod;
import SzybkieNadania.Pages.MainPage.Components.ParcelSize;
import SzybkieNadania.Pages.MainPage.Components.SenderForm.SenderForm;
import SzybkieNadania.Pages.MainPage.MainPage;
import SzybkieNadania.Pages.PaymentPage.Components.Payment;
import SzybkieNadania.Pages.PaymentPage.PaymentPage;
import SzybkieNadania.Pages.SummaryPage.SummaryPage;
import SzybkieNadania.Utils.PageAction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SendParcelTest {
    private WebDriver webDriver;
    private MainPage mainPage;

    @BeforeAll
    static void staticSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeEach
    void setup() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        try {
            webDriver.get("https://test-oneclick-pl.easypack24.net/SzybkieNadania/");
        } catch (TimeoutException e) {
            webDriver.navigate().refresh();
        }
        PageAction.closeCookies(webDriver);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();

    }

    @Nested
    @DisplayName("Test cases for sending P2P parcel")
    class APM {
        @BeforeEach
        void setup() {
            mainPage = new MainPage(webDriver);
            mainPage.chooseDeliveryType(DeliveryMethod.Type.APM);
            mainPage.fillRecipientForm("Nadine Wilkerson", "in@enimCurabitur.edu", "957399532", "POP-ZYA1");
            mainPage.fillSenderForm("Quin Chan", "arcu.imperdiet.ullamcorper@pellentesquemassalobortis.edu", "751614425");
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeAPMParcelWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeAPMParcelWithCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.COMPANY, "1010000338");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeAPMParcelWithIndividualInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.INDIVIDUAL, "Eryk", "wpl@wp.pl", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeAPMParcelWithForeignCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.FOREIGN_COMPANY, "BG", "999999999", "Interactive Museum of Industry", "Bułgaria", "5300", "Gabrovo", "Nikolaevska", "3", "", "bulgarskaMoc@wp.pl");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }
    }

    @Nested
    @DisplayName("Test cases for sending P2H parcel")
    class D2D {
        @BeforeEach
        void setup() {
            mainPage = new MainPage(webDriver);
            mainPage.chooseDeliveryType(DeliveryMethod.Type.D2D);
            mainPage.fillRecipientForm("Nadine Wilkerson", "in@enimCurabitur.edu", "957399532", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.fillSenderForm("Quin Chan", "arcu.imperdiet.ullamcorper@pellentesquemassalobortis.edu", "751614425");
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeD2DParcelWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeD2DParcelWithExtraCourierCommentWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantAddExtraCourierComment("Uwaga na sąsiada");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeD2DParcelWithCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.COMPANY, "1010000338");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeD2DParcelWithIndividualInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.INDIVIDUAL, "Eryk", "wpl@wp.pl", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeD2DParcelWithForeignCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.FOREIGN_COMPANY, "BG", "999999999", "Interactive Museum of Industry", "Bułgaria", "5300", "Gabrovo", "Nikolaevska", "3", "", "bulgarskaMoc@wp.pl");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeD2DParcelWithExtraCourierCommentAndCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantAddExtraCourierComment("Uwaga na sąsiada");
            mainPage.wantInvoice(SenderForm.InvoiceType.COMPANY, "1010000338");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeD2DParcelWithExtraCourierCommentAndIndividualInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantAddExtraCourierComment("Uwaga na sąsiada");
            mainPage.wantInvoice(SenderForm.InvoiceType.INDIVIDUAL, "Eryk", "wpl@wp.pl", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeD2DParcelWithExtraCourierCommentAndForeignCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantAddExtraCourierComment("Uwaga na sąsiada");
            mainPage.wantInvoice(SenderForm.InvoiceType.FOREIGN_COMPANY, "BG", "999999999", "Interactive Museum of Industry", "Bułgaria", "5300", "Gabrovo", "Nikolaevska", "3", "", "bulgarskaMoc@wp.pl");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).refresh().isPaid());
        }


    }
}
