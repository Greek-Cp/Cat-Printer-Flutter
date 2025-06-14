package com.luckprinter.demo;

public enum MenuTypeEnum {
    SCAN("reScan", "扫描打印机设备"),
    PRINT("print", "连续纸打印图片"),
    PRINT_LABEL("printLabel", "缝隙纸打印图片"),
    PRINT_BLACK_LABEL("printBlackLabel", "黑标缝隙纸打印图片"),
    PRINT_TATTOO("printTattoo", "纹身打印"),
    PRINT_SHEET_LABEL("printSheetLabel", "面单缝隙纸打印图片"),
    PRINT_A4_FOLDER("printA4Folder", "A4折叠纸打印图片"),
    PRINTER_MODEL_LUCK("printerModelLuck", "设备型号"),
    PRINTER_BOOT_LUCK("getBoot", "Boot版本"),
    PRINTER_SN_LUCK("printerSNLuck", "SN号码"),
    GET_ALL_INFO("getAllInfo", "获取所有信息"),
    PRINTER_VERSION_LUCK("printerVersionLuck", "固件版本"),
    GET_SHUT_TIME("getShutTime", "查自动关机时间"),
    PRINTER_STATUS_LUCK("printerStatusLuck", "状态"),
    PRINTER_BATTERY_LLUCK("printerBatteryLuck", "电量"),
    GET_DENSITY("getDensity", "浓度参数"),
    SET_DENSITY("setDensity", "设置浓度"),
    GET_SPEED("getSpeed", "速度参数"),
    SET_SPEED("setSpeed", "设置速度"),
    SET_SHUTTIME_LUCK("setShutTimeLuck", "设置自动关机时间"),
    SET_RECOVERY_LUCK("setRecoveryLuck", "出厂设置"),
    GO_PAPER("goPaper", "走纸"),
    REVERSE_GO_PAPER("reverseGoPaper", "反向走纸"),
    PACK_ERROR_LOG("packErrorLog", "发送错误日志"),
    PRINTER_SETTING_LUCK("printerSettingLuck", "配置查询"),
    TEST("test", "测试"),

    ;
    String type;
    String name;

    MenuTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static MenuTypeEnum getByType(String type) {
        MenuTypeEnum[] list = values();
        for (MenuTypeEnum item : list) {
             if(item.getType().equals(type)) {
                 return item;
             }
        }
        return null;
    }
}
