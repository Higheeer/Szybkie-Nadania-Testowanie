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
        webDriver.get("https://test-oneclick-pl.easypack24.net/SzybkieNadania/");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        PageAction.closeCookies(webDriver);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();

    }

    @Nested
    @DisplayName("Test cases for sending P2P parcel")
    class SendP2PParcel {
        @BeforeEach
        void setup() {
            mainPage = new MainPage(webDriver);
            mainPage.chooseDeliveryType(DeliveryMethod.Type.P2P);
            mainPage.fillRecipientForm("Nadine Wilkerson", "in@enimCurabitur.edu", "957399532", "POP-ZYA1");
            mainPage.fillSenderForm("Quin Chan", "arcu.imperdiet.ullamcorper@pellentesquemassalobortis.edu", "751614425");
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2PParcelWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2PParcelWithCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.COMPANY, "1010000338");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2PParcelWithIndividualInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.INDIVIDUAL, "Eryk", "wpl@wp.pl", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2PParcelWithForeignCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.FOREIGN_COMPANY, "BG", "999999999", "Interactive Museum of Industry", "Bułgaria", "5300", "Gabrovo", "Nikolaevska", "3", "", "bulgarskaMoc@wp.pl");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

    }

    @Nested
    @DisplayName("Test cases for sending P2H parcel")
    class SendP2HParcel {
        @BeforeEach
        void setup() {
            mainPage = new MainPage(webDriver);
            mainPage.chooseDeliveryType(DeliveryMethod.Type.P2H);
            mainPage.fillRecipientForm("Nadine Wilkerson", "in@enimCurabitur.edu", "957399532", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.fillSenderForm("Quin Chan", "arcu.imperdiet.ullamcorper@pellentesquemassalobortis.edu", "751614425");
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2HParcelWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2HParcelWithExtraCourierCommentWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantAddExtraCourierComment("Uwaga na sąsiada");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2HParcelWithCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.COMPANY, "1010000338");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2HParcelWithIndividualInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.INDIVIDUAL, "Eryk", "wpl@wp.pl", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2HParcelWithForeignCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantInvoice(SenderForm.InvoiceType.FOREIGN_COMPANY, "BG", "999999999", "Interactive Museum of Industry", "Bułgaria", "5300", "Gabrovo", "Nikolaevska", "3", "", "bulgarskaMoc@wp.pl");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2HParcelWithExtraCourierCommentAndCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantAddExtraCourierComment("Uwaga na sąsiada");
            mainPage.wantInvoice(SenderForm.InvoiceType.COMPANY, "1010000338");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2HParcelWithExtraCourierCommentAndIndividualInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantAddExtraCourierComment("Uwaga na sąsiada");
            mainPage.wantInvoice(SenderForm.InvoiceType.INDIVIDUAL, "Eryk", "wpl@wp.pl", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }

        @ParameterizedTest
        @EnumSource(ParcelSize.Size.class)
        void shouldMakeP2HParcelWithExtraCourierCommentAndForeignCompanyInvoiceWhenCorrectDetailsAreGiven(ParcelSize.Size size) {
            mainPage.chooseParcelSize(size);
            mainPage.wantAddExtraCourierComment("Uwaga na sąsiada");
            mainPage.wantInvoice(SenderForm.InvoiceType.FOREIGN_COMPANY, "BG", "999999999", "Interactive Museum of Industry", "Bułgaria", "5300", "Gabrovo", "Nikolaevska", "3", "", "bulgarskaMoc@wp.pl");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.closeMiddleCookies(webDriver);

            assertTrue((new SummaryPage(webDriver)).isPaid());
        }


    }
}
