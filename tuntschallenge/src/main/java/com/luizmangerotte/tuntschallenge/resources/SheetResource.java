package com.luizmangerotte.tuntschallenge.resources;


import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.luizmangerotte.tuntschallenge.services.SheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping(value = "/sheets")
@Slf4j
@Api(value = "API Rest Google Sheet")
@CrossOrigin(origins = "*")
public class SheetResource {
    final private SheetService sheetsService;

    public SheetResource(SheetService sheetsService) {
        this.sheetsService = sheetsService;
    }


    @GetMapping
    @ApiOperation(value = "Returns a list of data from a sheet")
    public ResponseEntity<ValueRange> getRead() throws IOException, GeneralSecurityException {
        log.info("Getting data from sheet");
        return new ResponseEntity<>(sheetsService.getValuesResponse(), HttpStatus.OK);
    }

    @PutMapping(value = "")
    @ApiOperation(value = "Updates the fields with the student's status")
    public ResponseEntity<BatchUpdateValuesResponse> insertSheet()
            throws IOException, GeneralSecurityException {
        log.info("Requesting sheets update");
        return new ResponseEntity<>(sheetsService.updateData(), HttpStatus.NO_CONTENT);
    }

}
