#Repository: wikiDataHanlder

##Problem definition and solution

This implementation retrieves data from Wikipedia about software languages and categorize it by their type system features. The categorization of this data is visualized by a graphical user interface. Furthermore the data get cleaned up and relations of it are shown in statistical evaluations.
To retrieve data from Wikipedia the implementation uses the Wiki Data API. The data visualisation is implemented with JavaFX and the statistical evaluations are made in Python.


##How to use the implementation

####Retrieve and export the data
There are two ways to retrieve and export the data from Wikipedia.
The first way is to open the class "App" in the folder "src/main/java/app" and starting der Application. After it started you can click on the button "Collect data". With that you start the process of data collection from Wikipedia
This process will last about 4-5 minutes. After the process indicator faded you can select elements in the left list component to look at items which are collected for a feature class. If you want to export the data just click the button "Export data". Now a file called "wikiData.csv" should be created in the project folder "WikiProject".

The second way is to open the class "WikiDataHandlerTest" in the folder "src/test". There you can run both test methods "testCollectData" and "testExportData". This will automatically retrieve the data and export it in a .csv-file as noticed before.

####Show the visualisation of statistical data

