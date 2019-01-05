import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import handler.WikiDataHandler;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WikiDataHandlerTest
{
   private static Map<String, List<String>> dataMap;

   /**
    * Fills the dataMap with content of wikipedia.
    */

   @BeforeClass
   public static void setUp()
   {
      dataMap = WikiDataHandler.collectLanguageFeatures();
   }

   /**
    * Tests the functionality of data collection.
    */

   @Test
   public void testCollectData()
   {
      Assert.assertTrue(!dataMap.keySet().isEmpty());
      Assert.assertTrue(dataMap.keySet().size() > 40);
   }

   /**
    * Tests the functionality of data export.
    *
    * @throws IOException
    */

   @Test
   public void testExportData() throws IOException
   {
      WikiDataHandler.exportData(dataMap);
      Assert.assertTrue(new File("wikiData.csv").exists());
   }
}
