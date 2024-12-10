package com.klef.example.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.klef.example.entity.Counsellor;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


import java.io.InputStreamReader;

public class CSVParser {
    public List<Counsellor> parse(InputStream inputStream) throws CsvValidationException {
        List<Counsellor> counsellors = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] line;
            reader.readNext(); // Skip header row
            while ((line = reader.readNext()) != null) {
                Counsellor counsellor = new Counsellor();
                counsellor.setName(line[0]);
                counsellor.setQualification(line[1]);
                counsellor.setEmail(line[2]);
                counsellor.setPhoneNumber(line[3]);
                counsellor.setPassword(line[4]);
                counsellor.setLinks(line[5]);
                counsellors.add(counsellor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counsellors;
    }
}
