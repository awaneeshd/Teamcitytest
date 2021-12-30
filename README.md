![Precisely](https://raw.githubusercontent.com/PreciselyData/big-data/release-5.0/Precisely_Logo.png)
# RasterSDK Sample
This RasterSDKSample gradle project demonstrates the usage of various RasterSDK APIs.
The `/src/` folder contains sample code of multiple APIs and their usage.

## Data
This sample application is using `/data/DC_MRR_CLIP_5M.MRR`, which is having one field and one band.

## Building the sample
1. Download the Spectrum RasterSDK for Big Data distribution and extract the contents.
2. Place the _spectrum-raster-sdk-&lt;version&gt;.jar_ into the `/lib` directory.
3. Depending upon client OS, copy the contents of `/resources/libs/windows` or `/resources/libs/linux` into the `/resources/nativeLibs` directory.
4. Run the following command from the root directory to build the project:
    ```
    gradlew build
    ```
   This command will generate the respective jar that will contain all the compiled code.

## Executing the sample
To execute the sample, complete the following steps:
1. Navigate to the `build/libs` directory generated after running `gradlew build` command.
2. **If the execution environment is Linux Based**, execute the following command on the terminal to set the environment variable:
   ```sh
   export LD_LIBRARY_PATH=<PATH_TO_RASTERSDK_SAMPLE_PROJECT>/resources/nativeLibs
   ```
3. Execute the jar with the fully qualified class name for the respective RasterSDK APIs using the command mentioned below:
   ```sh
   java -cp raster-sdk-samples-<version>.jar <fully_qualified_api_class_name>
    ```

**Sample execution command:**
1. To run getCellValue sample:
   ```sh
      java -cp raster-sdk-samples-1.0.jar com.precisely.rastersdk.samples.api.GetCellValueSample
    ```
2. To run getLineStatistics sample:
   ```sh
      java -cp raster-sdk-samples-1.0.jar com.precisely.rastersdk.samples.api.GetLineStatisticsSample
    ```
3. To run getPolygonStatistics sample:
   ```sh
      java -cp raster-sdk-samples-1.0.jar com.precisely.rastersdk.samples.api.GetPolygonStatisticsSample
    ```
4. To run getCrossSectionInfo sample:
   ```sh
      java -cp raster-sdk-samples-1.0.jar com.precisely.rastersdk.samples.api.GetCrossSectionInfoSample
    ```
5. To run RasterRenderer sample:
   ```sh
      java -cp raster-sdk-samples-1.0.jar com.precisely.rastersdk.samples.api.RasterRendererSample
    ```

## Custom Changes
The sample application is using hardcoded values (listed in `CommonUtils.java`) for the execution of APIs depending upon the Raster File.

To make custom changes, follow the below steps:
1. Navigate to `CommonUtils.java` class file in `src/main/java/com/precisely/rastersdk/samples/util` directory.
2. Provide/Replace hardcoded values as per raster dataset used in the `/data/` directory and follow the aforementioned steps to build and execute the sample project.