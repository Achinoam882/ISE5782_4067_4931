package primitives;

public class Material {
    /**
     * represents transparency factor.
     */
    public Double3 kT;
    /**
     * represents reflection factor.
     */
    public Double3 kR;
    /**
     * represents diffuse factor.
     */
    public Double3 kD;
    /**
     * represents specular factor.
     */
    public Double3 kS;

    public int nShininess;

    public Material(){
        kT = Double3.ZERO;
        kR = Double3.ZERO;
        kD = Double3.ZERO;
        kS = Double3.ZERO;
        nShininess = 0;
    }
    /**
     * ------------- setter -----------------
     *
     * @param kD the kD to set
     * @return itself material
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * ------------- setter -----------------
     *
     * @param kS the kS to set
     * @return itself material
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
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

    /**
     * ------------- setter -----------------
     *
     * @param kT the kT to set
     * @return itself material
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }
    /**
     * ------------- setter -----------------
     *
     * @param kR the kR to set
     * @return itself material
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

}
