package pl.konczak.nzoz.ewus.client.ewus.checkcwu;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pl.konczak.nzoz.ewus.config.EwusPersistenceConfiguration;

import javax.xml.soap.SOAPMessage;

@Component
public class CheckCWUStatusResponsePersistence {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckCWUStatusResponsePersistence.class);

    private final EwusPersistenceConfiguration ewusPersistenceConfiguration;

    private final DateTimeFormatter dateTimeFormatter;

    public CheckCWUStatusResponsePersistence(EwusPersistenceConfiguration ewusPersistenceConfiguration) {
        this.ewusPersistenceConfiguration = ewusPersistenceConfiguration;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    }

    void persist(String pesel, SOAPMessage message) throws Exception {
        if (!ewusPersistenceConfiguration.isExecute()) {
            return;
        }
        String pathToFile = createFilePath(pesel);

        File file = new File(pathToFile);
        file.createNewFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            message.writeTo(fileOutputStream);
            fileOutputStream.flush();
        }

        LOGGER.info("CheckCWUStatusResponse save to file <{}>", pathToFile);
    }

    private String createFilePath(String pesel) {
        LocalDateTime now = LocalDateTime.now();

        String date = now.format(dateTimeFormatter);

        return ewusPersistenceConfiguration.getFolder() + date + "_" + pesel + ".xml";
    }
}