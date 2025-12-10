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
        String image_url = scan.nextLine();
        image_url = image_url.trim();
        
        BufferedImage image = ImageIO.read(new URL(image_url));

        String ascii_image = "";
        for (int y = image.getMinY(); y < image.getHeight() - 4; y+=4)
        {
            for (int x = image.getMinX(); x < image.getWidth() - 4; x+=4)
            {
                for (int row = 0; row < 4; row++)
                {
                    int brightness = 0;
                    for(int col = 0; col < 4; col++)
                    {
                        brightness += image.get(x + row, y + col);
                    }
                }
            }
        }


    }
}