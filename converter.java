import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
import java.util.Scanner;

public class AsciiImage
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        //Takes image URL and stores it in image_raster
        System.out.println("Provide an image URL"); 
        String image_url = scan.nextLine().trim();
        scan.close();

        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new URL(image_url));
        }
        catch (java.io.IOException e)
        {
            System.out.println("IOException Caught");
            return;
        }

        StringBuilder ascii_image = new StringBuilder();

        for (int y = image.getMinY(); y < image.getHeight() - 4; y+=4)
        {
            for (int x = image.getMinX(); x < image.getWidth() - 4; x+=4)
            {
                double brightness = 0;
                for (int row = 0; row < 4; row++)
                {
                    for(int col = 0; col < 4; col++)
                    {
                        int rgb = image.getRGB(x + col, y + row);

                        int red = (rgb >> 16) & 0xFF;
                        int green = (rgb >> 8) & 0xFF;
                        int blue = (rgb) & 0xFF;

                        brightness += (0.2126 * red + 0.7152 * green + 0.0722 * blue);
                    }
                }
                brightness /= 16;

                if (brightness < 25)        ascii_image.append("@") ;
                else if (brightness < 50)   ascii_image.append("#") ;
                else if (brightness < 75)   ascii_image.append("$") ;
                else if (brightness < 100)  ascii_image.append("%") ;
                else if (brightness < 125)  ascii_image.append("*") ;
                else if (brightness < 150)  ascii_image.append("+") ;
                else if (brightness < 175)  ascii_image.append("-") ;
                else if (brightness < 200)  ascii_image.append(".") ;
                else if (brightness < 225)  ascii_image.append("'") ;
                else                        ascii_image.append(" ") ;
            }
            
            ascii_image.append("\n");
        }
        System.out.println(ascii_image.toString());
    }
}