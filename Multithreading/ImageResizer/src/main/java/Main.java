import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "D:\\Ycheba\\SkillBox\\Module17.4\\Multithreading\\src";
        String dstFolder = "D:\\Ycheba\\SkillBox\\Module17.4\\Multithreading\\dst";


        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Êîëè÷åñòâî ÿäåğ: " + cores);

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        int count = 1;

        int elementsCount = files.length/cores;
        System.out.println("Êîëè÷åñòâî èçîáğàæåíèé íà îäèí ïîòîê: " + elementsCount);

        long start = System.currentTimeMillis();

        for(int i = 0; i < files.length; i += 2) {
            File[] files1 = new File[elementsCount];
            System.arraycopy(files, i, files1, 0, files1.length);
            ImageResizer imageResizer = new ImageResizer(files1, dstFolder, newWidth, start, count);
            imageResizer.start();
            count += 1;
        }

//        // ÏÅĞÂÛÉ ÏÎÒÎÊ
//        File[] files1 = new File[2];
//        System.arraycopy(files, 0, files1, 0, files1.length);
//        ImageResizer imageResizer1 = new ImageResizer(files1,dstFolder, newWidth, start, 1);
//        imageResizer1.start();
//
//        // ÂÒÎĞÎÉ ÏÎÒÎÊ
//        File[] files2 = new File[2];
//        System.arraycopy(files, 2, files2, 0, files2.length);
//        ImageResizer imageResizer2 = new ImageResizer(files2,dstFolder, newWidth, start, 2);
//        imageResizer2.start();
//
//        // ÒĞÅÒÈÉ ÏÎÒÎÊ
//        File[] files3 = new File[2];
//        System.arraycopy(files, 4, files3, 0, files3.length);
//        ImageResizer imageResizer3 = new ImageResizer(files3, dstFolder, newWidth, start, 3);
//        imageResizer3.start();
//
//        // ×ÅÒÂÅĞÒÛÉ ÏÎÒÎÊ
//        File[] files4 = new File[2];
//        System.arraycopy(files, 6, files4, 0, files4.length);
//        ImageResizer imageResizer4 = new ImageResizer(files4, dstFolder, newWidth, start,4);
//        imageResizer4.start();
//
//        // ÏßÒÛÉ ÏÎÒÎÊ
//        File[] files5 = new File[2];
//        System.arraycopy(files, 8, files5, 0, files5.length);
//        ImageResizer imageResizer5 = new ImageResizer(files5, dstFolder, newWidth, start, 5);
//        imageResizer5.start();
//
//        // ØÅÑÒÎÉ ÏÎÒÎÊ
//        File[] files6 = new File[2];
//        System.arraycopy(files, 10, files6, 0, files6.length);
//        ImageResizer imageResizer6 = new ImageResizer(files6, dstFolder, newWidth, start, 6);
//        imageResizer6.start();
//
//        // ÑÅÄÜÌÎÉ ÏÎÒÎÊ
//        File[] files7 = new File[2];
//        System.arraycopy(files, 12, files7, 0, files7.length);
//        ImageResizer imageResizer7 = new ImageResizer(files7, dstFolder, newWidth, start, 7);
//        imageResizer7.start();
//
//        // ÂÎÑÜÌÎÉ ÏÎÒÎÊ
//        File[] files8 = new File[2];
//        System.arraycopy(files, 14, files8, 0, files8.length);
//        ImageResizer imageResizer8 = new ImageResizer(files8, dstFolder, newWidth, start, 8);
//        imageResizer8.start();

    }
}
