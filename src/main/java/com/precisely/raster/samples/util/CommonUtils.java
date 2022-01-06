/*
 * Copyright 2022, Precisely. All rights reserved.
 * This document contains unpublished, confidential, and proprietary information of Precisely.
 * No disclosure or use of any portion of the contents of this document may be made without the express written consent of Precisely.
 *
 */
package com.precisely.raster.samples.util;

import com.precisely.raster.common.MapInfoUnitCode;
import com.precisely.raster.common.RasterFieldBandFilterMode;
import com.precisely.raster.io.RasterDataset;
import com.precisely.raster.io.RasterEngine;
import com.precisely.raster.io.RasterEngines;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class CommonUtils {

    /**
     * Current Working Directory.
     */
    private static final String CURRENT_DIR = System.getProperty("user.dir") + "\\..\\..";

    /**
     * Field Index.
     */
    public static final int FIELD_INDEX = 0;

    /**
     * Band Index.
     */
    public static final int BAND_INDEX = 0;

    /**
     * Raster Data file.
     */
    public static final String DATAFILE_NAME = "DC_MRR_CLIP_5M";

    /**
     * Relative Path to Raster Dataset.
     */
    public static final String DATAFILE = "\\data\\" + DATAFILE_NAME + ".MRR";

    /**
     * Absolute Path to Raster Dataset.
     */
    public static final String ABSOLUTE_DATASET = CURRENT_DIR + DATAFILE;

    /**
     * Path for Native Binaries.
     */
    private static final String NATIVE_LIBS_PATH = CURRENT_DIR + "\\resources\\nativeLibs";

    /**
     * Internal Environment Variable.
     */
    private static final String NATIVE_LIBS_ENVIRONMENT = "com.precisely.raster.lib.dir";

    /**
     * JVM Process Isolation flag.
     * 'false': To run the RasterSDK in the same JVM.
     * 'true': To run the RasterSDK in a separate JVM.
     */
    public static final boolean ISOLATED_PROCESS = false;

    /**
     * Formatting String to display message.
     */
    public static final String FORMAT_OUTPUT = "=====================================================================================";

    /**
     * WorldCoordinate-X to calculate CellValue.
     */
    public static final double WORLD_X = 330708.677188346;

    /**
     * WorldCoordinate-Y to calculate CellValue.
     */
    public static final double WORLD_Y = 4314892.20617208;

    /**
     * Coordinate System String Value.
     */
    public static final String COORDINATE_SYSTEM = "mapinfo:CoordSys 8, 74, 7, -75, 0, 0.9996, 500000, 0";

    /**
     * Well Known Binary String to calculate Polygon Statistics.
     */
    public static final String POLYGON_WKB_STRING = "010300000001000000050000009a9999994f9a134100000020457050419a9999994f9a1341d7a3707d3d755041b81e85eb6de31341d7a3707d3d755041b81e85eb6de3134100000020457050419a9999994f9a13410000002045705041";

    /**
     * Well Known Binary String to calculate Line Statistics.
     */
    public static final String LINE_WKB_STRING = "010200000002000000713d0ad7af7813411f85eb71006e5041295c8fc2918e134152b81ea567725041";

    /**
     * Well Known Binary String to calculate CrossSectionInfo.
     */
    public static final String CROSSSECTION_WKB_STRING = "01020000000200000000000000c4891341b81e856b247850419a999999f6e3134133333353a4775041";

    /**
     * The number of times the line or polyline to be sampled.
     */
    public static final int SAMPLE_COUNT = 100;

    /**
     * Represents the MapInfo units to measure the distance covered by the line or polyline object.
     */
    public static final MapInfoUnitCode DISTANCE_UNIT_CODE = MapInfoUnitCode.METERS;

    /**
     * Flag to indicate whether base resolution of the input raster is to be used to extract a cross section profile.
     */
    public static final boolean READ_FROM_BASE_RESOLUTION_ONLY = true;

    /**
     * Mode to filter out raster's field and bands.
     */
    public static final RasterFieldBandFilterMode FILTER_MODE = RasterFieldBandFilterMode.ALL_FIELDS_ALL_BANDS;

    /**
     * Zero-based band indexes as an array.
     */
    public static final int[] BANDS = null;

    /**
     * Output directory for Raster Renderer Sample.
     */
    public static final String OUTPUT_DIRECTORY = "\\rendering_output";

    /**
     * Absolute path to the Raster Renderer Output.
     */
    private static final String RENDER_OUTPUT = CURRENT_DIR +  OUTPUT_DIRECTORY;

    /**
     * Minimum value of x-coordinate for the selected area.
     */
    public static final double MIN_X = 320386.27;

    /**
     * Minimum value of y-coordinate for the selected area.
     */
    public static final double MIN_Y = 4298816.29;

    /**
     * Maximum value of x-coordinate for the selected area.
     */
    public static final double MAX_X = 330804.33;

    /**
     * Maximum value of y-coordinate for the selected area.
     */
    public static final double MAX_Y = 4308824.45;

    /**
     * Pixel Width.
     */
    public static final int PIXEL_WIDTH = 296;

    /**
     * Pixel Height.
     */
    public static final int PIXEL_HEIGHT = 296;

    /**
     * Private Constructor.
     */
    private CommonUtils() {
        // DO NOTHING.
    }

    /**
     * Setting up the environment Path for RasterSDK Libraries.
     */
    public static void setEnvironment() {
        System.setProperty(NATIVE_LIBS_ENVIRONMENT, NATIVE_LIBS_PATH);
    }

    /**
     * @param isolated: 'true' to run the RasterSDK in separate JVM otherwise 'false' to run in the same JVM.
     * @return Object of RasterDataset.
     */
    public static RasterDataset getRasterDataset(boolean isolated) {
        /*
         * Get the RasterEngine based on isolated flag.
         */
        RasterEngine rasterEngine = RasterEngines.getRasterEngine(isolated);

        /*
         * Open the RasterEngine and return the RasterDataset.
         */
        return rasterEngine.open(ABSOLUTE_DATASET);
    }

    /**
     * Save the Rendered Image file to the Local Directory.
     *
     * @param actualImage Object of Rendered Image.
     * @throws IOException Exception if unable to save the image.
     */
    public static void saveRenderedImage(BufferedImage actualImage) throws IOException {
        /*
         * Path to the output directory where rendered image file will be saved.
         */
        Path path = Paths.get(RENDER_OUTPUT);

        /*
         * Remove the contents of the directory if it already exists.
         */
        if (Files.isDirectory(path))
            removeDirectory(path);

        /*
         * Create the directory if it doesn't exist.
         */
        Files.createDirectory(path);

        /*
         * Write the rendered raster output file in the PNG format.
         */
        ImageIO.write(actualImage, "png", new File(path.toFile(), DATAFILE_NAME + ".PNG"));
    }

    /**
     * Removes the content of the output directory.
     *
     * @param dir Path to the output directory.
     * @throws IOException return IOException if unable to delete/open the directory.
     */
    private static void removeDirectory(Path dir) throws IOException {
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(dir)) {
            paths.forEach(file -> {
                try {
                    Files.delete(file);
                } catch (IOException e) {
                    System.out.println("Unable to delete the contents in the '" + OUTPUT_DIRECTORY + "' directory." +
                            "The directory is expected to be empty, please remove the contents manually.");
                    System.out.println("Exception: " + e.getMessage());
                }
            });
        }
        Files.delete(dir);
    }

}
