/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  public static void testKeepOnlyBlue()
  {
    Picture beach = new Picture("davis1.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  public static void testNegate()
  {
    Picture davis = new Picture("davis1.jpg");
    davis.explore();
    davis.negate();
    davis.explore();
  }
  
  public static void testGrayscale()
  {
    Picture davis = new Picture("davis1.jpg");
    davis.explore();
    davis.grayscale();
    davis.explore();
  }
  
  public static void testFixUnderwater()
  {
    Picture water = new Picture("water.jpg");
    water.explore();
    water.fixUnderwater();
    water.explore();
  }
  
  public static void testFixUnderwater2()
  {
    Picture water = new Picture("water.jpg");
    water.explore();
    water.fixUnderwater2();
    water.explore();
  }
  
  public static void testScaleByHalf()
  {
    Picture davis = new Picture("davis1.jpg");
    davis.explore();
    davis.scaleByHalf(davis);
    davis.explore();
  }
  
  public static void testEdgeDetection2()
  {
    Picture davis = new Picture("davis1.jpg");
    davis.explore();
    davis.edgeDetection2(davis);
    davis.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture davis1 = new Picture("davis1.jpg");
    davis1.explore();
    davis1.mirrorVertical();
    davis1.explore();
  }
  
  public static void testMirrorRightToLeft()
  {
      Picture davis1 = new Picture("davis1.jpg");
      davis1.explore();
      davis1.mirrorRightToLeft();
      davis1.explore();
    }
    
  public static void testMirrorHorizontal()
  {
      Picture davis1 = new Picture("davis1.jpg");
      davis1.explore();
      davis1.mirrorHorizontal();
      davis1.explore();
    }
    
  public static void testMirrorBotToTop()
  {
      Picture davis1 = new Picture("davis1.jpg");
      davis1.explore();
      davis1.mirrorBotToTop();
      davis1.explore();
    }
    
  public static void testMirrorArms()
  {
      Picture snowman = new Picture("snowman.jpg");
      snowman.explore();
      snowman.mirrorArms();
      snowman.explore();
    }
 
  public static void testMirrorGull()
  {
      Picture seagull = new Picture("seagull.jpg");
      seagull.explore();
      seagull.mirrorGull();
      seagull.explore();
    }
    
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture(880,720);
    Picture davis1 = new Picture("davis1.jpg");
    Picture davis2 = new Picture("davis2.jpg");
    Picture davis3 = new Picture("davis3.jpg");
    Picture davis4 = new Picture("davis4.jpg");
    Picture davis5 = new Picture("davis5.jpg");
    //davis1.explore();
    //davis2.explore();
    //davis3.explore();
    //davis4.explore();
    //davis5.explore();
    canvas.createCollage();
    canvas.explore();
  }
  
  public static void testCropAndCopy()
  {
    Picture davis = new Picture("davis1.jpg");
    Picture seagull = new Picture("seagull.jpg");
    Picture snowman = new Picture("snowman.jpg");
    davis.explore();
    davis.cropAndCopy(seagull, 234, 330, 232, 338, 622, 349);
    davis.cropAndCopy(snowman, 81, 300, 155, 250, 267, 50);
    davis.explore();
    // copies the region defined by rows 10-100 and columns 20-200 of picture1 into picture2
    // such that the upper-left corner of the copied picture starts at row 30 and column 40
    //(and, therefore, extends to row 120 and column 220).
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture davis = new Picture("davis1.jpg");
    davis.explore();
    davis.edgeDetection(13);
    davis.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorRightToLeft();
    //testMirrorHorizontal();
    //testMirrorBotToTop();
    //testMirrorArms();
    //testMirrorGull();
    //testKeepOnlyBlue();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater2();
    //testCropAndCopy();
    //testScaleByHalf();
    //testEdgeDetection2();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}