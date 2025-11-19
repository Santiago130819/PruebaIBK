package com.intercorp.shop.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    public static List<Map<String,String>> leerCSV(String ruta) {
        List<Map<String,String>> filas = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(ruta));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                Map<String,String> fila = new HashMap<>();
                record.toMap().forEach(fila::put);
                filas.add(fila);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo CSV: " + ruta, e);
        }
        return filas;
    }
}
