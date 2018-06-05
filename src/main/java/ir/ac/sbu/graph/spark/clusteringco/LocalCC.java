package ir.ac.sbu.graph.spark.clusteringco;

import ir.ac.sbu.graph.spark.*;
import ir.ac.sbu.graph.spark.triangle.Triangle;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.storage.StorageLevel;

/**
 * Calculate Local Clustering Coefficient per Vertex
 */
public class LocalCC extends SparkApp {

    private Triangle triangle;

    private JavaPairRDD<Integer, Float> lcc;
    private long nodes = 1;

    public LocalCC(Triangle triangle) {
        super(triangle);
        this.triangle = triangle;
    }

    /**
     * calculate local average cc
     * @return a pairRDD such that per each key-value we have key=vertex id , value=average cc
     */
    public JavaPairRDD<Integer, Float> getLcc() {
        JavaPairRDD<Integer, int[]> fonl = triangle.createFonl();
        JavaPairRDD<Integer, Integer> vertexTC = triangle.createVertexTC().repartition(fonl.getNumPartitions());
        lcc = fonl
                .leftOuterJoin(vertexTC)
                .mapValues(v -> (!v._2.isPresent() || v._1[0] < 2) ? 0.0f : 2.0f * v._2.get() / (v._1[0] * (v._1[0] - 1)))
                .persist(StorageLevel.MEMORY_AND_DISK());
        return lcc;
    }

    public float avgLCC() {
        Float sum = getLcc().map(kv -> kv._2).reduce((a, b) -> a + b);
        nodes = triangle.getFonl().count();
        return sum / nodes;
    }

    public static void main(String[] args) {

        SparkAppConf conf = new SparkAppConf(new ArgumentReader(args)) {
            @Override
            protected String createAppName() {
                return "LCC - " + super.createAppName();
            }
        };
        conf.init();

        LocalCC lcc = new LocalCC(new Triangle(new NeighborList(new EdgeLoader(conf))));

        float avgLcc = lcc.avgLCC();
        System.out.println("avg lcc: " + avgLcc);

        lcc.close();
    }
}
