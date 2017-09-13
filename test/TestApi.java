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
}
