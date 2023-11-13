 package logger.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String format(LogRecord record) {
        String dateTime = dateFormat.format(new Date());
        return record.getLevel() + ": [" + dateTime + "]: " + record.getMessage() + "\n";
    }
}
