
package id.delta.ayodonor.retrofit.models.stok;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Datum {

    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("gol_darah")
    @Expose
    private String golDarah;
    @SerializedName("jum_stok")
    @Expose
    private String jumStok;
    @SerializedName("stok_id")
    @Expose
    private String stokId;

    /**
     * 
     * @return
     *     The lokasi
     */
    public String getLokasi() {
        return lokasi;
    }

    /**
     * 
     * @param lokasi
     *     The lokasi
     */
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    /**
     * 
     * @return
     *     The golDarah
     */
    public String getGolDarah() {
        return golDarah;
    }

    /**
     * 
     * @param golDarah
     *     The gol_darah
     */
    public void setGolDarah(String golDarah) {
        this.golDarah = golDarah;
    }

    /**
     * 
     * @return
     *     The jumStok
     */
    public String getJumStok() {
        return jumStok;
    }

    /**
     * 
     * @param jumStok
     *     The jum_stok
     */
    public void setJumStok(String jumStok) {
        this.jumStok = jumStok;
    }

    /**
     * 
     * @return
     *     The stokId
     */
    public String getStokId() {
        return stokId;
    }

    /**
     * 
     * @param stokId
     *     The stok_id
     */
    public void setStokId(String stokId) {
        this.stokId = stokId;
    }

}
