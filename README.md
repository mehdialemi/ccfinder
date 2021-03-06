<H1>CCFinder</H1>
Calculate <b>global and local clustering coefficient for big graphs using Apache Spark</b> <br>
<b>Journal Paper</b> => <a href="https://link.springer.com/epdf/10.1007/s11227-017-2040-8?author_access_token=TW4a-7JzCf89bJoDZUNMSve4RwlQNchNByi7wbcMAY6ErF9saB9cMGINzlgrVkntVxlqO8jdZ0Nkgfb_taNvC-uz--I-uXUcYtI5bthNGSzV4E1WR7_7fyLlnmRcm7A-5BhDs9qIpIcoUKMsiODygw%3D%3D">CCFinder: using Spark to find clustering coefficient in big graphs</a>

<br>
<br>
<br>

<b>Some considerations before running CCFinder:</b> <br>
 - Spark version: 2.2.0
 - Java version: 1.8
 - Input graph is considered to be as edge list, e.g.,
 <a href="http://snap.stanford.edu/data/bigdata/communities/com-amazon.ungraph.txt.gz">amazon graph</a> 
 in <a href="http://snap.stanford.edu/data/index.html">Stanford Large Network Dataset Collection</a>
 - $SPARK_HOME (address of spark directory) should be set in the Environment
 - Jar file <b>ccfinder.jar</b> should be located in the lib directory
   - To generate it, run `mvn clean package` in the home directory of ccfinder project


To get Clustering Coefficient, you should run ccfinder from the <b>project home directory</b> as: <br>
<b>bin/ccfinder $spark_master_address [LCC|GCC] $graph_path</b>

For example, if we have spark_master_address = 127.0.0.1 and graph_path = inputs/amazon.txt
To get Average Global and Local Clustering Coefficient, respectively, you should run ccfinder as <br>
  - bin/ccfinder 127.0.0.1 GCC inputs/amazon.txt
  - bin/ccfinder 127.0.0.1 LCC inputs/amazon.txt


<b>Note: </b><br>
If the input graph is located in the HDFS you should provide the complete HDFS path of the input graph, e.g., <i>
hdfs://localhost:8020/inputs/amazon.txt </i>
