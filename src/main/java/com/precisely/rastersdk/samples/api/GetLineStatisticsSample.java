/*
 * Copyright 2022, Precisely. All rights reserved.
 * This document contains unpublished, confidential, and proprietary information of Precisely.
 * No disclosure or use of any portion of the contents of this document may be made without the express written consent of Precisely.
 *
 */
package com.precisely.rastersdk.samples.api;

import com.precisely.rastersdk.io.RasterBandInfo;
import com.precisely.rastersdk.io.RasterDataset;
import com.precisely.rastersdk.io.RasterLineStatistics;

import static com.precisely.rastersdk.samples.util.CommonUtils.BAND_INDEX;
import static com.precisely.rastersdk.samples.util.CommonUtils.COORDINATE_SYSTEM;
import static com.precisely.rastersdk.samples.util.CommonUtils.DATAFILE;
import static com.precisely.rastersdk.samples.util.CommonUtils.FIELD_INDEX;
import static com.precisely.rastersdk.samples.util.CommonUtils.FORMAT_OUTPUT;
import static com.precisely.rastersdk.samples.util.CommonUtils.ISOLATED_PROCESS;
import static com.precisely.rastersdk.samples.util.CommonUtils.LINE_WKB_STRING;
import static com.precisely.rastersdk.samples.util.CommonUtils.SAMPLE_COUNT;
import static com.precisely.rastersdk.samples.util.CommonUtils.getRasterDataset;
import static com.precisely.rastersdk.samples.util.CommonUtils.setEnvironment;

public final class GetLineStatisticsSample {

    /**
     * Private Constructor.
     */
    private GetLineStatisticsSample() {
        // DO NOTHING.
    }

    /**
     * Main method to execute GetLineStatistics Sample.
     */
    public static void main(String[] args) {

        /*
         * Setting the Environment Variable Required for RasterSDK.
         */
        setEnvironment();

        /*
         * Get the RasterDataset using the Isolated Flag.
         */
        RasterDataset rasterDataset = getRasterDataset(ISOLATED_PROCESS);

        /*
         * Get the RasterBandInfo for the mentioned Band.
         */
        RasterBandInfo rasterBandInfo = rasterDataset.getRasterInfo().getFieldInfos()
                .get(FIELD_INDEX).getBandInfos().get(BAND_INDEX);

        /*
         * getLineStatistics API call to get the required RasterLineStatistics Object.
         */
        RasterLineStatistics statistics = rasterDataset.getLineStatistics(LINE_WKB_STRING,
                COORDINATE_SYSTEM, FIELD_INDEX, BAND_INDEX, SAMPLE_COUNT);

        /*
         *  Display getLineStatistics Output for provided wkbString, coordinateSystem, sample count.
         */
        System.out.println("\n"+FORMAT_OUTPUT);
        System.out.println("\t\t\tGET LINE STATISTICS OUTPUT");
        System.out.println(FORMAT_OUTPUT);
        System.out.println("\tRaster file used: " + DATAFILE);
        System.out.println("\tRaster Field Index: " + FIELD_INDEX);
        System.out.println("\tRaster Band Index: " + BAND_INDEX);
        System.out.println("\tRaster Band Data Type: " + rasterBandInfo.getDataType());
        System.out.println("\tWKB String: " + LINE_WKB_STRING);
        System.out.println("\tCoordinate System: " + COORDINATE_SYSTEM);
        System.out.println("\tMinimum Value: " + statistics.getMin());
        System.out.println("\tMaximum Value: " + statistics.getMax());
        System.out.println("\tMean: " + statistics.getMean());
        System.out.println("\tRange: " + statistics.getRange());
        System.out.println("\tStart Value: " + statistics.getStartValue());
        System.out.println("\tMiddle Value: " + statistics.getMiddleValue());
        System.out.println("\tEnd Value: " + statistics.getEndValue());
        System.out.println(FORMAT_OUTPUT);
    }
}
