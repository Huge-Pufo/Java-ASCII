import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.net.URL;
import java.util.Scanner;

public class AsciiImage
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        //Takes image URL and stores it in image_raster
        System.out.println("Paste the full image path:"); 
        String image_path = scan.nextLine().trim();

        if (image_path.startsWith("\"") && image_path.endsWith("\"")) 
        {
            image_path = image_path.substring(1, image_path.length() - 1);
        }
        
        System.out.println("Choose a resolution(4, 8, 10, 16...");
        System.out.println("Lower numbers means higher resolution and a bigger image (min = 1)");
        System.out.println("A resolution between 16 - 32 is recommended for most images");
        int resolution = scan.nextInt();
        scan.close();

        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File(image_path));
            if (image == null) 
            {
                System.out.println("Failed to load image. Make sure it's a valid JPG, PNG, BMP, or GIF file.");
                return;
            }
        }
        catch (java.io.IOException e)
        {
            System.out.println("IOException caught: " + e.getMessage());
            return;
        }


        StringBuilder ascii_image = new StringBuilder();

        for (int y = image.getMinY(); y < image.getHeight() - resolution; y+=resolution)
        {
            for (int x = image.getMinX(); x < image.getWidth() - resolution; x+=resolution)
            {
                double brightness = 0;
                for (int row = 0; row < resolution; row++)
                {
                    for(int col = 0; col < resolution; col++)
                    {
                        int rgb = image.getRGB(x + col, y + row);

                        int red = (rgb >> 16) & 0xFF;
                        int green = (rgb >> 8) & 0xFF;
                        int blue = (rgb) & 0xFF;

                        brightness += (0.2126 * red + 0.7152 * green + 0.0722 * blue);
                    }
                }
                brightness /= (resolution * resolution);

                if (brightness < 25)        ascii_image.append(" @ ") ;
                else if (brightness < 50)   ascii_image.append(" # ") ;
                else if (brightness < 75)   ascii_image.append(" $ ") ;
                else if (brightness < 100)  ascii_image.append(" % ") ;
                else if (brightness < 125)  ascii_image.append(" * ") ;
                else if (brightness < 150)  ascii_image.append(" + ") ;
                else if (brightness < 175)  ascii_image.append(" - ") ;
                else if (brightness < 200)  ascii_image.append(" . ") ;
                else if (brightness < 225)  ascii_image.append(" ' ") ;
                else                        ascii_image.append("  ") ;
            }
            
            ascii_image.append("\n");
        }
        System.out.println(ascii_image.toString());
    }
}