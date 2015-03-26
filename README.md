# JAXB Annotated Classes for ECB FX Rates

A set of classes that can be used to parse [ECB Euro foreign exchange reference rates](https://www.ecb.europa.eu/stats/exchange/eurofxref/html/index.en.html) in XML format using JAXB, for example, [daily rates](http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml).

### Usage

A working example is included as a [test](src/test/java/io/github/oliverdm/eurofxref/EurofxrefTest.java).

```
import java.util.Currency;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import io.github.oliverdm.eurofxref.*;

// define data source
URL url = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");

// parse XML
JAXBContext context = JAXBContext.newInstance(Envelope.class);
Unmarshaller unmarshaller = context.createUnmarshaller();
Envelope envelope = (Envelope) unmarshaller.unmarshal(url);

// loop through data
Currency euro = Currency.getInstance("EUR");
Cube timeCube = envelope.getCube().getCubes().get(0);
Date date = timeCube.getTime();
for (Cube cube : timeCube.getCubes()) {
    Currency currency = Currency.getInstance(cube.getCurrency());
    String rate = cube.getRate();
    System.out.println(date.toString() + ": 1 " + euro.getSymbol() + " = " + rate + " " + currency.getSymbol());
}

```