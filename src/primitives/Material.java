package primitives;

public class Material {
    public Double3 kD = new Double3(0);
    public Double3 kS = new Double3(0);
    public int nShininess = 0;
    /**
     * ------------- setter -----------------
     *
     * @param kD the kD to set
     * @return itself material
     */
    public Material setKd(double kD) {
        return setKd(new Double3(kD));
    }

    /**
     * ------------- setter -----------------
     *
     * @param kS the kS to set
     * @return itself material
     */
    public Material setKs(double kS) {
        return setKs(new Double3(kS));
    }

    /**
     * ------------- setter -----------------
     *
     * @param nShininess the nShininess to set
     * @return itself material
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
    /**
     * ------------- setter -----------------
     *
     * @param kD the kD to set
     * @return itself material
     */
    public Material setKd(Double3 kD) {
        this.kD=kD;
        return this;
    }

    /**
     * ------------- setter -----------------
     *
     * @param kS the kS to set
     * @return itself material
     */
    public Material setKs(Double3 kS) {
        this.kS=kS;
        return this;

    }


}
