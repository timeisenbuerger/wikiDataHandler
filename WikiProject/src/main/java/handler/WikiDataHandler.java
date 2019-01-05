package handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import fastily.jwiki.core.Wiki;

public class WikiDataHandler
{
   private final static String TYPE_STATIC = "static";
   private final static String TYPE_DYNAMIC = "dynamic";
   private final static String TYPE_STRONG = "strong";
   private final static String TYPE_WEAK = "weak";
   private final static String TYPE_INFERRED = "inferred";
   private final static String TYPE_PARTIALLY_INFERRED = "partially inferred";
   private final static String TYPE_NOMINATIVE = "nominative";
   private final static String TYPE_NOMINAL = "nominal";
   private final static String TYPE_PARAMETRIC = "parametric";
   private final static String TYPE_OPTIONAL = "optional";
   private final static String TYPE_STRUCTURAL = "structural";
   private final static String TYPE_DUCK = "duck";
   private final static String TYPE_SAFE = "safe";
   private final static String TYPE_MANIFEST = "manifest";
   private final static String TYPE_DEPENDENT = "dependent";
   private final static String TYPE_HYBRID = "hybrid";
   private final static String TYPE_STRING = "string";
   private final static String TYPE_POLYMORPHIC = "polymorphic";
   private final static String TYPE_NONE = "none";
   private final static String TYPE_NONE_UNTYPED = "untyped";
   private final static String DELIMITER_FEATURES = ", ";
   private final static String DELIMITER = ";";


   /**
    * Method which collects all programming languages from wikipedia and categorize them by their type system.
    *
    * @return map with data. keys are the features and values are programming languages
    */

   public static Map<String, List<String>> collectLanguageFeatures()
   {
      Map<String, List<String>> featureLanguageMap = new HashMap<>();
      Wiki wiki = new Wiki("en.wikipedia.org");

      List<String> links = wiki.getLinksOnPage("List_of_programming_languages");

      removeUnnecessaryLinks(links);

      for( String link : links )
      {
         if( wiki.exists(link.replace(" ", "_")) )
         {
            String text = wiki.getPageText(link.replace(" ", "_"));

            if( text.contains("Infobox") && text.contains("typing =") )
            {
               String typeLine = text.substring(text.indexOf("typing =")).substring(0, text.substring(text.indexOf("typing =")).indexOf("\n"));
               String[] split = typeLine.split("=");

               if( split.length > 1 && !split[1].isEmpty() )
               {
                  String types = split[1].trim().replace("[", "").replace("]", "");

                  addToMap(featureLanguageMap, link, types);
               }
            }
            else
            {
               addToMap(featureLanguageMap, link, TYPE_NONE);
            }
         }
      }

      return featureLanguageMap;
   }

   /**
    *
    * Adds the determined features with their programming language to the data map.
    *
    * @param featureLanguageMap
    * @param link
    * @param type
    */

   private static void addToMap(Map<String, List<String>> featureLanguageMap, String link, String type)
   {
      List<String> list;

      String category = determineCategory(type);

      if( featureLanguageMap.containsKey(category) )
      {
         list = featureLanguageMap.get(category);
      }
      else
      {
         list = new ArrayList<>();
      }

      list.add(link);
      featureLanguageMap.put(category, list);
   }

   /**
    *
    * determines the features of a programming language based on their type system.
    *
    * @param type
    * @return determined features
    */

   private static String determineCategory(String type)
   {
      String features = "";

      if( Pattern.compile(Pattern.quote(TYPE_STATIC), Pattern.CASE_INSENSITIVE).matcher(type).find() &&
          Pattern.compile(Pattern.quote(TYPE_DYNAMIC), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_HYBRID + DELIMITER_FEATURES;
      }
      else if( Pattern.compile(Pattern.quote(TYPE_STATIC), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_STATIC + DELIMITER_FEATURES;
      }
      else if( Pattern.compile(Pattern.quote(TYPE_DYNAMIC), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_DYNAMIC + DELIMITER_FEATURES;
      }

      if( Pattern.compile(Pattern.quote(TYPE_STRONG), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_STRONG + DELIMITER_FEATURES;
      }
      else if( Pattern.compile(Pattern.quote(TYPE_WEAK), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_WEAK + DELIMITER_FEATURES;
      }

      if( Pattern.compile(Pattern.quote(TYPE_PARTIALLY_INFERRED), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_PARTIALLY_INFERRED + DELIMITER_FEATURES;
      }
      else if( Pattern.compile(Pattern.quote(TYPE_INFERRED), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_INFERRED + DELIMITER_FEATURES;
      }

      if( Pattern.compile(Pattern.quote(TYPE_NOMINATIVE), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_NOMINATIVE + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_NOMINAL), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_NOMINAL + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_PARAMETRIC), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_PARAMETRIC + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_OPTIONAL), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_OPTIONAL + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_STRUCTURAL), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_STRUCTURAL + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_DUCK), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_DUCK + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_SAFE), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_SAFE + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_MANIFEST), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_MANIFEST + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_DEPENDENT), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_DEPENDENT + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_HYBRID), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_HYBRID + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_STRING), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_STRING + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_POLYMORPHIC), Pattern.CASE_INSENSITIVE).matcher(type).find() )
      {
         features += TYPE_POLYMORPHIC + DELIMITER_FEATURES;
      }
      if( Pattern.compile(Pattern.quote(TYPE_NONE), Pattern.CASE_INSENSITIVE).matcher(type).find() ||
          Pattern.compile(Pattern.quote(TYPE_NONE_UNTYPED), Pattern.CASE_INSENSITIVE).matcher(type).find() ||
          features.isEmpty() )
      {
         features += TYPE_NONE + DELIMITER_FEATURES;
      }

      if( !features.isEmpty() )
      {
         features = features.substring(0, features.lastIndexOf(DELIMITER_FEATURES));
      }

      return features;
   }

   /**
    *
    * Removes links which are not programming languages.
    *
    * @param links
    */

   private static void removeUnnecessaryLinks(List<String> links)
   {
      List<String> linksToRemove = new ArrayList<>();
      for( int i = 0; i < links.size(); i++ )
      {
         String link = links.get(i);
         if( link.contains("Template:") ||
             link.contains("Wikipedia:") ||
             link.contains("Template talk:") ||
             link.contains("Category:") ||
             link.contains("Portal:") ||
             link.contains("History of programming languages") ||
             link.contains("List of") ||
             link.contains("Comparison of") ||
             link.equals("Programming language") )
         {
            linksToRemove.add(link);
         }
      }

      links.removeAll(linksToRemove);
   }

   /**
    *
    * Exports the content of the dataMap to a .csv-file.
    *
    * @param dataMap
    * @throws IOException
    */

   public static void exportData(Map<String, List<String>> dataMap) throws IOException
   {
      File wikiDataFile = new File("wikiData.csv");

      if( wikiDataFile.exists() )
      {
         wikiDataFile.delete();
      }

      wikiDataFile.createNewFile();

      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(wikiDataFile));

      for( Map.Entry<String, List<String>> entry : dataMap.entrySet() )
      {
         for( int i = 0; i < entry.getValue().size(); i++ )
         {
            String val = entry.getValue().get(i);
            String line = entry.getKey() + DELIMITER + val;

            bufferedWriter.write(line);
            bufferedWriter.newLine();
         }
      }

      bufferedWriter.close();
   }
}
