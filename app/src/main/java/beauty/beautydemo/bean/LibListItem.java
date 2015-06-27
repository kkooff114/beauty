package beauty.beautydemo.bean;

/**
 * Created by LJW on 15/4/9.
 * 商品列表item
 */
public class LibListItem {

    //id
    private String productId;

    //商品主图片
    private String productImage;

    //商品名字
    private String productNameCN;

    //商品名字
    private String productNameEN;

    //商品型号
    private String productModel;

    //综合评分
    private float rate;

    //试妆图片
    private String tryImage;
    //试妆图片
    private String oneRate;

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductNameCN() {
        return productNameCN;
    }

    public void setProductNameCN(String productNameCN) {
        this.productNameCN = productNameCN;
    }

    public String getProductNameEN() {
        return productNameEN;
    }

    public void setProductNameEN(String productNameEN) {
        this.productNameEN = productNameEN;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTryImage() {
        return tryImage;
    }

    public void setTryImage(String tryImage) {
        this.tryImage = tryImage;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOneRate() {
        return oneRate;
    }

    public void setOneRate(String oneRate) {
        this.oneRate = oneRate;
    }
}
