import java.net.URL;
import java.awt.image.BufferedImage;
import java.util.scanner;

public class AsciiImages
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        //Takes image URL and stores it in image_raster
        System.out.println("Provide an image URL"); 
        String image_url = scan.nextLine();
        
        BufferedImage image_raster = ImageIO.read(new URL(image_url));
    }
}