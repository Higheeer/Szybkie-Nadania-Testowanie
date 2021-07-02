package SzybkieNadania.Pages.Summary;

import SzybkieNadania.Pages.MainPage.Components.DeliveryMethod;
import SzybkieNadania.Pages.MainPage.Components.Forms.Sender.SenderForm;
import SzybkieNadania.Pages.MainPage.MainPage;
import SzybkieNadania.Pages.PaymentPage.Components.Payment;
import SzybkieNadania.Pages.PaymentPage.PaymentPage;
import SzybkieNadania.Pages.SummaryPage.SummaryPage;
import SzybkieNadania.Utils.PageAction;
import org.junit.jupiter.api.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummaryTest {
    private WebDriver webDriver;

    @BeforeAll
    static void staticSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
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
    class APM {
        private MainPage mainPage;

        @BeforeEach
        void setup(){
            mainPage = new MainPage(webDriver);
            mainPage.chooseDeliveryType(DeliveryMethod.Type.APM);
            mainPage.fillRecipientForm("Nadine Wilkerson", "in@enimCurabitur.edu", "957399532", "POP-ZYA1");
            mainPage.fillSenderForm("Quin Chan", "arcu.imperdiet.ullamcorper@pellentesquemassalobortis.edu", "751614425");
        }

        @Test
        void shouldSenderDetailsMatch() {
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(1000);

            SummaryPage summaryPage = new SummaryPage(webDriver);

            assertAll(
                    () -> assertEquals("Quin Chan", summaryPage.senderDetails().getName()),
                    () -> assertEquals("arcu.imperdiet.ullamcorper@pellentesquemassalobortis.edu", summaryPage.senderDetails().getEmail()),
                    () -> assertEquals("751614425", summaryPage.senderDetails().getPhoneNumber())
            );
        }

        @Test
        void shouldReceiverDetailsMatch() {
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(1000);

            SummaryPage summaryPage = new SummaryPage(webDriver);

            assertAll(
                    () -> assertEquals("Nadine Wilkerson", summaryPage.receiverDetails().getName()),
                    () -> assertEquals("in@enimCurabitur.edu", summaryPage.receiverDetails().getEmail()),
                    () -> assertEquals("957399532", summaryPage.receiverDetails().getPhoneNumber())
            );
        }

        @Test
        void shouldParcelLockerDetailsMatch() {
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(1000);

            SummaryPage summaryPage = new SummaryPage(webDriver);

            assertAll(
                    () -> assertEquals("POP-ZYA1", summaryPage.parcelLockerDetails().getName()),
                    () -> assertEquals("Bohaterów Warszawy 69", summaryPage.parcelLockerDetails().getAddress()),
                    () -> assertEquals("Żyrardów 96-300", summaryPage.parcelLockerDetails().getTown()),
                    () -> assertEquals("DIABELSKI MLYNEK", summaryPage.parcelLockerDetails().getHint())
            );
        }

        @Test
        void shouldCompanyInvoiceDetailsMatch() {
            mainPage.wantInvoice(SenderForm.InvoiceType.COMPANY, "1010000338");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(1000);

            SummaryPage summaryPage = new SummaryPage(SenderForm.InvoiceType.COMPANY, webDriver);

            assertAll(
                    () -> assertEquals("MÜLLER SACHSEN GMBH ODDZIAŁ W POLSCE", summaryPage.invoiceDetails().getCompanyName()),
                    () -> assertEquals("NIP: PL1010000338", summaryPage.invoiceDetails().getNip()),
                    () -> assertEquals("59-800 Lubań", summaryPage.invoiceDetails().getTown()),
                    () -> assertEquals("ul. Test-Krucza 24/2", summaryPage.invoiceDetails().getAddress())
            );

        }

        @Test
        void shouldIndividualInvoiceDetailsMatch() {
            mainPage.wantInvoice(SenderForm.InvoiceType.INDIVIDUAL, "Eryk", "wpl@wp.pl", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(1000);

            SummaryPage summaryPage = new SummaryPage(SenderForm.InvoiceType.INDIVIDUAL, webDriver);

            assertAll(
                    () -> assertEquals("Eryk", summaryPage.invoiceDetails().getCompanyName()),
                    () -> assertEquals("31-513 Kraków", summaryPage.invoiceDetails().getTown()),
                    () -> assertEquals("Olszańska 25/31", summaryPage.invoiceDetails().getAddress())
            );

        }

        @Test
        void shouldForeignCompanyInvoiceDetailsMatch() {
            mainPage.wantInvoice(SenderForm.InvoiceType.FOREIGN_COMPANY, "BG", "999999999", "Interactive Museum of Industry", "Bułgaria", "5300", "Gabrovo", "Nikolaevska", "3", "", "bulgarskaMoc@wp.pl");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(1000);

        SummaryPage summaryPage = new SummaryPage(SenderForm.InvoiceType.FOREIGN_COMPANY, webDriver);

            assertAll(
                    () -> assertEquals("Interactive Museum of Industry", summaryPage.invoiceDetails().getCompanyName()),
                    () -> assertEquals("NIP: BG999999999", summaryPage.invoiceDetails().getNip()),
                    () -> assertEquals("5300 Gabrovo", summaryPage.invoiceDetails().getTown()),
                    () -> assertEquals("Nikolaevska 3", summaryPage.invoiceDetails().getAddress())
            );

        }
    }

    @Nested
    class D2D {
        private MainPage mainPage;

        @BeforeEach
        void setup(){
            mainPage = new MainPage(webDriver);
            mainPage.chooseDeliveryType(DeliveryMethod.Type.D2D);
            mainPage.fillRecipientForm("Nadine Wilkerson", "in@enimCurabitur.edu", "957399532", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.fillSenderForm("Quin Chan", "arcu.imperdiet.ullamcorper@pellentesquemassalobortis.edu", "751614425");
        }

        @Test
        void shouldSenderDetailsMatchWhen() {
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(1000);

            SummaryPage summaryPage = new SummaryPage(webDriver);

            assertAll(
                    () -> assertEquals("Quin Chan", summaryPage.senderDetails().getName()),
                    () -> assertEquals("arcu.imperdiet.ullamcorper@pellentesquemassalobortis.edu", summaryPage.senderDetails().getEmail()),
                    () -> assertEquals("751614425", summaryPage.senderDetails().getPhoneNumber())
            );
        }

        @Test
        void shouldReceiverDetailsMatch() {
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(1000);

            SummaryPage summaryPage = new SummaryPage(webDriver);

            assertAll(
                    () -> assertEquals("Nadine Wilkerson", summaryPage.receiverDetails().getName()),
                    () -> assertEquals("in@enimCurabitur.edu", summaryPage.receiverDetails().getEmail()),
                    () -> assertEquals("957399532", summaryPage.receiverDetails().getPhoneNumber()),
                    () -> assertEquals("Olszańska 25/31", summaryPage.receiverDetails().getAddress()),
                    () -> assertEquals("31-513 Kraków", summaryPage.receiverDetails().getTown())
            );
        }

        @Test
        void shouldCompanyInvoiceDetailsMatch() {
            mainPage.wantInvoice(SenderForm.InvoiceType.COMPANY, "1010000338");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(1000);

            SummaryPage summaryPage = new SummaryPage(SenderForm.InvoiceType.COMPANY,webDriver);

            assertAll(
                    () -> assertEquals("MÜLLER SACHSEN GMBH ODDZIAŁ W POLSCE", summaryPage.invoiceDetails().getCompanyName()),
                    () -> assertEquals("NIP: PL1010000338", summaryPage.invoiceDetails().getNip()),
                    () -> assertEquals("59-800 Lubań", summaryPage.invoiceDetails().getTown()),
                    () -> assertEquals("ul. Test-Krucza 24/2", summaryPage.invoiceDetails().getAddress())
            );

        }

        @Test
        void shouldIndividualInvoiceDetailsMatch() {
            mainPage.wantInvoice(SenderForm.InvoiceType.INDIVIDUAL, "Eryk", "wpl@wp.pl", "31-513", "Kraków", "Olszańska", "25", "31");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(2000);

            SummaryPage summaryPage = new SummaryPage(SenderForm.InvoiceType.INDIVIDUAL,webDriver);

            assertAll(
                    () -> assertEquals("Eryk", summaryPage.invoiceDetails().getCompanyName()),
                    () -> assertEquals("31-513 Kraków", summaryPage.invoiceDetails().getTown()),
                    () -> assertEquals("Olszańska 25/31", summaryPage.invoiceDetails().getAddress())
            );

        }

        @Test
        void shouldForeignCompanyInvoiceDetailsMatch() {
            mainPage.wantInvoice(SenderForm.InvoiceType.FOREIGN_COMPANY, "BG", "999999999", "Interactive Museum of Industry", "Bułgaria", "5300", "Gabrovo", "Nikolaevska", "3", "", "bulgarskaMoc@wp.pl");
            mainPage.acceptTermsAndConditions();
            mainPage.submit();
            mainPage.payFor();

            (new PaymentPage(webDriver)).choosePaymentMethod("Millennium").finish(Payment.APPROVED);
            PageAction.wait(2000);

        SummaryPage summaryPage = new SummaryPage(SenderForm.InvoiceType.FOREIGN_COMPANY, webDriver);

            assertAll(
                    () -> assertEquals("Interactive Museum of Industry", summaryPage.invoiceDetails().getCompanyName()),
                    () -> assertEquals("NIP: BG999999999", summaryPage.invoiceDetails().getNip()),
                    () -> assertEquals("5300 Gabrovo", summaryPage.invoiceDetails().getTown()),
                    () -> assertEquals("Nikolaevska 3", summaryPage.invoiceDetails().getAddress())
            );

        }
    }

}
