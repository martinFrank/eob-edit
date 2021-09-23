package com.github.martinfrank.eobedit.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class ScreenShotSnipper {


    public static void main(String[] args) {
        new ScreenShotSnipper().snippEmAll();
    }

    private static final String SRC_DIRECTORY = "src/main/resources/item_raw";
    private static final String DEST_DIRECTORY = "src/main/resources/item";
    private static final String PNG = ".PNG";
    public static final int BACKGROUND_COLOR = 0x6569B6;
    private static final int SLOT_WIDTH = 2;
    private static final int SLOT_HEIGHT = 7;
    private static final int SUB_IMG_SRC_X_OFFSET = 2;
    private static final int SUB_IMG_SRC_Y_OFFSET = 2;
    private static final int SUB_IMG_SRC_WIDTH = 32;
    private static final int SUB_IMG_SRC_HEIGHT = 32;

    private void snippEmAll() {
        File[] srcRaw = new File(SRC_DIRECTORY).listFiles();
        for (File file : srcRaw) {
            String imgStartIndex = getStartIndex(file);
            int primary = getPrimary(imgStartIndex);
            int secondary = getSecondary(imgStartIndex);
            snippFile(file, primary, secondary);

        }
    }

    private void snippFile(File file, int primary, int secondary) {
        try{
            boolean breakOnOverflow = false;
            BufferedImage img = ImageIO.read(file);
            for(int dy = 0; dy < SLOT_HEIGHT; dy++){
                for(int dx = 0; dx <SLOT_WIDTH; dx++){
                    int subImageX = SUB_IMG_SRC_X_OFFSET +
                            (dx*2) * SUB_IMG_SRC_X_OFFSET +
                            dx * SUB_IMG_SRC_WIDTH ;
                    int subImageY = SUB_IMG_SRC_Y_OFFSET +
                            (dy*2) * SUB_IMG_SRC_Y_OFFSET +
                            dy * SUB_IMG_SRC_HEIGHT;
                    File destFile = getDestFile(primary, secondary);

                    BufferedImage subImage = img.getSubimage(subImageX, subImageY, SUB_IMG_SRC_WIDTH, SUB_IMG_SRC_HEIGHT);
                    BufferedImage filtered = filterTransparency(subImage);
                    ImageIO.write(filtered, "PNG", destFile);

                    primary = primary + 1;
                    if (primary > 0xFF) {
                        breakOnOverflow = true;
                        break;
                    }
                }
                if (breakOnOverflow) {
                    breakOnOverflow = false;
                    break;
                }
            }
        }catch (IOException e){
            //just a dev tool, so devs can read stacktrace
            e.printStackTrace();
        }
    }

    private BufferedImage filterTransparency(BufferedImage subImage) {
        ImageProducer ip = new FilteredImageSource(subImage.getSource(), TRANSPARENT_FILTER);
        Image image = Toolkit.getDefaultToolkit().createImage(ip);
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        bufferedImage.createGraphics().drawImage(image, 0,0,null);
        return bufferedImage;
    }

    private File getDestFile(int primary, int secondary) {
        String filename = (primary < 0x10 ? "0" : "") + Integer.toHexString(primary).toUpperCase(Locale.ROOT) +
                (secondary < 0x10 ? "0" : "") + Integer.toHexString(secondary).toUpperCase(Locale.ROOT) +
                PNG;
        return new File(DEST_DIRECTORY, filename);
    }

    private int getPrimary(String imgStartIndex) {
        return Integer.parseInt(imgStartIndex.substring(0,2), 16);
    }
    private int getSecondary(String imgStartIndex) {
        return Integer.parseInt(imgStartIndex.substring(2,4), 16);
    }

    private String getStartIndex(File file) {
        int trimIndex = file.getName().indexOf('-');
        return file.getName().substring(0,trimIndex);
    }

    private static final ImageFilter TRANSPARENT_FILTER = new RGBImageFilter() {

        public int filterRGB(int x, int y, int rgb) {
            return (0x00FFFFFF & rgb) == BACKGROUND_COLOR ? 0x00FFFFFF : rgb;
        }

    };

}
