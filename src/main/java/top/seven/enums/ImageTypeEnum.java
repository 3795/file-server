package top.seven.enums;

/**
 * Description: 图片类型
 * Created At 2020/5/20
 */
public enum ImageTypeEnum {
    LIFE(1, "life", "生活类"),
    ARTICLE(2, "article", "文章类"),
    OTHER(3, "other", "其他分类");
    private int type;

    private String path;

    ImageTypeEnum(int type, String path, String desc) {
        this.type = type;
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    /**
     * 根据图片类型获取对应的存储路径
     *
     * @param type 图片类型
     * @return
     */
    public static String getPathByType(int type) {
        for (ImageTypeEnum e : ImageTypeEnum.values()) {
            if (e.getType() == type) {
                return e.getPath();
            }
        }
        return OTHER.getPath();
    }
}
