
package id.delta.ayodonor.retrofit.models.stok;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Page {

    @SerializedName("prev_page")
    @Expose
    private Object prevPage;
    @SerializedName("next_page")
    @Expose
    private Integer nextPage;

    /**
     * 
     * @return
     *     The prevPage
     */
    public Object getPrevPage() {
        return prevPage;
    }

    /**
     * 
     * @param prevPage
     *     The prev_page
     */
    public void setPrevPage(Object prevPage) {
        this.prevPage = prevPage;
    }

    /**
     * 
     * @return
     *     The nextPage
     */
    public Integer getNextPage() {
        return nextPage;
    }

    /**
     * 
     * @param nextPage
     *     The next_page
     */
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

}
