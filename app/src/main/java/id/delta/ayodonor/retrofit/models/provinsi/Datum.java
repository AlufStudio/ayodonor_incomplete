
package id.delta.ayodonor.retrofit.models.provinsi;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Datum implements Serializable {

    @SerializedName("propinsi")
    @Expose
    private String propinsi;
    @SerializedName("kode")
    @Expose
    private String kode;

    /**
     * 
     * @return
     *     The propinsi
     */
    public String getPropinsi() {
        return propinsi;
    }

    /**
     * 
     * @param propinsi
     *     The propinsi
     */
    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    /**
     * 
     * @return
     *     The kode
     */
    public String getKode() {
        return kode;
    }

    /**
     * 
     * @param kode
     *     The kode
     */
    public void setKode(String kode) {
        this.kode = kode;
    }

}
