package com.cnfantasia.server.api.kitchen.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.kitchen.constant.KitchenDict;

/**
 * @author wangzhe
 * @date 2015年11月6日
 * @description 饮食分析统计结果
 *
 */
public class DietAnalysisEntity {

    private Integer grain;

    private Integer vegetable;

    private Integer fruit;

    private Integer aquatic;

    private Integer egg;

    private Integer meat;

    private Integer bean;

    private Integer nut;

    private Integer milk;

    public Integer getAquatic() {
        return aquatic;
    }

    public Integer getBean() {
        return bean;
    }

    public Integer getEgg() {
        return egg;
    }

    public Integer getFruit() {
        return fruit;
    }

    public Integer getGrain() {
        return grain;
    }

    public Integer getMeat() {
        return meat;
    }

    public Integer getMilk() {
        return milk;
    }

    public Integer getNut() {
        return nut;
    }

    public Integer getVegetable() {
        return vegetable;
    }

    public void setAquatic(Integer aquatic) {
        this.aquatic = aquatic;
    }

    public void setBean(Integer bean) {
        this.bean = bean;
    }

    public void setEgg(Integer egg) {
        this.egg = egg;
    }

    public void setFruit(Integer fruit) {
        this.fruit = fruit;
    }

    public void setGrain(Integer grain) {
        this.grain = grain;
    }

    public void setMeat(Integer meat) {
        this.meat = meat;
    }

    public void setMilk(Integer milk) {
        this.milk = milk;
    }

    public void setNut(Integer nut) {
        this.nut = nut;
    }

    public void setVegetable(Integer vegetable) {
        this.vegetable = vegetable;
    }

    @Override
    public String toString() {
        return "DietAnalysisEntity [grain=" + grain + ", vegetable=" + vegetable + ", fruit=" + fruit + ", aquatic=" + aquatic + ", egg=" + egg + ", meat=" + meat + ", bean=" + bean + ", nut=" + nut
                + ", milk=" + milk + "]";
    }

    private Map<String, Object> mapDietCategory(String name, int value, String color) {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("name", name);
        ret.put("value", value);
        ret.put("color", color);
        return ret;
    }

    /**
     * @author wangzhe
     * @date 2015年11月6日
     * @description 生成json array
     *
     * @return
     */
    private List<Map<String, Object>> genList() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        // if (0 < grain) {
        // result.add(mapDietCategory("谷物", grain, "#ffb400"));
        // } else {
        // }
        if (0 < vegetable) {
            result.add(mapDietCategory("蔬菜", vegetable, "#44c54f"));
        } else {
        }
        // if (0 < fruit) {
        // result.add(mapDietCategory("水果", fruit, "#ffffff"));
        // } else {
        // }
        if (0 < aquatic) {
            result.add(mapDietCategory("鱼虾", aquatic, "#f9ad81"));
        } else {
        }
        if (0 < egg) {
            result.add(mapDietCategory("蛋", egg, "#ffb400"));
        } else {
        }
        if (0 < meat) {
            result.add(mapDietCategory("肉类", meat, "#f38491"));
        } else {
        }
        if (0 < bean) {
            result.add(mapDietCategory("豆类", bean, "#6dcff6"));
        } else {
        }
        // if (0 < nut) {
        // result.add(mapDietCategory("坚果", nut, "#ffffff"));
        // } else {
        // }
        // if (0 < milk) {
        // result.add(mapDietCategory("奶制品", milk, "#ffffff"));
        // } else {
        // }
        return result;
    }

    /**
     * @author wangzhe
     * @date 2015年11月6日
     * @description json转换
     *
     * @return
     */
    public Map<String, Object> genResult() {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("list", genList());
        int comment = 0;
        if (0 < grain || 0 < vegetable || 0 < fruit || 0 < aquatic || 0 < egg || 0 < meat || 0 < bean || 0 < nut) {
            long denominator = 0L + bean + vegetable;
            if (0 < denominator) {
                float src = (meat + aquatic + egg) * 1f / denominator;
                if (Float.compare(src, 1f / 4) < 0 || Float.compare(src, 2f / 3) > 0) {
                    comment = KitchenDict.Kitchen_Diet_Comment.ROOKIE;
                } else if (/* 0 < grain && */0 < vegetable && /* 0 < fruit && */ 0 < aquatic && 0 < egg && 0 < meat
                        && 0 < bean/* && 0 < nut */ ) {
                    comment = KitchenDict.Kitchen_Diet_Comment.EXPERT;
                } else {
                    comment = KitchenDict.Kitchen_Diet_Comment.STU;
                }
            } else {
                comment = KitchenDict.Kitchen_Diet_Comment.ROOKIE;
            }
        } else {
            comment = KitchenDict.Kitchen_Diet_Comment.STUPID;
        }
        ret.put("comment", comment);
        return ret;
    }
}
