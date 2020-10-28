package it.unimore.dipi.iot.server.model;

/**
 *
 * Demo POJO used to structure a simple Double Data Structure
 *
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project coap-demo-smartobject
 * @created 20/10/2020 - 21:54
 */
public class SimpleDoubleDataDescriptor {

    private long timestamp;

    private double value;

    public SimpleDoubleDataDescriptor() {
    }

    public SimpleDoubleDataDescriptor(long timestamp, double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TemperatureData{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
