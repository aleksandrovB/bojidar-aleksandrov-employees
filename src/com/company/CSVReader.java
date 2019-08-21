package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {

    /**
     * Reads lines from .csv file and converts them into ProjectParticipationData
     * @param path path of .csv file
     * @return data contained in .cvs file converted into list of ProjectParticipationData
     */
    public List<ProjectsParticipationData> readCSV(String path) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<ProjectsParticipationData> data = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null ){
                String[] temp = line.split(",");
                if(temp.length<4){
                    data.add(new ProjectsParticipationData(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
                            format.parse(temp[2]), new Date()));
                }else {
                    data.add(new ProjectsParticipationData(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
                            format.parse(temp[2]), format.parse(temp[3])));
                }
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Invalid date format.Date format should be yyyy-MM-dd.");
        }
        return data;
    }
}
