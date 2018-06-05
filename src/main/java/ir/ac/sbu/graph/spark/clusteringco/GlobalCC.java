package ir.ac.sbu.graph.spark.clusteringco;

import ir.ac.sbu.graph.spark.*;
import ir.ac.sbu.graph.spark.triangle.Triangle;

/**
 * Calculate Global Clustering Coefficient
 */
public class GlobalCC extends SparkApp {

    private Triangle triangle;

    public GlobalCC(Triangle triangle) {
        super(triangle);
        this.triangle = triangle;
    }

    public float getGCC() {
        long tc = triangle.triangleCount();
        long nodes = triangle.getOrCreateFonl().count();
        return tc / (float) (nodes * (nodes - 1));
    }

    public static void main(String[] args) {

        SparkAppConf conf = new SparkAppConf(new ArgumentReader(args)) {
            @Override
            protected String createAppName() {
                return "GCC - " + super.createAppName();
            }
        };
        conf.init();

        GlobalCC gcc = new GlobalCC(new Triangle(new NeighborList(new EdgeLoader(conf))));
        System.out.println("global cc: " + gcc.getGCC());

        gcc.close();
    }
}
