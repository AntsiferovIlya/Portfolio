import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread{

    private File[] files;
    private String dstFolder;
    private int newWidth;
    private int threadNumber;
    private long start;

    public ImageResizer(File[] files, String dstFolder, int newWidth, long start, int threadNumber) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.newWidth = newWidth;
        this.start = start;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {

        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = resize(image, newWidth, newHeight);

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
               // System.out.println("Поток номер " + threadNumber + " обработал файл: " + file.getName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(
                "Поток номер: "
                        + threadNumber
                        + " завершил свою работу за: "
                        + (System.currentTimeMillis() - start)
                        + "мс");
    }

    public static BufferedImage resize(BufferedImage src, int targetWidth, int targetHeight) {
        return Scalr.resize(
                src,
                Scalr.Method.AUTOMATIC,
                Scalr.Mode.FIT_EXACT,
                targetWidth,
                targetHeight,
                Scalr.OP_ANTIALIAS
        );
    }
}
