package com.luizmangerotte.tuntschallenge.services;

import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.luizmangerotte.tuntschallenge.config.GoogleServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SheetService {

    @Value("${spreadsheet.id}")
    private String spreadsheetId;
    @Value("${range}")
    private String range;

    private final StudentService studentService;

    public SheetService(StudentService studentService) {
        this.studentService = studentService;
    }
    public ValueRange getValuesResponse()
            throws GeneralSecurityException, IOException {
        log.info("Getting data from sheet");
        return GoogleServiceConfig.getSheetsService()
                .spreadsheets()
                .values()
                .get(spreadsheetId, range)
                .execute();
    }
    public BatchUpdateValuesResponse updateData()
            throws GeneralSecurityException, IOException {

        List<ValueRange> data = new ArrayList<>();
        log.info("Organizing data to include in Json file");
        data.add(new ValueRange()
                .setRange(range)
                .setValues(studentService.validationAndInsertListObject(
                        getValuesResponse())));

        log.info("Making a request to update");
        BatchUpdateValuesRequest body = new BatchUpdateValuesRequest()
                .setValueInputOption("RAW")
                .setData(data);

        log.info("Requesting update");
        return GoogleServiceConfig.getSheetsService()
                .spreadsheets()
                .values()
                .batchUpdate(spreadsheetId, body)
                .execute();
    }

}
