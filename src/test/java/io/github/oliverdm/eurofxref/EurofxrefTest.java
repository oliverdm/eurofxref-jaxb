package io.github.oliverdm.eurofxref;

import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class EurofxrefTest {
    
    @Test
    public void testParse() throws Exception {
        URL url = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Envelope root = (Envelope) unmarshaller.unmarshal(url);
        
        assertNotNull(root);
        assertNotNull(root.getSubject());
        assertNotNull(root.getSender());
        
        Cube cube = root.getCube();
        assertNotNull(cube);
        
        assertNotNull(cube.getCubes());
        assertNotNull(cube.getCubes().isEmpty());
        Cube timeCube = cube.getCubes().get(0);
        Date time = timeCube.getTime();
        assertNotNull(time);
        
        assertNotNull(timeCube.getCubes());
        List<Cube> dataCubes = timeCube.getCubes();
        assertFalse(dataCubes.isEmpty());
        
        Cube fxCube = dataCubes.get(0);
        assertNotNull(fxCube);
        String currencyCode = fxCube.getCurrency();
        assertNotNull(currencyCode);
        String rate = fxCube.getRate();
        assertNotNull(rate);
        Double rateNumber = Double.valueOf(rate);
    }
    

}
