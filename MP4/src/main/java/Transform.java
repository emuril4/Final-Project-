/**
 * A class that runs implements several simple transformations on 2D image arrays.
 * <p>
 * This file contains stub code for your image transformation methods that are called by the main
 * class. You will do your work for this MP in this file.
 * <p>
 * Note that you can make several assumptions about the images passed to your functions, both by the
 * web front end and by our testing harness:
 * <ul>
 * <li>You will not be passed empty images.</li>
 * <li>All images will have even width and height.</li>
 * </ul>
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/4/">MP4 Documentation</a>
 */
public class Transform {

    /**
     * Default amount to shift an image's position. Not used by the testing suite, so feel free to
     * change this value.
     */
    public static final int DEFAULT_POSITION_SHIFT = 16;

    /**
     * Pixel value to use as filler when you don't have any valid data. All white with complete
     * transparency. DO NOT CHANGE THIS VALUE: the testing suite relies on it.
     */
    public static final int FILL_VALUE = 0x00FFFFFF;

    /**
     * Shift the image left by the specified amount.
     * <p>
     * Any pixels shifted in from off screen should be filled with FILL_VALUE. This function <i>does
     * not modify the original image</i>.
     *
     * @param originalImage the image to shift to the left
     * @param amount the amount to shift the image to the left
     * @return the shifted image
     */
    public static int[][] shiftLeft(final int[][] originalImage, final int amount) {
      /*  int[][] imageOne = new int[originalImage.length][originalImage[0].length];
        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[i].length; j++) {
                imageOne[i - 1][j] = originalImage[i][(j + 1) % originalImage[i].length];
            }
        }
        */
        int[][] imageOne = new int[originalImage.length][originalImage[0].length];
        for (int i = 0; i < originalImage.length - 1; i++) {
            for (int j = 0; j < originalImage[i].length - 1; j++) {
                imageOne[i][j] = originalImage[i][(j - 1) % originalImage[i].length];
            }
        }
        return imageOne;
    }




    /**
     * Shift right like above.
     *
     * @param originalImage : the image input
     * @param amount : amount to shift the original image right
     * @return the image shifted right
     */

    public static int[][] shiftRight(final int[][] originalImage, final int amount) {
        int[][] imageOne = new int[originalImage.length][originalImage[0].length];
        for (int i = originalImage.length - 1; i == -1; i--) {
            for (int j = 0; j < imageOne[i].length - 1; j++) {
                if (i - amount < 0) {
                    imageOne[i][j] = FILL_VALUE;
                } else {
                    imageOne[i][j] = imageOne[i - amount][j];
                }
            }
        }
        return imageOne;
    }

    /**
     * Shift up like above.
     * @param originalImage  image input
     * @param amount  pixels to shift
     * @return  image shifted up
     */

    public static int[][] shiftUp(final int[][] originalImage, final int amount) {
        int[][] imageOne = new int[originalImage.length][originalImage[0].length];
        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[i].length; j++) {
                if (i + amount > (originalImage[i].length - 1)) {
                    imageOne[i][j] = FILL_VALUE;
                } else {
                    imageOne[i][j] = imageOne[i][j + amount];
                }
            }
        }
        return imageOne;
    }

    /**
     * Shift down like above.
     * @param originalImage : image input
     * @param amount : pixels to shift down
     * @return : image shifted down
     */

    public static int[][] shiftDown(final int[][] originalImage, final int amount) {
        int[][] imageOne = new int[originalImage.length][originalImage[0].length];
        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[i].length; j++) {
                if (j < amount) {
                    imageOne[i][j] = FILL_VALUE;
                } else {
                    imageOne[i][j] = imageOne[i][j - amount];
                }
            }
        }
        return imageOne;
    }

    /**
     * Rotate the image left by 90 degrees around its center.
     * <p>
     * Any pixels rotated in from off screen should be filled with FILL_VALUE. This function <i>does
     * not modify the original image</i>.
     *
     * @param originalImage the image to rotate left 90 degrees
     * @return the rotated image
     */
    public static int[][] rotateLeft(final int[][] originalImage) {
        int[][] rotateL = new int[originalImage.length][originalImage[0].length];
        for (int i = 0; i < originalImage[0].length; i++) {
            for (int j = 0; j < originalImage.length; j++) {
                rotateL[i][j] = originalImage[j][originalImage[0].length - 1 - i];
            }
        }
        return rotateL;
    }

    /**
     * Rotate the image right like above.
     * @param originalImage : image input
     * @return returns the rotated image
     */

    public static int[][] rotateRight(final int[][] originalImage) {
        int[][] rotateR = new int[originalImage[0].length - 1][originalImage.length - 1];
        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[0].length; j++) {
                rotateR[i][j] = originalImage[j][i - rotateR.length - 1];
            }
        }
        return rotateR;
    }

    /**
     * Flip the image on the vertical axis across its center.
     *
     * @param originalImage : image input
     * @return : returns image flipped vertically
     */

    public static int[][] flipVertical(final int[][] originalImage) {
        int[][]verticalImg = new int[originalImage.length][originalImage[0].length];
        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[i].length; j++) {
                verticalImg[i][originalImage[j].length - j - 1] = originalImage[i][j];
            }
        }
        return verticalImg;
    }

    /**
     * @param originalImage : image input
     * @return : returns image flipped horizontally
     * Flip the image on the horizontal axis across its center.
     */
    public static int[][] flipHorizontal(final int[][] originalImage) {
        int[][]horizontalImg = new int[originalImage.length][originalImage[0].length];
        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[i].length; j++) {
                horizontalImg[originalImage.length - 1 - i][j] = originalImage[i][j];
            }
        }
        return horizontalImg;
    }

    /**
     * Default amount to shift colors by. Not used by the testing suite, so feel free to change this
     * value.
     */
    public static final int DEFAULT_COLOR_SHIFT = 32;

    /**
     * Add red to the image.
     * <p>
     * This function <i>does not modify the original image</i>. It should also not generate any new
     * filled pixels.
     *
     * @param originalImage the image to add red to
     * @param amount the amount of red to add
     * @return the recolored image
     */
    public static int[][] moreRed(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * Remove red from the image.
     * @param originalImage the image to subtract red to
     * @param amount the amount of red to subtract
     * @return the recolored image
     */
    public static int[][] lessRed(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * @param originalImage the image to add green to
     * @param amount the amount of green to add
     * @return the recolored image
     *         Add green to the image.
     */
    public static int[][] moreGreen(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * @param originalImage the image to subtract green to
     * @param amount the amount of green to subtract
     * @return the recolored image
     *         Remove green from the image.
     */
    public static int[][] lessGreen(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * @param originalImage the image to add blue to
     * @param amount the amount of blue to add
     * @return the recolored image
     *         Add blue to the image.
     */
    public static int[][] moreBlue(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * @param originalImage the image to subtract blue to
     * @param amount the amount of blue to subtract
     * @return the recolored image
     *         Remove blue from the image.
     */
    public static int[][] lessBlue(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * Increase the image alpha channel.
     *
     * @param originalImage : image input that will be altered
     * @param amount : amount to alter
     * @return : new image
     */
    public static int[][] moreAlpha(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * @param originalImage :
     * @param amount :
     *        Decrease the image alpha channel.
     * @return the new image
     */
    public static int[][] lessAlpha(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * The default resize factor. Not used by the testing suite, so feel free to change this value.
     */
    public static final int DEFAULT_RESIZE_AMOUNT = 2;

    /**
     * Shrink in the vertical axis around the image center.
     * <p>
     * An amount of 2 will result in an image that is half its original height. An amount of 3 will
     * result in an image that's a third of its original height. Any pixels shrunk in from off
     * screen should be filled with FILL_VALUE. This function <i>does not modify the original
     * image</i>.
     *
     * @param originalImage the image to shrink
     * @param amount the factor by which the image's height is reduced
     * @return the shrunken image
     */
    public static int[][] shrinkVertical(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * Expand in the vertical axis around the image center.
     *
     * @param originalImage the image to expand
     * @param amount the factor by which the image's height is increased
     * @return the expanded image
     */

    public static int[][] expandVertical(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * @param originalImage the image to shrink
     * @param amount the factor by which the image's width is reduced
     * @return the shrunken image
     *         Shrink in the horizontal axis around the image center.
     */
    public static int[][] shrinkHorizontal(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * @param originalImage the image to expand
     * @param amount the factor by which the image's width is expanded
     * @return the expanded image
     *         Expand in the horizontal axis around the image center.
     */
    public static int[][] expandHorizontal(final int[][] originalImage, final int amount) {
        return null;
    }

    /**
     * Remove a green screen mask from an image.
     * <p>
     * This function should remove primarily green pixels from an image and replace them with
     * transparent pixels (FILL_VALUE), allowing you to achieve a green screen effect. Obviously
     * this function will destroy pixels, but it <i>does not modify the original image</i>.
     * <p>
     * While this function is tested by the test suite, only extreme edge cases are used. Getting it
     * work work will with real green screen images is left as a challenge for you.
     *
     * @param originalImage the image to remove a green screen from
     * @return the image with the green screen removed
     */
    public static int[][] greenScreen(final int[][] originalImage) {
        return null;
    }

    /**
     * A wild and mysterious image transform.
     * <p>
     * You are free to implement this in any way you want. It is not tested rigorously by the test
     * suite, but it should do something (change the original image) and <i>not modify the original
     * image</i>.
     * <p>
     * Call this function mystery. It should take only the original image as a single argument and
     * return a modified image.
     *
     * @param originalImage the image to perform a strange and interesting transform on
     * @return the image transformed in wooly and frightening ways
     */
    public static int[][] mystery(final int[][] originalImage) {
        int[][]horizontalImg = new int[originalImage.length][originalImage[0].length];
        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[i].length; j++) {
                horizontalImg[originalImage.length - 1 - i][j] = originalImage[i][j];
            }
        }
        return horizontalImg;
    }
}
