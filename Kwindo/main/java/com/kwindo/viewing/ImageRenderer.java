package com.kwindo.viewing;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Sijmen on 14-5-2017.
 */
public class ImageRenderer {

    public static void main(String[] args) throws IOException {
        if(args.length == 0)
            throw new IllegalArgumentException("Must supply data directory");
        File datadir = new File(args[0]);


        File[] datafiles = datadir.listFiles();
        if(datafiles == null)
            throw new IllegalArgumentException("Datafiles not found!");

        ArrayList<Float> fulldataset = new ArrayList<>();
        Arrays.stream(datafiles)
                .filter(f -> f.getName().endsWith("prices.csv"))
                .map(ImageRenderer::plotFile)
                .forEach(fulldataset::addAll);
        
        makePlottedImage(datadir + "plot", fulldataset);
        
    }

    private static ArrayList<Float> plotFile(File file) {
        ArrayList<Float> ydatas = new ArrayList<>();

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(line.startsWith("times"))
                        continue;
                    ydatas.add(Float.parseFloat(line.split(",")[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ydatas;
    }

    private static void makePlottedImage(String filename, ArrayList<Float> ydatas) throws IOException {
        ArrayList<Integer> xData = new ArrayList<>();
        
        for(int i = 0; i < ydatas.size(); i++)
            xData.add(i);
        
        XYChart chart = QuickChart.getChart("Chart", "time", "data", "time(data)", xData, ydatas);

//        new SwingWrapper<>(chart).displayChart();

        BitmapEncoder.saveBitmap(chart, filename, BitmapEncoder.BitmapFormat.PNG);
        
        BitmapEncoder.saveBitmapWithDPI(chart, filename, BitmapEncoder.BitmapFormat.PNG, 500);

//        BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.PNG, 300);
    }

}
