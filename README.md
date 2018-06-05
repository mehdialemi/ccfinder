Spark program to calculate global and local clustering coefficient on big graphs
To read more: <a href="https://link.springer.com/epdf/10.1007/s11227-017-2040-8?author_access_token=TW4a-7JzCf89bJoDZUNMSve4RwlQNchNByi7wbcMAY6ErF9saB9cMGINzlgrVkntVxlqO8jdZ0Nkgfb_taNvC-uz--I-uXUcYtI5bthNGSzV4E1WR7_7fyLlnmRcm7A-5BhDs9qIpIcoUKMsiODygw%3D%3D">CCFinder: using Spark to find clustering coefficient in big graphs</a>


To run
input graph is considered to be given as edge list such as https://snap.stanford.edu/data/soc-LiveJournal1.html
$SPARK_HOME should be defined
ccfinder-jar-with-dependencies.jar should be located in the lib directory
  - to generate it run `mvn package` in the home directory of ccfinder project
  - then copy target/ccfinder-jar-with-dependencies.jar to bin

suppose:
 - spark_master_address = 127.0.0.1
 - graph_path = $PWD/amazon.txt

To get Global Clustering Coefficient, you should run ccfinder as
bin/ccfinder 127.0.0.1 GCC input/amazon.txt

To get Average Local Clustering Coefficient, you should run ccfinder as
bin/ccfinder 127.0.0.1 LCC input/amazon.txt

if input is located in hdfs you should set the complete hdfs path such as
hdfs://localhost:8020/inputs/amazon.txt
