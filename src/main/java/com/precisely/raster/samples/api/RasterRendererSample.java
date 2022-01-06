/*
 * Copyright 2022, Precisely. All rights reserved.
 * This document contains unpublished, confidential, and proprietary information of Precisely.
 * No disclosure or use of any portion of the contents of this document may be made without the express written consent of Precisely.
 *
 */
package com.precisely.raster.samples.api;

import com.precisely.raster.internal.midev_core.RasterExtent;
import com.precisely.raster.io.RasterEngine;
import com.precisely.raster.io.RasterEngines;
import com.precisely.raster.renderer.RasterRenderer;

import java.awt.image.BufferedImage;

import static com.precisely.raster.samples.util.CommonUtils.ABSOLUTE_DATASET;
import static com.precisely.raster.samples.util.CommonUtils.FORMAT_OUTPUT;
import static com.precisely.raster.samples.util.CommonUtils.ISOLATED_PROCESS;
import static com.precisely.raster.samples.util.CommonUtils.MAX_X;
import static com.precisely.raster.samples.util.CommonUtils.MAX_Y;
import static com.precisely.raster.samples.util.CommonUtils.MIN_X;
import static com.precisely.raster.samples.util.CommonUtils.MIN_Y;
import static com.precisely.raster.samples.util.CommonUtils.OUTPUT_DIRECTORY;
import static com.precisely.raster.samples.util.CommonUtils.PIXEL_HEIGHT;
import static com.precisely.raster.samples.util.CommonUtils.PIXEL_WIDTH;
import static com.precisely.raster.samples.util.CommonUtils.saveRenderedImage;
import static com.precisely.raster.samples.util.CommonUtils.setEnvironment;

public final class RasterRendererSample {

    /**
     * Private Constructor.
     */
    private RasterRendererSample() {
        // DO NOTHING
    }

    /**
     * Main method to execute RasterRenderer Sample.
     */
    public static void main(String[] args) {

        /*
         * Setting the Environment Variable Required for RasterSDK.
         */
        setEnvironment();

        /*
         * Get the RasterEngine based on the isolated flag.
         */
        RasterEngine rasterEngine = RasterEngines.getRasterEngine(ISOLATED_PROCESS);

        /*
         * Create the RasterRenderer using the rasterEngine for the given Raster file.
         */
        try (RasterRenderer rasterRenderer = rasterEngine.createRenderer(ABSOLUTE_DATASET)) {

            /*
             * Create a RasterExtent for the provided area using World Coordinates.
             */
            RasterExtent rasterExtent = new RasterExtent(MIN_X, MIN_Y, MAX_X, MAX_Y);

            /*
             * Render the raster using the rasterExtent and Pixel Information.
             */
            BufferedImage actualImage = rasterRenderer.render(PIXEL_WIDTH, PIXEL_HEIGHT,
                    rasterExtent.getWidth(), rasterExtent.getHeight(), rasterExtent.getMinX(), rasterExtent.getMinY());

            /*
             * Save the rendered image to the output directory.
             */
            saveRenderedImage(actualImage);

            /*
             * Display the output message if Raster Renderer is successful.
             */
            System.out.println("\n" + FORMAT_OUTPUT);
            System.out.println("\tRENDERED RASTER SAVED SUCCESSFULLY IN THE '" + OUTPUT_DIRECTORY + "' DIRECTORY.");
            System.out.println(FORMAT_OUTPUT);

        } catch (Exception e) {
            System.out.println("Unable to render raster using provided information.");
            System.out.println("Exception: " + e.getMessage());
        }
    }

}
