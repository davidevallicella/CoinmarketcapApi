import com.coinmarketcup.models.Currencies;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.concurrent.TimeUnit;

/**
 * Created by Davide Vallicella on 31/08/2017.
 */
public class TestApi {
    private final static String TAB = "                  ";

    @Test
    public void test_api() throws IOException {
        initProxy();
        Currencies currencies = new Currencies();

        currencies.refreshData();
        String leftAlignFormat = "| %-30s | %-20s | %-10s | %-10s | %-10s |%n";

        System.out.format("+--------------------------------+----------------------+------------+------------+------------%n");
        System.out.format("| coins                          | ID                   | 1H         | price      | deltaUSD  |%n");
        System.out.format("+--------------------------------+----------------------+------------+------------+------------%n");

        currencies.getTopByPercentage().forEach(
                currency -> {
                    System.out.format(leftAlignFormat, currency.name, currency.symbol, currency.percentChange7d + "%", currency.priceUSD, currency.deltaUSD);
                }
        );//0231279
        System.out.format("+--------------------------------+----------------------+------------+------------+------------+%n");

    }

    public void test_strategy1() throws IOException, InterruptedException {
        Currencies currencies = new Currencies();
        String leftAlignFormat = "| %-30s | %-20s | %-10s | %-10s | %-10s |%n";
        Integer amount = 100;
        while (true) {
            currencies.refreshData();

            currencies.getTopByPercentage().forEach(
                    currency -> {
                        System.out.format(leftAlignFormat, currency.name, currency.symbol, currency.percentChange1h + "%", currency.priceUSD, currency.deltaUSD);
                    }
            );

            TimeUnit.MINUTES.sleep(1);
        }
    }

    private static void initProxy() {
        System.setProperty("http.proxyHost", "vsgbc.cattolica.gca.net");
        System.setProperty("http.proxyPort", "8080");
        // settings proxy credentials
        System.setProperty("http.proxyUser", "Cattolica\\VallicellaD");
        System.setProperty("http.proxyPassword", "Cattolica18");

        System.setProperty("https.proxyHost", "vsgbc.cattolica.gca.net");
        System.setProperty("https.proxyPort", "8080");
        // settings proxy credentials
        System.setProperty("https.proxyUser", "Cattolica\\VallicellaD");
        System.setProperty("https.proxyPassword", "Cattolica18");

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (getRequestorType() == RequestorType.PROXY) {
                    String prot = getRequestingProtocol().toLowerCase();
                    String host = System.getProperty(prot + ".proxyHost", "");
                    String port = System.getProperty(prot + ".proxyPort", "80");
                    String user = System.getProperty(prot + ".proxyUser", "");
                    String password = System.getProperty(prot + ".proxyPassword", "");
                    if (getRequestingHost().equalsIgnoreCase(host)) {
                        if (Integer.parseInt(port) == getRequestingPort()) {
                            return new PasswordAuthentication(user, password.toCharArray());
                        }
                    }
                }
                return null;
            }
        });
    }
}
