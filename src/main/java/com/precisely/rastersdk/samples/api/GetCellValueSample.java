/*
 * Copyright 2022, Precisely. All rights reserved.
 * This document contains unpublished, confidential, and proprietary information of Precisely.
 * No disclosure or use of any portion of the contents of this document may be made without the express written consent of Precisely.
 *
 */
package com.precisely.rastersdk.samples.api;


import com.precisely.rastersdk.io.CellValue;
import com.precisely.rastersdk.io.RandomBand;
import com.precisely.rastersdk.io.RasterBandInfo;
import com.precisely.rastersdk.io.RasterDataset;
import com.precisely.rastersdk.io.RasterPoint;
import com.precisely.rastersdk.io.RasterRandomIterator;

import static com.precisely.rastersdk.samples.util.CommonUtils.BAND_INDEX;
import static com.precisely.rastersdk.samples.util.CommonUtils.DATAFILE;
import static com.precisely.rastersdk.samples.util.CommonUtils.FIELD_INDEX;
import static com.precisely.rastersdk.samples.util.CommonUtils.FORMAT_OUTPUT;
import static com.precisely.rastersdk.samples.util.CommonUtils.ISOLATED_PROCESS;
import static com.precisely.rastersdk.samples.util.CommonUtils.WORLD_X;
import static com.precisely.rastersdk.samples.util.CommonUtils.WORLD_Y;
import static com.precisely.rastersdk.samples.util.CommonUtils.getRasterDataset;
import static com.precisely.rastersdk.samples.util.CommonUtils.setEnvironment;

public final class GetCellValueSample {

    /**
     * Private Constructor.
     */
    private GetCellValueSample() {
        // DO NOTHING.
    }

    /**
     * Main method to execute GetCellValue Sample.
     */
    public static void main(String[] args) {

        /*
         * Setting up environment path for native binaries.
         */
        setEnvironment();

        /*
         * Get the RasterDataset using isolated flag as 'false' to run the RasterSDK in the same JVM.
         */
        RasterDataset rasterDataset = getRasterDataset(ISOLATED_PROCESS);

        /*
         * Get the RasterBandInfo from the given Raster Dataset.
         */
        RasterBandInfo rasterBandInfo = rasterDataset.getRasterInfo().getFieldInfos().get(FIELD_INDEX).getBandInfos().get(BAND_INDEX);

        /*
         * GetCellValue from the Raster Dataset.
         */
        CellValue cellValue = getCellValue(rasterDataset);

        /*
         *  Display getCellValue for provided world co-ordinates.
         */
        System.out.println("\n" + FORMAT_OUTPUT);
        System.out.println("\t\t\tGET CELL VALUE OUTPUT");
        System.out.println(FORMAT_OUTPUT);
        System.out.println("\tRaster file used: " + DATAFILE);
        System.out.println("\tRaster Field Index: " + FIELD_INDEX);
        System.out.println("\tRaster Band Index: " + BAND_INDEX);
        System.out.println("\tRaster Band Data Type: " + rasterBandInfo.getDataType());
        System.out.println("\tWorld Coordinates (X,Y) : (" + WORLD_X + "," + WORLD_Y + ")");
        System.out.println("\tCellValue: " + cellValue.numberValue().floatValue());
        System.out.println(FORMAT_OUTPUT);
    }

    /**
     * @param rasterDataset: Provided Raster Dataset.
     * @return Object of CellValue.
     */
    private static CellValue getCellValue(RasterDataset rasterDataset) {
        /*
         * Create RasterPoint after converting World Co-ordinates to Cell Co-ordinates.
         */
        RasterPoint rasterPoint = rasterDataset.convertWorldToCell(WORLD_X, WORLD_Y);

        /*
         * Get RandomIterator for the RasterDataset.
         */
        RasterRandomIterator randomIterator = rasterDataset.getRandomIterator();

        /*
         * Get the RasterBand for the provided Band_Index.
         */
        RandomBand randomBand = randomIterator.getBand(BAND_INDEX);

        /*
         * Get CellValue using the calculated RandomBand and RasterPoint.
         */
        return randomBand.getCellValue((long) rasterPoint.getX(), (long) rasterPoint.getY());
    }
}
