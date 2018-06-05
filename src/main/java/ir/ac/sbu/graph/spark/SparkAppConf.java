package ir.ac.sbu.graph.spark;

import ir.ac.sbu.graph.types.VertexDeg;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.File;

/**
 * Configuration for spark applications
 */
public class SparkAppConf {

    private String inputPath;
    private int partitionNum;
    private SparkConf sparkConf;
    private JavaSparkContext sc;
    private String sparkMaster;

    public SparkAppConf (ArgumentReader argumentReader) {
        inputPath = argumentReader.nextString(DEFAULT_GRAPH_PATH);
        sparkMaster = argumentReader.nextString("local[*]");
    }

    protected String createAppName() {
        return getFileName();
    }

    public String getFileName() {
        return new File(getInputPath()).getName();
    }

    public void init() {
        String appName = createAppName();
        sparkConf = new SparkConf();
        sparkConf.setMaster(sparkMaster);
        sparkConf.setAppName(appName);
        sparkConf.set("spark.submit.deployMode", "client");
        sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        sparkConf.registerKryoClasses(new Class[] {int[].class,VertexDeg.class});
        sc = new JavaSparkContext(sparkConf);
    }

    public String getInputPath() {
        return inputPath;
    }

    public int getPartitionNum() {
        return partitionNum;
    }

    public void setPartitionNum(int partitionNum) {
        this.partitionNum = partitionNum;
    }

    public JavaSparkContext getSc() {
        return sc;
    }
}
