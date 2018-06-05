<H1>CCFinder</H1>
Calculate <b>global and local clustering coefficient for big graphs using Apache Spark</b> <br>
<b>Journal Paper</b> => <a href="https://link.springer.com/epdf/10.1007/s11227-017-2040-8?author_access_token=TW4a-7JzCf89bJoDZUNMSve4RwlQNchNByi7wbcMAY6ErF9saB9cMGINzlgrVkntVxlqO8jdZ0Nkgfb_taNvC-uz--I-uXUcYtI5bthNGSzV4E1WR7_7fyLlnmRcm7A-5BhDs9qIpIcoUKMsiODygw%3D%3D">CCFinder: using Spark to find clustering coefficient in big graphs</a>


Some considerations for running CCFinder: <br>
- Input graph is considered to be given as edge list such as => https://snap.stanford.edu/data/soc-LiveJournal1.html
- $SPARK_HOME (address of spark directory) should be set in the Environment
- Jar file <b>ccfinder.jar</b> should be located in the lib directory
  - To generate it, run `mvn clean package` in the home directory of ccfinder project
  - Then copy target/ccfinder.jar to the lib directory


To get Global Clustering Coefficient, you should run ccfinder from the project home directory as <br>
bin/ccfinder $spark_master_address [LCC|GCC] $graph_path

For example, if we have spark_master_address = 127.0.0.1 and graph_path = inputs/amazon.txt
To get Average Global and Local Clustering Coefficient, respectively, you should run ccfinder as <br>
  - bin/ccfinder 127.0.0.1 GCC inputs/amazon.txt
  - bin/ccfinder 127.0.0.1 LCC inputs/amazon.txt


<b>Note: </b><br>
If input graph is located in the HDFS you should provide the complete HDFS path of the input graph, e.g., <i>
hdfs://localhost:8020/inputs/amazon.txt </i>
