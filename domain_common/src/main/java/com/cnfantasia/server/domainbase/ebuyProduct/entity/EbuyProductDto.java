package com.cnfantasia.server.domainbase.ebuyProduct.entity;

import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.entity.EbuyHomeTypeHasProduct;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shiyl on 2016/5/24.
 */
public class EbuyProductDto implements Serializable {
    private static final long serialVersionUID = -6433275780235949362L;

    private EbuyProduct ebuyProduct;
    private List<EbuyProductPic> ebuyProductPics;
    private List<EbuyProductIntroducePic> ebuyProductIntroducePics;
    private EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct;
    private EbuyProductShelf ebuyProductShelf;
    private List<EbuyProductParameters> parameterses;

    public EbuyProduct getEbuyProduct() {
        return ebuyProduct;
    }

    public void setEbuyProduct(EbuyProduct ebuyProduct) {
        this.ebuyProduct = ebuyProduct;
    }

    public List<EbuyProductPic> getEbuyProductPics() {
        return ebuyProductPics;
    }

    public void setEbuyProductPics(List<EbuyProductPic> ebuyProductPics) {
        this.ebuyProductPics = ebuyProductPics;
    }

    public List<EbuyProductIntroducePic> getEbuyProductIntroducePics() {
        return ebuyProductIntroducePics;
    }

    public void setEbuyProductIntroducePics(List<EbuyProductIntroducePic> ebuyProductIntroducePics) {
        this.ebuyProductIntroducePics = ebuyProductIntroducePics;
    }

    public EbuyHomeTypeHasProduct getEbuyHomeTypeHasProduct() {
        return ebuyHomeTypeHasProduct;
    }

    public void setEbuyHomeTypeHasProduct(EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct) {
        this.ebuyHomeTypeHasProduct = ebuyHomeTypeHasProduct;
    }

    public EbuyProductShelf getEbuyProductShelf() {
        return ebuyProductShelf;
    }

    public void setEbuyProductShelf(EbuyProductShelf ebuyProductShelf) {
        this.ebuyProductShelf = ebuyProductShelf;
    }

    public List<EbuyProductParameters> getParameterses() {
        return parameterses;
    }

    public void setParameterses(List<EbuyProductParameters> parameterses) {
        this.parameterses = parameterses;
    }
}
