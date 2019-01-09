#Repository: wikiDataHanlder

##Problem definition and solution

This implementation retrieves data from Wikipedia about software languages and categorizes them by their type system features. The collection and export of the data is handled by a graphical user interface. The data is also categorized and cleaned up in the process. 
Furthermore, the distribution of the type system features and correlations between them are visualized.
To retrieve data from Wikipedia the implementation uses the Wiki Data API. The GUI is implemented with JavaFX and the data visualisation are made in Python.

##How to use the implementation

####Retrieve and export the data
There are two ways to retrieve and export the data from Wikipedia.
The first way is by opening the class "App" in the folder "src/main/java/app" and starting the application. After it started you can click on the button "Collect data" and the data collection from Wikipedia will start. 
This process will last about 4-5 minutes. After the process indicator faded you can select elements in the left list component to look at items which are collected for a feature class. 
If you want to export the data just click the button "Export data". Now a file called "wikiData.csv" should be created in the project folder "WikiProject".

The other way is to open the class "WikiDataHandlerTest" in the folder "src/test". There you can run both test methods "testCollectData" and "testExportData". This will automatically retrieve the data and export it in a .csv-file as described before.

####Show the visualisation of statistical data
To run the visualizations you need to have python, jupyter notebook, pandas, matplotlib, seaborn and numpy installed. To make the installation easier you can install a bundled version like 'Anaconda'. 
After that you start jupyter notebook either by running "jupyter notebook' or by clicking on the program icon.
Locate the project folder and go to the 'python' directory and click on 'visualizations.ipynb' using the browser tab jupyter opened.
Now you can run all the cells by clicking 'restart and run all cells' next to the reload icon in the top bar.  
