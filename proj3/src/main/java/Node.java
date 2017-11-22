/**
 * Created by 丁天庆 on 2017/11/18.
 */
public class Node {
    long id;
    double lat, lon;

    public Node(long id, double latitude, double lon) {

        this.id = id;
        this.lat = latitude;
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (id != node.id) return false;
        if (Double.compare(node.lat, lat) != 0) return false;
        return Double.compare(node.lon, lon) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
