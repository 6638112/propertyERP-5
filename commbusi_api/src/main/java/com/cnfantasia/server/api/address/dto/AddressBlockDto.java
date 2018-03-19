package com.cnfantasia.server.api.address.dto;

/**
 * @className: AddressBlockDto.
 * @date: 2017-11-08 17:11
 * @author: kangduo
 * @description: ()
 */
public class AddressBlockDto {

    private Long blockId;
    private String blockName;

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
}
