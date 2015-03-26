package io.github.oliverdm.eurofxref;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CubeTimeAdapter extends XmlAdapter<String, Date> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public String marshal(Date time) throws Exception {
        if (time != null) {
            // SimpleDateFormat is not thread-safe
            return new SimpleDateFormat(DATE_FORMAT).format(time);
        }
        return null;
    }

    @Override
    public Date unmarshal(String time) throws Exception {
        if (time != null) {
            return new SimpleDateFormat(DATE_FORMAT).parse(time);
        }
        return null;
    }

}
