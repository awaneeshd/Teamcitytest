/*
 * Copyright 2022, Precisely. All rights reserved.
 * This document contains unpublished, confidential, and proprietary information of Precisely.
 * No disclosure or use of any portion of the contents of this document may be made without the express written consent of Precisely.
 *
 */
package com.precisely.raster.samples.api;

import com.precisely.raster.io.RasterBandInfo;
import com.precisely.raster.io.RasterCrossSection;
import com.precisely.raster.io.RasterCrossSectionInfo;
import com.precisely.raster.io.RasterDataset;

import java.util.Arrays;
import java.util.List;

import static com.precisely.raster.samples.util.CommonUtils.BANDS;
import static com.precisely.raster.samples.util.CommonUtils.BAND_INDEX;
import static com.precisely.raster.samples.util.CommonUtils.COORDINATE_SYSTEM;
import static com.precisely.raster.samples.util.CommonUtils.CROSSSECTION_WKB_STRING;
import static com.precisely.raster.samples.util.CommonUtils.DATAFILE;
import static com.precisely.raster.samples.util.CommonUtils.DISTANCE_UNIT_CODE;
import static com.precisely.raster.samples.util.CommonUtils.FIELD_INDEX;
import static com.precisely.raster.samples.util.CommonUtils.FILTER_MODE;
import static com.precisely.raster.samples.util.CommonUtils.FORMAT_OUTPUT;
import static com.precisely.raster.samples.util.CommonUtils.ISOLATED_PROCESS;
import static com.precisely.raster.samples.util.CommonUtils.READ_FROM_BASE_RESOLUTION_ONLY;
import static com.precisely.raster.samples.util.CommonUtils.SAMPLE_COUNT;
import static com.precisely.raster.samples.util.CommonUtils.getRasterDataset;
import static com.precisely.raster.samples.util.CommonUtils.setEnvironment;

public final class GetCrossSectionInfoSample {

    /**
     * Private Constructor.
     */
    private GetCrossSectionInfoSample() {
        // DO NOTHING.
    }

    /**
     * Main method to execute GetCrossSectionInfo Sample.
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
         * Get the information about each sample point in form of List.
         */
        List<RasterCrossSectionInfo> crossSectionInfo = rasterDataset.getCrossSectionInfo(CROSSSECTION_WKB_STRING, COORDINATE_SYSTEM, FILTER_MODE,
                FIELD_INDEX, BANDS, SAMPLE_COUNT, DISTANCE_UNIT_CODE, READ_FROM_BASE_RESOLUTION_ONLY);

        /*
         * Get the List of sample points, their location and cell value.
         */
        List<RasterCrossSection> crossSections = crossSectionInfo.get(0).getRasterCrossSections();

        System.out.println("\n" + FORMAT_OUTPUT);
        System.out.println("\t\t\tGET CROSS SECTION INFO OUTPUT");
        System.out.println(FORMAT_OUTPUT);
        System.out.println("\tRaster File used: " + DATAFILE);
        System.out.println("\tRaster Field Index: " + FIELD_INDEX);
        System.out.println("\tWKB String: " + CROSSSECTION_WKB_STRING);
        System.out.println("\tCoordinate System: " + COORDINATE_SYSTEM);
        System.out.println("\tRaster Band Data Type: " + rasterBandInfo.getDataType());
        System.out.println("\tDistance Unit Code: " + DISTANCE_UNIT_CODE);
        System.out.println("\tRead from Base Resolution Only: " + READ_FROM_BASE_RESOLUTION_ONLY);
        System.out.println("\tFilter Mode: " + FILTER_MODE);
        System.out.println("\tNumber of Bands: " + Arrays.toString(BANDS));
        System.out.println("\tNumber of Samples: " + crossSections.size());

        System.out.println("\n\t32nd SAMPLE CROSSSECTION INFO...");
        System.out.println("\t\tX: " + crossSections.get(31).getX());
        System.out.println("\t\tY: " + crossSections.get(31).getY());
        System.out.println("\t\tValue: " + crossSections.get(31).getValue());
        System.out.println("\t\tDistance: " + crossSections.get(31).getDistance());
        System.out.println("\t\tSample Point Status: " + crossSections.get(31).getPointStatus().toString());

        System.out.println("\n\t33rd SAMPLE CROSSSECTION INFO...");
        System.out.println("\t\tX: " + crossSections.get(32).getX());
        System.out.println("\t\tY: " + crossSections.get(32).getY());
        System.out.println("\t\tValue: " + crossSections.get(32).getValue());
        System.out.println("\t\tDistance: " + crossSections.get(32).getDistance());
        System.out.println("\t\tSample Point Status: " + crossSections.get(32).getPointStatus().toString());

        System.out.println(FORMAT_OUTPUT);

    }
}