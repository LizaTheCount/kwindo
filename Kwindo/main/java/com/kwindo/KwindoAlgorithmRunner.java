package com.kwindo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sijmen on 13-5-2017.
 */
public class KwindoAlgorithmRunner {

    public static final SimpleDateFormat datetimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        if(args.length == 0)
            throw new IllegalArgumentException("Must supply data directory");
        File datadir = new File(args[0]);
        
        
        KwindoAlgorithmRunner runner = new KwindoAlgorithmRunner();
        
        System.out.println("Total profit: " +
                runner.runAlgorithm(new FlatAlgorithm(), datadir)
        );
        
    }
    
    private KwindoAlgorithm algorithm;
    private float runAlgorithm(KwindoAlgorithm algorithm, File datadir) {
        this.algorithm = algorithm;
        File[] datafiles = datadir.listFiles();
        if(datafiles == null)
            throw new IllegalArgumentException("Datafiles not found!");

        Arrays.stream(datafiles)
                .sorted(fileCom)
                .filter(f -> f.getName().endsWith("prices.csv"))
                .forEach(this::handleFile);
        return algorithm.profit;
    }
    
    private void handleFile(File file) {
        File out = new File(file.getParent() + "\\" + file.getName().substring(0, 11) + "trades.csv");
        if(out.exists())
            out.delete();
        try {
            out.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileWriter fw = new FileWriter(out)) {
            fw.write("times,trades\n");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                     handleLine(fw, line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void handleLine(FileWriter fw, String line) throws IOException {
        if(line == null || line.startsWith("times,price") || line.trim().isEmpty())
            return;
        String[] split = line.split(",");
        Integer result = algorithm.processSecond(Float.parseFloat(split[1]));
        
        if(result != null)
            fw.write(split[0] + "," + (float)result  + "\n");
    }

    private Comparator<File> fileCom = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            int n1 = extractNumber(o1.getName());
            int n2 = extractNumber(o2.getName());
            return n1 - n2;
        }
    
        private int extractNumber(String name) {
            int i;
            try {
                int s = name.indexOf('_')+1;
                int e = name.lastIndexOf('.');
                String number = name.substring(s, e);
                i = Integer.parseInt(number);
            } catch(Exception e) {
                i = 0; // if filename does not match the format
                // then default to 0
            }
            return i;
        }
    };
    
}
