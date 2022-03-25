package immutable_objects;

public class SynchronizedRGB {
    private int red;
    private int green;
    private int blue;
    private String name;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public SynchronizedRGB(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public void set(int red, int green, int blue, String name) {
        check(red, green, blue);
        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized int getRGB() {
        return red << 16 | green << 8 | blue;
    }

    public synchronized void invert() {
        this.red = 255 - red;
        this.green = 255 - green;
        this.blue = 255 - blue;
        this.name = "Inverse of " + name;
    }
}
