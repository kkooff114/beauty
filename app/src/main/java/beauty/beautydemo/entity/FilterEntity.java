package beauty.beautydemo.entity;

import android.content.Context;

import beauty.beautydemo.custview.imagefilter.filter.util.GPUImageFilterTools;
import beauty.beautydemo.custview.imagefilter.lib.GPUImageFilter;

/**
 * 滤镜
 * Created by LJW on 15/7/22.
 */
public class FilterEntity {

    private String name;
    private GPUImageFilterTools.FilterType type;

    public FilterEntity(String name, GPUImageFilterTools.FilterType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GPUImageFilterTools.FilterType getType() {
        return type;
    }

    public void setType(GPUImageFilterTools.FilterType type) {
        this.type = type;
    }

    /**
     * 获得滤镜
     *
     * @param context
     * @return
     */
    public GPUImageFilter getFilter(Context context) {
        return GPUImageFilterTools.createFilterForType(context, type);
    }
}
