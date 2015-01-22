import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
  }
  
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int gray = (pixelObj.getRed()+pixelObj.getGreen()+pixelObj.getBlue())/3;
        pixelObj.setRed(gray);
        pixelObj.setGreen(gray);
        pixelObj.setBlue(gray);
      }
    }
  }
  
  public void sepia()
  {
    Pixel[][] pixels = this.getPixels2D();
    this.grayscale();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int gray = (pixelObj.getRed()+pixelObj.getGreen()+pixelObj.getBlue())/3;
        int origRed = pixelObj.getRed();
        int origBlue = pixelObj.getBlue();
        int origGreen = pixelObj.getGreen();
        if (pixelObj.getRed()<60)
        {
            pixelObj.setRed((int)(origRed*.85));
            pixelObj.setGreen((int)(origGreen*.9));
            pixelObj.setBlue((int)(origBlue*.80));
        }
        else if (pixelObj.getRed()<190)
        {
            pixelObj.setRed((int)(origRed*.9));
            pixelObj.setGreen((int)(origGreen*.8));
            pixelObj.setBlue((int)(origBlue*.6));
        }
        else
        {
            pixelObj.setBlue((int)(origBlue*.8));
        }
      }
    }
  }
  
  public void edgeDetection2(Picture sourcePicture)
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel pixel1 = null;
    Pixel pixel2 = null;
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        pixel1 = pixels[row][col];
        pixel2 = pixels[row+1][col];
        int color1 = (int)pixel1.getAverage();
        int color2 = (int)pixel2.getAverage();
        if (color2-color1>100 || color1-color2>100)
        {
            pixel2.setRed(0);
            pixel2.setBlue(0);
            pixel2.setGreen(0);
        }
        else
        {
            pixel2.setColor(pixel1.getColor());
        }
      }
    } 
    }
  
  public void scaleByHalf(Picture sourcePicture)
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    for (int row = 0; row < pixels.length; row+=2)
    {
      for (int col = 0; col < pixels[0].length; col+=2)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row/2][col/2];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    /*
     * for (int row = 0; row < pixels.length; row++)
    {
      for (int col = pixels[0].length/2; col < pixels[0].length; col++)
      {
        leftPixel = pixels[35][115];
        rightPixel = pixels[row][col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
    
     for (int row = pixels.length/2; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        leftPixel = pixels[35][115];
        rightPixel = pixels[row][col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
    */
    }
  
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
      int red = pixelObj.getRed();
      int green = pixelObj.getGreen();
      int blue = pixelObj.getBlue();
      if (red<50){red=120;}
      if (green>blue){red+=40;}
      if (green<50){green=150;}
      if (blue<50){blue=120;}
        pixelObj.setRed((int)((red-120)*2.05));
        pixelObj.setGreen((int)((green-150)*2.05));
        pixelObj.setBlue((int)((blue-100)*2.05));
      }
    }
  }
  
  public void fixUnderwater2()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
      int red = pixelObj.getRed();
      int green = pixelObj.getGreen();
      int blue = pixelObj.getBlue();
      if (red<30){red=50;}
      if (green<30){green=50;}
      if (blue<30){blue=60;}
      else if (blue>160 && green<170){red=190;}
      if ((green-60)*1.45>55&&red<90){green=0;blue=0;red=0;}
        pixelObj.setRed((int)((red-0)*12.45));
        pixelObj.setGreen((int)((green-60)*12.45));
        pixelObj.setBlue((int)((blue-90)*12.45));
      
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = width/2+1; col < width; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length/2; row++)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length-1-row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture */
  public void mirrorBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int row = pixels.length/2; row < pixels.length; row++)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length-1-row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture */
  public void mirrorArms()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int row = 155; row < 196; row++)
    {
      for (int col = 101; col < 171; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length-row+80][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
    
    for (int row = 166; row < 195; row++)
    {
      for (int col = 238; col < 293; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length-row+88][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture */
  public void mirrorGull()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 230; row < 328; row++)
    {
      for (int col = 230; col < 345; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][700-col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  void cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol,
         int startDestRow, int startDestCol )
         {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = sourcePicture.getPixels2D();
    for (int fromRow = startSourceRow, toRow = startDestRow; 
         fromRow < endSourceRow &&
         toRow < startDestRow+(endSourceRow-startSourceRow); 
         fromRow++, toRow++)
    {
      for (int fromCol = startSourceCol, toCol = startDestCol; 
           fromCol < endSourceCol &&
           toCol < startDestCol+(endSourceCol-startSourceCol);  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }       
            }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture davis1 = new Picture("davis1.jpg");
    Picture davis2 = new Picture("davis2.jpg");
    Picture davis3 = new Picture("davis3.jpg");
    Picture davis4 = new Picture("davis4.jpg");
    Picture davis5 = new Picture("davis5.jpg");
    
    Picture davisGray = new Picture(davis1);
    davisGray.grayscale();
    this.copy(davisGray,0,0);
    
    Picture davisMirrorRightLeft = new Picture(davis4);
    davisMirrorRightLeft.mirrorRightToLeft();
    davisMirrorRightLeft.scaleByHalf(davisMirrorRightLeft);
    davisMirrorRightLeft.scaleByHalf(davisMirrorRightLeft);
    this.cropAndCopy(davisMirrorRightLeft,0,239,0,179,340,0);
    
    Picture davisSepia = new Picture(davis2);
    davisSepia.scaleByHalf(davisSepia);
    davisSepia.scaleByHalf(davisSepia);
    davisSepia.sepia();
    this.cropAndCopy(davisSepia,0,179,0,119,350,600);
    
    Picture davisTopLeft = new Picture(davis1);
    davisTopLeft.scaleByHalf(davisTopLeft);
    davisTopLeft.scaleByHalf(davisTopLeft);
    davisTopLeft.negate();
    this.cropAndCopy(davisTopLeft,0,239,0,179,0,0);
    
    Picture davisTopRight = new Picture(davis1);
    davisTopRight.scaleByHalf(davisTopRight);
    davisTopRight.scaleByHalf(davisTopRight);
    davisTopRight.edgeDetection(16);
    this.cropAndCopy(davisTopRight,0,239,0,179,0,540);
    
    Picture davisBotLeft = new Picture(davis1);
    davisBotLeft.scaleByHalf(davisBotLeft);
    davisBotLeft.scaleByHalf(davisBotLeft);
    davisBotLeft.zeroBlue();
    this.cropAndCopy(davisBotLeft,0,239,0,179,640,0);
    
    Picture davisBotRight = new Picture(davis1);
    davisBotRight.scaleByHalf(davisBotRight);
    davisBotRight.scaleByHalf(davisBotRight);
    davisBotRight.fixUnderwater();
    this.cropAndCopy(davisBotRight,0,239,0,179,640,540);
    
    Picture davisEdgeDetection = new Picture(davis5);
    davisEdgeDetection.scaleByHalf(davisEdgeDetection);
    davisEdgeDetection.sepia();
    this.cropAndCopy(davisEdgeDetection,0,207,0,311,305,230);
    
    Picture davisMirrorPool = new Picture(davis3);
    davisMirrorPool.mirrorBotToTop();
    davisMirrorPool.scaleByHalf(davisMirrorPool);
    davisMirrorPool.scaleByHalf(davisMirrorPool);
    this.cropAndCopy(davisMirrorPool,0,103,0,157,774,305);
    
    
    
    this.write("finalCollage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
            {
          leftPixel.setColor(Color.GREEN);
        }
        else
        {
          leftPixel.setBlue(0);
          leftPixel.setGreen(00);
          leftPixel.setRed(0);
        }
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
