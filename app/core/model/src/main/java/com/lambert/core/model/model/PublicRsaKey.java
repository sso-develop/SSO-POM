package com.lambert.core.model.model;

/**
 * @Auther: lambert
 * @Date: 2018/12/25 22:04
 * @Description:
 */
public class PublicRsaKey {

    private String exponent;
    private String modulus;

    /**
     *
     * @param exponent
     * @param modulus
     */
    public PublicRsaKey(String exponent, String modulus) {
        this.exponent = exponent;
        this.modulus = modulus;
    }

    public String getExponent() {
        return exponent;
    }

    public void setExponent(String exponent) {
        this.exponent = exponent;
    }

    public String getModulus() {
        return modulus;
    }

    public void setModulus(String modulus) {
        this.modulus = modulus;
    }
}
