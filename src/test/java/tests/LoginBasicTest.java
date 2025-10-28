package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Teste automatizado básico de login no site Swag Labs.
 * Atualizado para usar WebDriverManager (não precisa baixar o driver manualmente).
 */
public class LoginBasicTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Configura automaticamente o ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void login() {
        System.out.println("0. Iniciando teste de login");

        System.out.println("1. Abrindo página de destino");
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1350, 637));

        System.out.println("2. Inserindo usuário e senha");

        // Usuário
        driver.findElement(By.cssSelector("*[data-test='username']")).sendKeys("standard_user");

        // Senha
        driver.findElement(By.cssSelector("*[data-test='password']")).sendKeys("secret_sauce");

        // Clicar no botão de login
        driver.findElement(By.cssSelector("*[data-test='login-button']")).click();

        System.out.println("3. Verificando login");

        // Verifica título e URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Título da página incorreto!");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "URL não contém 'inventory'.");

        // Espera 2 segundos para visualizar a tela
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("4. Teste finalizado com sucesso ✅");
    }
}
